 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Miguel Rivera
 */
public class Puesto {
    private int id_Puesto;
    private String Puesto;
    private Conexion Con;

    public Puesto() {
    }

    public Puesto(int id_Puesto, String Puesto) {
        this.id_Puesto = id_Puesto;
        this.Puesto = Puesto;
    }

    public int getId_Puesto() {
        return id_Puesto;
    }

    public void setId_Puesto(int id_Puesto) {
        this.id_Puesto = id_Puesto;
    }

    public String getPuesto() {
        return Puesto;
    }

    public void setPuesto(String Puesto) {
        this.Puesto = Puesto;
    }
    
    public HashMap ListaPuesto(){
        HashMap<String,String> Lista=new HashMap();
        try {
            Con=new Conexion();
            String query="select idPuestos as id,Puesto from puestos;";
            Con.abrir_conexion();
            ResultSet consulta=Con.conexionbd.createStatement().executeQuery(query);
            while (consulta.next()) {
                Lista.put(consulta.getString("id"),consulta.getString("Puesto"));
                
            }
            
            Con.cerrar_conexion();
        } catch (SQLException e) {
            System.out.println("Error prro "+e.getMessage() );
        }
        return Lista;
    }
   public DefaultTableModel TablaPuesto(){
        DefaultTableModel tblPuesto=new DefaultTableModel();
        try {
            Con=new Conexion();
            Con.abrir_conexion();
            String query="select idPuestos as id,Puesto from puestos;";
            ResultSet consulta=Con.conexionbd.createStatement().executeQuery(query);
            String Header[]={"id","Puesto"};
            tblPuesto.setColumnIdentifiers(Header);
            String data[]=new String[2];
            while (consulta.next()) {
               data[0]=consulta.getString("id");
               data[1]=consulta.getString("Puesto"); 
               tblPuesto.addRow(data);
            }
            Con.cerrar_conexion();
        } catch (SQLException e) {
            System.out.println("Error >:V "+e.getMessage());
        }
        return tblPuesto;
    }
     public int agregarPuesto(){
         int resultado=0;
         try {
             Con=new Conexion();
             String Query="insert into puestos(Puesto)values(?);";
             Con.abrir_conexion();
             PreparedStatement parameter=Con.conexionbd.prepareStatement(Query);
             parameter.setString(1,getPuesto());
             resultado=parameter.executeUpdate();
             Con.cerrar_conexion();
         } catch (SQLException e) {
             System.out.println("Error prro "+e.getMessage() );
         }
         return resultado;
     }
       public int modificarPuesto(){
         int resultado=0;
         try {
             Con=new Conexion();
             String Query="update puestos set Puesto=? where idPuestos=?;";
             Con.abrir_conexion();
             PreparedStatement parameter=Con.conexionbd.prepareStatement(Query);
             parameter.setString(1,getPuesto());
             parameter.setInt(2,getId_Puesto());
             resultado=parameter.executeUpdate();
             Con.cerrar_conexion();
         } catch (SQLException e) {
             System.out.println("Error prro "+e.getMessage() );
         }
         return resultado;
     }
         public int eliminarPuesto(){
         int resultado=0;
         try {
             Con=new Conexion();
             String Query="delete from puestos where idPuestos=?;";
             Con.abrir_conexion();
             PreparedStatement parameter=Con.conexionbd.prepareStatement(Query);
             parameter.setInt(1,getId_Puesto());
             resultado=parameter.executeUpdate();
             Con.cerrar_conexion();
         } catch (SQLException e) {
             System.out.println("Error prro "+e.getMessage() );
         }
         return resultado;
     }
}
