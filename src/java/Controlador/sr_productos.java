/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Productos;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Miguel Rivera
 */
@MultipartConfig
public class sr_productos extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    Productos producto;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet sr_productos</title>");            
            out.println("</head>");
            out.println("<body>");
            String h=request.getParameter("nombreimagen");
           /* out.println("<h2>n:"+h+"</h2>");
            out.println("<h2> existencias:"+Integer.parseInt(request.getParameter("txt_existencias"))+"</h2>");
            out.println("<h2>pc:"+Double.parseDouble(request.getParameter("txt_precioC"))+"</h2>");
            out.println("<h2>pv:"+Double.parseDouble(request.getParameter("txt_precioV"))+"</h2>");
            out.println("<h2>pr:"+request.getParameter("txt_producto")+"</h2>");
            out.println("<h2>des:"+request.getParameter("txt_descripcion")+"</h2>");
            out.println("<h2>id:"+Integer.parseInt(request.getParameter("txt_id"))+"</h2>");
            out.println("<h2>m:"+Integer.parseInt(request.getParameter("listadom"))+"</h2>");*/
           producto=new Productos(request.getParameter("txt_producto"),request.getParameter("txt_descripcion"),h,
                   request.getParameter("txt_fi"),Integer.parseInt(request.getParameter("txt_id")),
                   Integer.parseInt(request.getParameter("listadom")),Integer.parseInt(request.getParameter("txt_existencias")),
                   Double.parseDouble(request.getParameter("txt_precioC")),Double.parseDouble(request.getParameter("txt_precioV")));
             if("agregar".equals(request.getParameter("boton_agregar"))){
                if(producto.AgregarProducto()>0){
                            Part archivo = request.getPart("imagen");
                    InputStream is = archivo.getInputStream();
                    File x = new File("C:\\Users\\Miguel Rivera\\Documents\\GitHub\\Proyecto_Final\\web\\imagenes\\"+h);
                    FileOutputStream ous = new FileOutputStream(x);
                    
                    int dato = is.read();
                    while (dato != -1) {
                        ous.write(dato);
                        dato = is.read();
                    }
                    ous.close();
                    is.close();
                    response.sendRedirect("Productos.jsp");
                }else{
                    out.println("<h1>PTM Error</h1>");
                }
            }
             if("modificar".equals(request.getParameter("boton_Modificar"))){
                if(producto.ModificarProducto()>0){
                    response.sendRedirect("Productos.jsp");
                }
                else{
                    out.println("<h1>PTM Error</h1>");
                }
            }
            if("eliminar".equals(request.getParameter("boton_Eliminar"))){
                if(producto.EliminaProducto()>0){
                    response.sendRedirect("Productos.jsp");
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
