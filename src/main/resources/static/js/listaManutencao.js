url_veiculo = "http://localhost:8080/veiculo"

async function finalizarManutencaoVeiculo(){
    const listaVeiculo = document.getElementById('list-itens-veih');
    const inputs = listaVeiculo.querySelectorAll('input[type="radio"]'); // pega todos os elementos dentro da lista

    let idSelecionado = null;

    inputs.forEach(elementoInput => {
        if (elementoInput.checked) {
            idSelecionado = elementoInput.id;
        }
    });
    if (idSelecionado) {
        let url_buscar_veiculo = url_veiculo+"?veiculoId="+idSelecionado
        console.log(url_buscar_veiculo)
        const veiculo = await fetch(url_buscar_veiculo, {
            method: "GET",
            headers: {"Content-Type": "application/json"}
        }) .then(response => {
            return response.json()
        })
        confirmed = window.confirm("Emitir ordem de Manutenção para o PLACA: "+veiculo[0].placa+" ?");

        if (!confirmed){
            return;
        }else {
            const url_finalizar_manutencao = url_veiculo+"/"+veiculo[0].placa
            bodyFinalizarManutencao = {
                "status":"DISPONIVEL"
            }

            await finalizar(url_finalizar_manutencao, bodyFinalizarManutencao)
        }
    } else {
        console.log("erro")
    }
}

async function finalizar(url_finalizar_manutencao, body){
    try {
        const putStatusVeiculo = await fetch(url_finalizar_manutencao, {
            method: "PUT",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(body)

        });
        if (putStatusVeiculo.ok){
            window.alert("Manutenção Finalizada! Veiculo Disponível.");
            location.reload();
        }else {
            throw new Error("Requisição mal sucedida: "+ putStatusVeiculo.status)

        }
    } catch (error){
        console.error(error);
    }
}