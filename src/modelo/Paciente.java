/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.time.LocalDate;

/**
 *
 * @author mburgos
 */
public class Paciente extends Persona {
    private String obraSocial;
    private LocalDate fecRegistro;

    public String getObraSocial() {
        return obraSocial;
    }

    public void setObraSocial(String obraSocial) {
        this.obraSocial = obraSocial;
    }

    public LocalDate getFecRegistro() {
        return fecRegistro;
    }

    public void setFecRegistro(LocalDate fecRegistro) {
        this.fecRegistro = fecRegistro;
    }
    
    
}
