/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Miguel Rivera
 */
public class Productos {
    private String producto,descripcion,imagen,fechaDeIngreso;
    private int id_producto,id_marca,existencia;
    private double precio_costo,precio_venta;
    private Conexion Con;
    public Productos() {
    }

    public Productos(String producto, String descripcion, String imagen, String fechaDeIngreso, int id_producto, int id_marca, int existencia, double precio_costo, double precio_venta) {
        this.producto = producto;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.fechaDeIngreso = fechaDeIngreso;
        this.id_producto = id_producto;
        this.id_marca = id_marca;
        this.existencia = existencia;
        this.precio_costo = precio_costo;
        this.precio_venta = precio_venta;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getFechaDeIngreso() {
        return fechaDeIngreso;
    }

    public void setFechaDeIngreso(String fechaDeIngreso) {
        this.fechaDeIngreso = fechaDeIngreso;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getId_marca() {
        return id_marca;
    }

    public void setId_marca(int id_marca) {
        this.id_marca = id_marca;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public double getPrecio_costo() {
        return precio_costo;
    }

    public void setPrecio_costo(double precio_costo) {
        this.precio_costo = precio_costo;
    }

    public double getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(double precio_venta) {
        this.precio_venta = precio_venta;
    }
    
    public DefaultTableModel TablaDeProductos(){
        DefaultTableModel tblMarcas=new DefaultTableModel();
        try {
            Con=new Conexion();
            Con.abrir_conexion();
            String query="select e.idProductos as id,e.Productos,i.idMarcas,i.Marca,e.Descripcion,e.Imagen,e.Precio_Costo,e.Precio_Venta,e.existencias,e.Fecha_Ingreso from productos as e inner join marcas as i on e.idMarca=i.idMarcas;";
            ResultSet consulta=Con.conexionbd.createStatement().executeQuery(query);
            String Header[]={"id","Productos","idMarca","Marca","Descripcion","Imagen","Precio Costo","Precio Venta","Existencias","Fecha De Ingreso"};
            tblMarcas.setColumnIdentifiers(Header);
            String data[]=new String[10];
            while (consulta.next()) {
               data[0]=consulta.getString("id");
               data[1]=consulta.getString("Productos"); 
               data[2]=consulta.getString("idMarcas");
               data[3]=consulta.getString("Marca");
               data[4]=consulta.getString("Descripcion");
               data[5]=consulta.getString("Imagen");
               data[6]=consulta.getString("Precio_Costo");
               data[7]=consulta.getString("Precio_Venta");
               data[8]=consulta.getString("existencias");
               data[9]=consulta.getString("Fecha_Ingreso");
               tblMarcas.addRow(data);
            }
            Con.cerrar_conexion();
        } catch (SQLException e) {
            System.out.println("Error >:V "+e.getMessage());
        }
        return tblMarcas;
    }
    public int AgregarProducto(){
        int resultado=0;
        try {
            Con=new Conexion();
            PreparedStatement parameter;
            String query="insert into productos(Productos,idMarca,Descripcion,Imagen,Precio_Costo,Precio_Venta,existencias,Fecha_Ingreso)values(?,?,?,?,?,?,?,now());";
            Con.abrir_conexion();
            parameter=Con.conexionbd.prepareStatement(query);
            parameter.setString(1, getProducto());
            parameter.setInt(2,getId_marca());
            parameter.setString(3, getDescripcion());
            parameter.setString(4, getImagen());
            parameter.setDouble(5, getPrecio_costo());
            parameter.setDouble(6, getPrecio_venta());
            parameter.setInt(7, getExistencia());

            resultado=parameter.executeUpdate();
            Con.cerrar_conexion();
        } catch (SQLException e) {
            System.out.println("Error Prro >:V"+e.getMessage());
        }
        return resultado;
    }
     public int ModificarProducto(){
        int resultado=0;
        try {
            Con=new Conexion();
            PreparedStatement parameter;
            String query="update productos set Productos=?,idMarca=?,Descripcion=?,Imagen=?,Precio_Costo=?,Precio_Venta=?,existencias=?,Fecha_Ingreso=? where idProductos=?;";
            Con.abrir_conexion();
            parameter=Con.conexionbd.prepareStatement(query);
            parameter.setString(1, getProducto());
            parameter.setInt(2,getId_marca());
            parameter.setString(3, getDescripcion());
            parameter.setString(4, getImagen());
            parameter.setDouble(5, getPrecio_costo());
            parameter.setDouble(6, getPrecio_venta());
            parameter.setInt(7, getExistencia());
            parameter.setString(8, getFechaDeIngreso());
            parameter.setInt(9,getId_producto());
            resultado=parameter.executeUpdate();
            Con.cerrar_conexion();
        } catch (SQLException e) {
            System.out.println("Error Prro >:V"+e.getMessage());
        }
        return resultado;
    }
          public int EliminaProducto(){
        int resultado=0;
        try {
            Con=new Conexion();
            PreparedStatement parameter;
            String query="delete from productos where idProductos=?;";
            Con.abrir_conexion();
            parameter=Con.conexionbd.prepareStatement(query);
            parameter.setInt(1,getId_producto());
            resultado=parameter.executeUpdate();
            Con.cerrar_conexion();
        } catch (SQLException e) {
            System.out.println("Error Prro >:V"+e.getMessage());
        }
        return resultado;
    }
}
