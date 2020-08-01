/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rrhh.managers;

import com.rrhh.controller.RolController;
import com.rrhh.entity.RhRol;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Maury
 */
@ManagedBean
@ViewScoped
public class rolManager implements Serializable {
    
    private RhRol rol;
    private RolController rc;
    /**
     * Creates a new instance of rolManager
     */
    
    @PostConstruct
    public void inicializar()
    {
        rol = new RhRol();
        rc = new RolController();
    }
    
    public RhRol getRol() {
        return rol;
    }

    public void setRol(RhRol rol) {
        this.rol = rol;
    }
    
    public void createRol()
    {
        try 
        {
            System.out.println("Rol: " + rol);
            rc.create(rol);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Rol Creado","Exitoso"));
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(rolManager.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Rol No Creado " + ex.getMessage(),""));
        }
    }
    
    
}
