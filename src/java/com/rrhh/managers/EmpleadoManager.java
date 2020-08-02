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
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import jpa.controller.EmpController;
import jpa.entity.RhEmpleado;

/**
 *
 * @author Mauricio Argumedo
 */
@ManagedBean
@ViewScoped
public class EmpleadoManager implements Serializable{
    
    private RhEmpleado emp;
    
    private EmpController ec;
    
    private List<RhEmpleado> empList;
    /**
     * Creates a new instance of EmpleadoManager
     */
    
    @PostConstruct
    public void loadData()
    {
        try 
        {
            emp = new RhEmpleado();
            ec = new EmpController();
            
            empList = ec.findAll(emp);
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(EmpleadoManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public RhEmpleado getEmp() {
        return emp;
    }

    public void setEmp(RhEmpleado emp) {
        this.emp = emp;
    }

    public EmpController getEc() {
        return ec;
    }

    public void setEc(EmpController ec) {
        this.ec = ec;
    }

    public List<RhEmpleado> getEmpList() {
        return empList;
    }

    public void setEmpList(List<RhEmpleado> empList) {
        this.empList = empList;
    }
    
    
    
    
}
