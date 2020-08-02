/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rrhh.managers;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import jpa.controller.RolController;
import jpa.controller.UsuController;
import jpa.entity.RhRol;
import jpa.entity.RhUsuario;

/**
 *
 * @author Mauricio Argumedo
 */
@ManagedBean
@ViewScoped
public class UsuarioManager implements Serializable{
    private RhUsuario usuario;
    
    private List<RhUsuario> usuList;
    
    private UsuController uc;

    /**
     * Creates a new instance of usuarioManager
     */
    @PostConstruct
    public void loadData()
    {
        try 
        {
            usuario = new RhUsuario();
            
            uc = new UsuController();
            
            usuList = uc.findAll(usuario);
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(UsuarioManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public RhUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(RhUsuario usuario) {
        this.usuario = usuario;
    }


    public List<RhUsuario> getUsuarioList() {
        return usuList;
    }

    public void setUsuarioList(List<RhUsuario> usuarioList) {
        this.usuList = usuarioList;
    }


    public UsuController getUc() {
        return uc;
    }

    public void setUc(UsuController uc) {
        this.uc = uc;
    }
    
    public void createUsu()
    {
        try 
        {
            usuario.setUsId(0);
            System.out.println("Usuario: " + usuario);
            uc.create(usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Rol Creado","Exitoso"));
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(UsuarioManager.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Rol No Creado " + ex.getMessage(),""));
        }
    }
    
}
