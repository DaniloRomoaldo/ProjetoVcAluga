url_filial = "http://localhost:8080/filial"


/*-----------------------------(CADASTRAR FILIAL) - (POST)-------------------------------------------------------*/

function registerFilial(){
    var nome = document.getElementById("input-nome-filial").value.toUpperCase();
    var cnpj = document.getElementById("input-cnpj-filial").value;
    var endereco = document.getElementById("input-end-filial").value.toUpperCase();

    body={
        "nome": nome,
        "cnpj" : cnpj,
        "endereco": endereco
    };

    post(url_filial, body);

    alert("Filial cadastrada!")
}


    /*--------------------ORDEM MANUTENÇÃO------------------------*/
    ordem.addEventListener('click', async function (){
        const previsao = document.getElementById("data-previsao-manutencao").value


        if (idSelecionado){
            //url para coletar as informações do veiculo selecionado
            url_veiculo_man = url_veiculo+"?veiculoId="+idSelecionado
            const veiculoManutencao = await fetch(url_veiculo_man, {
                method: "GET",
                headers: {"Content-Type": "application/json"}
            })
                .then(response => {
                    return response.json();
                });
            //coleta a placa do veículo selecionado e prepara o body para fazer um post na manutenção
            veiculoManutencao.map(veiculo =>{
                bodyManutencao = {
                    "veiculoDTO":veiculoManutencao.placa,
                    "dt_previsao":previsao
                }
                placa = veiculoManutencao.placa;
            })

            const confirmed = window.confirm("Emitir ordem de Manutenção para o PLACA: "+placa+" ?");


            if (!confirmed) {
                return;  // Se o usuário clicou em "Cancelar", interrompe a execução da função
            }else {
                await post(url_manutencao, bodyManutencao); //criando o registro de manutenção no banco de dados

                url_statusManutencao = url_veiculo+"/"+idSelecionado;
                bodyVeich ={
                    "status":"MANUTENCAO"
                }
                await enviarViculoManuntencao(url_statusManutencao, bodyManutencao) // atualizando o status do veiculo atual para MANUTENÇÃO após criar registro
            }



        }
    })