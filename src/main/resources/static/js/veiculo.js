url_veiculo = "http://localhost:8080/veiculo"
url_filial = "http://localhost:8080/filial"
url_manutencao = "http://localhost:8080/manutencao"


/*-----------------------------(COLETAR VEICULOS) - (GET)-------------------------------------------------------*/

function listVeiculos(route, value){
    event.preventDefault();
    const typeGet = "veiculo";
    let url_busca;

    if (route === "placa") {
        url_busca = url_veiculo + "?placa=" + value;
    } else if (route === "status" ) {
        url_busca = url_veiculo+"?status="+value;
    }else if (route === "cat"){
        url_busca = url_veiculo+"?categoria="+value;
    }else {
        url_busca = url_veiculo;
    }
    get(typeGet, url_busca)
}

function appendListVeiculo(data){
    const listaVeiculo = document.getElementById("list-itens-veih")

    listaVeiculo.innerHTML='';

    const veiculos = data.map(veiculo => {
        var item = criarLi();
        var inputElement = criarInput("listGroupRadio", veiculo.id);
        var labelElement = criarLabel(veiculo.id);
        labelElement.textContent= veiculo.nome + " - Placa: "+veiculo.placa+" | Status: " + veiculo.status + " - categoria: " + veiculo.categoria +
            " KM: " + veiculo.km_total + "- Local Atual: " + veiculo.filialDTO.nome;

        item.appendChild(inputElement);
        item.appendChild(labelElement);

        listaVeiculo.appendChild(item);
    })
}

/*-----------------------------(DELETAR VEICULOS) - (EVENT)-------------------------------------------------------*/
document.addEventListener("DOMContentLoaded", function (){
    const excluir = document.querySelector('.excluir-bnt');
    const ordem =document.querySelector('.ordem-manutencao-bnt')


    const listaVeiculo = document.getElementById('list-itens-veih'); //pega o elemento ul que contém a lista
    const inputs = listaVeiculo.querySelectorAll('input[type="radio"]'); // pega todos os elementos dentro da lista

    let idSelecionado = null;

    inputs.forEach(elementoInput => {
        if (elementoInput.checked){
            idSelecionado = elementoInput.id;
        }
    });

            /*--------------------EXCLUIR------------------------*/
    excluir.addEventListener('click', function (){
        if (idSelecionado){
            url_delete = url_veiculo+"/"+idSelecionado  // roda do funcionário
            deleteRegister(url_delete);
        }else {
            console.log("erro")
        }
    })

})
/*-----------------------------(MANUTENÇÃO VEICULOS) - (EVENT)-------------------------------------------------------*/
document.addEventListener("DOMContentLoaded", function (){
    const manutencao = document.querySelector('.ordem-manutencao-bnt');

    manutencao.addEventListener('click', function (){
        const listaVeiculo= document.getElementById('list-itens-veih');
        const inputs = listaVeiculo.querySelectorAll('input[type="radio"]'); // pega todos os elementos dentro da lista

        idSelecionado = null;

        inputs.forEach(elementoInput => {
            if (elementoInput.checked){
                idSelecionado = elementoInput.id;
            }
        });
        if (idSelecionado){
            url_get_hist_manu = url_manutencao+"?veiculoManutencaoId="+idSelecionado
             get("manutencao", url_get_hist_manu)
        }else {
            console.log("erro")
        }
    })
})


function appendListManutencaoVeiculo(data){ // o método get envia os dados para essa função que insere no html
    const listaManuntencoes = document.getElementById("list-manutencao-veiculo") //elemento html (ul)

    listaManuntencoes.innerHTML='';

    const historico = data.map(manutencao => {
        var item = criarLiSimple(); // cria um <lil> modelo simples
        item.textContent= manutencao.veiculoDTO.nome + "- placa: "+ manutencao.veiculoDTO.placa+
            " | Data de entrada: "+manutencao.dt_entrada + " - Previsão: "+manutencao.dt_previsao;


        listaManuntencoes.appendChild(item);
    })
}


async function emitirOrdem(){
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
                "veiculoDTO":{"placa":veiculo.placa},
                "funcionarioDTO":{"nome": "ADMIN"},
                "dt_previsao":previsao,
                "status": "EM MANUTENÇÃO"
            }
            confirmed = window.confirm("Emitir ordem de Manutenção para o PLACA: "+veiculo.placa+" ?");
            url_statusManutencao = url_veiculo+"/"+veiculo.placa; // preparando url para dar um PUT no status

        })


        if (!confirmed) {
            return;  // Se o usuário clicou em "Cancelar", interrompe a execução da função
        }else {
            console.log(url_manutencao + " | "+ JSON.stringify(bodyManutencao))

           await post(url_manutencao, bodyManutencao); //criando o registro de manutenção no banco de dados

            bodyVeich ={
                "status":"EM MANUTENCAO"
            }

            console.log(url_statusManutencao + "  -  " + JSON.stringify(bodyVeich))
             await enviarViculoManuntencao(url_statusManutencao, bodyVeich) // atualizando o status do veiculo atual para MANUTENÇÃO após criar registro
        }



    }
}

async function enviarViculoManuntencao(url_status_manutencao, body){

    try {
        const putManutencao = await fetch(url_status_manutencao, {
            method: "PUT",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(body)

        });
        if (putManutencao.ok){
            window.alert("Ordem de Manutencao Emitida!");
            location.reload();
        }else {
            throw new Error("Requisição mal sucedida: "+ putManutencao.status)

        }
    } catch (error){
        console.error(error);
    }

}


/*-----------------------------(CADASTRAR VEICULO) - (POST)-------------------------------------------------------*/


document.addEventListener("DOMContentLoaded", async function () {
        const listaFiliais = document.getElementById("dropdown-filiais");

        const filiais = await fetch(url_filial, {
            method: "GET",
            headers: {"Content-Type": "application/json"}
        })
            .then(response => {
                return response.json();
            });

        filiais.map(filial =>{
            var item = criarOption(filial.nome);
            item.textContent = filial.nome + " - " + filial.cnpj + " - " + filial.endereco;
            listaFiliais.appendChild(item);
        })

    }

)
document.addEventListener("DOMContentLoaded", function (){
    var filial = document.getElementById("dropdown-filiais");
    filial.addEventListener("change", function (){
        filialSelect = filial.value;
        // console.log(filialSelect);
    });

    var categoria = document.getElementById("dropdown-categorias");
    categoria.addEventListener("change",function (){
        categoriaSelect = categoria.value;
        // console.log(categoriaSelect)
    });

    var status = document.getElementById("dropdown-status");
    status.addEventListener("change", function (){
        statusSelect = status.value;
        // console.log(statusSelect)
    })

})
function criarOption(value){
    var optrionElement = document.createElement("option");
    optrionElement.setAttribute("value",value);
    return optrionElement;
}

function registerVeiculo(){
    var modelo = document.getElementById("inputModelo").value.toUpperCase()
    var placa = document.getElementById("input-placa").value.toUpperCase()
    var km = document.getElementById("input-km").value

    body={
        "filialDTO":{"nome": filialSelect},
        "nome": modelo,
        "status": statusSelect,
        "categoria": categoriaSelect,
        "placa":placa,
        "km_total":km
    }

    // console.log(body);
    post(url_veiculo, body);

}

function getCookieNome(cookieName){
    const cookies = document.cookie;

    for(let i=0; i<cookies.length; i++){
        const cookie = cookies[i].split('=');
        console.log(cookie)
        if (cookie[0]===cookieName){
            return cookie[1]; // segunda casa do cookie [0- name, 1- value]
        }
    }
    return null;
}