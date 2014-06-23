/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udea.logica;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.udea.modelo.Cliente;
import org.udea.modelo.Fruits;

/**
 *
 * @author Joaquin David
 */
@Stateless
public class ClienteFacade extends AbstractFacade<Cliente> implements ClienteFacadeLocal {
    @PersistenceContext(unitName = "Laboratorio2AdeS-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClienteFacade() {
        super(Cliente.class);
    }

    @Override
    @Transactional 
    public void create(Cliente entity) {
    //  em.getTransaction().begin();
      em.persist(entity);
    //  em.getTransaction().commit();   
    }

    @Override
    public Cliente find(Object id) {
       try{ 
        Query q=em.createNamedQuery("Cliente.findByCedula");
         int p=(int)id;
        q.setParameter("cedula",p);
        return (Cliente)q.getSingleResult();
       }catch(Exception e){
           return null;
       }
    }
       
    
}
