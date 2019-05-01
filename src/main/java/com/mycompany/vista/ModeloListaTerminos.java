/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vista;

import com.mycompany.recuperacion.Metodo;
import javax.swing.AbstractListModel;

/**
 *
 * @author kero
 */
public class ModeloListaTerminos extends AbstractListModel{

    Metodo metodo;
    
    public ModeloListaTerminos(Metodo metodo) {
        this.metodo = metodo;
    }
        
    @Override
    public int getSize() {
        return metodo.getTerminos().size();
    }

    @Override
    public Object getElementAt(int index) {
        return metodo.getTerminos().get(index);
    }
    
    public void addTermino() {
        this.fireIntervalAdded(this, getSize(), getSize()+1);
    }
}
