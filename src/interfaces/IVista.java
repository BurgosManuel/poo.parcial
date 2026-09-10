/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import controlador.Controlador;

/**
 *
 * @author mburgos
 */
public interface IVista {
    public void inicializar();
    public void setControlador(Controlador controlador);
}
