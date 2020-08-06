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
import jpa.controller.DepController;
import jpa.entity.RhDepartamento;

/**
 *
 * @author gusst
 */
@FacesConverter("depConverter")
public class DepartamentConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        DepController controlador = new DepController();
        System.out.println("Convirtiendo a objeto : " + value);
        try {
            return controlador.find(new RhDepartamento(), Integer.parseInt(value));
        } catch (Exception ex) {
            Logger.getLogger(DepartamentConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        System.out.println("Convirtiendo a texto: " + ((RhDepartamento)value).getDepId().toString());
        return ((RhDepartamento)value).getDepId().toString();
    }
    
}
