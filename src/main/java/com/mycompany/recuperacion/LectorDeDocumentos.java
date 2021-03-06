/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.recuperacion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kero
 */
public class LectorDeDocumentos {

    private final ArrayList<String> palabras;
    private final ArrayList<String> palabrasVacias;

    public LectorDeDocumentos() {
        palabrasVacias = new ArrayList<>();
        palabras = new ArrayList<>();
    }

    public boolean leerDocumento(String filePath) {
        boolean result = false;
        if (!filePath.isEmpty()) {
            File file = new File(filePath);
            if (file.isFile()) {
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
                    String text;
                    while ((text = reader.readLine()) != null) {
                        separarPalabras(text);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(LectorDeDocumentos.class.getName()).log(Level.SEVERE, null, ex);
                }
                result = true;
            }
        }
        return result;
    }

    public boolean agregarPalabrasVacias(String urlPath) {
        boolean result = false;
        if (!urlPath.isEmpty()) {
            File file = new File(urlPath);
            if (file.isFile()) {
                try {
                    String palabraVacia;
                    BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
                    while ((palabraVacia = reader.readLine()) != null) {
                        for(String palabra : palabraVacia.split("\\s")){
                            if(!palabrasVacias.contains(palabra)) {
                                palabrasVacias.add(palabra.toLowerCase());
                            }
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(LectorDeDocumentos.class.getName()).log(Level.SEVERE, null, ex);
                }
                result = true;
            }
        }
        return result;
    }

    public List<String> getPalabrasVacias() {
        return palabrasVacias;
    }

    public List<String> getPalabras() {
        return palabras;
    }

    private void separarPalabras(String lineaTexto) {
        String[] frase = lineaTexto.toLowerCase().split("[^(a-zA-ZÀ-ÿ)]+");
        this.palabras.addAll(Arrays.asList(frase));
    }

    public void limpiarLista() {
        palabras.clear();
    }

    public void refinarPalabras() {
        List<String> aux = new ArrayList<>();
        palabras.forEach((termino) -> {
            if (palabrasVacias.contains(termino)) {
                aux.add(termino);
            }
        });
        palabras.removeAll(aux);
    }

    void leerConsulta(String consulta) {
        separarPalabras(consulta);
        refinarPalabras();
    }
}
