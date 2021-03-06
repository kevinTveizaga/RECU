/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.recuperacion;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Kero
 */
public class MetodoVectorial implements Metodo {

    LectorDeDocumentos lector;
    List<Documento> documentos;
    List<String> terminos;
    Map<String, Integer> idf;
    int[][] tf;
    float[][] mPesos;
    List<Documento> consultas;
    int[][] tfConsulta;
    float[][] mPesoConsultas;

    public MetodoVectorial() {
        lector = new LectorDeDocumentos();
        documentos = new ArrayList<>();
        terminos = new ArrayList<>();
        consultas = new ArrayList<>();
        idf = new HashMap<>();
    }

    @Override
    public void agregarDocumento(String doc) {
        lector.leerDocumento(doc);
        lector.refinarPalabras();
        Documento documento = new Documento();
        documento.setPalabras(new ArrayList(lector.getPalabras()));
        documentos.add(documento);
        lector.limpiarLista();
    }

    @Override
    public boolean agregarPalabrasVacias(String palabras) {
        return lector.agregarPalabrasVacias(palabras);
    }

    public void crearTF() {
        tf = new int[terminos.size()][documentos.size()];
        for (int docIndex = 0; docIndex < documentos.size(); docIndex++) {
            for (int termIndex = 0; termIndex < terminos.size(); termIndex++) {
                for (String termino : documentos.get(docIndex).getPalabras()) {
                    if (terminos.get(termIndex).equals(termino)) {
                        tf[termIndex][docIndex] = tf[termIndex][docIndex] + 1;
                    }
                }

            }
        }
    }

    public List<String> cargarTerminos() {
        documentos.forEach((Documento terminoAux) -> {
            terminoAux.getPalabras().stream().filter((String elemento) -> 
            (!terminos.contains(elemento))).forEachOrdered((elemento) -> {
                terminos.add(elemento);
            });
        });
        return terminos;
    }

    public void crearMatrizPesos() {
        mPesos = new float[terminos.size()][documentos.size()];
        for (int iTerm = 0; iTerm < terminos.size(); iTerm++) {
            for (int iDoc = 0; iDoc < documentos.size(); iDoc++) {
                NumberFormat formatter = new DecimalFormat("0.000");
                double division = (double) documentos.size() / idf.get(terminos.get(iTerm));
                String numberFormatted = formatter.format(Math.log10(division));
                float number = Float.parseFloat(numberFormatted.replace(',', '.'));
                mPesos[iTerm][iDoc] = number * tf[iTerm][iDoc];
            }
        }
    }

    public void llenarIdf() {
        for (int indexTerm = 0; indexTerm < terminos.size(); indexTerm++) {
            int contador = 1;
            for (int indexDoc = 0; indexDoc < documentos.size(); indexDoc++) {
                if (documentos.get(indexDoc).getPalabras().contains(terminos.get(indexTerm))) {
                    idf.put(terminos.get(indexTerm), contador++);
                }
            }
        }
    }

    public void agregarConsulta(String consulta) {
        lector.leerConsulta(consulta);
        Documento documento = new Documento();
        documento.setPalabras(new ArrayList<>(lector.getPalabras()));
        consultas.add(documento);
        lector.limpiarLista();
    }

    public void crearTfConsulta() {
        tfConsulta = new int[terminos.size()][consultas.size()];
        for (int queIndex = 0; queIndex < consultas.size(); queIndex++) {
            for (int termIndex = 0; termIndex < terminos.size(); termIndex++) {
                for (String termino : consultas.get(queIndex).getPalabras()) {
                    if (terminos.get(termIndex).equals(termino)) {
                        tfConsulta[termIndex][queIndex] = tfConsulta[termIndex][queIndex] + 1;
                    }
                }

            }
        }
    }

    public void crearMatrizPesosConsulta() {
        mPesoConsultas = new float[terminos.size()][consultas.size()];
        for (int iTerm = 0; iTerm < terminos.size(); iTerm++) {
            for (int iQuery = 0; iQuery < consultas.size(); iQuery++) {
                NumberFormat formatter = new DecimalFormat("0.000");
                int N = documentos.size();
                double division = (double) N / idf.get(terminos.get(iTerm));
                String numberFormatted = formatter.format(Math.log10(division));
                float number = Float.parseFloat(numberFormatted.replace(',', '.'));
                mPesoConsultas[iTerm][iQuery] = number * tfConsulta[iTerm][iQuery];
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

    @Override
    public List<Documento> getDocumentos() {
        return documentos;
    }

    public List<Documento> getConsultas() {
        return consultas;
    }

    int[][] getTfConsulta() {
        return tfConsulta;
    }

    float[][] getMatrizPesosConsulta() {
        return mPesoConsultas;
    }

    public List<Documento> calcularSimilaridad() {
        List<Documento> result = new ArrayList<>();
        for (int iQuery = 0; iQuery < consultas.size(); iQuery++) {
            for (int iDoc = 0; iDoc < documentos.size(); iDoc++) {
                float sumatoriaSim = 0.0f;
                for (int iTerm = 0; iTerm < terminos.size(); iTerm++) {
                    
                    sumatoriaSim += mPesos[iTerm][iDoc] * mPesoConsultas[iTerm][iQuery];
                }
                if (sumatoriaSim > 0.0f) {
                    documentos.get(iDoc).setPeso(sumatoriaSim);
                    result.add(documentos.get(iDoc));
                    Collections.sort(result);
                }
            }
        }
        return result;
    }

    @Override
    public void nombrarDocumentos() {
        if (!documentos.isEmpty()) {
            for (int i = 0; i < documentos.size(); i++) {
                documentos.get(i).setName("D" + (i + 1));
            }
        }
    }

    @Override
    public List<String> getTerminos() {
        return terminos;
    }
    
    public void limpiarResultado() {
        consultas.clear();
        tfConsulta = new int[0][0];
        mPesoConsultas = new float[0][0];
        idf.clear();
        tf = new int[0][0];
        mPesos = new float[0][0];
    }
}
