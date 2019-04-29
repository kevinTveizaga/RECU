/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vista.acciones;

import com.mycompany.recuperacion.Metodo;
import com.mycompany.vista.ModeloListaDocumentos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author kero
 */
public class AgregarDocumento implements ActionListener {
   
    Metodo mtd;
    ModeloListaDocumentos lst; 
    File fileSelected;
    public AgregarDocumento(Metodo metodo, ModeloListaDocumentos lista) {
        mtd = metodo;
        lst = lista;
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String filePath = abrirArchivo();
        mtd.agregarDocumento(filePath);
        mtd.nombrarDocumentos();
        if(fileSelected != null) {
            lst.addDocumento();
        }
    }

    private String abrirArchivo() {
        String result = "";
        JFileChooser file = new JFileChooser();
        file.showOpenDialog(file);
        fileSelected = file.getSelectedFile();
        if (fileSelected != null) {
            result = fileSelected.getPath();
        }
        return result;
    }
}
