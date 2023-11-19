class MobileNavBar{

    constructor(mobileMenu, navList ,navLinks){
        this.mobileMenu = document.querySelector(mobileMenu);
        this.navList = document.querySelector(navList);
        this.navLinks = document.querySelectorAll(navLinks);
        this.activeClass = "active";

        this.handleClick = this.handleClick.bind(this);

    }

    handleClick(){
        this.navList.classList.toggle(this.activeClass);
    }

    addClickEvent(){
        this.mobileMenu.addEventListener("click", this.handleClick)
    }

    init(){
        if (this.mobileMenu) {
            this.addClickEvent();
        }
        return this;
    }

}


const menu = new MobileNavBar(
    ".mobile-menu",
    ".nav-list",
    ".nav-list li",
); 
menu.init();


function logOut(){
   window.location.href = "http://localhost:8080/sair"

}

function home(){
    window.location.href = "http://localhost:8080/home"
}


/*------------------------------------------FUNÇÕES GLOBAIS-------------------------------------------------------*/

                        /*--------------------GET------------------------*/
function get(typeGet,url){  // função GET padrão para todos os request de buscar lista de valores no banco
    fetch(url, {
        method: "GET",
        headers: {"Content-Type": "application/json"}
    })
        .then(response => {
            if (response.ok){
                return response.json();
            }else {
                throw new Error("Erro de requisição");
            }
        })
        .then(data =>{  // criar método para converter o data em elementos html e inserir no campo desejado
            console.log("dados recebidos:", data)

            switch (typeGet){
                case typeGet = "cliente":
                    appednListClients(data)
                case typeGet = "funcionario":
                    appendListFuncionario(data)
                case typeGet = "motorista":
                    appendListMotorista(data)
                case typeGet = "veiculo":
                    appendListVeiculo(data)
                case typeGet = "locacoesAtivas":
                    appendListLocacoesAtivas(data)

            }


        })
        .catch(error =>{
           // console.log(error)
        })

}

                            /*--------------------POST------------------------*/
async function post(url, body){

    try{
        const response = await fetch(url,{
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(body)
        });
        if (response.ok){
            console.log("REGISTRADO")
        }else {
            throw new Error("requisição erro "+ response.status);
        }
    } catch (error){
        console.log(error);
    }
}



/*------------------------------------------FUNÇÕES DELETAR REGISTRO-------------------------------------------------------*/

function deleteRegister(url, event){
    const confirmed = window.confirm("Tem certeza que deseja excluir o registro?");

    if (!confirmed) {
        return;  // Se o usuário clicou em "Cancelar", interrompe a execução da função
    }

    fetch(url,{
        method: "DELETE",
        headers: {"Content-Type": "application/json"}
    })
        .then(response =>{
            if (response.ok){
                window.alert("Registro deletado!");
                location.reload();
            }else {
                throw  new Error("erro na requisição")
            }
        })

}


/*-------------------------------------FUNÇÕES CRIAÇÃO DE DINAMICA DE ELEMENTOS-------------------------------------------*/

function criarLabel(id){

    var labelElement = document.createElement("label");  //criar o elemento label que recebe os dados
    labelElement.setAttribute("for", id);
    labelElement.classList.add("form-check-label")

    return labelElement;

}

function criarLi(){
    var item = document.createElement("li"); //criar o elemento list
    item.classList.add("list-group-item", "list-itens-loc");

    return item;
}


function criarInput(name, id){
    var inputElement = document.createElement("input"); //criar o elemento input verificador
    inputElement.setAttribute("type","radio");
    inputElement.setAttribute("name",name);
    inputElement.setAttribute("id", id);

    inputElement.classList.add("form-check-input", "me-1" )

    return inputElement;

}


function formatarTimestamp(timestamp, typeData){
    const timesTampObj = new Date(timestamp);
    const data = timesTampObj.toLocaleDateString();
    if (typeData === "data"){
        return `${data}`
    }else{
        const hora = timesTampObj.toLocaleTimeString();
        return `${data} às ${hora}`;
    }

}