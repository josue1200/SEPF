/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Empleado;
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
public class sr_empleados extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    Empleado empleados;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet sr_empleados</title>");            
            out.println("</head>");
            out.println("<body>");
            empleados=new Empleado(Integer.parseInt(request.getParameter("ListaPuesto")),Integer.parseInt(request.getParameter("txt_id")),Integer.parseInt(request.getParameter("ListaGen")),request.getParameter("txt_nombres"),request.getParameter("txt_apellidos"),request.getParameter("txt_fecha_ingreso"),request.getParameter("txt_direccion"),request.getParameter("txt_telefono"),request.getParameter("txt_fn"),request.getParameter("txt_DPI"),request.getParameter("txt_fil"));
            if("agregar".equals(request.getParameter("boton_agregar"))){
                if(empleados.AgregarEmpleado()>0){
                    response.sendRedirect("Empleados.jsp");
                }else{
                    out.println("<h1>PTM Error</h1>");
                }
            }
            if("modificar".equals(request.getParameter("boton_Modificar"))){
                if(empleados.ModificarEmpleado()>0){
                    response.sendRedirect("Empleados.jsp");
                }
                else{
                    out.println("<h1>PTM Error</h1>");
                }
            }
            if("eliminar".equals(request.getParameter("boton_Eliminar"))){
                if(empleados.EliminarEmpleado()>0){
                    response.sendRedirect("Empleados.jsp");
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
