url_locacoes = "http://localhost:8080/locacoes"

function listLocacoesAtivas(route, value){
    event.preventDefault();
    const typeGet = "locacoesAtivas";
    let url_busca;
    if (route === "status"){
        url_busca = url_locacoes+"?status="+value;
    }else if (route === "cpfCnpj"){
        url_busca = url_locacoes+"?cpfCnpj="+value;
    }else if (route === "cod"){
        url_busca = url_locacoes+"?codLocacao="+value;
    }else {
        url_busca = url_locacoes+"?nome="+value;
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
        var inputElement = criarInput("listGroupRadio", locacao.codLocacao);
        var labelElement = criarLabel(locacao.codLocacao);
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
            url_finalizar = url_locacoes+"/"+idSelecionado  // roda do funcionário
            body = {"status": "FINALIZADO"}

            finalizarLocacao(url_finalizar, body);

        }else {
            console.log("erro")
        }
    })
})

async function finalizarLocacao(url_finalizar, body){
    const confirmed = window.confirm("Finalizar Locação selecionada?");


    if (!confirmed) {
        return;  // Se o usuário clicou em "Cancelar", interrompe a execução da função
    }
    try {
        const putLocacao = await fetch(url_finalizar, {
            method: "PUT",

        });
        if (putLocacao.ok){
            window.alert("Locação Finalizada!");
            location.reload();
        }else {
            throw new Error("Requisição mal sucedida: "+ putLocacao.status)

        }
    } catch (error){
        console.error(error);
    }

}