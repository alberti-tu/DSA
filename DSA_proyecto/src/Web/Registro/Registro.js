var nickName = document.getElementById("InputNickName");
var password = document.getElementById("InputPassword");
var repetirPassword = document.getElementById("InputRepetirPassword");
var email = document.getElementById("InputEmail");
var isHombre = document.getElementById("inlineRadio1");
var isMujer = document.getElementById("inlineRadio2");
var btnRegistrar = document.getElementById("Registrar");
var nicknameCorrecto;
var formRes = document.getElementById("formRegistrar");
var formularioDiv = document.getElementsByClassName("formulario");
var espacioTitulo = document.getElementById("TituloEspacio")

nickName.addEventListener('click',function () {


    loadUsuarios(nickName.value);

})

nickName.onkeyup = function () {

    loadUsuarios(nickName.value);

}

btnRegistrar.addEventListener("click", function(){


    if( nickName.value !== ""){

        if(nicknameCorrecto){

            if(password.value !== ""){

                if(password.value === repetirPassword.value){

                    if(email.value !== ""){

                        if(isHombre.checked || isMujer.checked){

                            usuarioRegistrar = {
                                "nombre": nickName.value.toLowerCase(),
                                "password": password.value,
                                "email": email.value,
                                "genero": genero()
                            }

                            postData(usuarioRegistrar);


                        }else{

                            alert("Selecciona un genero.");

                        }

                    }else{

                        alert("Escriba un email.")

                    }

                }else{

                    alert("Las contraseñas no son iguales.");

                }

            }else{

                alert("Escribe una contraseña");
            }


        }else{

            alert("Usuario ya existente.");

        }

    }else{

        alert("Escribe un usuario.");

    }




})

function loadUsuarios(usuario){

    if(usuario !== "") {
        var url = "/myapp/web/usuarioExsistente/" + usuario;
        loadJSON(url, gotData)
    }else{
        nickName.classList.remove('check');
        nickName.classList.add('cross');
    }
}

function gotData(data){

    if(data.key === 0){
        nicknameCorrecto = true;
        nickName.classList.remove('cross');
        nickName.classList.add('check');
    }else{
        nickName.classList.remove('check');
        nickName.classList.add('cross');
        nicknameCorrecto = true;
    }

}

function postData(usuarioRegistrar){

    var response;
    var xhr = new XMLHttpRequest();
    xhr.open(formRes.method, formRes.action, true);
    xhr.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
    xhr.send(JSON.stringify(usuarioRegistrar));
    xhr.onreadystatechange = function()
    {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                response = JSON.parse(xhr.responseText);
                if(response !== "" && response.key === 0){

                    registrarOk();

                }else{

                    alert("Error al registrar");

                }
            }
        }
    };

}

function loadJSON(path, success, error)
{
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function()
    {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                if (success)
                    success(JSON.parse(xhr.responseText));
            } else {
                if (error)
                    error(xhr);
            }
        }
    };
    xhr.open("GET", path, true);
    xhr.send();
}

function registrarOk(){

    if (formRes) {
        formRes.remove();
    }
    formularioDiv[0].id = 'registradoOk';
    formularioDiv[0].innerHTML = "<h2 class='resultadoTitulo'>Usuario registrado correctamente</h2>"


}

Element.prototype.remove = function() {
    this.parentElement.removeChild(this);
}

NodeList.prototype.remove = HTMLCollection.prototype.remove = function() {
    for(var i = this.length - 1; i >= 0; i--) {
        if(this[i] && this[i].parentElement) {
            this[i].parentElement.removeChild(this[i]);
        }
    }
}

function genero() {

    if(isHombre.checked){
        return true;
    }

    return false;

}

