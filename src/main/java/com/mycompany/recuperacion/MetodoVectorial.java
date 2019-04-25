/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.recuperacion;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Kero
 */
public class MetodoVectorial {

    LectorDeDocumentos lector;
    List<List<String>> documentos;
    List<String> terminos;
    Map<String,Integer> idf;
    int[][]tf;
    float[][] mPesos;
    List<List<String>> consultas;
    int[][]tfConsulta;
    float[][]mPesoConsultas;
    Map<String, Integer> idfConsulta;
    
    public MetodoVectorial() {
        lector = new LectorDeDocumentos();
        documentos = new ArrayList<>();
        terminos = new ArrayList<>();
        consultas = new ArrayList<>();
        idf = new HashMap<>();
    }
    
    void addDocument(String doc) {
        lector.leerDocumento(doc);
        lector.refinarPalabras();
        documentos.add(new ArrayList(lector.getPalabras()));
        lector.limpiarLista();
    }
    
    boolean agregarPalabrasVacias(String palabras) {
        return lector.agregarPalabrasVacias(palabras);
    }
    
    void crearTF() {
        tf = new int[terminos.size()][documentos.size()];
        for(int docIndex = 0; docIndex < documentos.size(); docIndex++) {
            for (int termIndex = 0; termIndex < terminos.size(); termIndex++) {
               for(String termino: documentos.get(docIndex)){
                   if(terminos.get(termIndex).equals(termino)){
                       tf[termIndex][docIndex] = tf[termIndex][docIndex]+1;
                   } 
               }
               
            }
        }
    }
    
    List<String> cargarTerminos() {
        documentos.forEach((terminosAux) -> {
            terminosAux.stream().filter((element) -> 
                    (!terminos.contains(element))).forEachOrdered((element) -> {
                terminos.add(element);
            });
        });
        return terminos;
    }

    void crearMatrizPesos() {
        mPesos = new float[terminos.size()][documentos.size()];
        for(int iTerm = 0; iTerm< terminos.size(); iTerm++) {
            for(int iDoc = 0; iDoc< documentos.size(); iDoc++) {
                NumberFormat formatter = new DecimalFormat("0.000");
                double division = (double)documentos.size()/idf.get(terminos.get(iTerm));
                String numberFormatted = formatter.format(Math.log10(division));
                float number = Float.parseFloat(numberFormatted.replace(',', '.'));
                mPesos[iTerm][iDoc] = number * tf[iTerm][iDoc];
            }
        }
    }

    void llenarIdf() {
        for(int indexTerm = 0; indexTerm < terminos.size(); indexTerm++) {
            int contador = 1;
            for(int indexDoc = 0; indexDoc < documentos.size(); indexDoc++) {
                if(documentos.get(indexDoc).contains(terminos.get(indexTerm))) {
                    idf.put(terminos.get(indexTerm), contador++);
                }
            }
        }
    }
    
    void agregarConsulta(String consulta) {
        lector.leerConsulta(consulta);
        consultas.add(lector.getPalabras());
    }
    
    void crearTfConsulta() {
        tfConsulta = new int[terminos.size()][consultas.size()];
        for(int queIndex = 0; queIndex < consultas.size(); queIndex++) {
            for (int termIndex = 0; termIndex < terminos.size(); termIndex++) {
               for(String termino: consultas.get(queIndex)){
                   if(terminos.get(termIndex).equals(termino)){
                       tfConsulta[termIndex][queIndex] = tfConsulta[termIndex][queIndex]+1;
                   } 
               }
               
            }
        }
    }
    
    Map<String, Integer> getIdf() {
        return idf;
    }
    
    float[][] getMPesos() {
        return mPesos;
    }
    
    int[][] getTf() {
        return tf;
    }

    List<List<String>> getDocuments() {
        return documentos;
    }

    List<List<String>> getConsultas() {
        return consultas;
    }


    int[][] getTfConsulta() {
        return tfConsulta;
    }
}
