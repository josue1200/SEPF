<%-- 
    Document   : Clientes
    Created on : 10/10/2020, 01:30:45 PM
    Author     : Miguel Rivera
--%>
<%@page import="java.util.List"%>
<%
      HttpSession actual =request.getSession(true);
      String usuario = (String) actual.getAttribute("Dentro");
      List<List<String>>meniu=(List<List<String>>) actual.getAttribute("menu");
     if(actual.getAttribute("Dentro")!=null){
        %>
<%@page import="Modelo.Cliente"%>
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
                 <%
  for( int i=0;i<meniu.get(0).size();i++){
      out.println("<a href='"+meniu.get(0).get(i)+"' class='btn btn-info'>"+ meniu.get(1).get(i)+"</a>");
  }
 %>
          <form action="sr_inicio" method="post">
            <h6 class="text-muted"><input type="submit" value="Cerrar Sesion" class="btn btn-dark" id="cerrarsesion" name="cerrarsesion"/></h6>
        </form>
        <h1 style="text-align: center;">CRUD De Clientes</h1>
        <div class="container">
        <form action="sr_clientes" method="POST" class="form-group">
            <a href="Primera.jsp" class="btn btn-info" role="button">Regresar</a><br><br>
      <button name="boton_agregar" id="boton_agregar" value="agregar" class="btn btn-success">Agregar</button>
  <button name="boton_Modificar" id="boton_Modificar" value="modificar" class="btn btn-primary">Modificar</button>
  <button name="boton_Eliminar" id="boton_Eliminar" value="eliminar" class="btn btn-danger" onclick="javascript:if(!confirm('¿Desea Eliminar?'))return false">Eliminar</button>
  <br>
    <label for="text">ID:</label>
    <input type="text" class="form-control" placeholder="ID" id="txt_id" name="txt_id" value="0" readonly>

    <label for="text">Nombres:</label>
    <input type="text" class="form-control" placeholder="Escriba los nombres" id="txt_nombres" name="txt_nombres" required>

    <label for="text">Apellidos:</label>
    <input type="text" class="form-control" placeholder="Escriba los apellidos" id="txt_apellidos" name="txt_apellidos" required>
    
    <label for="text">NIT</label>
    <input type="text" class="form-control" placeholder="Escriba el NIT" id="txt_NIT" name="txt_NIT" required>
    <label for="text">Genero</label>
    <select name="ListaGen" id="ListaGen">
        <option value="0">Elija</option>
        <option value="1">Hombre</option>
        <option value="2">Mujer</option>
    </select>
    <br>
    <label for="text">Telefono:</label>
    <input type="text" class="form-control" placeholder="Escriba el Telefono" id="txt_telefono" name="txt_telefono" required>
    
    <label for="text">Correo Electronico:</label>
    <input type="text" class="form-control" placeholder="Escriba el Correo electronico" id="txt_correo_electronico" name="txt_correo_electronico" required>
     
    <label for="text">Fecha De Ingreso:</label>
    <input type="text" class="form-control" placeholder="Escriba la Fecha De Ingreso" id="txt_fecha_ingreso" name="txt_fecha_ingreso" required>
    
  
</form>
        </div>
        <div>
 <table class="table table-hover">
    <thead>
      <tr>
        <th>Nombres</th>
        <th>Apellidos</th>
        <th>NIT</th>
        <th>Genero</th>
        <th>Telefono</th>
        <th>Correo Electronico</th>
        <th>Fecha de ingreso</th>
      </tr>
    </thead>
    <tbody id="tblClientes">
        <%
            Cliente cliente=new Cliente();
            DefaultTableModel tablacliente=new DefaultTableModel();
            tablacliente=cliente.TablaDeCliente();
            for (int i = 0; i < tablacliente.getRowCount(); i++) {
                         out.println("<tr data-idc="+tablacliente.getValueAt(i, 0)+">"); 
                         out.println("<td>"+tablacliente.getValueAt(i,1)+"</td>");
                         out.println("<td>"+tablacliente.getValueAt(i,2)+"</td>");
                         out.println("<td>"+tablacliente.getValueAt(i,3)+"</td>");
                         out.println("<td>"+tablacliente.getValueAt(i,4)+"</td>");
                         out.println("<td>"+tablacliente.getValueAt(i,5)+"</td>");
                         out.println("<td>"+tablacliente.getValueAt(i,6)+"</td>");
                         out.println("<td>"+tablacliente.getValueAt(i,7)+"</td>");
                      out.println("</tr>");
                }

        

              %>
    </tbody>
  </table>
        </div>
    <script type="text/javascript">
        $('#tblClientes').on('click','tr td',function(e){
            var target,id,nombres,Nit,Apellidos,genero,Fecha,Correo,Telefono;
            target=$(event.target);
            id=target.parent().data('idc');
            nombres=target.parent("tr").find("td").eq(0).html();
            Apellidos=target.parent("tr").find("td").eq(1).html();
            Nit=target.parent("tr").find("td").eq(2).html();
            genero=target.parent("tr").find("td").eq(3).html();
            Telefono=target.parent("tr").find("td").eq(4).html();
            Correo=target.parent("tr").find("td").eq(5).html();
            Fecha=target.parent("tr").find("td").eq(6).html();
            $("#txt_id").val(id);
            $("#txt_nombres").val(nombres);
            $("#txt_apellidos").val(Apellidos);
            $("#txt_NIT").val(Nit);
            $("#ListaGen").val(genero);
            $("#txt_correo_electronico").val(Correo);
            $("#txt_fecha_ingreso").val(Fecha);
            $("#txt_telefono").val(Telefono);
             $('#txt_fecha_ingreso').attr("readonly", false);
        });
        </script>
         <script type="text/javascript">
             $(document).ready(function () {
                 $('#txt_fecha_ingreso').attr("readonly", true);
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

