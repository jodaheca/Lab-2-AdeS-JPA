/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udea.logica;

import java.util.List;
import javax.ejb.Local;
import org.udea.modelo.Factura;

/**
 *
 * @author Joaquin David
 */
@Local
public interface FacturaFacadeLocal {

    void create(Factura factura);

    void edit(Factura factura);

    void remove(Factura factura);

    Factura find(Object id);

    List<Factura> findAll();

    List<Factura> findRange(int[] range);

    int count();

    //Factura getUltimaFactura();

    int gedIdUltimaFactura();

}
