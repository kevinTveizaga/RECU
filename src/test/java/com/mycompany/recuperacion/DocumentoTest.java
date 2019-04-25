/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.recuperacion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kero
 */
public class DocumentoTest {
    
    Documento documento;
    String URL = "C:\\Users\\kero\\Documents\\NetBeansProjects\\recuperacion\\src\\test\\java\\com\\mycompany\\recuperacion\\archivos\\textoCorto.txt";
    
    public DocumentoTest() {
        documento = new Documento();
    }
    
    @Test
    public void testSetURL() {
        documento.setURL(URL);
        String expected = "C:\\Users\\kero\\Documents\\NetBeansProjects\\recuperacion\\src\\test\\java\\com\\mycompany\\recuperacion\\archivos\\textoCorto.txt";
        assertEquals(expected, documento.getURL());
    }
        
    @Test
    public void testSetName() {
        documento.setName("D2");
        String expected = "D2";
        assertEquals(expected, documento.getName());
    }
    
    @Test
    public void testSetPalabras() {
        List<String> palabras = new ArrayList<>(Arrays.asList("esta","es","una","linea"));
        documento.setPalabras(palabras);
        assertEquals(palabras, documento.getPalabras()); 
    }
    
    @Test
    public void testGetPeso() {
        documento.setPeso(0.45f);
        assertEquals(0.45f, documento.getPeso(),0.00);
    }
    
    @Test
    public void testCompareTo() {
        Documento d1 = new Documento();
        Documento d2 = new Documento();
        d1.setPeso(0.23f);
        d2.setPeso(0.23f);
        assertEquals(0, d1.compareTo(d2));
    }
}
