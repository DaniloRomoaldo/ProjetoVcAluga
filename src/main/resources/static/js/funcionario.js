url_funcionario = "http://localhost:8080/funcionario"
url_filial = "http://localhost:8080/filial"


/*-----------------------------(COLETAR FUNCIONARIO) - (GET)-------------------------------------------------------*/

function listFuncio(route, value){
    event.preventDefault();
    const typeGet = "funcionario";
    let url_busca;

    if (route === "funcao"){
        url_busca = url_funcionario+"?funcao="+value;
    }else if (route === "all"){
        url_busca = url_funcionario+"?all="+value;
    }else {
        url_busca = url_funcionario+"?nome="+encodeURIComponent(value);
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

/*-----------------------------(CADASTRAR FUNCIONARIO) - (POST)-------------------------------------------------------*/

document.addEventListener("DOMContentLoaded", function (){
    var filial = document.getElementById("dropdown-filiais")
    filial.addEventListener("change", function (){
        filialSelect = filial.value;
        console.log(filialSelect);
    })

})
function registerFuncionario(){
    var nome = document.getElementById("input-nome-funcio").value.toUpperCase();
    var cpf = document.getElementById("input-cpf-funcio").value;
    var funcao = document.querySelector('input[name="gridRadios"]:checked').value;

    body={
        "nome":nome,
        "cpf":cpf,
        "funcao":funcao,
        "status": "ATIVO",
        "filialDTO":{"nome":filialSelect}
    }
    console.log(body);
    post(url_funcionario, body);

    alert("Funcionário cadastrado!")
}


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



function criarOption(value){
    var optrionElement = document.createElement("option");
    optrionElement.setAttribute("value",value);
    return optrionElement;
}