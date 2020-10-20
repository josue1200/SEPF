/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Proveedores;
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
public class sr_proveedores extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    Proveedores proveedor;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet sr_proveedores</title>");            
            out.println("</head>");
            out.println("<body>");
            proveedor=new Proveedores(Integer.parseInt(request.getParameter("txt_id")),request.getParameter("txt_Proveedor"),request.getParameter("txt_NIT"),request.getParameter("txt_Direccion"),request.getParameter("txt_Telefono")); 
             if("agregar".equals(request.getParameter("boton_agregar"))){
                if(proveedor.AgregarProveedor()>0){
                    response.sendRedirect("Proveedores.jsp");
                }else{
                    out.println("<h1>PTM Error</h1>");
                }
            }
            if("modificar".equals(request.getParameter("boton_Modificar"))){
                if(proveedor.ModificarProveedor()>0){
                    response.sendRedirect("Proveedores.jsp");
                }
                else{
                    out.println("<h1>PTM Error</h1>");
                }
            }
            if("eliminar".equals(request.getParameter("boton_Eliminar"))){
                if(proveedor.EliminarProveedor()>0){
                    response.sendRedirect("Proveedores.jsp");
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
