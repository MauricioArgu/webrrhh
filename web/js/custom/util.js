var menuOpen = false;
let toggleMenu=()=>{
    if(menuOpen){

        $(".menuRigh").hide("slide", { direction: "left" }, 1000);
        menuOpen=false;
    }else{
        $(".menuRigh").show("slide", { direction: "left" }, 1000);
        menuOpen=true;
    }
}

let validateRol=()=>{
    if(userRol!=1){
        Swal.fire({
            type:'info',
            title:'Error de Permisos',
            text:'Lo sentimos, no está autorizado para esta acción.'
        });
        return false;
    }else{
        return true;
    }
}

let simpleAlert=(type,title,text)=>{
    Swal.fire({
        type:type,
        title:title,
        text:text
    });
}