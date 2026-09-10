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
public interface IVistaAddPaciente extends IVista {
    public static final String BTN_ADD_PACIENTE = "BTN_ADD_PACIENTE";
    
    public String getDni();
    public String getNombre();
    public int getEdad();
    public char getSexo();
    public String getObraSocial();
    public void limpiar();
}
