<%-- 
    Document   : Nuevousad
    Created on : 18/10/2020, 06:22:14 PM
    Author     : Miguel Rivera
--%>
<%@page import="Modelo.usuario"%>
<%@page import="java.util.List"%>
<%
      HttpSession actual =request.getSession(true);
      String usuario = (String) actual.getAttribute("Dentro");
      String jerarquia = (String) actual.getAttribute("j");
             List<List<String>>meniu=(List<List<String>>) actual.getAttribute("menu");
      if((actual.getAttribute("Dentro")!=null)&&(jerarquia.equals("administrador"))){
        %>
<%@page import="Modelo.Empleado"%>
<%@page import="java.util.HashMap"%>
<%@page import="Modelo.Puesto"%>
<%@page import="javax.swing.table.DefaultTableModel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
        <title>JSP Page</title>
    </head>
    <body>
          <form action="sr_inicio" method="post">
            <h6 class="text-muted"><input type="submit" value="Cerrar Sesion" class="btn btn-dark" id="cerrarsesion" name="cerrarsesion"/></h6>
        </form>
                 <%
  for( int i=0;i<meniu.get(0).size();i++){
      out.println("<a href='"+meniu.get(0).get(i)+"' class='btn btn-info'>"+ meniu.get(1).get(i)+"</a>");
  }
 %>
        <h1 style="text-align: center;">CRUD De Empleados</h1>
        <div class="container">
        <form action="sr_nuevo" method="POST" class="form-group">
   
      <button name="boton_agregar" id="boton_agregar" value="agregar" class="btn btn-success">Agregar</button>
  <button name="boton_Modificar" id="boton_Modificar" value="modificar" class="btn btn-primary">Modificar</button>
  <button name="boton_Eliminar" id="boton_Eliminar" value="eliminar" class="btn btn-danger" onclick="javascript:if(!confirm('¿Desea Eliminar?'))return false">Eliminar</button>
  <br>
  <label for="text">ID</label>
    <input type="text" class="form-control" placeholder="ID" id="txt_ID" name="txt_ID" value="0" readonly>
  
    <label for="text">Usuario</label>
    <input type="text" class="form-control" placeholder="user" id="txt_usuario" name="txt_usuario"  required>

    <label for="text">Nombres:</label>
    <input type="text" class="form-control" placeholder="Escriba los nombres" id="txt_nombres" name="txt_nombres" required>

    <label for="text">Apellidos:</label>
    <input type="text" class="form-control" placeholder="Escriba los apellidos" id="txt_apellidos" name="txt_apellidos" required>
    
 
    <label for="text">Correo</label>
    <input type="email" class="form-control" placeholder="Escriba el Correo" id="txt_correo" name="txt_correo" required>
    
    <label for="text">Contraseña</label>
    <input type="text" class="form-control" placeholder="Escriba la contraseña" id="txt_contra" name="txt_contra" required>
    
    <label for="text">Tipo</label>
    <select name="Listatipe" id="Listatipe">
        <option value="0">Elija</option>
        <option value="administrador">Admin</option>
        <option value="usuario">User</option>
    </select>
    <br>



  
</form>
        </div>
        <div>
 <table class="table table-hover">
    <thead>
      <tr>
          <th>Usuario</th>
        <th>Nombre</th>
        <th>Apellidos</th>
        <th>Correo</th>
        
        <th>Contraseña</th>
        <th>Tipo</th>
        
      </tr>
    </thead>
    <tbody id="tbl_usuarios">
        <%
            usuario use=new usuario();
            DefaultTableModel tablausers=new DefaultTableModel();
            tablausers=use.TablaDeusers();
            for (int i = 0; i < tablausers.getRowCount(); i++) {
                         out.println("<tr data-ide="+tablausers.getValueAt(i, 0)+">"); 
                         out.println("<td>"+tablausers.getValueAt(i,1)+"</td>");
                         out.println("<td>"+tablausers.getValueAt(i,2)+"</td>");
                         out.println("<td>"+tablausers.getValueAt(i,3)+"</td>");
                         out.println("<td>"+tablausers.getValueAt(i,4)+"</td>");
                         out.println("<td>"+tablausers.getValueAt(i,5)+"</td>");
                         out.println("<td>"+tablausers.getValueAt(i,6)+"</td>");
                        
                      out.println("</tr>");
                }

        

              %>
    </tbody>
  </table>
        </div>
    <script type="text/javascript">
        $('#tbl_usuarios').on('click','tr td',function(e){
            var target,ide,nombres,Apellidos,tipo,correo,user,contra;
            target=$(event.target);
            ide=target.parent().data('ide');
           
            user=target.parent("tr").find("td").eq(0).html();
            nombres=target.parent("tr").find("td").eq(1).html();
            Apellidos=target.parent("tr").find("td").eq(2).html();
            correo=target.parent("tr").find("td").eq(3).html();
            contra=target.parent("tr").find("td").eq(4).html();
            tipo=target.parent("tr").find("td").eq(5).html();
            
            $("#txt_ID").val(ide);
            $("#txt_usuario").val(user);
            $("#txt_nombres").val(nombres);
            $("#txt_apellidos").val(Apellidos);
            $("#txt_correo").val(correo);
            $("#txt_contra").val(contra);
            $("#Listatipe").val(tipo);
 
        });
        </script>
  
    </body>
</html>
<%
   }
else{

response.sendRedirect("index.jsp");

}
%>