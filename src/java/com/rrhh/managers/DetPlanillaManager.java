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
import jpa.controller.DetPlaController;
import jpa.entity.RhDetallePlanilla;

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
    
    
    
    
}
