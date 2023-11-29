url_motorista = "http://localhost:8080/motorista"

function cadastrarMotorista(){
    var nome = document.getElementById("input-nome-motorista").value.toUpperCase();
    var cpf = document.getElementById("input-cpf-motorista").value;
    var cnh = document.getElementById("input-cnh-motorista").value;
    var dt_nascimento = document.getElementById("data-nasc-motorista-cadastro").value;

        body={
            "nome": nome,
            "cpf": cpf,
            "cnh": cnh,
            "dt_nascimento": dt_nascimento,
            "status": "DISPONIVEL"
        };

        post(url_motorista, body);

        window.location.href = 'http://localhost:8080/locacao'
}
