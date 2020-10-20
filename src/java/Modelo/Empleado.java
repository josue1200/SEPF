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
public class Empleado {
      private int id_Puesto;
        private int id_empleado,Genero;
    private String nombres,apellidos,fecha_de_ingreso,direccion,telefono,fecha_nacimiento,DPI,fecha_inicio;
    private Conexion Con;

    public Empleado() {
    }

    public Empleado(int id_Puesto, int id_empleado, int Genero, String nombres, String apellidos, String fecha_de_ingreso, String direccion, String telefono, String fecha_nacimiento, String DPI, String fecha_inicio) {
        this.id_Puesto = id_Puesto;
        this.id_empleado = id_empleado;
        this.Genero = Genero;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fecha_de_ingreso = fecha_de_ingreso;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fecha_nacimiento = fecha_nacimiento;
        this.DPI = DPI;
        this.fecha_inicio = fecha_inicio;
    }

    public int getId_Puesto() {
        return id_Puesto;
    }

    public void setId_Puesto(int id_Puesto) {
        this.id_Puesto = id_Puesto;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public int getGenero() {
        return Genero;
    }

    public void setGenero(int Genero) {
        this.Genero = Genero;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFecha_de_ingreso() {
        return fecha_de_ingreso;
    }

    public void setFecha_de_ingreso(String fecha_de_ingreso) {
        this.fecha_de_ingreso = fecha_de_ingreso;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getDPI() {
        return DPI;
    }

    public void setDPI(String DPI) {
        this.DPI = DPI;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }
    
    public DefaultTableModel TablaDeCliente(){
        DefaultTableModel tblCliente=new DefaultTableModel();
        try {
            Con=new Conexion();
            Con.abrir_conexion();
            String query="select e.idempleados as id,e.nombres,e.apellidos,e.direccion,e.telefono,e.DPI,e.genero,e.fecha_nacimiento,p.idPuestos,p.Puesto,e.fecha_inicio_laborales,e.fechaingreso from empleados as e inner join puestos as p on e.idPuesto=p.idPuestos;";
            ResultSet consulta=Con.conexionbd.createStatement().executeQuery(query);
            String Header[]={"id","Nombres","Apellidos","Direccion","Telefono","DPI","Genero","Fecha Nacimiento","idPuesto","Puesto","Fecha incio labores","Fecha De Ingreso"};
            tblCliente.setColumnIdentifiers(Header);
            String data[]=new String[12];
            while (consulta.next()) {
               data[0]=consulta.getString("id");
               data[1]=consulta.getString("nombres"); 
               data[2]=consulta.getString("apellidos");
               data[3]=consulta.getString("direccion"); 
               data[4]=consulta.getString("telefono");
               data[5]=consulta.getString("DPI"); 
               data[6]=consulta.getString("genero");
               data[7]=consulta.getString("fecha_nacimiento"); 
               data[8]=consulta.getString("idPuestos"); 
               data[9]=consulta.getString("Puesto"); 
               data[10]=consulta.getString("fecha_inicio_laborales"); 
               data[11]=consulta.getString("fechaingreso"); 
               tblCliente.addRow(data);
            }
            Con.cerrar_conexion();
        } catch (SQLException e) {
            System.out.println("Error >:V "+e.getMessage());
        }
        return tblCliente;
  }
   
    public int AgregarEmpleado(){
        int resultado=0;
        try {
            Con=new Conexion();
            PreparedStatement parameter;
            String query="INSERT INTO empleados (nombres, apellidos, direccion, telefono, DPI, genero, fecha_nacimiento, idPuesto, fecha_inicio_laborales, fechaingreso) VALUES (?,?,?,?,?,?,?,?,?,now());";
            Con.abrir_conexion();
            parameter=Con.conexionbd.prepareStatement(query);
            parameter.setString(1,getNombres());
            parameter.setString(2,getApellidos());
            parameter.setString(3,getDireccion());
            parameter.setString(4,getTelefono());
            parameter.setString(5,getDPI());
            parameter.setInt(6,getGenero());
            parameter.setString(7,getFecha_nacimiento());
            parameter.setInt(8,getId_Puesto());
            parameter.setString(9,getFecha_inicio());
            resultado=parameter.executeUpdate();
            Con.cerrar_conexion();
        } catch (SQLException e) {
            System.out.println("Error Prro >:V"+e.getMessage());
        }
        return resultado;
    }
      public int ModificarEmpleado(){
        int resultado=0;
        try {
            Con=new Conexion();
            PreparedStatement parameter;
            String query="update empleados set nombres=?,apellidos=?,direccion=?,telefono=?,DPI=?,genero=?,fecha_nacimiento=?,idPuesto=?,fecha_inicio_laborales=?,fechaingreso=? where idempleados=?;";
            Con.abrir_conexion();
            parameter=Con.conexionbd.prepareStatement(query);
            parameter.setString(1,getNombres());
            parameter.setString(2,getApellidos());
            parameter.setString(3,getDireccion());
            parameter.setString(4,getTelefono());
            parameter.setString(5,getDPI());
            parameter.setInt(6,getGenero());
            parameter.setString(7,getFecha_nacimiento());
            parameter.setInt(8,getId_Puesto());
            parameter.setString(9,getFecha_inicio());
            parameter.setString(10,getFecha_de_ingreso());
            parameter.setInt(11,getId_empleado());
            resultado=parameter.executeUpdate();
            Con.cerrar_conexion();
        } catch (SQLException e) {
            System.out.println("Error Prro >:V"+e.getMessage());
        }
        return resultado;
    }
    
       public int EliminarEmpleado(){
        int resultado=0;
        try {
            Con=new Conexion();
            PreparedStatement parameter;
            String query="delete from empleados where idempleados=?;";
            Con.abrir_conexion();
            parameter=Con.conexionbd.prepareStatement(query);
            parameter.setInt(1,getId_empleado());
            resultado=parameter.executeUpdate();
            Con.cerrar_conexion();
        } catch (SQLException e) {
            System.out.println("Error Prro >:V"+e.getMessage());
        }
        return resultado;
    }
    
    
}
