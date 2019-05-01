/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vista;

import com.mycompany.recuperacion.MetodoBooleano;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;

/**
 *
 * @author kero
 */
public class ModeloListaResultados extends AbstractListModel {

    MetodoBooleano mtd;
    List<String> resultados;
    DefaultListModel<Object> l;
    public ModeloListaResultados(MetodoBooleano booleano) {
        mtd = booleano;
        resultados = new ArrayList<>();
    }

    @Override
    public int getSize() {
        int result = 0;
        if (mtd.getResult() != null) {
            resultados = new ArrayList<>(Arrays.asList(mtd.getResult()));
            result = resultados.size();
        }
        return result;
    }

    @Override
    public Object getElementAt(int index) {
        return resultados.get(index);
    }
    
    public void addResultado(String resultado) {
        int indice = resultados.size();
        resultados.add(resultado);
        this.fireIntervalAdded(this, indice, indice+1);
    }
    
    public void eliminarResultados(){
        int indice = resultados.size();
        if (indice >= 0) {
            fireIntervalRemoved(this, 0, indice);
        }
    }
}
