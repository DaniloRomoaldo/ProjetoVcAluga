url_veiculo = "http://localhost:8080/veiculo"

function listVeiculos(route, value){
    event.preventDefault();
    const typeGet = "veiculo";
    let url_busca;

    if (route === "placa") {
        url_busca = url_veiculo + "?placa=" + value;
    } else if (route === "status" ) {
        url_busca = url_veiculo+"?status="+value;
    }else if (route === "cat"){
        url_busca = url_veiculo+"?categoria="+value;
    }else {
        url_busca = url_veiculo;
    }
    get(typeGet, url_busca)
}

function appendListVeiculo(data){
    const listaVeiculo = document.getElementById("list-itens-veih")

    listaVeiculo.innerHTML='';

    const veiculos = data.map(veiculo => {
        var item = criarLi();
        var inputElement = criarInput("listGroupRadio", veiculo.id);
        var labelElement = criarLabel(veiculo.id);
        labelElement.textContent= veiculo.nome + ", Status: " + veiculo.status + " - categoria: " + veiculo.categoria +
            " KM: " + veiculo.km_total + "- Local Atual: " + veiculo.filialDTO.nome;

        item.appendChild(inputElement);
        item.appendChild(labelElement);

        listaVeiculo.appendChild(item);
    })
}

document.addEventListener("DOMContentLoaded", function (){
    const excluir = document.querySelector('.excluir-bnt');

    excluir.addEventListener('click', function (){
        const listaVeiculo = document.getElementById('list-itens-veih'); //pega o elemento ul que contém a lista
        const inputs = listaVeiculo.querySelectorAll('input[type="radio"]'); // pega todos os elementos dentro da lista

        let idSelecionado = null;

        inputs.forEach(elementoInput => {
            if (elementoInput.checked){
                idSelecionado = elementoInput.id;
            }
        });

        if (idSelecionado){
            url_delete = url_veiculo+"/"+idSelecionado  // roda do funcionário
            deleteRegister(url_delete);
        }else {
            console.log("erro")
        }
    })
})