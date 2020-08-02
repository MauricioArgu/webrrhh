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
import jpa.controller.PlaController;
import jpa.entity.RhPlanilla;

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
    /**
     * Creates a new instance of PlanillaManager
     */
    
    @PostConstruct
    public void loadData()
    {
        try 
        {
            pla = new RhPlanilla();
            pc = new PlaController();
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
    
    
    
    
}
