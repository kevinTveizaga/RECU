/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.recuperacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author Kero
 */
public class MetodoBooleano {
    
    private final LectorDeDocumentos lector;
    private final List<Documento> documentos;
    private final List<String> terminos;
    private final String NOMBRE_DOCUMENTO = "D";
    private int[][] matriz;
    private Queue<String> numeros;
    private Queue<String> signos;
    
    public MetodoBooleano() {
        lector = new LectorDeDocumentos();
        documentos = new ArrayList<>();
        terminos = new ArrayList<>();
        numeros = new LinkedBlockingQueue<>();
        signos = new LinkedBlockingQueue<>();
    }

    public void agregarDocumento(String urlDocumento) {
        lector.leerDocumento(urlDocumento);
        lector.refinarPalabras();
        Documento doc = new Documento();
        doc.setPalabras(new ArrayList<>(lector.getPalabras()));
        documentos.add(doc);
        lector.limpiarLista();
    }
    
    public boolean agregarPalabrasVacias(String palabras) {
        return lector.agregarPalabrasVacias(palabras);
    }
    
    public List<Documento> getDocumentos() {
        return documentos;
    }

    public List<String> eliminarRepetidos() {
        documentos.forEach((terminosAux) -> {
            terminosAux.getPalabras().stream().filter((element) -> 
                    (!terminos.contains(element))).forEachOrdered((element) -> {
                terminos.add(element);
            });
        });
        return terminos;
    }

    public int[][] crearMatriz() {
        matriz = new int[terminos.size()][documentos.size()];
        for(int doc = 0; doc < documentos.size(); doc++) {
            for(int indTerminos = 0; indTerminos < terminos.size(); indTerminos++){
                if(documentos.get(doc).getPalabras().contains(terminos.get(indTerminos))) {
                    matriz[indTerminos][doc] = 1;
                } else {
                    matriz[indTerminos][doc] = 0;
                }
            }
        }
        return matriz;
    }

    void nombrarDocumentos() {
        if (!documentos.isEmpty()) {
            for (int i = 0; i < documentos.size(); i++) {
                documentos.get(i).setName(NOMBRE_DOCUMENTO + (i + 1));
            }
        }
    }
    
    String[] consultar(String consulta) {
        String[]result;
        String[] con = consulta.toLowerCase().split("\\s");
        String resultado = "";
        clasificarSigno(con);
        if(consulta.length()>1){	
            String resAct = numeros.peek();
            while (!signos.isEmpty()) {
                if(signos.peek().equals("(")){
                    this.implementacionParentesis(resAct);					
                }else{
                    String term1= numeros.peek();
                    numeros.poll();
                    if(!numeros.isEmpty()){
                        String term2= numeros.peek();
                        numeros.poll();
                        resAct=calcularExpresion(term1,term2,signos.peek());
                        signos.poll();
                    }
                    String resultadoActualString2= String.valueOf(resAct);
                    numeros.add(resultadoActualString2);

                }
                resultado = numeros.peek();
            }
        } else{ 
            resultado= numeros.peek();

        }
        result = escogerDocumento(resultado);
        return result;
    }
    
    String[] escogerDocumento(String resultado) {
        List<String> respuesta = new ArrayList<>();
        if(!"".equals(resultado)){
            for(int i = 0; i < resultado.length(); i++) {
                if ('1' == resultado.charAt(i)) {
                    respuesta.add(documentos.get(i).getName());
                }
            }
        }
        return respuesta.toArray(new String[0]);
    }
    
    String convertirABinario(String palabra) {
        String terminoInt = "";
        for(int iDoc = 0; iDoc <documentos.size(); iDoc++) {
            int iTerm = terminos.indexOf(palabra);
            terminoInt = terminoInt + matriz[iTerm][iDoc];
        }
        return terminoInt;
    }

    String calcularExpresion(String term1, String term2, String operador) {
        String result = "";
        switch(operador) {
            case "and" :
                result = calcularAnd(term1, term2);
                break;
            case "or" :
                result = calcularOr(term1, term2);
                break;
        }
        return result;
    }

    String calcularAnd(String term1, String term2) {
        String result = "";
        if(term1.length() == term2.length()) {
            for(int iC = 0; iC < term1.length(); iC++) {
                if (term1.charAt(iC) == term2.charAt(iC)) {
                    if (term1.charAt(iC) == '1' && term2.charAt(iC) == '1') {
                        result += "1";
                    } else {
                        result += "0";
                    }
                } else {
                    result += "0";
                }
            }
        }
        return result;
    }
    String calcularOr(String term1, String term2) {
        String result = "";
        if(term1.length() == term2.length()) {
            for(int iC = 0; iC < term1.length(); iC++) {
                if (term1.charAt(iC) == term2.charAt(iC)) {
                    if (term1.charAt(iC) == '0' && term2.charAt(iC) == '0') {
                        result += "0";
                    } else {
                        result += "1";
                    }
                } else {
                    result += "1";
                }
            }
        }
        return result;
    }

    void clasificarSigno(String[] expresion) {
        for (String expresion1 : expresion) {
            if ("and".equals(expresion1) || "or".equals(expresion1) || "(".equals(expresion1) || ")".equals(expresion1)) {
                signos.add(expresion1);
            } else {
                numeros.add(convertirABinario(expresion1));
            }
        }
    }

    void implementacionParentesis(String term) {
        signos.poll();
        while(!(signos.peek().equals(")"))){
            if(signos.peek().equals("(")){
                signos.poll();
                signos.add("(");
            }
            String term1= numeros.peek();
            numeros.poll();
            if(!(numeros.isEmpty()&&!(signos.isEmpty()))){
                String signoRevisado = signos.peek();
                signos.poll();
                if(signos.peek().equals("(")){
                    signos.add("(");
                    signos.add(signoRevisado);
                }else{
                    String term2= numeros.peek();
                    numeros.poll();	
                    term=calcularExpresion(term1,term2,signoRevisado);

                    String numero= String.valueOf(term);
                    numeros.add(numero);				
                }
            }
        }
        if(signos.peek().equals(")")){
            signos.poll();
            if(!signos.isEmpty()){
                String signoAUsar=signos.peek();
                signos.poll();
                signos.add(signoAUsar);
            }
        }

    }
    
    Queue<String> getSignos() {
        return signos;
    }

    Queue<String> getNumeros() {
        return numeros;
    }
}
