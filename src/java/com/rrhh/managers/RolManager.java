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
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import jpa.controller.RolController;
import jpa.entity.RhRol;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Mauricio Argumedo
 */
@ManagedBean
@ViewScoped
public class RolManager implements Serializable {

    private RhRol rol;
    private List<RhRol> rolList;
    private RolController rc;
    /**
     * Creates a new instance of rolManager
     */

    @PostConstruct
    public void loadData()
    {
        try {
            rol = new RhRol();
            rc = new RolController();
            System.out.println("Rol: " + rol);
            rolList = rc.findAll(rol);
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(RolManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public RhRol getRol() {
        return rol;
    }

    public void setRol(RhRol rol) {
        this.rol = rol;
    }

    public List<RhRol> getRolList() {
        return rolList;
    }

    public void setRolList(List<RhRol> rolList) {
        this.rolList = rolList;
    }




    public void createRol()
    {
        try 
        {
            rol.setRolId(0);
            System.out.println("Rol: " + rol);
            rc.create(rol);
            simpleAlert("success", "Creado", "El registro se ha creado satisfactoriamente.");
            updateColumns();
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(RolManager.class.getName()).log(Level.SEVERE, null, ex);
            simpleAlert("info", "No creado", ex.getMessage());
        }
    }

    public void updateRol()
    {

        try 
        {
            System.out.println("Rol: " + rol);
            rc.edit(rol);
            simpleAlert("success", "Actualizado", "El registro se ha actualizado satisfactoriamente.");
            updateColumns();
        } 
        catch (Exception ex) 
        {
            simpleAlert("info", "No actualizado", ex.getMessage());
            Logger.getLogger(RolManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void deleteRol()
    {
        try 
        {
            System.out.println("Rol: " + rol);
            rc.delete(rol);
            simpleAlert("success", "Eliminado", "El registro se ha eliminado satisfactoriamente.");
            updateColumns();
        } 
        catch (Exception ex) 
        {
            simpleAlert("info", "No eliminado", ex.getMessage());
            Logger.getLogger(RolManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void findRol()
    {
        rol.setRolId(0);
        rol.setRolNombre("rol");
        System.out.println("Rol: " + rol);
        try 
        {
            if (rc.findAll(rol).isEmpty()) 
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Esta vacio","Exitoso"));
            }
            else
            {
                for(int i = 0; i < rc.findAll(rol).size(); i++)
                {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Rol: " + rc.findAll(rol).get(i).toString(),""));
                }
            }
            rc.find(rol, 0);
        }   
        catch (Exception ex) 
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error  : " + ex.getMessage(),""));
            Logger.getLogger(RolManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateColumns() {
        //reset table state
        loadData();
        PrimeFaces.current().ajax().update("formTable:data");
    }
    
    public void simpleAlert(String type, String title, String text){
        StringBuilder alert = new StringBuilder();
        alert.append("hideModal();simpleAlert('").append(type).append("','");
        alert.append(title).append("','").append(text).append("');");
        PrimeFaces.current().executeScript(alert.toString());
    }
}