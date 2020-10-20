/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Miguel Rivera
 */
public class sr_inicio extends HttpServlet {

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
            out.println("<title>Servlet sr_inicio</title>");            
            out.println("</head>");
            out.println("<body>");
            //out.println("<h1>User: "+usuario+" Pass: "+pass+"</h1>");
            usuario obj=new usuario();
            String usuario,pass;
            usuario=request.getParameter("usuario");
            pass=request.getParameter("pass");
            if ("ingresar".equals(request.getParameter("btn_ingresar"))) {
                if(obj.Encontrar(pass,usuario)>0) {
                    HttpSession actual = request.getSession(true);
                    String jerarquia=obj.tipe(usuario);
                    List<List<String>> Menu=obj.Menu(jerarquia);
                    actual.setAttribute("Dentro", request.getParameter("usuario"));
                    actual.setAttribute("menu", Menu);
                    actual.setAttribute("j", jerarquia);
                    response.sendRedirect("Primera.jsp");
                }
                else {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>USUARIO NO ENCONTRADO</title>");            
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>USUARIO NO ENCONTRADO</h1>");
                    out.println("</body>");
                    out.println("</html>");
                }
            }
            
            else if(request.getParameter("cerrarsesion") != null) {
                request.getSession().removeAttribute("Dentro");
                response.sendRedirect("index.jsp");
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
