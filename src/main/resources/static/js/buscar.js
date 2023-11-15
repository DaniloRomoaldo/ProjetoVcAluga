url_cliente = "http://localhost:8080/cliente"
url_funcionario = "http://localhost:8080/funcionario"
url_motorista = "http://localhost:8080/motorista"
url_veiculo = "http://localhost:8080/veiculo"

function  listClients(){
    event.preventDefault();
    const typeGet = "cliente";
    get(typeGet,url_cliente);
}

function listFuncio(){
    event.preventDefault();
    const typeGet = "funcionario";
    get(typeGet,url_funcionario);
}

function listMotorista(){
    event.preventDefault();
    const typeGet = "motorista";
    get(typeGet,url_motorista)
}

function listVeiculos(){
    event.preventDefault();
    const typeGet = "veiculo";
    get(typeGet, url_veiculo)
}


function get(typeGet,url){  // função GET padrão para todos os request de buscar lista de valores no banco
    fetch(url, {
        method: "GET",
        headers: {"Content-Type": "application/json"}
    })
        .then(response => {
            if (response.ok){
                return response.json();
            }else {
                throw new Error("Erro de requisição");
            }
        })
        .then(data =>{  // criar método para converter o data em elementos html e inserir no campo desejado
            console.log("dados recebidos:", data)

            switch (typeGet){
                case typeGet = "cliente":
                    appednListClients(data)
                case typeGet = "funcionario":
                    appendListFuncionario(data)
                case typeGet = "motorista":
                    appendListMotorista(data)
                case typeGet = "veiculo":
                    appendListVeiculo(data)
            }


        })
        .catch(error =>{
            console.log(error)
        })

}

function appednListClients(data){
    const listaClientes = document.getElementById("list-clients-ul") ; // elemento que vai receber os clientes

    listaClientes.innerHTML=''; //limpar os elementos da lista para n gerar duplicadas

    const clientes = data.map(cliente => {

        dataFormatada = formatarTimestamp(cliente.dt_cadastro, "data e hora");  //primeiro formatar data

        var item = criarLi();

        var inputElement = criarInput("listGroupRadio", cliente.id);

        var labelElement = criarLabel(cliente.id)
        labelElement.textContent= cliente.nome + ", CPF/CNPJ: " + cliente.cpfCnpj + " Cadastrado em: " + dataFormatada

        item.appendChild(inputElement)
        item.appendChild(labelElement)


        listaClientes.appendChild(item);
    })

}

function appendListFuncionario(data){
    const listaFuncionario = document.getElementById("list-itens-loc")

    listaFuncionario.innerHTML='';

    const funcionarios = data.map(funcionario => {
        var item = criarLi();
        var inputElement = criarInput("listGroupRadio", funcionario.id);
        var labelElement = criarLabel(funcionario.id);
        labelElement.textContent= funcionario.nome + ", Filial1: " + funcionario.filialDTO.nome + " - CPF: " + funcionario.cpf +
                    " - Função: " + funcionario.funcao + " - Situação: " + funcionario.status;

        item.appendChild(inputElement);
        item.appendChild(labelElement);

        listaFuncionario.appendChild(item);
    })
}

function appendListMotorista(data){
    const listaMotorista = document.getElementById("list-itens-mot")

    listaMotorista.innerHTML='';

    const motoristas = data.map(motorista => {
        dataNascimento = formatarTimestamp(motorista.dt_nascimento, "data");
        var item = criarLi();
        var inputElement = criarInput("listGroupRadio", motorista.id);
        var labelElement = criarLabel(motorista.id);
        labelElement.textContent= motorista.nome + ", CPF: " + motorista.cpf + " - CNH: " + motorista.cnh + "Data de Nascimento: " + dataNascimento;

        item.appendChild(inputElement);
        item.appendChild(labelElement);

        listaMotorista.appendChild(item);
    })
}


function appendListVeiculo(data){
    const listaVeiculo = document.getElementById("list-itens-veih")

    listaVeiculo.innerHTML='';

    const veiculos = data.map(veiculo => {
        var item = criarLi();
        var inputElement = criarInput("listGroupRadio", veiculo.id);
        var labelElement = criarLabel(veiculo.id);
        labelElement.textContent= veiculo.nome + ", Status: " + veiculo.status + " - categoria: " + veiculo.categoria +
            "KM: " + veiculo.km_total + "- Local Atual: " + veiculo.filialDTO.nome;

        item.appendChild(inputElement);
        item.appendChild(labelElement);

        listaVeiculo.appendChild(item);
    })
}







function criarLabel(id){

    var labelElement = document.createElement("label");  //criar o elemento label que recebe os dados
    labelElement.setAttribute("for", id);
    labelElement.classList.add("form-check-label")

    return labelElement;

}

function criarLi(){
    var item = document.createElement("li"); //criar o elemento list
    item.classList.add("list-group-item", "list-itens-loc");

    return item;
}


function criarInput(name, id){
    var inputElement = document.createElement("input"); //criar o elemento input verificador
    inputElement.setAttribute("type","radio");
    inputElement.setAttribute("name",name);
    inputElement.setAttribute("id", id);

    inputElement.classList.add("form-check-input", "me-1" )

    return inputElement;

}







function formatarTimestamp(timestamp, typeData){
    const timesTampObj = new Date(timestamp);
    const data = timesTampObj.toLocaleDateString();
    if (typeData === "data"){
        return `${data}`
    }else{
        const hora = timesTampObj.toLocaleTimeString();
        return `${data} às ${hora}`;
    }

}




document.addEventListener("DOMContentLoaded", function (){
    const excluir = document.querySelector('.excluir-bnt');

    excluir.addEventListener('click', function (){
        const listaClientes = document.getElementById('list-clients-ul'); //pega o elemento ul que contém a lista
        const inputs = listaClientes.querySelectorAll('input[type="radio"]'); // pega todos os elementos dentro da lista

        let idSelecionado = null;

        inputs.forEach(elementoInput => {
            if (elementoInput.checked){
                idSelecionado = elementoInput.id;
            }
        });

        if (idSelecionado){
            url_delete = url_cliente+"/"+idSelecionado
            deleteRegister(url_delete);
        }else {
            console.log("erro")
        }
    })
})


document.addEventListener("DOMContentLoaded", function (){
    const excluir = document.querySelector('.excluir-bnt');

    excluir.addEventListener('click', function (){
        const listaFuncionarios = document.getElementById('list-itens-loc'); //pega o elemento ul que contém a lista
        const inputs = listaFuncionarios.querySelectorAll('input[type="radio"]'); // pega todos os elementos dentro da lista

        let idSelecionado = null;

        inputs.forEach(elementoInput => {
            if (elementoInput.checked){
                idSelecionado = elementoInput.id;
            }
        });

        if (idSelecionado){
            url_delete = url_funcionario+"/"+idSelecionado  // roda do funcionário
            deleteRegister(url_delete);
        }else {
            console.log("erro")
        }
    })
})


document.addEventListener("DOMContentLoaded", function (){
    const excluir = document.querySelector('.excluir-bnt');

    excluir.addEventListener('click', function (){
        const listaMotoristas = document.getElementById('list-itens-mot'); //pega o elemento ul que contém a lista
        const inputs = listaMotoristas.querySelectorAll('input[type="radio"]'); // pega todos os elementos dentro da lista

        let idSelecionado = null;

        inputs.forEach(elementoInput => {
            if (elementoInput.checked){
                idSelecionado = elementoInput.id;
            }
        });

        if (idSelecionado){
            url_delete = url_motorista+"/"+idSelecionado  // roda do funcionário
            deleteRegister(url_delete);
        }else {
            console.log("erro")
        }
    })
})


document.addEventListener("DOMContentLoaded", function (){
    const excluir = document.querySelector('.excluir-bnt');

    excluir.addEventListener('click', function (){
        const listaVeiculo = document.getElementById('list-itens-veih'); //pega o elemento ul que contém a lista
        const inputs = listaVeiculo.querySelectorAll('input[type="radio"]'); // pega todos os elementos dentro da lista

        let idSelecionado = null;

        inputs.forEach(elementoInput => {
            if (elementoInput.checked){
                idSelecionado = elementoInput.id;
            }
        });

        if (idSelecionado){
            url_delete = url_veiculo+"/"+idSelecionado  // roda do funcionário
            deleteRegister(url_delete);
        }else {
            console.log("erro")
        }
    })
})



function deleteRegister(url, event){
    const confirmed = window.confirm("Tem certeza que deseja excluir o registro?");

    if (!confirmed) {
        return;  // Se o usuário clicou em "Cancelar", interrompe a execução da função
    }

    fetch(url,{
        method: "DELETE",
        headers: {"Content-Type": "application/json"}
    })
        .then(response =>{
            if (response.ok){
                window.alert("Registro deletado!");
                location.reload();
            }else {
                throw  new Error("erro na requisição")
            }
        })

}

