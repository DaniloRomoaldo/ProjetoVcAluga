function exibirLista(data){
    const listaClientes = document.getElementById("cliente-container");

    const clientes = data.map(cliente => {
        const itens = document.createElement("div");
        dataFormatada = formatarTimestamp(cliente.dt_cadastro);
        itens.innerHTML = `<li>Cliente: ${cliente.nome} - Tipo: ${cliente.tipo} - Cadastrado em: ${dataFormatada} </li>`;
        return itens
    });

    clientes.forEach(cliente => listaClientes.appendChild(cliente))

    const homeLink = document.createElement("a");
    homeLink.href = "http://localhost:8080/home";
    homeLink.textContent = "Voltar";
    listaClientes.appendChild(homeLink);
}




function formatarTimestamp(timestamp){
    const timestampObj = new Date(timestamp);
    const data = timestampObj.toLocaleDateString();
    const hora = timestampObj.toLocaleTimeString();

    return `${data} às ${hora}`
}

function get(url) {
    fetch(url, {
        method: "GET",
        headers: {"Content-Type": "application/json"}
    })
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error("Erro na requisição");
            }
        })
        .then(data => { // coleta os dados vindo do banco
            console.log("Dados recebidos: ", data);
            exibirLista(data)
        })
        .catch(error => {
            console.error(error);
        })

}

function listClient(){
    event.preventDefault()
    url = "http://localhost:8080/cliente"

    get(url)
}

function registerRedirect(){
    url = "http://localhost:8080/register"
    getRedirect(url);
}

function getRedirect(url,){
    fetch(url, {
        method: "GET",
        headers: {"Content-Type": "application/json"}
    })
        .then(response => {if (response.ok){
            console.log("ok")
        }})
        .catch(error => console.log(error))
}