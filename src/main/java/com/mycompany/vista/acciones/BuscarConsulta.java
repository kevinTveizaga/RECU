/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vista.acciones;

import com.mycompany.recuperacion.Metodo;
import com.mycompany.recuperacion.MetodoBooleano;
import com.mycompany.vista.ModeloListaResultados;
import com.mycompany.vista.ModeloListaTerminos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JTextField;

/**
 *
 * @author kero
 */
public class BuscarConsulta implements ActionListener{

    Metodo mtd;
    ModeloListaTerminos terminos;
    ModeloListaResultados resultados;
    JTextField consulta;
    
    public BuscarConsulta(Metodo metodo, JTextField con) {
        mtd = metodo;
        consulta = con;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String texto = consulta.getText();
        if (!"".equals(texto)) {
            MetodoBooleano booleano = (MetodoBooleano) mtd;
            booleano.eliminarRepetidos();
            booleano.crearMatriz();
            booleano.limpiarResultado();
            String[] resultado = booleano.consultar(texto);
            int cTerm = 0;
            while(cTerm<terminos.getSize()) {
                terminos.addTermino();
                cTerm++;
            }
            String[] lResultados = booleano.getResult();
            if (lResultados.length > 0) {
                int cResult = 0;
                while(cResult < resultado.length) {
                    resultados.addResultado(lResultados[cResult]);
                    cResult++;
                }
            }else {
                resultados.eliminarResultados();
            }
        }
    }

    public void setTerminosList(ModeloListaTerminos modListaTerm) {
        terminos = modListaTerm;
    }

    public void setResultList(ModeloListaResultados modListaResult) {
        resultados = modListaResult;
    }
}
