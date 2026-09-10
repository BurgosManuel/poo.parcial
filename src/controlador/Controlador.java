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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
