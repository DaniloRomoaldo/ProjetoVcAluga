url_cliente = "http://localhost:8080/cliente"


/*-----------------------------(COLETAR CLIENTE) - (GET)-------------------------------------------------------*/

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

/*-----------------------------(CADASTRAR CLIENTE) - (POST)-------------------------------------------------------*/

function registerCliente(event){
    var nome = document.getElementById("input-nome-cliente").value.toUpperCase();
    var cpfCnpj = document.getElementById("input-cpf-cnpj-cliente").value;
    var telefone = document.getElementById("input-telefone-cliente").value;
    var tipo = document.querySelector('input[name="gridRadios"]:checked').value;
    var fidelidade = document.getElementById("input-fidelidade-cliente").value;

    if (tipo === "PESSOA FISICA" && cpfCnpj.length !== 11){
        window.alert("Tipo de Cliente inválido, sugestão: PESSOA JURÍDICA")
        event.preventDefault();
    }else if (tipo === "PESSOA JURIDICA" && cpfCnpj.length !== 14){
        window.alert("Tipo de Cliente inválido, sugestão: PESSOA FÍSICA")
        event.preventDefault();
    }else {
        body={
            "nome": nome,
            "cpfCnpj": cpfCnpj,
            "telefone": telefone,
            "tipo": tipo,
            "total_fidelidade": fidelidade
        };
        post(url_cliente, body);
    }


}