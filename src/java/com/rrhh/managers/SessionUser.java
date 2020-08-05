/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rrhh.managers;

import com.rrhh.entity.User;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Mauricio Argumedo
 */
@ManagedBean
@SessionScoped
public class SessionUser implements Serializable {

    /**
     * Creates a new instance of SessionUser
     */
    private User user;
    
    
    
}
