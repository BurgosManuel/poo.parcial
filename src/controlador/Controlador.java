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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        
        actualizarVistaPrincipal();
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
            generarReporte();
            vistaReporte.inicializar();
        }
        
        if(IVistaAddAtencion.BTN_ADD_ATENCION.equalsIgnoreCase(e.getActionCommand())) {
            guardarAtencion();
            actualizarVistaPrincipal();
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
    
    private void generarReporte() {
        int cantMayores = 0;
        int cantMenores = 0;
        int cantMasc = 0;
        int cantFem = 0;
        Map<String, Integer> cantEspecialidad = new HashMap();
        
        for(Paciente p : pacientes) {
            if(p.esMayorEdad()) {
                cantMayores++;
            } else {
                cantMenores++;
            }
            
            if('M' == p.getSexo()) {
                cantMasc++;
            } else {
                cantFem++;
            }
        }
        
        for(Atencion a : historialAtencion) {
            Integer cant = cantEspecialidad.get(a.getEspecialidad());
            
            if(null == cant) {
                cantEspecialidad.put(a.getEspecialidad(), 1);
            } else {
                cantEspecialidad.put(a.getEspecialidad(), cant + 1);
            }
        }
        
        List<Object[]> items = new ArrayList();
        
        // Mapeamos los valores del Map a un Object[]
        cantEspecialidad.entrySet().forEach(entry -> {
            Object[] row = new Object[2];
            row[0] = entry.getKey();
            row[1] = entry.getValue();
            items.add(row);
        });
        
        vistaReporte.cargarDatos(items);
        vistaReporte.setPacientesMayores(cantMayores);
        vistaReporte.setPacientesMenores(cantMenores);
        vistaReporte.setPacientesMasculinos(cantMasc);
        vistaReporte.setPacientesFemeninos(cantFem);
    }
    
    private void actualizarVistaPrincipal() {
        List<Object[]> items = new ArrayList();
        for(Atencion a : historialAtencion) {
            Object[] row = new Object[3];
            row[0] = a.getPaciente().getDni();
            row[1] = a.getEspecialidad();
            row[2] = a.getFecha();
            items.add(row);
        }
        vistaPrincipal.cargarHistorialAtencion(items);
    }
}
