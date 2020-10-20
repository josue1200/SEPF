<%-- 
    Document   : Productos
    Created on : 16/10/2020, 02:28:23 PM
    Author     : Miguel Rivera
--%>
<%@page import="java.util.List"%>
<%
      HttpSession actual =request.getSession(true);
      String usuario = (String) actual.getAttribute("Dentro");
             List<List<String>>meniu=(List<List<String>>) actual.getAttribute("menu");
      if(actual.getAttribute("Dentro")!=null){
        %>
<%@page import="java.util.HashMap"%>
<%@page import="Modelo.Marca"%>
<%@page import="javax.swing.table.DefaultTableModel"%>
<%@page import="Modelo.Productos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
<script type="text/javascript">    
    function guardarimagen(elemento){
     var file = elemento.files[0]; 
    var objHidden = document.Products.nombreimagen;
    objHidden.value = file.name;
};</script>
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
         <h1 style="text-align: center;">CRUD De Productos</h1>
        <div class="container">
        <form action="sr_productos" method="POST" class="form-group" name="Products" enctype="multipart/form-data" role="form">
            <a href="Primera.jsp" class="btn btn-info" role="button">Regresar</a><br><br>
      <button name="boton_agregar" id="boton_agregar" value="agregar" class="btn btn-success" type="submit">Agregar</button>
  <button name="boton_Modificar" id="boton_Modificar" value="modificar" class="btn btn-primary">Modificar</button>
  <button name="boton_Eliminar" id="boton_Eliminar" value="eliminar" class="btn btn-danger" onclick="javascript:if(!confirm('¿Desea Eliminar?'))return false">Eliminar</button>
  <br>
    <label for="text">ID:</label>
    <input type="text" class="form-control" placeholder="ID" id="txt_id" name="txt_id" value="0" readonly>

    <label for="text">Producto</label>
    <input type="text" class="form-control" placeholder="Escriba el Producto" id="txt_producto" name="txt_producto" required>
 <label for="text">Marca</label>
 <select id="listadom" name="listadom" class="form-control">
     <%
     Marca marca=new Marca();
     HashMap<String,String>Listado=marca.ListadoMarcas();
     for (String elem : Listado.keySet()) {
             out.println("<option value='"+elem+"'>"+Listado.get(elem)+"</option>");
         }
     %>
 </select><br>
  <label for="text">Descripcion</label>
    <input type="text" class="form-control" placeholder="Escriba la Descripcion" id="txt_descripcion" name="txt_descripcion" required>
      <label for="text">Imagen</label>
    <input type="file" class="form-control" id="txt_imagen" name="imagen" onchange="guardarimagen(this)" >
    <input type="hidden" name="nombreimagen" id="file" class="nombreimagen">
  
     <label for="text">Precio Costo</label>
    <input type="text" class="form-control" placeholder="Escriba el Precio Costo" id="txt_precioC" name="txt_precioC" required>
     <label for="text">Precio Venta</label>
    <input type="text" class="form-control" placeholder="Escriba el Precio Venta" id="txt_precioV" name="txt_precioV" required>
     <label for="text">Existencias</label>
    <input type="text" class="form-control" placeholder="Escriba las Existencias" id="txt_existencias" name="txt_existencias" required>
     <label for="text">Fecha de Ingreso</label>
    <input type="date" class="form-control" placeholder="Escriba la fecha de ingreso" id="txt_fi" name="txt_fi" >
    
</form>
        </div>
        <div>
 <table class="table table-bordered">
    <thead>
      <tr>
        <th>Productos</th>
        <th>Marca</th>
        <th>Descipcion</th>
        <th>Imagen</th>
        <th>Precio Costo</th>
        <th>Precio Venta</th>
        <th>Existencias</th>
        <th>Fecha de Ingreso</th>
      </tr>
    </thead>
    <tbody id="tblMarcas">
        <%
            Productos producto=new Productos();
            DefaultTableModel tablaProducto=new DefaultTableModel();
            tablaProducto=producto.TablaDeProductos();
            for (int i = 0; i < tablaProducto.getRowCount(); i++) {
                         out.println("<tr data-idp="+tablaProducto.getValueAt(i, 0)+" data-idm="+tablaProducto.getValueAt(i, 2)+" data-idimagen="+ tablaProducto.getValueAt(i, 5) +">"); 
                         out.println("<td>"+tablaProducto.getValueAt(i,1)+"</td>");
                         out.println("<td>"+tablaProducto.getValueAt(i,3)+"</td>");
                         out.println("<td>"+tablaProducto.getValueAt(i,4)+"</td>");
                         out.println("<td><img src='imagenes/"+tablaProducto.getValueAt(i,5)+"' style='max-width:50px; max-height:50px;'</img></td>");
                         out.println("<td>"+tablaProducto.getValueAt(i,6)+"</td>");
                         out.println("<td>"+tablaProducto.getValueAt(i,7)+"</td>");
                         out.println("<td>"+tablaProducto.getValueAt(i,8)+"</td>");
                         out.println("<td>"+tablaProducto.getValueAt(i,9)+"</td>");
                      out.println("</tr>");
                }

        

              %>
    </tbody>
  </table>
        </div>
 
    <script type="text/javascript">
        $('#tblMarcas').on('click','tr td',function(e){
            var target,id,producto,desc,img,pc,pv,e,f,m;
            target=$(event.target);
            id=target.parent().data('idp');
            m=target.parent().data('idm');
            producto=target.parent("tr").find("td").eq(0).html();
            desc=target.parent("tr").find("td").eq(2).html();
            img=target.parent().data('idimagen');
            pc=target.parent("tr").find("td").eq(4).html();
            pv=target.parent("tr").find("td").eq(5).html();
            e=target.parent("tr").find("td").eq(6).html();
            f=target.parent("tr").find("td").eq(7).html();
            
            $("#txt_id").val(id);
            $("#listadom").val(m);
            $("#txt_producto").val(producto);
            $("#txt_descripcion").val(desc);
            $(".nombreimagen").val(img);
            $("#txt_precioC").val(pc);
            $("#txt_precioV").val(pv);
            $("#txt_existencias").val(e);
            $("#txt_fi").val(f);
            
$('#txt_fi').attr("readonly", false);
        })
        </script>
        <script type="text/javascript">
             $(document).ready(function () {
                 $('#txt_fi').attr("readonly", true);
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