/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rrhh.controller;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Mauricio
 */
public abstract class AbstractController <T>
{
    public Class<T> entityClass;
    
    public void create(T entity) throws Exception
    {
        EntityManager em = getEntityManager();
        try
        {
            em.persist(entity);
        }
        catch(Exception e)
        {
            throw new Exception(e);
        }
        finally
        {
            if (em.isOpen()) 
            {
                em.close();
            }
        }
    }
    
    public void edit(T entity) throws Exception
    {
        EntityManager em = getEntityManager();
        try
        {
            em.merge(entity);
        }
        catch(Exception e)
        {
            throw new Exception(e);
        }
        finally
        {
            if (em.isOpen()) 
            {
                em.close();
            }
        }
    }
    
    public void destroy(T entity) throws Exception
    {
        EntityManager em = getEntityManager();
        try
        {
            em.remove(em.merge(entity));
        }
        catch(Exception e)
        {
            throw new Exception(e);
        }
        finally
        {
            if (em.isOpen()) 
            {
                em.close();
            }
        }
    }
    
    public boolean find(T entity,Object id) throws Exception
    {
        EntityManager em = getEntityManager();
        
        try
        {
            em.find(entity.getClass(), id);
           
            return true;
        }
        catch(Exception e)
        {
            throw new Exception(e);
        }
        finally
        {
            if (em.isOpen()) 
            {
                em.close();
            }
        }
    }
    
    public List<T> findAll(T entity) throws Exception
    {
        
        EntityManager em = getEntityManager();
        
        
        List<T> entities = new ArrayList<>();
        
        try
        {
            String jpql = "SELECT o FROM " + entity.getClass().getSimpleName() + " o";
            Query q = em.createQuery(jpql);
            entities = q.getResultList();
        }
        catch(Exception e)
        {
            throw new Exception(e);
        }
        finally
        {
            if (em.isOpen()) 
            {
                em.close();
            }
        }
        return entities;
    }
    
    
    
    protected abstract EntityManager getEntityManager();
}
