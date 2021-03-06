/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rrhh.managers;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import jpa.controller.PlaController;
import jpa.entity.RhPlanilla;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Mauricio Argumedo
 */
@ManagedBean
@ViewScoped
public class PlanillaManager implements Serializable
{
    private RhPlanilla pla;
    
    private PlaController pc;
    
    private List<RhPlanilla> plaList;
    
    private String strDateFormat = "dd MMMM yyyy"; // El formato de fecha está especificado  
    private SimpleDateFormat objSDF; // La cadena de formato de fecha se pasa como un argumento al objeto
    /**
     * Creates a new instance of PlanillaManager
     */
    
    @PostConstruct
    public void loadData()
    {
        try 
        {
            
            objSDF  = new SimpleDateFormat(strDateFormat);
            pla     = new RhPlanilla();
            pc      = new PlaController();
            plaList = pc.findAll(pla);
        } catch (Exception ex) 
        {
            Logger.getLogger(PlanillaManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public RhPlanilla getPla() {
        return pla;
    }

    public void setPla(RhPlanilla pla) {
        this.pla = pla;
    }

    public PlaController getPc() {
        return pc;
    }

    public void setPc(PlaController pc) {
        this.pc = pc;
    }

    public List<RhPlanilla> getPlaList() {
        return plaList;
    }

    public void setPlaList(List<RhPlanilla> plaList) {
        this.plaList = plaList;
    }

    public SimpleDateFormat getObjSDF() {
        return objSDF;
    }

    public void setObjSDF(SimpleDateFormat objSDF) {
        this.objSDF = objSDF;
    }
    
    public boolean validateMonth()
    {
        try 
        {
            return pc.validateMonth();
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(PlanillaManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    
    public void createPla()
    {
        try 
        {
            pla.setPlnId(0);
            System.out.println("Planilla: " + pla);
            pc.create(pla);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Planilla Creada","Exitoso"));
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(PlanillaManager.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Planilla No Creada " + ex.getMessage(),"Fail"));
        }
    }
    
    public void updatePla()
    {

        try 
        {
            System.out.println("Planilla: " + pla);
            pc.edit(pla);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"PLanilla Editada","Exitoso"));
        } 
        catch (Exception ex) 
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Planilla No Editada: " + ex,"Fail"));
            Logger.getLogger(PlanillaManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deletePla()
    {
        try 
        {
            System.out.println("Planilla: " + pla);
            pc.delete(pla);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Planilla Borrada","Exitoso"));
        } 
        catch (Exception ex) 
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Planilla No Borrada: " + ex,"Fail"));
            Logger.getLogger(PlanillaManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void prepareForCreate(){
        if(validateMonth()){executeJSFunction("$('#generatePayroll').show(300);");}
        else{simpleAlert("error", "La planilla ya ha sido creata", "Ya existe una planilla para el mes actual, por favor verifique en los datos.");}
    }
    
    public void payPayroll(){
        try 
        {
            pla.setPlnEstado(1);
            System.out.println("-------------> Planilla: " + pla);
            pc.edit(pla);
            simpleAlert("success", "Actualizada", "El estado de la planilla se actualizó satisfactoriamente.");
            updateColumns();
        } 
        catch (Exception ex) 
        {
            simpleAlert("info", "No eliminado", ex.getMessage());
            Logger.getLogger(DetPlanillaManager.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public void executeJSFunction(String function){
        PrimeFaces.current().executeScript(function);
    }
}
