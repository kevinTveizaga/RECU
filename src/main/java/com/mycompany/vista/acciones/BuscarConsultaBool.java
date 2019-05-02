/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vista.acciones;

import com.mycompany.recuperacion.Metodo;
import com.mycompany.recuperacion.MetodoBooleano;
import com.mycompany.vista.ModeloLista;
import com.mycompany.vista.ModeloListaTerminos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

/**
 *
 * @author kero
 */
public class BuscarConsultaBool implements ActionListener{

    Metodo mtd;
    ModeloLista resultados;
    JTextField consulta;
    
    public BuscarConsultaBool(Metodo metodo, JTextField con) {
        mtd = metodo;
        consulta = con;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String texto = consulta.getText();
        if (!"".equals(texto)) {
            MetodoBooleano booleano = (MetodoBooleano) mtd;
            booleano.crearMatriz();
            booleano.limpiarResultado();
            String[] resultado = booleano.consultar(texto);
            if (resultado != null) {
                if (resultado.length > 0) {
                    int cResult = 0;
                    resultados.eliminarElementos();
                    while(cResult < resultado.length) {
                        resultados.adElemento(resultado[cResult]);
                        cResult++;
                    }
                }else {
                    resultados.eliminarElementos();
                }   
            }
        }
    }

    public void setResultList(ModeloLista modListaResult) {
        resultados = modListaResult;
    }
}
