/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vista.acciones;

import com.mycompany.recuperacion.MetodoVectorial;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author kero
 */
public class BuscarConsultaVecto implements ActionListener{

    MetodoVectorial mtd;
    
    public BuscarConsultaVecto(MetodoVectorial metodo) {
        mtd = metodo;
    }
        
    @Override
    public void actionPerformed(ActionEvent e) {
        mtd.calcularSimilaridad();
    }
    
}
