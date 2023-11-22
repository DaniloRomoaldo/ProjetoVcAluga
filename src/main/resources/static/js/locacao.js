url_locacoes = "http://localhost:8080/locacoes"
url_veiculo = "http://localhost:8080/veiculo"
url_cliente = "http://localhost:8080/cliente"
url_motorista = "http://localhost:8080/motorista"




/*-----------------------------(CADASTRAR LOCACAO) - (POST)-------------------------------------------------------*/
async function registerLocacao(event){

    //dados do cliente
    var nomeCliente = document.getElementById("input-nome-cliente").value.toUpperCase();
    var cpfCnpjCliente = document.getElementById("input-cpfCnpj-cliente").value;
    var telefoneCliente = document.getElementById("input-telefone-cliente").value;
    var tipo;
    if (cpfCnpjCliente.length === 11){
        tipo = "PESSOA FISICA";
    }else {
        tipo = "PESSOA JURIDICA";
    }

    //dados do motorista
    var nomeMotorista = document.getElementById("input-nome-motorista").value.toUpperCase();
    var cpfMotorista = document.getElementById("input-cpf-motorista").value;
    var cnhMotorista = document.getElementById("input-cnh-motorista").value;
    var dt_nascimento = document.getElementById("data-nasc-motorista").value;

    //dados da locacao
    var dt_retirada = document.getElementById("data-retirada").value;
    var dt_devolucao = document.getElementById("data-devolucao").value;
    var valorLocacao = document.getElementById("valor-locacao").textContent;
    var fidelidade = parseInt(valorLocacao.replace("R$ ",""))/10;

    var tipoPagamento = document.querySelector('input[name="grid-pagamento"]:checked').value;
    var checkBoxContra = document.querySelector("#input-contrato")
    var contratoAssi = checkBoxContra.checked ? checkBoxContra.value : null;
    const listaVeiculoDisponiveis = document.getElementById('list-itens-veih-disponi'); //pega o elemento ul que contém a lista
    const inputs = listaVeiculoDisponiveis.querySelectorAll('input[type="radio"]'); // pega todos os elementos dentro da lista

    if (contratoAssi === null){
        alert("assinar contrato!")
        event.preventDefault();
    }else {
        contratoAssi = true;
    }


    let idSelecionado = null;

    inputs.forEach(elementoInput => {
        if (elementoInput.checked){
            idSelecionado = elementoInput.id;
        }
    });
    const url_busca_cliente = url_cliente + "?registro=" + cpfCnpjCliente;
    const url_busca_motorista = url_motorista + "?cnh=" + cnhMotorista;
    const url_veiculo_selec = url_veiculo + "?veiculoId=" + idSelecionado;

    const fetchCliente = fetch(url_busca_cliente).then(response => response.json());
    const fetchMotorista = fetch(url_busca_motorista).then(response => response.json());
    const fetchVeiculo = fetch(url_veiculo_selec).then(response => response.json());


    await Promise.all([fetchCliente, fetchVeiculo, fetchMotorista])
        .then(values => {
       return values.forEach((data, index) => {
            //  console.log(`Data for request ${index + 1}:`, JSON.stringify(data, null, 2));
           clienteData = values[0];
           veiculoData = values[1];
           motoristaData = values[2];

        });
    }).catch(error => {
        console.error(error);
    })


    // console.log(JSON.stringify(clienteData))
    // console.log(clienteData[0].cpfCnpj)







// -----------------Consultar se o cliente já existe e se as informações são dele

// --------------------------- VALIDAÇÕES DO CLIENTE--------------------------
    if ( clienteData[0].cpfCnpj == null){ // se for um novo cliente
        alert("cliente novo!!!!!!!!!!!!!!!!!!")
        bodyNovoCliente={
            "nome": nomeCliente,
            "cpfCnpj": cpfCnpjCliente,
            "telefone": telefoneCliente,
            "tipo": tipo,
            "total_fidelidade": fidelidade
        };
        // post(url_cliente, bodyNovoCliente);

    }else if ((clienteData[0].nome !== nomeCliente)){ // se o documento pertencer a outro cliente já registrado
        alert("Documento pertencente a outro Cliente: "+ clienteData[0].nome )
        event.preventDefault(event);

    }else {
        // -------------------- VALIDAÇÕES DO MOTORISTA

        if (motoristaData[0].cnh == null){
            bodyNovoMotorista={
                "nome":nomeMotorista,
                "cpf":cpfMotorista,
                "cnh":cnhMotorista,
                "dt_nascimento": dt_nascimento,
                "status":"INDISPONIVEL"
            };
            // post(url_motorista, bodyNovoMotorista);
        }else if ((motoristaData[0].nome !== nomeMotorista)){
            alert("CNH pertencente a outro Motorista: "+motoristaData[0].nome)
            event.preventDefault(event);
        }else if ((motoristaData[0].status !== "DISPONIVEL")){
            alert("Motorista vinculado a uma locação ATIVA!")
            event.preventDefault();
        }

    }

}


function listarVeiculosDisponiveis(categoria){
    const typeGet = "veiculoDisponivel";
    url_veiculoDisponivel = url_veiculo+"?veiculoDisponivel="+categoria;

    get(typeGet, url_veiculoDisponivel)

}


function appendListVeiculosDisponiveis(data){
    const listVeiculosDisponiveis = document.getElementById("list-itens-veih-disponi");

    listVeiculosDisponiveis.innerHTML='';

    const veiculos = data.map(veiculo => {
        var item = criarLi();
        var inputElement = criarInput("listGroupRadio", veiculo.id);
        var labelElement = criarLabel(veiculo.id);
        labelElement.textContent= veiculo.nome + " - Placa: "+veiculo.placa+" | Status: " + veiculo.status + " - categoria: " + veiculo.categoria +
            " KM: " + veiculo.km_total + "- Local Atual: " + veiculo.filialDTO.nome;

        item.appendChild(inputElement);
        item.appendChild(labelElement);

        listVeiculosDisponiveis.appendChild(item);
    })

}

