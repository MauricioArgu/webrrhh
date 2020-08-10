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
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import jpa.controller.UsuController;
import jpa.entity.RhRol;
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
        RhUsuario usr = new RhUsuario();
        boolean isNotValidUser = true;
        try{
            usr = uc.validarUsuario(tempSession.getUsCorreo());
        }catch(Exception ex){
            usr = null;
        }
        
        if (usr != null) 
        {
            Email email = new Email();
            Encryption enc = new Encryption();
            System.out.println("Pass: " + enc.encrypt(tempSession.getUsContra()));
            if (enc.encrypt(tempSession.getUsContra()).equals(usr.getUsContra())) 
            {
                tempSession.setRolId(usr.getRolId());
                tempSession.setUsUsuario(usr.getUsUsuario());
                
                codigo = email.sendMail(tempSession.getUsCorreo());
                
                System.out.println("ENTRO");
                System.out.println(codigo);
                isNotValidUser = false;
                executeJSFunction("$('.first-step').hide(300);$('.second-step').show(400);alert.close();");
            }
        }
        if (isNotValidUser) 
        {
            System.out.println("IS NOT VALID USER");
            executeJSFunction("alertFail();");
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
        System.out.println("rol: " + tempSession.getRolId().getRolNombre());
        try{
            if(codigo == userResponse){
                session = tempSession;
                System.out.println("Código correcto");
                if(session.getRolId().getRolId()==1){sendRedirect("pages/dashboard.rrhh");}
                else{sendRedirect("pages/dashboard.rrhh");}
            }else{simpleAlert("info", "Código incorrecto", "Por favor verifique el código enviado a su correo electrónico.");}
        }catch(Exception ex){
            simpleAlert("info", "Código incorrecto", "Por favor verifique el código enviado a su correo electrónico.");
        }
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
    
    public void validateRRHHUser(){
        if(session.getRolId()!=null){
            System.out.println("session" +session.getRolId().getRolId() );
            if(session.getRolId().getRolId()!=2){
                sendRedirect("dashboard.rrhh");
            }
        }else{
            sendRedirect("../login.rrhh");
        }
    }
    
    public void validateAdminUser(){
        if(session.getRolId() != null){
            System.out.println("session" +session.getRolId().getRolId());
            if(session.getRolId().getRolId()!=1){
                sendRedirect("dashboard.rrhh");
            }
        }else{
            sendRedirect("../login.rrhh");
        }
    }
    
    public void validateUser(){
        if(session.getRolId() == null){
            sendRedirect("../login.rrhh");
        }
    }
    
    public void logOut(){
        tempSession = new RhUsuario();
        session     = new RhUsuario();
        sendRedirect("../login.rrhh");
    }
    
    
    public void sendRedirect(String url){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        } catch (IOException ex) {
            Logger.getLogger(SessionUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void simpleAlert(String type, String title, String text){
        StringBuilder alert = new StringBuilder();
        alert.append("simpleAlert('").append(type).append("','");
        alert.append(title).append("','").append(text).append("');");
        PrimeFaces.current().executeScript(alert.toString());
    }
    
    public void executeJSFunction(String function){
        PrimeFaces.current().executeScript(function);
    }
}
