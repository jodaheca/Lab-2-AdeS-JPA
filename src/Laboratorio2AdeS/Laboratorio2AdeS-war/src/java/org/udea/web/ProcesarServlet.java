/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.udea.web;

import com.udea.logica.FruitsFacadeLocal;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.udea.modelo.Compra;
import org.udea.modelo.Fruits;

/**
 *
 * @author Joaquin David
 */
public class ProcesarServlet extends HttpServlet {
    @EJB
    private FruitsFacadeLocal fruitsFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
                HttpSession miSesion= request.getSession();
                List paraCompra =(List) miSesion.getAttribute("lista");
                String fruta= request.getParameter("fruta");
                String cantidad= request.getParameter("cantidad");
                int frut=Integer.parseInt(fruta);
                int cant=Integer.parseInt(cantidad);
                Compra x= new Compra();
                x.setCantidad(cant);
                x.setId(frut);
                Fruits fr=fruitsFacade.find(frut);
                double precio=fr.getPrice();
                double total= precio*cant;
                String nombre=fr.getName();
                x.setNombre(nombre);
                x.setTotal(total);
                paraCompra.add(x);
                List frutas=(List)fruitsFacade.getFruitsList();
                request.setAttribute("Frutas", frutas);
                request.setAttribute("paraComprar", paraCompra);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Facturar.jsp");
                    dispatcher.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
