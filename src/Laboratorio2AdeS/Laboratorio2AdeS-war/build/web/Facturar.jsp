 
<%@page import="org.udea.modelo.Compra"%>
<!--    Document   : Facturar
    Created on : 5/06/2014, 04:12:24 PM
    Author     : Joaquin David-->


<%@page import="org.udea.modelo.Fruits"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<% 
     
    List frutas=(List) request.getAttribute("Frutas");
    List paraComprar=(List) request.getAttribute("paraComprar");
   
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="shortcut icon" href="../../assets/ico/favicon.ico">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/tabla.css" type="text/css"><link>
        <script type="text/javascript" src="js/funcionesRequeridas.js"></script>
        <title>Factura</title>
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="css/logo-nav.css" rel="stylesheet" type="text/css" />
        <link href="../../dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="signin.css" rel="stylesheet">
    </head>
            <body>
        <center>             
                <h1>Informacion de Factura</h1><br><br>
                <form action="ProcesarServlet" method="Post" class="form-inline" role="form">
  <div class="form-group">
    <label class="sr-only" for="exampleInputEmail2">Email address</label>
    <select name="fruta" id="fruta" required="true" class="form-control">
                     <option value="">Seleccionar</option> 
                    <%int co2=0;
                         for(int j=0; j<frutas.size(); j++){
                           Fruits frut=(Fruits)frutas.get(j);
                           String co =frut.getId();
                           String name= frut.getName();
                         
                         %>
                        <option value="<%=co%>"> 
                           <%=name%>
                        </option>
                    <%
                          }
                    %>    
            </select>
    
                </div>
                <div class="form-group">
                  <label class="sr-only" for="exampleInputPassword2">Password</label>
                  <input type="number" name="cantidad" class="form-control" value="" required="true" placeholder="Cantidad">
                                   <% session.setAttribute("lista",paraComprar); %>
                </div>
                <button type="submit" class="btn btn-primary">Agregar Fruta</button>
              </form>
                <br>

                <form action="AlmacenarFactura" method="POST" class="form-inline" role="form">
                <div class="form-group">
                  <input type="text" name="Cliente"class="form-control" value="" id="prueba"required="true"  placeholder="Nombre Cliente">
                </div>
                <div class="form-group">
                    <input type="number" name="cedula" class="form-control" value="" required="true" placeholder="Cedula Cliente">
                </div>
                 <div class="form-group">
                     <input type="number" name="telefono" class="form-control" value="" required="true" placeholder="Telefono">
                </div>   
                <button type="submit" class="btn btn-primary">Guardar Factura</button>
              </form>
                <br>       
                <table width="80%" border="1" cellpadding="0" cellspacing="0" bordercolor="#000000">
                    <tr>
                    <th>Id </th>
                    <th>Nombre</th>
                    <th>Cantidad</th>
                    <th>Total</th>
                    </tr>    
                  <%
                    for(int m=0;m<paraComprar.size();m++){
                       Compra agg =(Compra) paraComprar.get(m);
                       int id=agg.getId();
                       String nombre=agg.getNombre();
                       int cantidad=agg.getCantidad();
                       double total=agg.getTotal();
                    %>
                    <tr>
                        <td><%=id%></td>
                        <td><%=nombre%></td>
                        <td><%=cantidad%></td>
                        <td><%=total%></td>
                    </tr>
                    <%   
                    }
                    %>  
                </table>
            <h1>Lista de Frutas disponibles</h1>
            
            <br><br>
                <table width="80%" border="1" cellpadding="0" cellspacing="0" bordercolor="#000000">
           <tr>
                    <th>Id </th>
                    <th>Nombre</th>
                    <th>Precio</th>
                    <th>Cantidad</th>
                    </tr>
                    <%
                    for(int i=0;i<frutas.size();i++){
                       Fruits agg =(Fruits) frutas.get(i);
                       int id=Integer.parseInt(agg.getId());
                       String nombre=agg.getName();
                       double precio=agg.getPrice();
                       int cantidad=agg.getQuantity();
                    %>
                    <tr>
                        <td><%=id%></td>
                        <td><%=nombre%></td>
                        <td><%=precio%></td>
                        <td><%=cantidad%></td>
                    </tr>
                    <%   
                    }
                    %>
                    
                    
                 </table>
        </center>
    </body>
</html>
