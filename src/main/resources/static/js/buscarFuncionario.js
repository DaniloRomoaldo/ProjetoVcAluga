url_funcionario = "http://localhost:8080/funcionario"

function listFuncio(route, value){
    event.preventDefault();
    const typeGet = "funcionario";
    let url_busca;

    if (route === "funcao"){
        url_busca = url_funcionario+"?funcao="+value;
    }else {
        url_busca = url_funcionario+"?nome="+value;
    }

    get(typeGet,url_busca);
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