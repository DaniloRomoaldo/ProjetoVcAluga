
const url = "http://localhost:8080/cliente"; //rota da api que recebe o cadastro do cliente

async function post(url, body) {
//    console.log(body)
    const headers = new Headers();
    headers.append("Content-Type", "application/json"); //define o cabeçalho da requisição
    try {
        const response = await fetch(url, {
            method: "POST",
            headers: headers,
            body: JSON.stringify(body) // converte a variável bo
        });
        if (response.ok){
            console.log("REGISTRADO")
        } else {
            throw new Error("Requisição mal sucedida: " + response.status)
        }
    } catch (error){
        console.error("error");
    }
}


function cadastrarUsuario() {
    event.preventDefault()
    // coleta das informações dos dados disponíveis no html
    let nome = document.getElementById("nome").value
    let cpf_cnpj = document.getElementById("cpf_cnpj").value
    let telefone = document.getElementById("telefone").value
    let fidelidade = 0
    let tipo = "pessoa física" //condição que precisa ser trabalhada pelo cpf
    console.log(nome)
    console.log(cpf_cnpj)
    console.log(telefone)

        //criação de um corpo JSON
    body = {
        "nome": nome,
        "cpf_cnpj": cpf_cnpj,
        "telefone": telefone,
        "fidelidade": fidelidade,
        "tipo": tipo
    }

    post(url, body) //função que chama o metodo post da api
}