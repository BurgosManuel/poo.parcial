/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import interfaces.IVistaAddAtencion;
import interfaces.IVistaAddPaciente;
import interfaces.IVistaPrincipal;
import interfaces.IVistaReporte;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import modelo.Atencion;
import modelo.Paciente;
import vistas.VistaAddAtencion;
import vistas.VistaAddPaciente;
import vistas.VistaPrincipal;
import vistas.VistaReporte;

/**
 *
 * @author mburgos
 */
public class Controlador implements ActionListener {
    // Lista de datos
    private List<Paciente> pacientes = new ArrayList();
    private List<Atencion> historialAtencion = new ArrayList();
   
    // Vistas
    private IVistaPrincipal vistaPrincipal = new VistaPrincipal();
    private IVistaAddPaciente vistaAddPaciente = new VistaAddPaciente(null, true);
    private IVistaAddAtencion vistaAddAtencion = new VistaAddAtencion(null, true);
    private IVistaReporte vistaReporte = new VistaReporte(null, true);
    
    public void inicializar() {
        vistaPrincipal.setControlador(this);
        vistaAddPaciente.setControlador(this);
        vistaAddAtencion.setControlador(this);
        vistaReporte.setControlador(this);
        
        vistaPrincipal.inicializar();
    };

    @Override
    public void actionPerformed(ActionEvent e) {
        if(IVistaPrincipal.BTN_ATENCION.equalsIgnoreCase(e.getActionCommand())) {
            vistaAddAtencion.inicializar();
        }
        
        if(IVistaPrincipal.BTN_PACIENTE.equalsIgnoreCase(e.getActionCommand())) {
            vistaAddPaciente.inicializar();
        }
        
        if(IVistaPrincipal.BTN_REPORTE.equalsIgnoreCase(e.getActionCommand())) {
            vistaReporte.inicializar();
        }
        
        if(IVistaAddAtencion.BTN_ADD_ATENCION.equalsIgnoreCase(e.getActionCommand())) {
            guardarAtencion();
        }
        
        if(IVistaAddPaciente.BTN_ADD_PACIENTE.equalsIgnoreCase(e.getActionCommand())) {
            guardarPaciente();
        }
    }
    
    private void guardarAtencion() {
        Atencion a = new Atencion();
        Paciente p = new Paciente(); // dao.getPaciente(dni);
        p.setDni(vistaAddAtencion.getDni());
        
        a.setPaciente(p);
        a.setEspecialidad(vistaAddAtencion.getEspecialidad());
        a.setFecha(LocalDate.now());
        
        historialAtencion.add(a); // dao.guardarAtencion();
        vistaAddAtencion.limpiar();
    }
    
    private void guardarPaciente() {
        Paciente p = new Paciente();
        p.setDni(vistaAddPaciente.getDni());
        p.setNombre(vistaAddPaciente.getNombre());
        p.setEdad(vistaAddPaciente.getEdad());
        p.setSexo(vistaAddPaciente.getSexo());
        p.setObraSocial(vistaAddPaciente.getObraSocial());
        
        vistaAddPaciente.limpiar();
        pacientes.add(p); //dao.guardarPaciente();
    }
}
