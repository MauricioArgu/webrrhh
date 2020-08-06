/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rrhh.converter;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import jpa.controller.EmpController;
import jpa.entity.RhEmpleado;

/**
 *
 * @author gusst
 */
@FacesConverter(forClass = RhEmpleado.class)
public class EmpleadoConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        EmpController controller = new EmpController();
        try {
            return controller.find(new RhEmpleado(), Integer.parseInt(value));
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((RhEmpleado)value).getEmpId().toString();
    }
    
}
