/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Atencion;
import modelo.Paciente;

/**
 *
 * @author mburgos
 */
public class DAOAtencion {
    private final Conector conector;
    private final List<Atencion> historialAtencion;

    public DAOAtencion(Conector conector, List<Atencion> historialAtencion) {
        this.conector = conector;
        this.historialAtencion = historialAtencion;
    }
    
    public void cargarHistorialAtencion() {
        try(Connection conn = conector.getConexion()) {
            String sql = "SELECT p.dni, p.nombre, p.edad, p.sexo, p.obra_social, p.fec_registro, "
                    + "a.especialidad, a.fecha "
                    + "FROM atencion a "
                    + "INNER JOIN paciente p ON p.dni = a.dni_paciente ";
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                Paciente p = new Paciente();
                p.setDni(rs.getString(1));
                p.setNombre(rs.getString(2));
                p.setEdad(rs.getInt(3));
                p.setSexo(rs.getString(4));
                p.setObraSocial(rs.getString(5));
                p.setFecRegistro(rs.getDate(6).toLocalDate());
                
                Atencion a = new Atencion();
                a.setPaciente(p);
                a.setEspecialidad(rs.getString(7));
                a.setFecha(rs.getDate(8).toLocalDate());
               
                historialAtencion.add(a);
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(DAOAtencion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void guardarAtencion(Atencion a) {
        try(Connection conn = conector.getConexion()) {
            String sql = "INSERT INTO atencion(dni_paciente, especialidad, fecha) VALUES(?,?,?)";
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, a.getPaciente().getDni());
            ps.setString(2, a.getEspecialidad());
            ps.setDate(3, Date.valueOf(a.getFecha()));
            
            ps.executeUpdate();
            System.out.println("Atencion guardada en DB");
           
        } catch (SQLException ex) {
            Logger.getLogger(DAOAtencion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       
}
