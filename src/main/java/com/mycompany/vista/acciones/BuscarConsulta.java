/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vista.acciones;

import com.mycompany.recuperacion.Metodo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author kero
 */
public class BuscarConsulta implements ActionListener{

    Metodo mtd;
    
    public BuscarConsulta(Metodo metodo) {
        mtd = metodo;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
}
