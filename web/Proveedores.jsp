<%-- 
    Document   : Proveedores
    Created on : 9/10/2020, 03:36:55 PM
    Author     : Miguel Rivera
--%>
<%@page import="java.util.List"%>
<%
      HttpSession actual =request.getSession(true);
      String usuario = (String) actual.getAttribute("Dentro");
      String jerarquia = (String) actual.getAttribute("j");
             List<List<String>>meniu=(List<List<String>>) actual.getAttribute("menu");
     if((actual.getAttribute("Dentro")!=null)&&(jerarquia.equals("administrador"))){
        %>
<%@page import="javax.swing.table.DefaultTableModel"%>
<%@page import="Modelo.Proveedores"%>
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
        <h1 style="text-align: center;">CRUD De Proveedores</h1>
        <div class="container">
        <form action="sr_proveedores" method="POST" class="form-group">
            <a href="Primera.jsp" class="btn btn-info" role="button">Regresar</a><br><br>
      <button name="boton_agregar" id="boton_agregar" value="agregar" class="btn btn-success">Agregar</button>
  <button name="boton_Modificar" id="boton_Modificar" value="modificar" class="btn btn-primary">Modificar</button>
  <button name="boton_Eliminar" id="boton_Eliminar" value="eliminar" class="btn btn-danger" onclick="javascript:if(!confirm('¿Desea Eliminar?'))return false">Eliminar</button>
  <br>
    <label for="text">ID:</label>
    <input type="text" class="form-control" placeholder="ID" id="txt_id" name="txt_id" value="0" readonly>

    <label for="text">Proveedor:</label>
    <input type="text" class="form-control" placeholder="Escriba el proveedor" id="txt_Proveedor" name="txt_Proveedor" required>

    <label for="text">NIT</label>
    <input type="text" class="form-control" placeholder="Escriba el NIT" id="txt_NIT" name="txt_NIT" required>
    
    <label for="text">Direccion:</label>
    <input type="text" class="form-control" placeholder="Escriba la Direccion" id="txt_Direccion" name="txt_Direccion" required>
    
    <label for="text">Telefono:</label>
    <input type="text" class="form-control" placeholder="Escriba el Telefono" id="txt_Telefono" name="txt_Telefono" required>
    
  
</form>
        </div>
        <div>
 <table class="table table-hover">
    <thead>
      <tr>
        <th>Proveedor</th>
        <th>NIT</th>
        <th>Direccion</th>
        <th>Telefono</th>
      </tr>
    </thead>
    <tbody id="tblProveedores">
        <%
            Proveedores proveedor=new Proveedores();
            DefaultTableModel tablaproveedor=new DefaultTableModel();
            tablaproveedor=proveedor.TablaDeProveedores();
            for (int i = 0; i < tablaproveedor.getRowCount(); i++) {
                         out.println("<tr data-idm="+tablaproveedor.getValueAt(i, 0)+">"); 
                         out.println("<td>"+tablaproveedor.getValueAt(i,1)+"</td>");
                         out.println("<td>"+tablaproveedor.getValueAt(i,2)+"</td>");
                         out.println("<td>"+tablaproveedor.getValueAt(i,3)+"</td>");
                         out.println("<td>"+tablaproveedor.getValueAt(i,4)+"</td>");
                      out.println("</tr>");
                }

        

              %>
    </tbody>
  </table>
        </div>
    <script type="text/javascript">
        $('#tblProveedores').on('click','tr td',function(e){
            var target,id,Proveedor,Nit,Direccion,Telefono;
            target=$(event.target);
            id=target.parent().data('idm');
            Proveedor=target.parent("tr").find("td").eq(0).html();
            Nit=target.parent("tr").find("td").eq(1).html();
            Direccion=target.parent("tr").find("td").eq(2).html();
            Telefono=target.parent("tr").find("td").eq(3).html();
            $("#txt_id").val(id);
            $("#txt_Proveedor").val(Proveedor);
            $("#txt_NIT").val(Nit);
            $("#txt_Direccion").val(Direccion);
            $("#txt_Telefono").val(Telefono);
        })
        </script>
    </body>
</html>
<%
   }
else{

response.sendRedirect("Primera.jsp");

}
%>
