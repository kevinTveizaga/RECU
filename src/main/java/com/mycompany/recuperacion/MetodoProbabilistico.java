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
public class MetodoProbabilistico implements Metodo{

    List<Documento> documentos;
    LectorDeDocumentos lector;
    List<String> terminos;
    String NOMBRE_DOCUMENTO = "D";
    Map<String, Integer> freqTerminConsulta;
    List<Float> pesos;
    int[][] matBin;

    public MetodoProbabilistico() {
        lector = new LectorDeDocumentos();
        documentos = new ArrayList<>();
        terminos = new ArrayList<>();
        freqTerminConsulta = new HashMap<>();
        pesos = new ArrayList<>();
    }

    @Override
    public void agregarDocumento(String docExt) {
        lector.leerDocumento(docExt);
        lector.refinarPalabras();
        Documento documento = new Documento();
        documento.setPalabras(new ArrayList(lector.getPalabras()));
        documentos.add(documento);
        lector.limpiarLista();
    }

    @Override
    public List<Documento> getDocumentos() {
        return documentos;
    }

    public List<String> cargarTerminos() {
        documentos.forEach((terminoAux) -> {
            terminoAux.getPalabras().stream().filter((elemento) -> 
                    (!terminos.contains(elemento))).forEachOrdered((elemento) -> {
                        terminos.add(elemento);
                    });
        });
        return terminos;
    }

    @Override
    public void nombrarDocumentos() {
        if (!documentos.isEmpty()) {
            for (int i = 0; i < documentos.size(); i++) {
                documentos.get(i).setName(NOMBRE_DOCUMENTO + (i + 1));
            }
        }
    }

    @Override
    public boolean agregarPalabrasVacias(String palabras) {
        return lector.agregarPalabrasVacias(palabras);
    }

    public int[][] crearMatrizBinaria() {
        matBin = new int[terminos.size()][documentos.size()];
        for (int doc = 0; doc < documentos.size(); doc++) {
            for (int indTerminos = 0; indTerminos < terminos.size(); indTerminos++) {
                if (documentos.get(doc).getPalabras().contains(terminos.get(indTerminos))) {
                    matBin[indTerminos][doc] = 1;
                } else {
                    matBin[indTerminos][doc] = 0;
                }
            }
        }
        return matBin;
    }

    void cargarConsulta(String consulta) {
        lector.leerConsulta(consulta);
        lector.refinarPalabras();
        lector.getPalabras().stream().filter((cTermino) -> (terminos.contains(cTermino))).forEachOrdered((cTermino) -> {
            int counter = 0;
            int index = 0;
            while (index < documentos.size()) {
                if (documentos.get(index).getPalabras().contains(cTermino)) {
                    counter++;
                }
                index++;
            }

            freqTerminConsulta.put(cTermino, counter);
        });
        lector.limpiarLista();
    }

    Map<String, Integer> getFreqTermConsultas() {
        return freqTerminConsulta;
    }

    void calcularPeso() {
        for (String termino : freqTerminConsulta.keySet()) {
            float ni = freqTerminConsulta.get(termino);
            float n = documentos.size();
            NumberFormat formatter = new DecimalFormat("0.000");
            float peso = (float) Math.log10((1 - (ni / n)) / (ni / n));
            float resultado = Float.parseFloat(formatter.format(peso).replace(',', '.'));
            pesos.add(resultado);
        }
    }

    List<Float> getPesos() {
        return pesos;
    }

    List<Documento> calcSimProb() {
        List<Documento> resultado = new ArrayList<>();
        for (int iDoc = 0; iDoc < documentos.size(); iDoc++) {
            List<String> keys = new ArrayList<>(freqTerminConsulta.keySet());
            float suma = 0.0f;
            for (int iPeso = 0; iPeso < pesos.size(); iPeso++) {
                int iTerm = terminos.indexOf(keys.get(iPeso));
                suma = suma + (pesos.get(iPeso) * matBin[iTerm][iDoc]);
            }
            if(suma != 0.000f) {
                Documento temp = documentos.get(iDoc);
                temp.setPeso(suma);
                resultado.add(temp);
                Collections.sort(resultado);
            }
        }
        return resultado;
    }

    @Override
    public List<String> getTerminos() {
        return terminos;
    }
}
