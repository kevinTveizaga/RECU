/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vista;

import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;

/**
 *
 * @author kero
 */
public class ModeloLista extends AbstractListModel {

    
    List<String> resultados;
    DefaultListModel<Object> l;
    public ModeloLista() {
        resultados = new ArrayList<>();
    }

    @Override
    public int getSize() {
        return resultados.size();
    }

    @Override
    public Object getElementAt(int index) {
        return resultados.get(index);
    }
    
    public void adElemento(String elemento) {
        int indice = resultados.size();
        resultados.add(elemento);
        this.fireIntervalAdded(this, indice, indice+1);
    }
    
    public void eliminarElementos(){
        int indice = resultados.size();
        resultados.clear();
        if (indice >= 0) {
            fireIntervalRemoved(this, 0, indice);
        }
    }
}
