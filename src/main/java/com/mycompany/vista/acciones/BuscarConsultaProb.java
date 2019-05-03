/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vista.acciones;

import com.mycompany.recuperacion.Documento;
import com.mycompany.recuperacion.MetodoProbabilistico;
import com.mycompany.vista.ModeloLista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

/**
 *
 * @author kero
 */
public class BuscarConsultaProb implements ActionListener {

    MetodoProbabilistico mtd;
    JTextField txt;
    ModeloLista resultados;
    
    public BuscarConsultaProb(MetodoProbabilistico probabilistico, JTextField txtFBuscarProb) {
        mtd = probabilistico;
        txt = txtFBuscarProb;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String texto = txt.getText();    
        if (!"".equals(texto)) {
            mtd.limpiarResultado();
            mtd.crearMatrizBinaria();
            mtd.cargarConsulta(texto);
            mtd.calcularPeso();
            if (!mtd.calcSimProb().isEmpty()) {
                resultados.eliminarElementos();
                mtd.calcSimProb().forEach((doc) -> {
                    resultados.adElemento(doc.getName());
                });
            } else {
                resultados.eliminarElementos();
            }
        } else {
            resultados.eliminarElementos();
        }
    }

    public void setResultList(ModeloLista modListaResult) {
        resultados=  modListaResult;
    }
    
}
