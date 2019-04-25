/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.recuperacion;

import java.util.List;

/**
 *
 * @author kero
 */
class Documento implements Comparable<Documento> {

    String URL;
    String name;
    float peso;
    List<String> palabras;
    
    void setURL(String URL) {
        this.URL = URL;
    }

    Object getURL() {
        return URL;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    void setPalabras(List<String> palabras) {
        this.palabras = palabras;
    }

    List<String> getPalabras() {
        return palabras;
    }

    @Override
    public int compareTo(Documento o) {
        int result = 0;
        if(this.peso < o.peso) {
            result = 1;
        } else {
            if (this.peso > o.peso) {
                result = -1;
            }
        }
        return result;
    }

    float getPeso() {
        return peso;
    }

    void setPeso(float d) {
        peso = d;
    }
    
}
