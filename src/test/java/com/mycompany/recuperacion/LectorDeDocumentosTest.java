/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.recuperacion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Kero
 */
public class LectorDeDocumentosTest {
    
    String filePath = "C:\\Users\\Kero\\Documents\\NetBeansProjects\\recuperacion\\src\\main\\java\\com\\mycompany\\archivos\\textoCorto.txt";
    LectorDeDocumentos lector;

    @Before
    public void setUp() {
        lector = new LectorDeDocumentos();
    }
    
    @Test
    public void testReadDocument() {
        boolean lectura = lector.readDocument(filePath);
        assertEquals(7,lector.getPalabras().size());
        assertTrue(lectura);
    }   
    
    @Test
    public void testGetPalabras() {
        lector.readDocument(filePath);
        List<String> result = lector.getPalabras();
        List<String> expected = new ArrayList(Arrays.asList("Este","es","un","texto","de","prueba","corto"));
        assertTrue(result.equals(expected));
    }
    
    @Test
    public void testAgregarPalabrasVacias(){
        String urlPath = "C:\\Users\\kero\\Documents\\NetBeansProjects\\recuperacion\\src\\main\\java\\com\\mycompany\\archivos\\palabrasVacias.txt";
        boolean cargado = lector.agregarPalabrasVacias(urlPath);
        assertTrue(cargado);
    }
    
    
}
