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
import org.udea.modelo.Factura;
import org.udea.modelo.Fruits;

/**
 *
 * @author Joaquin David
 */
@Stateless
public class FacturaFacade extends AbstractFacade<Factura> implements FacturaFacadeLocal {
    @PersistenceContext(unitName = "Laboratorio2AdeS-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
 
    @Override
     @Transactional
    public void create(Factura entity) {
        em.persist(entity);
    }

    public FacturaFacade() {
        super(Factura.class);
    }

//    @Override
//    public int getUltimaFactura() {
//        Query q=em.createNativeQuery("SELECT id FROM factura ORDER BY id DESC LIMIT 1");
//        System.out.println(q.getSingleResult().toString());
////        Query r= em.createNamedQuery("Factura.findById");
////        int id=(int)q.getSingleResult();
////        r.setParameter("id",id);
//        //return (Factura)r.getSingleResult();
//        return null;
//    }
    @Override
    public int gedIdUltimaFactura() {
         Query q=em.createNativeQuery("SELECT id FROM factura ORDER BY id DESC LIMIT 1");
        return (int)q.getSingleResult();
    }


    
    
}
