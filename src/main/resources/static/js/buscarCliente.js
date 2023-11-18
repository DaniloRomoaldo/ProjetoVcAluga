url_cliente = "http://localhost:8080/cliente"

function  listClients(route, value){
    event.preventDefault();
    const typeGet = "cliente";
    let url_busca;

    if (route === "registro"){
        url_busca = url_cliente+"?registro="+value;
    }else if (route === "tipo"){
        url_busca = url_cliente+"?tipo="+value;
    }else {
        url_busca = url_cliente+"?nome="+value;
    }

    get(typeGet,url_busca);
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
