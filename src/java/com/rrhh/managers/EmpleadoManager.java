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
    
    public void createEmp()
    {
        try 
        {
            emp.setEmpId(0);
            System.out.println("Empleado: " + emp);
            ec.create(emp);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Empleado Creado","Exitoso"));
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(EmpleadoManager.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Empelado No Creado " + ex.getMessage(),"Fail"));
        }
    }
    
    public void updateEmp()
    {

        try 
        {
            System.out.println("Empleado: " + emp);
            ec.edit(emp);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Empleado Editado","Exitoso"));
        } 
        catch (Exception ex) 
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Empleado No Editado: " + ex,"Fail"));
            Logger.getLogger(EmpleadoManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteEmp()
    {
        try 
        {
            System.out.println("Empleado: " + emp);
            ec.delete(emp);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Empleado Borrado","Exitoso"));
        } 
        catch (Exception ex) 
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Empleado No Borrado: " + ex,"Fail"));
            Logger.getLogger(EmpleadoManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
