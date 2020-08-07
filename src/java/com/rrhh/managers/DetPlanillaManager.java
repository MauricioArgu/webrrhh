/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rrhh.managers;

import java.io.Serializable;
import java.util.ArrayList;
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
import jpa.controller.EmpController;
import jpa.entity.RhDetallePlanilla;
import jpa.entity.RhEmpleado;
import jpa.entity.RhPlanilla;
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
    private EmpController ec;
    
    private List<RhDetallePlanilla> detplaList;
    
    private List<RhDetallePlanilla> detplainsert;
    
    private List<RhDetallePlanilla> detByIdList;
    
    private List<RhEmpleado> empActive;
    
    private RhPlanilla pla;
    
    
    /**
     * Creates a new instance of DetPlanillaManager
     */
    @PostConstruct
    public void loadData()
    {
        try 
        {
            
            detpla      = new RhDetallePlanilla();
            
            dpc         = new DetPlaController();
            
            ec          = new EmpController();
            
            detplaList  = new ArrayList<RhDetallePlanilla>();
            
            empActive   =  ec.findActive(new RhEmpleado());
            
            detplainsert= new ArrayList<RhDetallePlanilla>();
            
            System.out.println("Empeados activos" + empActive.size());
            
            configDetList();
            
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
    
    public RhPlanilla getPla() {
        return pla;
    }

    public void setPla(RhPlanilla pla) {
        this.pla = pla;
    }
    
    
    
    public void getDetailById(RhPlanilla obj)
    {
        try 
        {
            detByIdList = dpc.findById(obj);
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
    
    public void configDetList(){
        for(RhEmpleado emp : empActive){
            detpla = new RhDetallePlanilla();
            detpla.setEmpId(emp);
            detplaList.add(detpla);
        }
    }
    
    public void createPayroll(){
        
    }
    
    public void addDetailToArray(){
        System.out.println("--------------------------------------------------------------");
        System.out.println("Diurnas: " + detpla.getDetPlnCantidadHorasDiurnas() + ", nocturnas: " + detpla.getDetPlnCantidadHorasNocturnas() + 
                ", empleado: " + detpla.getEmpId().getEmpNombres());
        //-------Calculos de descuentos
        
        int diurnas = detpla.getDetPlnCantidadHorasDiurnas(), nocturnas = detpla.getDetPlnCantidadHorasNocturnas();//- Cantidad de horas extras
        Double sueldoI = detpla.getEmpId().getEmpSueldo();//-Sueldo exacto del empelado
        Double sueldo = 0.0;//-Sueldo neto = sueldoEmpleado + (bonoHorasDiurnas) + (BonoHorasNocturnas);
        Double bonoDiurnas = 0.0;//- Bono total por horas diurnas
        Double bonoNocturnas = 0.0;///- Bono total por horas nocturnas
        Double bonoTotal = 0.0;//-Bono total por horas extra
        Double sueldoPorHora = 0.0;//-Calculo de sueldo por hora
        //-Bonos por horas extras y sueldo para descuentos
        //-Sueldo por hora
        sueldoPorHora = (sueldoI / 30) / 8;
        //-Bono por hora extra diurna
        bonoDiurnas = diurnas * (sueldoPorHora * 2);
        //-Bono por hora extra nocturna
        bonoNocturnas = nocturnas * (sueldoPorHora * 2.25);
        //-Bono total por horas extras
        bonoTotal = bonoDiurnas + bonoNocturnas;
        //-sueldo para descuentos
        sueldo = sueldoI + bonoTotal;
        //-Variables para descuentos
        Double nSueldo = 0.0;//- Sueldo para renta
        Double afp = 0.0, isss = 0.0, renta = 0.0, cuota = 0.0;// descuentos
        Double tDescuentos = 0.0,total = 0.0;//- Total a pagar(Sueldo - descuentos) y total descuetnos
        //AFP 7.25%
        afp = sueldo * 0.0725;
        
        //ISSS 3% MÁXIMO $30 MENSUALES
        isss = sueldo * 0.03;
        if(isss>30.0){isss=30.0;}
        
        //- SUELDO TO RENTA
        nSueldo = sueldo - afp - isss;
        
        //----------- RENTA --------------
        //TRAMO 1 
        //- DE 0.01 A 472.0
        if(nSueldo>=0.01 && nSueldo<=472.0){
            cuota = 0.0;
            renta = 0.0;
        }
        
        //TRAMO 2
        //- DE 472.01 A 895.24
        if(nSueldo>=472.01 && nSueldo<=895.24){
            cuota = 17.67;
            renta =((sueldo - 472.0) * 0.1) + cuota;
        }
        
        //TRAMO 3
        //- DE 895.25 A 2038.10
        if(nSueldo>=895.25 && nSueldo<=2038.10){
            cuota = 60.00;
            renta =((sueldo - 895.24) * 0.2) + cuota;
        }
        
        //TRAMO 4
        //- DE 2038.11 ->
        if(nSueldo>=2038.11){
            cuota = 288.57;
            renta =((sueldo - 2038.10) * 0.3) + cuota;
        }
        
        //-Total de descuentos
        tDescuentos = renta + isss + afp;
        //-Total a pagar al empleado
        total = sueldo - tDescuentos;
        
        detplainsert.add(detpla);
        
        if(detplainsert.size() == empActive.size()){
            executeJSFunction("alertInserting()");
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
