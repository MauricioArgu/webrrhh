/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rrhh.managers;


import com.rrhh.utility.Email;
import com.rrhh.utility.Encryption;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import jpa.controller.UsuController;
import jpa.entity.RhUsuario;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Mauricio Argumedo
 */
@ManagedBean
@SessionScoped
public class SessionUser implements Serializable {

    private RhUsuario session;
    private RhUsuario tempSession;
    private UsuController uc;
    private int codigo;
    private int userResponse;
    
    
    @PostConstruct
    public void inicializar()
    {
        session     = new RhUsuario();
        tempSession = new RhUsuario();
        uc          = new UsuController();
        codigo      = 1;
    }
    
    public void validarUsuario()
    {
        RhUsuario usr = uc.validarUsuario(tempSession.getUsCorreo());   
        boolean isNotValidUser = true;
        if (usr != null) 
        {
            Email email = new Email();
            Encryption enc = new Encryption();
            System.out.println("Pass: " + enc.encrypt(tempSession.getUsContra()));
            if (enc.encrypt(tempSession.getUsContra()).equals(usr.getUsContra())) 
            {
                tempSession.setRolId(usr.getRolId());
                tempSession.setUsUsuario(usr.getUsUsuario());
                System.out.println("ENTRO");
                setCodigo(email.sendMail(tempSession.getUsCorreo()));
                System.out.println(codigo);
                isNotValidUser = false;
                executeJSFunction("$('.first-step').hide(300);$('.second-step').show(400);Swal.clickCancel();");
            }
        }
        if (isNotValidUser) 
        {
            System.out.println("IS NOT VALID USER");
        }
    }
    
    public void validarSesion()
    {
        System.out.println("Validando Sesion");
        if (session.getRolId() == null) 
        {
            System.out.println("No permitido");
        }
    }
    
    public void validarCodigo(){
        System.out.println("verificando codigo: " + userResponse);
        System.out.println("Codigo generado: " + codigo);
        System.out.println("Sesion: " + tempSession.getUsCorreo());
        if(codigo == userResponse){
            session = tempSession;
            System.out.println("Código correcto");
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("pages/empleado.rrhh");
            } catch (IOException ex) {
                Logger.getLogger(SessionUser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{simpleAlert("error", "Código incorrecto", "Por favor verifique el código enviado a su correo electrónico.");}
    }
    

    public RhUsuario getSesion() {
        return session;
    }

    public void setSesion(RhUsuario sesion) {
        this.session = sesion;
    }
    
    

    public UsuController getUc() {
        return uc;
    }

    public void setUc(UsuController uc) {
        this.uc = uc;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getUserResponse() {
        return userResponse;
    }

    public void setUserResponse(int userResponse) {
        this.userResponse = userResponse;
    }

    public RhUsuario getTempSession() {
        return tempSession;
    }

    public void setTempSession(RhUsuario tempSession) {
        this.tempSession = tempSession;
    }
    
    
    
    public void simpleAlert(String type, String title, String text){
        StringBuilder alert = new StringBuilder();
        alert.append("hideModal();simpleAlert('").append(type).append("','");
        alert.append(title).append("','").append(text).append("');");
        PrimeFaces.current().executeScript(alert.toString());
    }
    
    public void executeJSFunction(String function){
        PrimeFaces.current().executeScript(function);
    }
}
