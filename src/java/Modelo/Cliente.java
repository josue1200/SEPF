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
public class Cliente {
    private int id_cliente,Genero;
    private String Nombres,Apellidos,NIT,Telefono,Correo_Electronico,FechaIngreso;
    private Conexion Con;

    public Cliente(int id_cliente, int Genero, String Nombres, String Apellidos, String NIT, String Telefono, String Correo_Electronico, String FechaIngreso) {
        this.id_cliente = id_cliente;
        this.Genero = Genero;
        this.Nombres = Nombres;
        this.Apellidos = Apellidos;
        this.NIT = NIT;
        this.Telefono = Telefono;
        this.Correo_Electronico = Correo_Electronico;
        this.FechaIngreso = FechaIngreso;
    }

    public Cliente() {
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getGenero() {
        return Genero;
    }

    public void setGenero(int Genero) {
        this.Genero = Genero;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String Nombres) {
        this.Nombres = Nombres;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    public String getNIT() {
        return NIT;
    }

    public void setNIT(String NIT) {
        this.NIT = NIT;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getCorreo_Electronico() {
        return Correo_Electronico;
    }

    public void setCorreo_Electronico(String Correo_Electronico) {
        this.Correo_Electronico = Correo_Electronico;
    }

    public String getFechaIngreso() {
        return FechaIngreso;
    }

    public void setFechaIngreso(String FechaIngreso) {
        this.FechaIngreso = FechaIngreso;
    }
  public DefaultTableModel TablaDeCliente(){
        DefaultTableModel tblCliente=new DefaultTableModel();
        try {
            Con=new Conexion();
            Con.abrir_conexion();
            String query="SELECT idclientes as id,Nombres,Apellidos,NIT,Genero,Telefono,Correo_Electronico,FechaIngreso FROM clientes;";
            ResultSet consulta=Con.conexionbd.createStatement().executeQuery(query);
            String Header[]={"id","Nombres","Apellidos","NIT","Genero","Telefono","Correo Electronico","Fecha De Ingreso"};
            tblCliente.setColumnIdentifiers(Header);
            String data[]=new String[8];
            while (consulta.next()) {
               data[0]=consulta.getString("id");
               data[1]=consulta.getString("Nombres"); 
               data[2]=consulta.getString("Apellidos");
               data[3]=consulta.getString("NIT"); 
               data[4]=consulta.getString("Genero");
               data[5]=consulta.getString("Telefono"); 
               data[6]=consulta.getString("Correo_Electronico");
               data[7]=consulta.getString("FechaIngreso"); 
               tblCliente.addRow(data);
            }
            Con.cerrar_conexion();
        } catch (SQLException e) {
            System.out.println("Error >:V "+e.getMessage());
        }
        return tblCliente;
  }
   
  public int AgregarCliente(){
        int resultado=0;
        try {
            Con=new Conexion();
            PreparedStatement parameter;
            String query="insert into clientes(Nombres,Apellidos,NIT,Genero,Telefono,Correo_Electronico,FechaIngreso)values(?,?,?,?,?,?,now());";
            Con.abrir_conexion();
            parameter=Con.conexionbd.prepareStatement(query);
            parameter.setString(1,getNombres());
            parameter.setString(2,getApellidos());
            parameter.setString(3,getNIT());
            parameter.setInt(4,getGenero());
            parameter.setString(5,getTelefono());
            parameter.setString(6,getCorreo_Electronico());
            resultado=parameter.executeUpdate();
            Con.cerrar_conexion();
        } catch (SQLException e) {
            System.out.println("Error Prro >:V"+e.getMessage());
        }
        return resultado;
    }
      public int ModificarCliente(){
        int resultado=0;
        try {
            Con=new Conexion();
            PreparedStatement parameter;
            String query="update clientes set Nombres=?,Apellidos=?,NIT=?,Genero=?,Telefono=?,Correo_Electronico=?,FechaIngreso=? where idclientes=?;";
            Con.abrir_conexion();
            parameter=Con.conexionbd.prepareStatement(query);
            parameter.setString(1,getNombres());
            parameter.setString(2,getApellidos());
            parameter.setString(3,getNIT());
            parameter.setInt(4,getGenero());
            parameter.setString(5,getTelefono());
            parameter.setString(6,getCorreo_Electronico());
            parameter.setString(7,getFechaIngreso());
            parameter.setInt(8,getId_cliente());
            resultado=parameter.executeUpdate();
            Con.cerrar_conexion();
        } catch (SQLException e) {
            System.out.println("Error Prro >:V"+e.getMessage());
        }
        return resultado;
    }
    
       public int EliminarCliente(){
        int resultado=0;
        try {
            Con=new Conexion();
            PreparedStatement parameter;
            String query="delete from clientes where idclientes=?;";
            Con.abrir_conexion();
            parameter=Con.conexionbd.prepareStatement(query);
            parameter.setInt(1,getId_cliente());
            resultado=parameter.executeUpdate();
            Con.cerrar_conexion();
        } catch (SQLException e) {
            System.out.println("Error Prro >:V"+e.getMessage());
        }
        return resultado;
    }
    
    
}
