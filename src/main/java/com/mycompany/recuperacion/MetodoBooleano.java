/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.recuperacion;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kero
 */
public class MetodoBooleano {
    
    private final LectorDeDocumentos lector;
    private final List<List<String>> documentos;
    private final List<String> terminos;
    
    public MetodoBooleano() {
        lector = new LectorDeDocumentos();
        documentos = new ArrayList<>();
        terminos = new ArrayList<>();
    }
    
    public String buscarTexto(String texto) {
        return "";
    }

    public void agregarDocumento(String urlDocumento) {
        lector.leerDocumento(urlDocumento);
        lector.refinarPalabras();
        documentos.add(new ArrayList<>(lector.getPalabras()));
        lector.limpiarLista();
    }
    
    public boolean agregarPalabrasVacias(String palabras) {
        return lector.agregarPalabrasVacias(palabras);
    }
    
    public List<List<String>> getDocumentos() {
        return documentos;
    }

    public List<String> eliminarRepetidos() {
        documentos.forEach((terminosAux) -> {
            terminosAux.stream().filter((element) -> 
                    (!terminos.contains(element))).forEachOrdered((element) -> {
                terminos.add(element);
            });
        });
        return terminos;
    }

    public int[][] crearMatriz() {
        int[][] result = new int[terminos.size()][documentos.size()];
        for(int doc = 0; doc < documentos.size(); doc++) {
            for(int indTerminos = 0; indTerminos < terminos.size(); indTerminos++){
                if(documentos.get(doc).contains(terminos.get(indTerminos))) {
                    result[indTerminos][doc] = 1;
                } else {
                    result[indTerminos][doc] = 0;
                }
            }
        }
        return result;
    }

    public String[] consulta(String consulta) {
        String[] resultado = new String[documentos.size()];
        
        return resultado;
    }
}
