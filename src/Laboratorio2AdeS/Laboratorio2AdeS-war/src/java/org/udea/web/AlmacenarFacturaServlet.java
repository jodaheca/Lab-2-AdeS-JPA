/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.udea.web;

import com.udea.logica.ClienteFacadeLocal;
import com.udea.logica.FacturaFacadeLocal;
import com.udea.logica.FruitsFacadeLocal;
import com.udea.logica.VentaFacadeLocal;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.udea.modelo.Cliente;
import org.udea.modelo.Compra;
import org.udea.modelo.Factura;
import org.udea.modelo.Fruits;
import org.udea.modelo.Venta;

/**
 *
 * @author Joaquin David
 */
public class AlmacenarFacturaServlet extends HttpServlet {
    @EJB
    private FruitsFacadeLocal fruitsFacade;
    @EJB
    private VentaFacadeLocal ventaFacade;
    @EJB
    private FacturaFacadeLocal facturaFacade;
    @EJB
    private ClienteFacadeLocal clienteFacade;
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
          HttpSession miSesion= request.getSession();
                List paraCompra =(List) miSesion.getAttribute("lista");
                String cliente= request.getParameter("Cliente");
                int cedula= Integer.parseInt(request.getParameter("cedula"));
                String telefono=request.getParameter("telefono");
                Cliente nuevo= new Cliente();
                nuevo.setCedula(cedula);
                nuevo.setNombre(cliente);
                nuevo.setTelefono(telefono);
                Cliente existe= clienteFacade.find(cedula);
                if(existe==null){
                    clienteFacade.create(nuevo);
                }
                double total=0;
                for(int i=0;i<paraCompra.size();i++){
                    Compra actual=(Compra) paraCompra.get(i);
                    total=total+actual.getTotal();
                }
                Factura nFactura= new Factura();
                nFactura.setCliente(cedula);
                nFactura.setTotal(total);
                Date fecha= new Date();
                java.sql.Date sqlFecha = new java.sql.Date(fecha.getTime());
                nFactura.setFecha(sqlFecha);
                facturaFacade.create(nFactura);
                int id=facturaFacade.gedIdUltimaFactura();
                
                //System.out.println("El id de la ultima factura es " + id);
                for(int i=0;i<paraCompra.size();i++){
                 Compra nuevaCompra=(Compra) paraCompra.get(i);
                 Venta nuevaVenta= new Venta();
                 nuevaVenta.setCantidad(nuevaCompra.getCantidad());
                 nuevaVenta.setIdFactura(id);
                 nuevaVenta.setIdFruta(nuevaCompra.getId());
                 nuevaVenta.setValorPagado(nuevaCompra.getTotal());
                 System.out.println("Total Pagado"+ nuevaVenta.getValorPagado());
                 ventaFacade.create(nuevaVenta);
                 Fruits modificar= fruitsFacade.find(nuevaVenta.getIdFruta());
                 int cant=modificar.getQuantity();
                 cant=cant-nuevaVenta.getCantidad();
//                 modificar.setQuantity(cant);
                 if(cant>=0){
                     fruitsFacade.updateCantidadFruits(nuevaVenta.getIdFruta(),cant);
                 }
                }
                 List frutas=(List)fruitsFacade.getFruitsList();
                request.setAttribute("Frutas", frutas);
                paraCompra.clear();
                request.setAttribute("paraComprar", paraCompra);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Facturar.jsp");
                    dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
