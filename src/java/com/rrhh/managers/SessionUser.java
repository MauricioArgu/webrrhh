/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rrhh.managers;


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

    private RhUsuario usuario;
    private UsuController uc;
    
    @PostConstruct
    public void inicializar()
    {
        usuario = new RhUsuario();
        uc = new UsuController();
    }
    
    public void validarUsuario()
    {
        
    }
    
}
