
const url = "http://localhost:8080/cliente"; //rota da api que recebe o cadastro do cliente


function cadastrarUsuario() {
    event.preventDefault()
    // coleta das informações dos dados disponíveis no html
    let nome = document.getElementById("nome").value
    let cpfCnpj = document.getElementById("cpfCnpj").value
    let telefone = document.getElementById("telefone").value
    let fidelidade = 0
    let tipo = "pessoa física" //condição que precisa ser trabalhada pelo cpf
    console.log("OK")

        //criação de um corpo JSON
    body = {
        "nome": nome,
        "cpfCnpj": cpfCnpj,
        "telefone": telefone,
        "fidelidade": fidelidade,
        "tipo": tipo
    }

    post(url, body) //função que chama o metodo post da api
}

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