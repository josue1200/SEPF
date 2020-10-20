/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Miguel Rivera
 */
public class usuario {
    private int id;
    private String Usuario,Nombre,Apellido,Correo,Pass,Tipe;

    public usuario(int id,String Usuario, String Nombre, String Apellido, String Correo, String Pass, String Tipe) {
        this.id=id;
        this.Usuario = Usuario;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Correo = Correo;
        this.Pass = Pass;
        this.Tipe = Tipe;
      
    }

    public usuario() {
    }
    
    private Conexion Con;
     public int Encontrar(String pass, String usuario){
        int retorno = 0;
        
        

        try {
            PreparedStatement parametro;
            String sql_insert;
            Con = new Conexion();
            Con.abrir_conexion();
            sql_insert = "select * from usuarios where Usuario='"+usuario+"' and Pass='"+pass+"';";
            
            parametro = Con.conexionbd.prepareStatement(sql_insert);
            ResultSet rs = parametro.executeQuery();
      
            if (rs.next()) {
                if (rs.getString(1).equals(pass)||rs.getString(2).equals(usuario)) {
                    retorno=1;
                }
                else {
                    return retorno=0;
                }
            }
            Con.cerrar_conexion();
        }
        catch (Exception ex) {
            retorno = 0;
        }
        return retorno;
    }
      public List<List<String>> Menu(String tipo) {
        List<List<String>> lista = new ArrayList<List<String>>();
        lista.add(new ArrayList<String>());
        lista.add(new ArrayList<String>());
        
        try {
            Con = new Conexion();
            Con.abrir_conexion();
            String consulta;
            
            consulta = "select Menujsp, Menu from menu_dinamico where Tipo='"+tipo+"';";
            
            ResultSet query = Con.conexionbd.createStatement().executeQuery(consulta);
  
            
            while (query.next()) {
                lista.get(0).add(query.getString("Menujsp"));
                lista.get(1).add(query.getString("Menu"));
            }
            Con.cerrar_conexion();
        }
        catch (SQLException e) {
            System.err.println("Error..."+e.getMessage());
        }
        return lista;
    }
        public String tipe(String usuario) {
        String retorno = null;
        
       try{
            Con = new Conexion();
            Con.abrir_conexion();
            PreparedStatement parametro;
            String Query = "select Tipe from usuarios where Usuario='"+usuario+"';";
            parametro = Con.conexionbd.prepareStatement(Query);
            ResultSet rs = parametro.executeQuery();
      
            while (rs.next()) {
              retorno=rs.getString("Tipe");
            }
            Con.cerrar_conexion();
       }catch(SQLException e){
           System.out.println("error ..."+e.getMessage());
       }
        return retorno;
    }
        public DefaultTableModel TablaDeusers(){
        DefaultTableModel tblMarcas=new DefaultTableModel();
        try {
            Con=new Conexion();
            Con.abrir_conexion();
            String query="SELECT idUsers as id,Usuario,Nombre,Apellido,Correo,Pass,Tipe FROM usuarios;";
            ResultSet consulta=Con.conexionbd.createStatement().executeQuery(query);
            String Header[]={"id","Usuario","Nombre","Apellido","Correo","Contraseña","Tipo"};
            tblMarcas.setColumnIdentifiers(Header);
            String data[]=new String[7];
            while (consulta.next()) {
               data[0]=consulta.getString("id");
               data[1]=consulta.getString("Usuario"); 
               data[2]=consulta.getString("Nombre"); 
               data[3]=consulta.getString("Apellido"); 
               data[4]=consulta.getString("Correo"); 
               data[5]=consulta.getString("Pass"); 
               data[6]=consulta.getString("Tipe"); 
               tblMarcas.addRow(data);
            }
            Con.cerrar_conexion();
        } catch (SQLException e) {
            System.out.println("Error >:V "+e.getMessage());
        }
        return tblMarcas;
    }
         public int AgregarUsuario(){
        int resultado=0;
        try {
            Con=new Conexion();
            PreparedStatement parameter;
            String query="insert into usuarios(Usuario,Nombre,Apellido,Correo,Pass,Tipe)values(?,?,?,?,?,?);";
            Con.abrir_conexion();
            parameter=Con.conexionbd.prepareStatement(query);
            parameter.setString(1,this.getUsuario());
            parameter.setString(2,this.getNombre());
            parameter.setString(3,this.getApellido());
            parameter.setString(4,this.getCorreo());
            parameter.setString(5,this.getPass());
            parameter.setString(6,this.getTipe());
            resultado=parameter.executeUpdate();
            Con.cerrar_conexion();
        } catch (SQLException e) {
            System.out.println("Error Prro >:V"+e.getMessage());
        }
        return resultado;
    }
          public int ModificarUsuario(){
        int resultado=0;
        try {
            Con=new Conexion();
            PreparedStatement parameter;
            String query="update usuarios set Usuario=?,Nombre=?,Apellido=?,Correo=?,Pass=?,Tipe=? where idUsers=?;";
            Con.abrir_conexion();
            parameter=Con.conexionbd.prepareStatement(query);
            
            parameter.setString(1,this.getUsuario());
            parameter.setString(2,this.getNombre());
            parameter.setString(3,this.getApellido());
            parameter.setString(4,this.getCorreo());
            parameter.setString(5,this.getPass());
            parameter.setString(6,this.getTipe());
            parameter.setInt(7,this.getId());
            resultado=parameter.executeUpdate();
            Con.cerrar_conexion();
        } catch (SQLException e) {
            System.out.println("Error Prro >:V"+e.getMessage());
        }
        return resultado;
    }
          public int EliminarUsuario(){
        int resultado=0;
        try {
            Con=new Conexion();
            PreparedStatement parameter;
            String query="delete from usuarios where idUsers=?;";
            Con.abrir_conexion();
            parameter=Con.conexionbd.prepareStatement(query);
            parameter.setInt(1,this.getId());
            resultado=parameter.executeUpdate();
            Con.cerrar_conexion();
        } catch (SQLException e) {
            System.out.println("Error Prro >:V"+e.getMessage());
        }
        return resultado;
    }
public int getId(){
    return id;
}
public void setId(int id){
    this.id=id;
}
    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String Pass) {
        this.Pass = Pass;
    }

    public String getTipe() {
        return Tipe;
    }

    public void setTipe(String Tipe) {
        this.Tipe = Tipe;
    }
}
