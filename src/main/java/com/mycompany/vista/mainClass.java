/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vista;

import com.mycompany.recuperacion.MetodoBooleano;
import com.mycompany.recuperacion.MetodoProbabilistico;
import com.mycompany.recuperacion.MetodoVectorial;
import static java.awt.EventQueue.invokeLater;
import javax.swing.JFrame;

/**
 *
 * @author Kero
 */
public class mainClass {
    public static boolean RIGHT_TO_LEFT = false;
    public static MetodoBooleano booleano;
    public static MetodoVectorial vectorial;
    public static MetodoProbabilistico probabilistico;
    
    public static void main(String[] args) {
        invokeLater(() -> {
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Buscador de informacion");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        PanelMetodos panel = new PanelMetodos();
        panel.addComponentToPane(frame.getContentPane());
    
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }
    
}
