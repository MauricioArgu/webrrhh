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
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Departamento Creado","Exitoso"));
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(DepartamentoManager.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Departamento No Creado " + ex.getMessage(),"Fail"));
        }
    }
    
    public void updateDep()
    {

        try 
        {
            System.out.println("Departamento: " + dep);
            dc.edit(dep);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Departamento Editado","Exitoso"));
        } 
        catch (Exception ex) 
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Departamento No Editado: " + ex,"Fail"));
            Logger.getLogger(DepartamentoManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteDep()
    {
        try 
        {
            System.out.println("Departamento: " + dep);
            dc.delete(dep);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Rol Borrado","Exitoso"));
        } 
        catch (Exception ex) 
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Rol No Borrado: " + ex,"Fail"));
            Logger.getLogger(DepartamentoManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
