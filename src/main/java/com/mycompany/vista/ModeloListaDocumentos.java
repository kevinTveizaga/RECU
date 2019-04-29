/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vista;

import com.mycompany.recuperacion.Documento;
import com.mycompany.recuperacion.Metodo;
import javax.swing.AbstractListModel;

/**
 *
 * @author kero
 */
public class ModeloListaDocumentos extends AbstractListModel{

    Metodo metodo;
    
    public ModeloListaDocumentos(Metodo metodo) {
        this.metodo = metodo;
    }
    
    @Override
    public int getSize() {
        return metodo.getDocumentos().size();
    }

    @Override
    public Object getElementAt(int index) {
        String nombre = metodo.getDocumentos().get(index).getName();
        return nombre;
    }
    
    public void addDocumento() {
        this.fireIntervalAdded(this, getSize(), getSize()+1);
    }
    
}
