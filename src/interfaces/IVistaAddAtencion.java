/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

/**
 *
 * @author mburgos
 */
public interface IVistaAddAtencion extends IVista {
    public static final String BTN_ADD_ATENCION = "BTN_ADD_ATENCION";
    
    public String getDni();
    public String getEspecialidad();
    public void limpiar();
}
