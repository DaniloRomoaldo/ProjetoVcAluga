url_motorista = "http://localhost:8080/motorista"

function listMotorista(route, value){
    event.preventDefault();
    const typeGet = "motorista";
    let url_busca;

    if (route === "CNH"){
        url_busca=url_motorista+"?cnh="+value;
    }else {
        url_busca=url_motorista+"?nome="+value;
    }
    get(typeGet,url_busca);
}

function appendListMotorista(data){
    const listaMotorista = document.getElementById("list-itens-mot")

    listaMotorista.innerHTML='';

    const motoristas = data.map(motorista => {
        dataNascimento = formatarTimestamp(motorista.dt_nascimento, "data");
        var item = criarLi();
        var inputElement = criarInput("listGroupRadio", motorista.id);
        var labelElement = criarLabel(motorista.id);
        labelElement.textContent= motorista.nome + ", CPF: " + motorista.cpf + " - CNH: " + motorista.cnh
            + "Data de Nascimento: " + dataNascimento+" - Status: "+motorista.status ;

        item.appendChild(inputElement);
        item.appendChild(labelElement);

        listaMotorista.appendChild(item);
    })
}

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