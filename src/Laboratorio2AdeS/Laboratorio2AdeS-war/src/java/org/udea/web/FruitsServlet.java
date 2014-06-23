

package org.udea.web;

import com.udea.logica.FruitsFacadeLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.udea.modelo.Compra;

/**
 *
 * @author Joaquin David
 */
public class FruitsServlet extends HttpServlet {
    @EJB
    private FruitsFacadeLocal fruitsFacade;

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        List frutas=(List)fruitsFacade.getFruitsList(); 
        List compra=new ArrayList();
         request.setAttribute("Frutas", frutas);
          request.setAttribute("paraComprar", compra);
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
