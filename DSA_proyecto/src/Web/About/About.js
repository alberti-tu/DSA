var foto = document.getElementsByClassName("foto");
var participantes = ["oscarmampel","alberti-tu","invocamanman","JaMateuC"];
var i = 0;

loadUsuarios(participantes);

function loadUsuarios(participantes) {

    var url = "https://api.github.com/users/" + participantes[i];
    loadJSON(url, gotData);
}

function gotData(data){

    foto[i].src = data.avatar_url;
    i++;
    if(i<participantes.length){

        loadUsuarios(participantes);


    }
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
