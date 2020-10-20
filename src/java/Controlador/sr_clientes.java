/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Cliente;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Miguel Rivera
 */
public class sr_clientes extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet sr_clientes</title>");            
            out.println("</head>");
            out.println("<body>");
           /* out.println("<h1>id:"+Integer.parseInt(request.getParameter("txt_id"))+"</h1>");
            out.println("<h1>gen:"+Integer.parseInt(request.getParameter("ListaGen"))+"</h1>");
            out.println("<h1>nom:"+request.getParameter("txt_nombres")+"</h1>");
            out.println("<h1>ap:"+request.getParameter("txt_apellidos")+"</h1>");
            out.println("<h1>ni:"+request.getParameter("txt_NIT")+"</h1>");
            out.println("<h1>te:"+request.getParameter("txt_telefono")+"</h1>");
            out.println("<h1>gen:"+request.getParameter("txt_correo_electronico")+"</h1>");
            out.println("<h1>gen:"+request.getParameter("txt_fecha_ingreso")+"</h1>");
            */
          Cliente cliente=new Cliente(Integer.parseInt(request.getParameter("txt_id")),Integer.parseInt(request.getParameter("ListaGen")),
            request.getParameter("txt_nombres"),request.getParameter("txt_apellidos"),request.getParameter("txt_NIT"),
            request.getParameter("txt_telefono"),request.getParameter("txt_correo_electronico"),request.getParameter("txt_fecha_ingreso")); 
             if("agregar".equals(request.getParameter("boton_agregar"))){
                if(cliente.AgregarCliente()>0){
                    response.sendRedirect("Clientes.jsp");
                }else{
                    out.println("<h1>PTM Error</h1>");
                }
            }
            if("modificar".equals(request.getParameter("boton_Modificar"))){
                if(cliente.ModificarCliente()>0){
                    response.sendRedirect("Clientes.jsp");
                }
                else{
                    out.println("<h1>PTM Error</h1>");
                }
            }
            if("eliminar".equals(request.getParameter("boton_Eliminar"))){
                if(cliente.EliminarCliente()>0){
                    response.sendRedirect("Clientes.jsp");
                }
                else{
                    out.println("<h1>PTM Error</h1>");
                }
            }
            out.println("</body>");
            out.println("</html>");
        }
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
