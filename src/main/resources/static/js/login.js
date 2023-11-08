const url = "http://localhost:8080/login"; // rota para verificar o funcionário

function login(){
    event.preventDefault()

    let funcionario = document.getElementById("nome").value;
    let codFuncionario = document.getElementById("codFuncionario").value;

    body = {
        "nome":funcionario,
        "cod_funcionario": codFuncionario
    }

    post(body, url)

}

async function post(body, url){

    const  headers = new Headers();
    headers.append("Content-Type", "application/json");
    try{
        const response = await fetch(url, {
            method: "POST",
            headers: headers,
            body: JSON.stringify(body)
        })
        if (!response.ok){
            const errorMessage =  response.text;
            console.error(errorMessage);
            console.log(response.headers)
            // return;
        }
        window.location.href = '/home'
        // const redirectUrl = response.headers.get("Location");
        // if(redirectUrl){
        //     window.location.href = redirectUrl;
        //     console.log(redirectUrl)
        // }else {
        //     alert("erro")
        // }
    } catch (error){
        console.error(error);
    }
}