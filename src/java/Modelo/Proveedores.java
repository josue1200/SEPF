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
public class Proveedores {
    private int id_Proveedores;
    private String Proveedores,Nit,Direccion,Telefono;
    private Conexion Con;

    public Proveedores() {
    }

    public Proveedores(int id_Proveedores, String Proveedores,String Nit, String Direccion, String Telefono) {
        this.id_Proveedores = id_Proveedores;
        this.Proveedores = Proveedores;
        this.Nit=Nit;
        this.Direccion = Direccion;
        this.Telefono = Telefono;
    }

    public int getId_Proveedores() {
        return id_Proveedores;
    }

    public void setId_Proveedores(int id_Proveedores) {
        this.id_Proveedores = id_Proveedores;
    }

    public String getProveedores() {
        return Proveedores;
    }

    public void setProveedores(String Proveedores) {
        this.Proveedores = Proveedores;
    }
     public String getNit() {
        return Nit;
    }

    public void setNit(String Nit) {
        this.Nit = Nit;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }
      //Tabla
    public DefaultTableModel TablaDeProveedores(){
        DefaultTableModel tblProveedores=new DefaultTableModel();
        try {
            Con=new Conexion();
            Con.abrir_conexion();
            String query="select idProveedores as id,Proveedor,NIT,Direccion,Telefono from proveedores;";
            ResultSet consulta=Con.conexionbd.createStatement().executeQuery(query);
            String Header[]={"id","Proveedor","NIT","Direccion","Telefono"};
            tblProveedores.setColumnIdentifiers(Header);
            String data[]=new String[5];
            while (consulta.next()) {
               data[0]=consulta.getString("id");
               data[1]=consulta.getString("Proveedor"); 
               data[2]=consulta.getString("NIT");
               data[3]=consulta.getString("Direccion"); 
               data[4]=consulta.getString("Telefono");
               tblProveedores.addRow(data);
            }
            Con.cerrar_conexion();
        } catch (SQLException e) {
            System.out.println("Error >:V "+e.getMessage());
        }
        return tblProveedores;
    }
    
    public int AgregarProveedor(){
        int resultado=0;
        try {
            Con=new Conexion();
            PreparedStatement parameter;
            String query="insert into proveedores (Proveedor,NIT,Direccion,Telefono)values(?,?,?,?);";
            Con.abrir_conexion();
            parameter=Con.conexionbd.prepareStatement(query);
            parameter.setString(1,getProveedores());
            parameter.setString(2,getNit());
            parameter.setString(3,getDireccion());
            parameter.setString(4,getTelefono());
            resultado=parameter.executeUpdate();
            Con.cerrar_conexion();
        } catch (SQLException e) {
            System.out.println("Error Prro >:V"+e.getMessage());
        }
        return resultado;
    }
     public int ModificarProveedor(){
        int resultado=0;
        try {
            Con=new Conexion();
            PreparedStatement parameter;
            String query="update proveedores set Proveedor=?,NIT=?,Direccion=?,Telefono=? where idProveedores=?;";
            Con.abrir_conexion();
            parameter=Con.conexionbd.prepareStatement(query);
            parameter.setString(1,getProveedores());
            parameter.setString(2,getNit());
            parameter.setString(3,getDireccion());
            parameter.setString(4,getTelefono());
            parameter.setInt(5,getId_Proveedores());
            resultado=parameter.executeUpdate();
            Con.cerrar_conexion();
        } catch (SQLException e) {
            System.out.println("Error Prro >:V"+e.getMessage());
        }
        return resultado;
    }
          public int EliminarProveedor(){
        int resultado=0;
        try {
            Con=new Conexion();
            PreparedStatement parameter;
            String query="delete from proveedores where idProveedores=?;";
            Con.abrir_conexion();
            parameter=Con.conexionbd.prepareStatement(query);
            parameter.setInt(1,getId_Proveedores());
            resultado=parameter.executeUpdate();
            Con.cerrar_conexion();
        } catch (SQLException e) {
            System.out.println("Error Prro >:V"+e.getMessage());
        }
        return resultado;
    }
}
