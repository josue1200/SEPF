<%-- 
    Document   : Marcas
    Created on : 9/10/2020, 02:36:21 PM
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
<%@page import="Modelo.Marca"%>
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
        <h1 style="text-align: center;">CRUD De Marcas</h1>
        <div class="container">
        <form action="sr_marcas" method="POST" class="form-group">
            <a href="Primera.jsp" class="btn btn-info" role="button">Regresar</a><br><br>
      <button name="boton_agregar" id="boton_agregar" value="agregar" class="btn btn-success">Agregar</button>
  <button name="boton_Modificar" id="boton_Modificar" value="modificar" class="btn btn-primary">Modificar</button>
  <button name="boton_Eliminar" id="boton_Eliminar" value="eliminar" class="btn btn-danger" onclick="javascript:if(!confirm('¿Desea Eliminar?'))return false">Eliminar</button>
  <br>
    <label for="text">ID:</label>
    <input type="text" class="form-control" placeholder="ID" id="txt_id" name="txt_id" value="0" readonly>

    <label for="text">Marca:</label>
    <input type="text" class="form-control" placeholder="Escriba la marca" id="txt_Marca" name="txt_Marca" required>

  
</form>
        </div>
        <div>
 <table class="table table-hover">
    <thead>
      <tr>
        <th>Marcas</th>
      </tr>
    </thead>
    <tbody id="tblMarcas">
        <%
            Marca marca=new Marca();
            DefaultTableModel tablaMarca=new DefaultTableModel();
            tablaMarca=marca.TablaDeMarcas();
            for (int i = 0; i < tablaMarca.getRowCount(); i++) {
                         out.println("<tr data-idm="+tablaMarca.getValueAt(i, 0)+">"); 
                         out.println("<td>"+tablaMarca.getValueAt(i,1)+"</td>");
                      out.println("</tr>");
                }

        

              %>
    </tbody>
  </table>
        </div>
    <script type="text/javascript">
        $('#tblMarcas').on('click','tr td',function(e){
            var target,id,marca;
            target=$(event.target);
            id=target.parent().data('idm');
            marca=target.parent("tr").find("td").eq(0).html();
            $("#txt_id").val(id);
            $("#txt_Marca").val(marca);
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