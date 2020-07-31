/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rrhh.managers;

import com.rrhh.entity.RhRol;
import javax.annotation.ManagedBean;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Mauricio Argumedo
 */
@ManagedBean
@ViewScoped
public class RolManager 
{
    private RhRol rol;
    /**
     * Creates a new instance of RolManager
     */
    public RolManager() {
    }

    public RhRol getRol() {
        return rol;
    }

    public void setRol(RhRol rol) {
        this.rol = rol;
    }
    
    
    
}
