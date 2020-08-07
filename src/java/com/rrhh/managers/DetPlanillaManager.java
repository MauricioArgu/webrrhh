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
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import jpa.controller.DetPlaController;
import jpa.entity.RhDetallePlanilla;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Mauricio Argumedo
 */
@ManagedBean
@ViewScoped
public class DetPlanillaManager implements Serializable
{
    private RhDetallePlanilla detpla;
    
    private DetPlaController dpc;
    
    private List<RhDetallePlanilla> detplaList;
    
    private List<RhDetallePlanilla> detByIdList;

    /**
     * Creates a new instance of DetPlanillaManager
     */
    @PostConstruct
    public void loadData()
    {
        try 
        {
            detpla = new RhDetallePlanilla();
            
            dpc = new DetPlaController();
            
            detplaList = dpc.findAll(detpla);
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(DetPlanillaManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public RhDetallePlanilla getDetpla() {
        return detpla;
    }

    public void setDetpla(RhDetallePlanilla detpla) {
        this.detpla = detpla;
    }

    public DetPlaController getDpc() {
        return dpc;
    }

    public void setDpc(DetPlaController dpc) {
        this.dpc = dpc;
    }

    public List<RhDetallePlanilla> getDetplaList() {
        return detplaList;
    }

    public void setDetplaList(List<RhDetallePlanilla> detplaList) {
        this.detplaList = detplaList;
    }

    public List<RhDetallePlanilla> getDetByIdList() {
        return detByIdList;
    }

    public void setDetByIdList(List<RhDetallePlanilla> detByIdList) {
        this.detByIdList = detByIdList;
    }
    
    public void getDetailById(int id)
    {
        try 
        {
            detByIdList = dpc.findById(id);
        } catch (Exception ex) {
            Logger.getLogger(DetPlanillaManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertDetails()
    {
        for(RhDetallePlanilla det : detByIdList)
        {
            detpla = det;
            createDetPla();
        }
        simpleAlert("success", "Creado", "La planilla se ha creado satisfactoriamente.");
    }
    
    
    public void createDetPla()
    {
        try 
        {
            detpla.setDetPlnId(0);
            System.out.println("Detalle: " + detpla);
            dpc.create(detpla);
            updateColumns();
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(DetPlanillaManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateDetPla()
    {
        try 
        {
            System.out.println("Detalle: " + detpla);
            dpc.edit(detpla);
            simpleAlert("success", "Actualizado", "El registro se ha actualizado satisfactoriamente.");
            updateColumns();
        } 
        catch (Exception ex) 
        {
            simpleAlert("info", "No actualizado", ex.getMessage());
            Logger.getLogger(DetPlanillaManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteDetPla()
    {
        try 
        {
            System.out.println("Detalle: " + detpla);
            dpc.delete(detpla);
            simpleAlert("success", "Eliminado", "El registro se ha eliminado satisfactoriamente.");
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
    
}
