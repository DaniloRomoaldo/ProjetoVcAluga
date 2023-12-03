url_locacoes = "http://localhost:8080/locacoes"
url_veiculo = "http://localhost:8080/veiculo"
url_motorista = "http://localhost:8080/motorista"


function listLocacoesAtivas(route, value){
    event.preventDefault();
    const typeGet = "locacoesAtivas";
    let url_busca;
    if (route === "status"){
        url_busca = url_locacoes+"?status="+value;
    }else if (route === "cpfCnpj"){
        url_busca = url_locacoes+"?registroCliente="+value;
    }else if (route === "cod"){
        url_busca = url_locacoes+"?codLocacao="+value;
    }else  if (route === "all"){
        url_busca = url_locacoes+"?all="+value;
    }else {
        url_busca = url_locacoes+"?nomeCliente="+encodeURIComponent(value);
    }
    get(typeGet, url_busca)
}

function appendListLocacoesAtivas(data){
    const listaLocacoesAtivas = document.getElementById("list-loc-ativ")

    listaLocacoesAtivas.innerHTML='';

    const locacoesAtivas = data.map(locacao => {
        dataRetirada = formatarTimestamp(locacao.dt_inicio, "data");
        dataDevolucao = formatarTimestamp(locacao.dt_fim, "data");

        var item = criarLi();
        var inputElement = criarInput("listGroupRadio", locacao.id);
        var labelElement = criarLabel(locacao.id);
        labelElement.textContent= "Locação: "+locacao.status + " | Cliente: "+ locacao.clienteDTO.nome + " -  CPF/CNPJ: " + locacao.clienteDTO.cpfCnpj +
            " | Motorista Vinculado: " + locacao.motoristaDTO.nome + " - CNH: " + locacao.motoristaDTO.cnh +
            " | Veiculo: " + locacao.veiculoDTO.nome + " - Placa: " + locacao.veiculoDTO.placa + " - Categoria: " + locacao.veiculoDTO.categoria +
            " | Retirado em: " + locacao.end_retirada + " - Dia: " + dataRetirada + " - Devolução em: " + locacao.end_devolucao + " - Dia: " + dataDevolucao

        item.appendChild(inputElement);
        item.appendChild(labelElement);

        listaLocacoesAtivas.appendChild(item);
    })
}

// finalizar uma locação
document.addEventListener("DOMContentLoaded", function (){
    const finalizar = document.querySelector('.finalizar-bnt');

    finalizar.addEventListener('click', function (){
        const listaLocacoesAtivas = document.getElementById('list-loc-ativ'); //pega o elemento ul que contém a lista
        const inputs = listaLocacoesAtivas.querySelectorAll('input[type="radio"]'); // pega todos os elementos dentro da lista

        let idSelecionado = null;

        inputs.forEach(elementoInput => {
            if (elementoInput.checked){
                idSelecionado = elementoInput.id;
            }
        });

        if (idSelecionado){
            const url_locacao_selecionada = url_locacoes+"?locacaoId="+idSelecionado
            const url_finalizar = url_locacoes+"/"+idSelecionado
            finalizarLocacao(url_locacao_selecionada, url_finalizar);

        }else {
            console.log("erro")
        }
    })
})

async function finalizarLocacao(url_locacao_selecionada, url_finalizar){
    const confirmed = window.confirm("Finalizar Locação selecionada?");
   // const locacaoSelecionada = await get("finalizarLocacao", url_finalizar);

    if (!confirmed) {
        return;  // Se o usuário clicou em "Cancelar", interrompe a execução da função
    }
    const locacaoSelecionada = await fetch(url_locacao_selecionada).then(response => response.json());
    try {
        const url_status_veiculo = url_veiculo+"/"+locacaoSelecionada[0].veiculoDTO.placa
        const url_status_motorista = url_motorista+"/"+locacaoSelecionada[0].motoristaDTO.cnh
        //--------Body's das requisições
        const bodyFinalizarLocacao= {
            "status":"FINALIZADA"
        }
        const bodyStatusVeiculo = {
            "status":"DISPONIVEL"
        }
        const bodyStatusMotorista = {
            "status":"DISPONIVEL"
        }


        //----------- Requisições de alteração (liberar finalização)
         const putLocacao = fetch(url_finalizar, {
            method: "PUT",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(bodyFinalizarLocacao)
        });
        const putStatusVeiculo = fetch(url_status_veiculo,{
            method:"PUT",
            headers: {"Content-Type": "application/json"},
            body:JSON.stringify(bodyStatusVeiculo)
        });

        const putStatusMotorista = fetch(url_status_motorista,{
            method:"PUT",
            headers: {"Content-Type": "application/json"},
            body:JSON.stringify(bodyStatusMotorista)
        });
       //
       // console.log(url_status_veiculo)
       // console.log(bodyStatusVeiculo)
       // console.log(url_status_motorista)
       // console.log(bodyStatusMotorista)

        const [responsePutLocacao, responsePutVeiculo, responsePutMotorista] =
            await Promise.all([putLocacao, putStatusVeiculo, putStatusMotorista])
        if (!responsePutLocacao){
            throw new Error("erro na locacao: " + responsePutLocacao.status)
        }
        if (!responsePutVeiculo){
            throw new Error("erro no veiculo: "+ responsePutVeiculo.status)
        }
        if (!responsePutMotorista){
            throw new Error("erro no motorista: "+ responsePutMotorista.status)
        }
        alert("Locação finalizada!")
        window.location.reload();

    } catch (error){
        console.error(error);
    }

}