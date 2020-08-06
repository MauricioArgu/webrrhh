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
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import jpa.controller.DepController;
import jpa.entity.RhDepartamento;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Mauricio Argumedo
 */
@ManagedBean
@ViewScoped
public class DepartamentoManager implements Serializable{
    
    private RhDepartamento dep;
    
    private List<RhDepartamento> depList;
    
    private DepController dc; 

    /**
     * Creates a new instance of departamentoManager
     */
    @PostConstruct
    public void loadData()
    {
        try 
        {
            dep = new RhDepartamento();
            
            dc = new DepController();
            
            depList = dc.findAll(dep);
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(DepartamentoManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public RhDepartamento getDep() {
        return dep;
    }

    public void setDep(RhDepartamento dep) {
        this.dep = dep;
    }


    public DepController getDc() {
        return dc;
    }

    public void setDc(DepController dc) {
        this.dc = dc;
    }

    public List<RhDepartamento> getDepList() {
        return depList;
    }

    public void setDepList(List<RhDepartamento> depList) {
        this.depList = depList;
    }
    
    public void createDep()
    {
        try 
        {
            dep.setDepId(0);
            System.out.println("Departamento: " + dep);
            dc.create(dep);
            simpleAlert("success", "Creado", "El registro se ha creado satisfactoriamente.");
            updateColumns();
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(DepartamentoManager.class.getName()).log(Level.SEVERE, null, ex);
            simpleAlert("info", "No creado", ex.getMessage());
        }
    }
    
    public void updateDep()
    {

        try 
        {
            System.out.println("Departamento: " + dep);
            dc.edit(dep);
            simpleAlert("success", "Actualizado", "El registro se ha actualizado satisfactoriamente.");
            updateColumns();
        } 
        catch (Exception ex) 
        {
            simpleAlert("info", "No actualizado", ex.getMessage());
            Logger.getLogger(DepartamentoManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteDep()
    {
        try 
        {
            System.out.println("Departamento: " + dep);
            dc.delete(dep);
            simpleAlert("success", "Eliminado", "El registro se ha eliminado satisfactoriamente.");
            updateColumns();
        } 
        catch (Exception ex) 
        {
            simpleAlert("info", "No eliminado", ex.getMessage());
            Logger.getLogger(DepartamentoManager.class.getName()).log(Level.SEVERE, null, ex);
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
