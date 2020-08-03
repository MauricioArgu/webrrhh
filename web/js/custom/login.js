$(document).ready(function(){
    var noLogin = false;
    if(!noLogin){
        $("#info").click();
        $("#logo").click(function(){
            $('#info').click();
        });
    }else {
        const Toast = Swal.mixin({
            toast: true,
            position: 'top-end',
            showConfirmButton: false,
            timer: 5000,
            timerProgressBar: true,
            onOpen: (toast) => {
                toast.addEventListener('mouseenter', Swal.stopTimer)
                toast.addEventListener('mouseleave', Swal.resumeTimer)
            }
        })

        Toast.fire({
            icon: 'warning',
            title:'Usuario o contraseña incorrectos.',
            text:'Por favor verifique los datos de inicio de sesión, e intente de nuevo.'
        })
    }
});