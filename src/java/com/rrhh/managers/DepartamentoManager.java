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
import javax.faces.bean.ManagedBean;
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
    
    
    
    
    
}
