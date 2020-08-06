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
import javax.faces.view.ViewScoped;
import jpa.controller.EmpController;
import jpa.entity.RhEmpleado;
import org.primefaces.PrimeFaces;

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
            simpleAlert("success", "Creado", "El registro se ha creado satisfactoriamente.");
            updateColumns();
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(EmpleadoManager.class.getName()).log(Level.SEVERE, null, ex);
            simpleAlert("info", "No creado", ex.getMessage());
        }
    }
    
    public void updateEmp()
    {

        try 
        {
            System.out.println("Empleado: " + emp);
            ec.edit(emp);
            simpleAlert("success", "Actualizado", "El registro se ha actualizado satisfactoriamente.");
            updateColumns();
        } 
        catch (Exception ex) 
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Empleado No Editado: " + ex,"Fail"));
            simpleAlert("info", "No actualizado", ex.getMessage());
        }
    }
    
    public void deleteEmp()
    {
        try 
        {
            System.out.println("Empleado: " + emp);
            ec.delete(emp);
            simpleAlert("success", "Eliminado", "El registro se ha eliminado satisfactoriamente.");
            updateColumns();
        } 
        catch (Exception ex) 
        {
            simpleAlert("info", "No eliminado", ex.getMessage());
            Logger.getLogger(EmpleadoManager.class.getName()).log(Level.SEVERE, null, ex);
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
