<%-- 
    Document   : Empleados
    Created on : 11/10/2020, 06:20:08 PM
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
        <form action="sr_empleados" method="POST" class="form-group">
            <a href="Primera.jsp" class="btn btn-info" role="button">Regresar</a>
            <a href="Puestos.jsp" class="btn btn-info" role="button">Puestos</a><br><br>
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
    
    <label for="text">Direccion</label>
    <input type="text" class="form-control" placeholder="Escriba el Direccion" id="txt_direccion" name="txt_direccion" required>
   
    <label for="text">Telefono</label>
    <input type="text" class="form-control" placeholder="Escriba el Telefono" id="txt_telefono" name="txt_telefono" required>
    
    <label for="text">DPI</label>
    <input type="text" class="form-control" placeholder="Escriba el DPI" id="txt_DPI" name="txt_DPI" required>
    
    <label for="text">Genero</label>
    <select name="ListaGen" id="ListaGen">
        <option value="0">Elija</option>
        <option value="1">Hombre</option>
        <option value="2">Mujer</option>
    </select>
    <br>
    <label for="text">Fecha de Nacimiento</label>
    <input type="DATE" class="form-control" id="txt_fn" name="txt_fn" required>
    <br>
        <label for="text">Puesto</label>
    <select name="ListaPuesto" id="ListaPuesto">
        <%
        out.println("<option value='0'>Elija</option>");
        Puesto puesto=new Puesto();
        HashMap<String,String>ListaP=puesto.ListaPuesto();
        for (String i:ListaP.keySet()) {
            out.println("<option value='"+i+"'>"+ListaP.get(i)+"</option>");
                
            }
        %>
    </select>
    
    <label for="text">Fecha Inicio de labores</label>
    <input type="DATETIME" class="form-control" placeholder="Escriba la Fecha Inicio de labores" id="txt_fil" name="txt_fil" required>
     
    <label for="text">Fecha De Ingreso:</label>
    <input type="datetime" class="form-control" placeholder="Escriba la Fecha De Ingreso" id="txt_fecha_ingreso" name="txt_fecha_ingreso" required>
    
  
</form>
        </div>
        <div>
 <table class="table table-hover">
    <thead>
      <tr>
        <th>Nombres</th>
        <th>Apellidos</th>
        <th>Direccion</th>
        <th>Telefono</th>
        <th>DPI</th>
        <th>Genero</th>
        <th>Fecha de Nacimiento</th>
        <th>Puesto</th>
        <th>Fecha incio laborales</th>
        <th>Fecha de ingreso</th>
      </tr>
    </thead>
    <tbody id="tblEmpleados">
        <%
            Empleado empleado=new Empleado();
            DefaultTableModel tablaempleado=new DefaultTableModel();
            tablaempleado=empleado.TablaDeCliente();
            for (int i = 0; i < tablaempleado.getRowCount(); i++) {
                         out.println("<tr data-ide="+tablaempleado.getValueAt(i, 0)+" data-idp="+tablaempleado.getValueAt(i, 8)+">"); 
                         out.println("<td>"+tablaempleado.getValueAt(i,1)+"</td>");
                         out.println("<td>"+tablaempleado.getValueAt(i,2)+"</td>");
                         out.println("<td>"+tablaempleado.getValueAt(i,3)+"</td>");
                         out.println("<td>"+tablaempleado.getValueAt(i,4)+"</td>");
                         out.println("<td>"+tablaempleado.getValueAt(i,5)+"</td>");
                         out.println("<td>"+tablaempleado.getValueAt(i,6)+"</td>");
                         out.println("<td>"+tablaempleado.getValueAt(i,7)+"</td>");
                         out.println("<td>"+tablaempleado.getValueAt(i,9)+"</td>");
                         out.println("<td>"+tablaempleado.getValueAt(i,10)+"</td>");
                         out.println("<td>"+tablaempleado.getValueAt(i,11)+"</td>");
                      out.println("</tr>");
                }

        

              %>
    </tbody>
  </table>
        </div>
    <script type="text/javascript">
        $('#tblEmpleados').on('click','tr td',function(e){
            var target,ide,nombres,Dpi,Direccion,Apellidos,genero,Fechaingreso,fechainicio,idp,Telefono,fechanac;
            target=$(event.target);
            ide=target.parent().data('ide');
            idp=target.parent().data('idp');
            nombres=target.parent("tr").find("td").eq(0).html();
            Apellidos=target.parent("tr").find("td").eq(1).html();
            Direccion=target.parent("tr").find("td").eq(2).html();
            Telefono=target.parent("tr").find("td").eq(3).html();
            Dpi=target.parent("tr").find("td").eq(4).html();
            genero=target.parent("tr").find("td").eq(5).html();
            fechainicio=target.parent("tr").find("td").eq(8).html();
            fechanac=target.parent("tr").find("td").eq(6).html();
            Fechaingreso=target.parent("tr").find("td").eq(9).html();
            $("#txt_id").val(ide);
            $("#ListaPuesto").val(idp);
            $("#txt_nombres").val(nombres);
            $("#txt_apellidos").val(Apellidos);
            $("#txt_direccion").val(Direccion);
            $("#txt_telefono").val(Telefono);
            $("#txt_DPI").val(Dpi);
            $("#ListaGen").val(genero);
            $("#txt_fn").val(fechanac);
            $("#txt_fil").val(fechainicio);
            $("#txt_fecha_ingreso").val(Fechaingreso);
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