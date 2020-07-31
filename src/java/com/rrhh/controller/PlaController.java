/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rrhh.controller;

import com.rrhh.connection.ConnectionFactory;
import com.rrhh.entity.RhPlanilla;
import javax.persistence.EntityManager;

/**
 *
 * @author Mauricio Argumedo
 */
public class PlaController extends AbstractController<RhPlanilla>
{
    @Override
    protected EntityManager getEntityManager() {
        return ConnectionFactory.getInstance().getEntityManagerFactory().createEntityManager();
    }
}
