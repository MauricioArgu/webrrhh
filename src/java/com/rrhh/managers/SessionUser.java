/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rrhh.managers;


import com.rrhh.utility.Email;
import com.rrhh.utility.Encryption;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import jpa.controller.UsuController;
import jpa.entity.RhUsuario;

/**
 *
 * @author Mauricio Argumedo
 */
@ManagedBean
@SessionScoped
public class SessionUser implements Serializable {

    private RhUsuario session;
    private UsuController uc;
    
    @PostConstruct
    public void inicializar()
    {
        session = new RhUsuario();
        uc = new UsuController();
    }
    
    public void validarUsuario()
    {
        RhUsuario usr = uc.validarUsuario(session.getUsCorreo());   
        boolean isNotValidUser = true;
        if (usr != null) 
        {
            Email email = new Email();
            Encryption enc = new Encryption();
            System.out.println("Pass: " + enc.encrypt(session.getUsContra()));
            if (enc.encrypt(session.getUsContra()).equals(usr.getUsContra())) 
            {
                session.setRolId(usr.getRolId());
                session.setUsUsuario(usr.getUsUsuario());
                System.out.println("ENTRO");
                email.sendMail(session.getUsCorreo());
                isNotValidUser = false;
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
    
    
    
}
