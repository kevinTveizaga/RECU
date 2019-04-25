/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.recuperacion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.Before;

/**
 *
 * @author kero
 */
public class MetodoVectorialTest {
    
    private final String doc1;
    private final String doc2;
    private final String doc3;
    private final String doc4;
    private final String palabrasVacias;
    private MetodoVectorial metodo;
    
    public MetodoVectorialTest() {
        this.doc1 = "C:\\Users\\kero\\Documents\\NetBeansProjects\\recuperacion\\src\\test\\java\\com\\mycompany\\recuperacion\\documentos\\DocumentoExtendido.txt";
        this.doc2 = "C:\\Users\\kero\\Documents\\NetBeansProjects\\recuperacion\\src\\test\\java\\com\\mycompany\\recuperacion\\documentos\\Documento2.txt";
        this.doc3 = "C:\\Users\\kero\\Documents\\NetBeansProjects\\recuperacion\\src\\test\\java\\com\\mycompany\\recuperacion\\documentos\\Documento3.txt";
        this.doc4 = "C:\\Users\\kero\\Documents\\NetBeansProjects\\recuperacion\\src\\test\\java\\com\\mycompany\\recuperacion\\documentos\\Documento4.txt";
        this.palabrasVacias = "C:\\Users\\kero\\Documents\\NetBeansProjects\\recuperacion\\src\\test\\java\\com\\mycompany\\recuperacion\\archivos\\palabrasVacias.txt";
    }

    @Before
    public void setUp() {
        metodo = new MetodoVectorial();
        metodo.agregarPalabrasVacias(palabrasVacias);
        metodo.addDocument(doc1);
        metodo.addDocument(doc2);
        metodo.addDocument(doc3);
        metodo.addDocument(doc4);
        metodo.nombrarDocumentos();
        metodo.cargarTerminos();
        metodo.agregarConsulta("el anillo que fue parte de la historia");
    }
    
    @Test
    public void testCargarTerminos() {
        List<String> result = metodo.cargarTerminos();
        List<String> expected = new ArrayList<>(Arrays.asList(
                "historia", "mundos","anillo","unico",
                "parte","vivo", "residia",
                "conocido","necesario","dramatica"));
        assertArrayEquals(result.toArray(), expected.toArray());
    }
    
    @Test
    public void testAddDocument() {
        metodo.addDocument(doc1);
        assertEquals(5, metodo.getDocuments().size());
    }
    
    @Test
    public void testAgregarPalabrasVacias() {
        assertTrue(metodo.agregarPalabrasVacias(palabrasVacias));
    }
    
    @Test
    public void testCrearTF() {
        metodo.crearTF();
        int[][] result = metodo.getTf();
        int[][] expected = {
            {2,1,0,0},
            {1,0,0,0},
            {1,1,0,0},
            {1,0,0,0},
            {0,1,1,1},
            {0,0,1,0},
            {0,0,1,0},
            {0,0,0,1},
            {0,0,0,1},
            {0,0,0,1}};
        assertArrayEquals(result, expected);
    }
    
    @Test 
    public void testLlenarIdf() {
        metodo.llenarIdf();
        Map<String, Integer> result = metodo.getIdf();
        Map<String, Integer> expected = new HashMap<>();
        expected.put("historia", 2);
        expected.put("mundos", 1);
        expected.put("anillo", 2);
        expected.put("unico", 1);
        expected.put("parte", 3);
        expected.put("vivo", 1);
        expected.put("residia", 1);
        expected.put("conocido", 1);
        expected.put("necesario", 1);
        expected.put("dramatica", 1);
        assertArrayEquals(expected.values().toArray(), result.values().toArray());
    }
    
    @Test
    public void testCrearMatrizPesos() {
        metodo.crearTF();
        metodo.llenarIdf();
        metodo.crearMatrizPesos();
        float[][] result = metodo.getMPesos();
        float[][] expected = {
            {0.602f,0.301f,0.0f,0.0f},
            {0.602f,0.0f,0.0f,0.0f},
            {0.301f,0.301f,0.0f,0.0f},
            {0.602f,0.0f,0.0f,0.0f},
            {0.0f,0.125f,0.125f,0.125f},
            {0.0f,0.0f,0.602f,0.0f},
            {0.0f,0.0f,0.602f,0.0f},
            {0.0f,0.0f,0.0f,0.602f},
            {0.0f,0.0f,0.0f,0.602f},
            {0.0f,0.0f,0.0f,0.602f}};
        assertArrayEquals(result, expected);
    }
    
    @Test
    public void testAgregarConsulta() {
        metodo.agregarConsulta("otra consulta agregada");
        assertEquals(2, metodo.getConsultas().size());
    }
    
    @Test
    public void testCrearTfConsulta() {
        metodo.crearTfConsulta();
        int[][] resultado = metodo.getTfConsulta();
        int[][] expected = {{1},
                            {0},
                            {1},
                            {0},
                            {1},
                            {0},
                            {0},
                            {0},
                            {0},
                            {0}};
        assertArrayEquals(expected, resultado);
    }
    @Test
    public void testNombrarDocumentos() {
        metodo.nombrarDocumentos();
        List<Documento> result = metodo.getDocuments();
        assertEquals("D3", result.get(2).getName());
    }
    
    @Test
    public void testCrearMatrizPesosConsulta() {
        metodo.crearTfConsulta();
        metodo.llenarIdf();
        metodo.crearMatrizPesosConsulta();
        float[][] resultado = metodo.getMatrizPesosConsulta();
        float[][] expected = {{0.301f},
                              {0.0f},
                              {0.301f},
                              {0.0f},
                              {0.125f},
                              {0.0f},
                              {0.0f},
                              {0.0f},
                              {0.0f},
                              {0.0f}};
        assertArrayEquals(expected,resultado);
    }
    
    @Test
    public void testCalcularSimilaridad() {
        metodo.crearTF();
        metodo.llenarIdf();
        metodo.crearTfConsulta();
        metodo.crearMatrizPesos();
        metodo.crearMatrizPesosConsulta();
        List<String> resultado = new ArrayList<>();
        metodo.calcularSimilaridad().forEach((doc) -> {
            resultado.add(doc.getName());
        });
        List<String> expected = new ArrayList<>(Arrays.asList("D1","D2","D3","D4"));
        assertArrayEquals(expected.toArray(),resultado.toArray());
    }
}
