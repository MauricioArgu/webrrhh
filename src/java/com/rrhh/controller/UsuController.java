/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rrhh.controller;

import com.rrhh.connection.ConnectionFactory;
import com.rrhh.entity.RhUsuario;
import javax.persistence.EntityManager;

/**
 *
 * @author Mauricio Argumedo
 */
public class UsuController extends AbstractController<RhUsuario>
{

    @Override
    protected EntityManager getEntityManager() {
        return ConnectionFactory.getInstance().getEntityManagerFactory().createEntityManager();
    }
    
}
