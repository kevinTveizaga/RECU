/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.recuperacion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Kero
 */
public class LectorDeDocumentosTest {
    
    private final String textoCortoPath;
    private final String palabrasVaciasPath;
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
    public void testLeerDocumento() {
        lector.leerDocumento(textoCortoPath);
        assertEquals(7,lector.getPalabras().size());
    }   
    
    @Test
    public void testGetPalabras() {
        lector.leerDocumento(textoCortoPath);
        lector.refinarPalabras();
        List<String> result = lector.getPalabras();
        List<String> expected = new ArrayList(Arrays.asList("Este","texto","prueba","corto"));
        assertTrue(result.equals(expected));
    }
    
    @Test
    public void testAgregarPalabrasVacias(){
        String urlPath = "C:\\Users\\kero\\Documents\\NetBeansProjects\\recuperacion\\src\\test\\java\\com\\mycompany\\recuperacion\\archivos\\palabrasVacias.txt";
        boolean cargado = lector.agregarPalabrasVacias(urlPath);
        assertTrue(cargado);
    }
    
    @Test
    public void testLimpiarLista() {
        lector.leerDocumento(textoCortoPath);
        lector.limpiarLista();
        assertEquals(0, lector.getPalabras().size());
    }
    
    @Test
    public void testRefinarPalabras() {
        lector.leerDocumento(textoCortoPath);
        lector.refinarPalabras();
        List<String> expected = new ArrayList<>(Arrays.asList("Este","texto", "prueba","corto"));
        assertArrayEquals(expected.toArray(), lector.getPalabras().toArray());
    }
    
    @Test 
    public void testLeerConsulta() {
        lector.leerConsulta("un ejemplo de consulta corta e interesante");
        List<String> expected = new ArrayList<>(Arrays.asList("ejemplo","consulta","corta", "interesante"));
        assertArrayEquals(expected.toArray(), lector.getPalabras().toArray());
    }
}
