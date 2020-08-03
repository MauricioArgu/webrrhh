$(document).ready(function(){
    $('#videos').hide();

    consultar();
    setInterval(consultar, 1000);
});
//FUNCIONES ---------------------------------->
var state = 0;
//Para cambiar entre los valores
/*
* 0 = no hay llamda
* 1 = llamada entrante
* 2 = ocupado //NO UTILIZADO AUN
* 3 = llamada rechazada
* */
let cambiarEstadoLlamada=(parametro)=>
{
    $.ajax({
        url: 'controlador/controlCall.php',
        method: 'POST',
        data: {
            parametro:parametro
        }
    })
}

let consultar=()=>//Funcion que se encarga de detectar los cambios de la llamada.
{
    $.ajax({
        url: 'controlador/controlCall.php'
    }).done(function(data){
        if(data==1 && state==0)
        {
            alertaDeLlamada();
            state = 1;
        }
    });
}

let alertaDeLlamada=()=>//Funcion que lanza la alerta cuando el estado de BD en llamda es 1
{
    $('#audio').append('<audio src="audio/call.mp3" loop="true" autoplay="true">');
    Swal.fire({
        title: 'Llamada entrante',
        text: "entrante",
        imageUrl: 'img/loading-54.gif',
        imageWidth: 400,
        imageHeight: 300,
        imageAlt: 'Custom image',
        width: 600,
        padding: '3em',
        background: '#f7f7f7',
        color: "#fff",
        showCancelButton: true,
        cancelButtonText: "Rechazar&nbsp;<i class='fas fa-phone'></i>",
        cancelButtonColor: 'red',
        confirmButtonText: "Aceptar llamada&nbsp;<i class='fas fa-phone-slash'></i> ",
        animation: true,
        showClass: {
            popup: 'animated fadeInDown faster'
        },
        hideClass: {
            popup: 'animated fadeOutUp faster'
        }
    }).then(result=>{
        if(result.value)
        {
            $('#audio').empty();
            state=0;
            cambiarEstadoLlamada(2);
            var html = '<div id="subscriber"></div><div id="publisher"></div><div id="zoom"></div>';
            $('#videos').append(html+"<script src='js/app.js'></script><script src='js/adapter.js'></script>");
            $('#videos').show(400);
        }
        else
        {
            $('#audio').empty();
            state=0;
            cambiarEstadoLlamada(3);
        }
    });
}