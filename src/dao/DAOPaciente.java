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
import modelo.Paciente;

/**
 *
 * @author mburgos
 */
public class DAOPaciente {
    private final Conector conector;
    private final List<Paciente> pacientes;

    public DAOPaciente(Conector conector, List<Paciente> pacientes) {
        this.conector = conector;
        this.pacientes = pacientes;
    }
    
    public Paciente getPaciente(String dni) {
        Paciente p = null;
        try(Connection conn = conector.getConexion()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM paciente p WHERE p.dni = ?");
            ps.setString(1, dni);
            
            System.out.println("Buscando paciente con DNI: " + dni);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                System.out.println("Paciente encontrado con DNI: " + dni);
                p = new Paciente();
                p.setDni(rs.getString("dni"));
                p.setNombre(rs.getString("nombre"));
                p.setEdad(rs.getInt("edad"));
                p.setSexo(rs.getString("sexo"));
                p.setObraSocial(rs.getString("obra_social"));
                p.setFecRegistro(rs.getDate("fec_registro").toLocalDate());
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return p;
    }
    
    public void cargarPacientes() {
        try(Connection conn = conector.getConexion()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM paciente");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Paciente p = new Paciente();
                p.setDni(rs.getString("dni"));
                p.setNombre(rs.getString("nombre"));
                p.setEdad(rs.getInt("edad"));
                p.setSexo(rs.getString("sexo"));
                p.setObraSocial(rs.getString("obra_social"));
                p.setFecRegistro(rs.getDate("fec_registro").toLocalDate());
                pacientes.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void guardarPaciente(Paciente p) {
        try(Connection conn = conector.getConexion()) {
            String sql = "INSERT INTO paciente VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, p.getDni());
            ps.setString(2, p.getNombre());
            ps.setInt(3, p.getEdad());
            ps.setString(4, p.getSexo());
            ps.setString(5, p.getObraSocial());
            ps.setDate(6, Date.valueOf(p.getFecRegistro()));
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAOPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
