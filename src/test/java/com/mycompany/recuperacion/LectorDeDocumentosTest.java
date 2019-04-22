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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Kero
 */
public class LectorDeDocumentosTest {
    
    private String textoCortoPath;
    private String palabrasVaciasPath;
    private LectorDeDocumentos lector;

    public LectorDeDocumentosTest() {
        textoCortoPath = "C:\\Users\\kero\\Documents\\NetBeansProjects\\recuperacion\\src\\test\\java\\com\\mycompany\\recuperacion\\archivos\\textoCorto.txt";
        palabrasVaciasPath = "C:\\Users\\kero\\Documents\\NetBeansProjects\\recuperacion\\src\\test\\java\\com\\mycompany\\recuperacion\\archivos\\palabrasVacias.txt";
    }
    
    @Before
    public void setUp() {
        lector = new LectorDeDocumentos();
        lector.agregarPalabrasVacias(palabrasVaciasPath);
    }
    
    @Test
    public void testReadDocument() {
        lector.readDocument(textoCortoPath);
        assertEquals(7,lector.getPalabras().size());
    }   
    
    @Test
    public void testGetPalabras() {
        lector.readDocument(textoCortoPath);
        lector.refinarPalabras();
        List<String> result = lector.getPalabras();
        List<String> expected = new ArrayList(Arrays.asList("Este","texto","prueba","corto"));
        assertTrue(result.equals(expected));
    }
    
    @Test
    public void testAgregarPalabrasVacias(){
        String urlPath = "C:\\Users\\kero\\Documents\\NetBeansProjects\\recuperacion\\src\\test\\java\\com\\mycompany\\recuperacion\\archivos\\palabrasVacias.txt";
        boolean cargado = lector.agregarPalabrasVacias(urlPath);
        assertFalse(cargado);
    }
    
    @Test
    public void testLimpiarLista() {
        lector.readDocument(textoCortoPath);
        lector.limpiarLista();
        assertEquals(0, lector.getPalabras().size());
    }
}
