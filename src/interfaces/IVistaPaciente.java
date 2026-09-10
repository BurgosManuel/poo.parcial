/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;

/**
 *
 * @author mburgos
 */
public interface IVistaPaciente extends IVista {
    public static final String BTN_INIT_ADD_PACIENTE = "BTN_INIT_ADD_PACIENTE";
    
    public void cargarDatos(List<Object[]> items);
}
