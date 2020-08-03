$(document).ready(function(){
    getUserData();
});

let getUserData=()=>{
    $.ajax({
        url:'../../controller/controlUser.php',
        data:{
            getData:true
        }
    }).done(function(data){
        $('#tableUser').html(data);
    });
}

let validateForm=()=>{
    var sendForm = false;
    if($('#username').val()=="" || $('#password').val()==""){
        $('#error').text('Por favor verifique que los campos estén llenos.');
    }
    else if($("#rol").prop('selectedIndex')==0 && userRol!=1){
        $('#errorRol').text('Usted no está autorizado para agregar otro usuario de tipo Administrador.');
    }
    else{
        sendForm = true;
    }

    if(sendForm){
        $('#userForm')[0].submit();
    }
}


let addForm=()=>{
    $('#action').val('add');
    $('#btnForm').removeClass('btn btn-warning');
    $('#btnForm').addClass('btn btn-primary');
    $('#btnForm').html('Agregar usuario &nbsp;<i class="fas fa-plus"></i>');
    $('#titleForm').html('Agregar Usuario <i class="fas fa-user-plus"></i>');
    $('#info').click();
    $('#userForm')[0].reset();
    $('#stateDiv').html('');
}

let editForm=()=>{
    $('#btnForm').removeClass('btn btn-primary');
    $('#btnForm').addClass('btn btn-warning');
    $('#btnForm').html('¿Guardar Cambios?');
    $('#titleForm').html('Editar Usuario <i class="fas fa-pencil"></i>');
    $('#action').val('edit');
    $('#info').click();
}

let editarUsuario=()=>{
    if(validateRol()){
        console.log('Editado');
    }
}

let eliminarUsuario=(id)=>{
    if(validateRol()){
        Swal.fire({
            type:'info',
            title:'¿Seguro que desea eliminar?',
            text:'El registro no podrá recuperarse.',
            showCancelButton: true,
            cancelButtonColor: 'red',
            cancelButtonText: 'Cancelar'
        }).then(function(result){
            if(result.value){
                $.ajax({
                    url:'../../controller/controlUser.php',
                    data:{
                        delete:true,
                        idusuario:id
                    }
                }).done(function(response){
                    if(response=='1'){
                        simpleAlert('success','Eliminado!','Registro eliminado satisfactoriamente.');
                        getUserData();
                    }else{
                        simpleAlert('info','Error al eliminar.',response);
                    }
                });
            }else{
                simpleAlert('info','¿Seguro que desea eliminar?','El registro no podrá recuperarse.');
            }
        });
    }
}

let changeState=(id,state,rol)=>{
    if(validateRol()){
        if(rol==1){
            simpleAlert('error','Error al cambiar estado.','No puedes alterar usuarios de tipo administrador.');
        }else{
            $.ajax({
                url:'../../controller/controlUser.php',
                data:{
                    changeState:true,
                    idusuario:id,
                    state:state
                }
            }).done(function(data){
                console.log(data);
                getUserData();
            });
        }
    }

}

let loadUsuario=(id,username,password,rol,state)=>{

    if(validateRol()){
        var estado = '<br><label>Estado</label><select name="state" id="state"class="form-control">' +
            '<option value="1">Activo</option>' +
            '<option value="0">Inactivo</option>' +
            '</select>';
        $('#stateDiv').html(estado);
        $('#id').val(id);
        $('#username').val(username);
        $('#password').val(password);
        $('#rol').val(rol);
        $('#state').val(state);
        editForm();
    }
}