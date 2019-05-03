/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vista.acciones;

import com.mycompany.recuperacion.Documento;
import com.mycompany.recuperacion.MetodoVectorial;
import com.mycompany.vista.ModeloLista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JTextField;

/**
 *
 * @author kero
 */
public class BuscarConsultaVecto implements ActionListener{

    MetodoVectorial mtd;
    ModeloLista resultados;
    JTextField txt;
    
    public BuscarConsultaVecto(MetodoVectorial metodo, JTextField txtField) {
        mtd = metodo;
        txt = txtField;
    }
        
    @Override
    public void actionPerformed(ActionEvent e) {
        String texto = txt.getText();
        if (!"".equals(texto)) {
            MetodoVectorial vectorial =  (MetodoVectorial) mtd;
            vectorial.limpiarResultado();
            vectorial.agregarConsulta(texto);
            vectorial.crearTF();
            vectorial.llenarIdf();
            vectorial.crearTfConsulta();
            vectorial.crearMatrizPesos();
            vectorial.crearMatrizPesosConsulta();
            List<Documento> resultado = vectorial.calcularSimilaridad();
            if (!resultado.isEmpty()) {
                if (resultado.size() > 0) {
                    int cResult = 0;
                    resultados.eliminarElementos();
                    while(cResult < resultado.size()) {
                        resultados.adElemento(resultado.get(cResult).getName());
                        cResult++;
                    }
                }else {
                    resultados.eliminarElementos();
                }   
            } else {
                resultados.eliminarElementos();
            }
        } else {
            resultados.eliminarElementos();
        }
    }
      public void setResultList(ModeloLista modListaResult) {
        resultados = modListaResult;
    }
}
