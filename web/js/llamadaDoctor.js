$(document).ready(function(){
    $('#videos').hide();
    activePatient();
    llamadaRechazada();
    setInterval(llamadaRechazada, 500);


});
let estadoRechazado = 0;
let call = 0;
let llamarPaciente=()=>
{
    call = 1;
    $.ajax({
        url: '../controller/controlCall.php',
        method: 'POST',
        data: {
            call:call
        }
    })
    estadoRechazado=0;
}

let llamadaRechazada=()=>
{
        $.ajax({
            url: '../controller/controlCall.php'
        }).done(function(data){
            if(data==3 && estadoRechazado==0)
            {
                estadoRechazado++;
                Swal.fire({
                    title: 'Llamada rechazada',
                    text: "La llamada ha sido rechazada por el paciente:(",
                    type: 'error',
                }).then(result=>{
                    if(result.value)
                    {
                        $.ajax({
                            url: 'controlador/controlCall.php',
                            method: 'POST',
                            data: {
                            rebootCall:0
                            }
                        });
                        ocultarDivLlamada();
                    }
                });
            }
        });
}

let ocultarDivLlamada=()=>
{
    $('#videos').hide();
    $('#videos').empty();
    $('#rowTable').show(300);
    location.reload();

}

let activePatient=()=>
{
    $.ajax({
        url: '../controller/actions/activePatient.php'
    }).done(function(data){
        $('#activeTable').html(data);
    });
}

let llamada=()=>
{
    var html = '<div id="subscriber"></div><div id="publisher"></div><div id="zoom"></div>';
    $('#videos').append(html+"<script src='js/app.js'></script><script src='js/adapter.js'></script>");
    $('#videos').show(400);
    llamarPaciente();
    call = 0;
    var html2 = '<button onclick="ocultarDivLlamada()" class="btn btn-danger btn-end-call rounded-circle"><i class="fas fa-phone-slash"></i></button><button onclick="pausarVideo()" class="btn btn-success rounded-circle btn-stop-video"><i class="fas fa-pause-circle"></i></button>';
    $('#rowTable').hide();
    $('#barAction').append(html2);
}


