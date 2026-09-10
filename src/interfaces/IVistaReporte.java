/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.ArrayList;

/**
 *
 * @author mburgos
 */
public interface IVistaReporte extends IVista {
    public void cargarDatos(ArrayList items);
    public void setPacientesMayores(int cantidad);
    public void setPacientesMenores(int cantidad);
    public void setPacientesMasculinos(int cantidad);
    public void setPacientesFemeninos(int cantidad);
}
