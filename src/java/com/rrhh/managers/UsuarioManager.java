/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rrhh.managers;

import com.rrhh.utility.Encryption;
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
import org.primefaces.PrimeFaces;

/**
 *
 * @author Mauricio Argumedo
 */
@ManagedBean
@ViewScoped
public class UsuarioManager implements Serializable{
    private RhUsuario usu;
    
    private List<RhUsuario> usuList;
    
    private UsuController uc;
    
    private Encryption encrypter;
    
    private RhRol rol;

    /**
     * Creates a new instance of usuarioManager
     */
    @PostConstruct
    public void loadData()
    {
        try 
        {
            usu = new RhUsuario();
            
            rol = new RhRol();
            
            uc = new UsuController();
            
            usuList = uc.findAll(usu);
            
            encrypter = new Encryption();
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(UsuarioManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public RhUsuario getUsuario() {
        return usu;
    }

    public void setUsuario(RhUsuario usuario) {
        this.usu = usuario;
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

    public RhRol getRol() {
        return rol;
    }

    public void setRol(RhRol rol) {
        this.rol = rol;
    }
    
    public void createUsu()
    {
        try 
        {
            usu.setUsId(0);
            String newPass = encrypter.encrypt(usu.getUsContra());
            usu.setUsContra(newPass);
            System.out.println("Usuario: " + usu);
            uc.create(usu);
            simpleAlert("success", "Creado", "El registro se ha creado satisfactoriamente.");
            updateColumns();
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(UsuarioManager.class.getName()).log(Level.SEVERE, null, ex);
            simpleAlert("info", "No creado", ex.getMessage());
        }
    }
    
    public void updateUsu()
    {

        try 
        {
            String newPass = encrypter.encrypt(usu.getUsContra());
            usu.setUsContra(newPass);
            System.out.println("Usuario: " + usu);
            usu.setRolId(rol);
            uc.edit(usu);
            simpleAlert("success", "Actualizado", "El registro se ha actualizado satisfactoriamente.");
            updateColumns();
        } 
        catch (Exception ex) 
        {
            simpleAlert("info", "No actualizado", ex.getMessage());
            Logger.getLogger(UsuarioManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteUsu()
    {
        try 
        {
            System.out.println("Usuario: " + usu);
            uc.delete(usu);
            simpleAlert("success", "Eliminado", "El registro se ha eliminado satisfactoriamente.");
            updateColumns();
        } 
        catch (Exception ex) 
        {
            simpleAlert("info", "No eliminado", ex.getMessage());
            Logger.getLogger(UsuarioManager.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public void reset() {
        PrimeFaces.current().resetInputs("formDetail");
    }
}
