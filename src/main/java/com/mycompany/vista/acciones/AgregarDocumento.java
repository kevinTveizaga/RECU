/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vista.acciones;

import com.mycompany.recuperacion.Metodo;
import com.mycompany.recuperacion.MetodoBooleano;
import com.mycompany.recuperacion.MetodoProbabilistico;
import com.mycompany.recuperacion.MetodoVectorial;
import com.mycompany.vista.ModeloLista;
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
    ModeloLista terminos;
    File fileSelected;
    JFileChooser file;
    File lastFileSelected;

    public AgregarDocumento(Metodo metodo, ModeloListaDocumentos lista) {
        mtd = metodo;
        lst = lista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String filePath = abrirArchivo();
        mtd.agregarDocumento(filePath);
        mtd.nombrarDocumentos();
        if (!"".equals(filePath)) {
            lst.addDocumento();
        }
        if (mtd instanceof MetodoBooleano) {
            MetodoBooleano mb = (MetodoBooleano) mtd;
            mb.eliminarRepetidos();
            terminos.eliminarElementos();
            if(!mb.getTerminos().isEmpty()){
                mb.getTerminos().forEach((term) -> {
                    terminos.adElemento(term);
                });
            }
        } else {
            if(mtd instanceof MetodoVectorial) {
                MetodoVectorial mv = (MetodoVectorial) mtd;
                terminos.eliminarElementos();
                mv.cargarTerminos().forEach((termino) -> {
                    terminos.adElemento(termino);
                });
            } else {
                if (mtd instanceof MetodoProbabilistico) {
                    MetodoProbabilistico mp = (MetodoProbabilistico) mtd;
                    terminos.eliminarElementos();
                    mp.cargarTerminos().forEach((termino) -> {
                       terminos.adElemento(termino);
                    });
                }
            }
        }
    }

    private String abrirArchivo() {
        String result = "";
        if (file == null) {
            file = new JFileChooser();
        } else {
            file.setCurrentDirectory(lastFileSelected);
        }
        file.showOpenDialog(file);
        fileSelected = file.getSelectedFile();
        if(fileSelected != null && fileSelected.isFile()) {
            lastFileSelected = fileSelected.getParentFile();
        }
        if (fileSelected != null) {
            result = fileSelected.getPath();
        }
        return result;
    }

    public void setTerminosList(ModeloLista modListaTerm) {
        terminos = modListaTerm;
    }

}
