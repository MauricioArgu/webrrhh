
$(document).ready(function(){
    $('.datepicker').datepicker();
});
let loadPatient=()=>
{
    $.ajax({
        url: "../controller/actions/loadPatient.php"
    }).done(function(data){
        $('#tablePatient').html(data);
    });
}

let loadExp=(nombre,apellido,exp)=>{
    var html = "<div ><h4>Name: "+nombre+" "+apellido+"</h4><br>"+exp+"</div>";
    $('#expediente').html(html);
}