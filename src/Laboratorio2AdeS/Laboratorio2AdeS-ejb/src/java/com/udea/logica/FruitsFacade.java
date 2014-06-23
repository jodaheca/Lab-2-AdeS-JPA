/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udea.logica;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.udea.modelo.Fruits;

/**
 *
 * @author joaquind.hernandez
 */
@Stateless
public class FruitsFacade extends AbstractFacade<Fruits> implements FruitsFacadeLocal {
    @PersistenceContext(unitName = "Laboratorio2AdeS-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FruitsFacade() {
        super(Fruits.class);
    }

    @Override
    public Fruits find(Object id) {
         Query q=em.createNamedQuery("Fruits.findById");
         int p=(int)id;
         String i=Integer.toString(p);
        q.setParameter("id",i);
        return (Fruits)q.getSingleResult();
    }
    
    @Override
    public Fruits findByName(String name){
        Query q=em.createNamedQuery("Fruits.findByName");
        q.setParameter("name",name);
        return (Fruits)q.getSingleResult();
    } 
    
    @Override
    public Collection getFruitsList(){
        List frutas=findAll();
        return frutas;
    }

    @Override
    public void edit(Fruits entity) {
        super.edit(entity);
    }

    @Override
    public void updateCantidadFruits(int id, int cantidad) {
        Fruits actual=find(id);
        actual.setQuantity(cantidad);
    }
    
}

