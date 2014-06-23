/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udea.logica;

import java.util.Collection;
import java.util.List;
import javax.ejb.Local;
import org.udea.modelo.Fruits;

/**
 *
 * @author joaquind.hernandez
 */
@Local
public interface FruitsFacadeLocal {

    void create(Fruits fruits);

    void edit(Fruits fruits);

    void remove(Fruits fruits);

    Fruits find(Object id);

    List<Fruits> findAll();

    List<Fruits> findRange(int[] range);

    int count();

    public Collection getFruitsList();

    public Fruits findByName(String name); 

    void updateCantidadFruits(int id, int cantidad);
}
