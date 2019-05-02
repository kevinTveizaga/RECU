/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vista.acciones;

import com.mycompany.recuperacion.Metodo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;

/**
 *
 * @author kero
 */
public class AgregarPalabrasVacias implements ActionListener{
    
    Metodo mtd;
    JButton btn;
    public AgregarPalabrasVacias(Metodo metodo, JButton button) {
        mtd = metodo;
        btn = button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String filePath = abrirArchivo();
        if (!"".equals(filePath)) {
            mtd.agregarPalabrasVacias(filePath);
            btn.setEnabled(false);
        }
    }
    
     private String abrirArchivo() {
        String result = "";
        JFileChooser file = new JFileChooser();
        file.showOpenDialog(file);
        File fileSelected = file.getSelectedFile();
        if (fileSelected != null) {
            result = fileSelected.getPath();
        }
        return result;
    }
}
