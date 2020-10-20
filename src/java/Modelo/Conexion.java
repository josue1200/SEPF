/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Miguel Rivera
 */
public class Conexion {
     public Connection conexionbd;
    public final String nombre="dbempresa";
    public final String url=String.format("jdbc:mysql://localhost:3306/%s?useTimezone=true&serverTimezone=UTC",nombre);
    public final String usuario="usr_empresa";
    public final String contraseña="Empresa@Final";
    public final String jdbc="com.mysql.cj.jdbc.Driver";
    public void abrir_conexion(){
        try{
            Class.forName(jdbc);
            conexionbd=DriverManager.getConnection(url,usuario,contraseña);
        }catch(HeadlessException|ClassNotFoundException|SQLException ex){
            System.out.println("Error "+ex.getMessage());
        }
    }
    public void cerrar_conexion(){
        try{
            conexionbd.close();
        }catch(SQLException ex){
                System.out.println("Error "+ex.getMessage());
        }
    }
 
    
}
