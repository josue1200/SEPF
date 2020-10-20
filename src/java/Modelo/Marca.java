/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.ResultSet;
import java.sql.*;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Miguel Rivera
 */
public class Marca {
    private int id_Marca;
    private String Marca;
    private Conexion Con;
    public Marca() {
    }

    public Marca(int id_Marca, String Marca) {
        this.id_Marca = id_Marca;
        this.Marca = Marca;
    }

    public int getId_Marca() {
        return id_Marca;
    }

    public void setId_Marca(int id_Marca) {
        this.id_Marca = id_Marca;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String Marca) {
        this.Marca = Marca;
    }
    //Para los otros crud esto es el combo box
    public HashMap ListadoMarcas(){
        HashMap<String,String> Lista=new HashMap();
        try {
            Con=new Conexion();
            String query="select idMarcas as id,Marca from marcas;";
            Con.abrir_conexion();
            ResultSet consulta=Con.conexionbd.createStatement().executeQuery(query);
            while (consulta.next()) {
                Lista.put(consulta.getString("id"),consulta.getString("Marca") );                
            }
            Con.cerrar_conexion();
        } catch (SQLException e) {
            System.out.println("Error >:V "+e.getMessage());
        }
        return Lista;
    }
    //Tabla
    public DefaultTableModel TablaDeMarcas(){
        DefaultTableModel tblMarcas=new DefaultTableModel();
        try {
            Con=new Conexion();
            Con.abrir_conexion();
            String query="select idMarcas as id,Marca from marcas;";
            ResultSet consulta=Con.conexionbd.createStatement().executeQuery(query);
            String Header[]={"id","Marca"};
            tblMarcas.setColumnIdentifiers(Header);
            String data[]=new String[2];
            while (consulta.next()) {
               data[0]=consulta.getString("id");
               data[1]=consulta.getString("Marca"); 
               tblMarcas.addRow(data);
            }
            Con.cerrar_conexion();
        } catch (SQLException e) {
            System.out.println("Error >:V "+e.getMessage());
        }
        return tblMarcas;
    }
    
    public int AgregarMarca(){
        int resultado=0;
        try {
            Con=new Conexion();
            PreparedStatement parameter;
            String query="insert into marcas (Marca)values(?);";
            Con.abrir_conexion();
            parameter=Con.conexionbd.prepareStatement(query);
            parameter.setString(1,getMarca());
            resultado=parameter.executeUpdate();
            Con.cerrar_conexion();
        } catch (SQLException e) {
            System.out.println("Error Prro >:V"+e.getMessage());
        }
        return resultado;
    }
     public int ModificarMarca(){
        int resultado=0;
        try {
            Con=new Conexion();
            PreparedStatement parameter;
            String query="update marcas set Marca=? where idMarcas=?;";
            Con.abrir_conexion();
            parameter=Con.conexionbd.prepareStatement(query);
            parameter.setString(1,getMarca());
            parameter.setInt(2,getId_Marca());
            resultado=parameter.executeUpdate();
            Con.cerrar_conexion();
        } catch (SQLException e) {
            System.out.println("Error Prro >:V"+e.getMessage());
        }
        return resultado;
    }
          public int EliminarMarca(){
        int resultado=0;
        try {
            Con=new Conexion();
            PreparedStatement parameter;
            String query="delete from marcas where idMarcas=?;";
            Con.abrir_conexion();
            parameter=Con.conexionbd.prepareStatement(query);
            parameter.setInt(1,getId_Marca());
            resultado=parameter.executeUpdate();
            Con.cerrar_conexion();
        } catch (SQLException e) {
            System.out.println("Error Prro >:V"+e.getMessage());
        }
        return resultado;
    }
}
