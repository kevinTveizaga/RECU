/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.recuperacion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author kero
 */
public class MetodoProbabilisticoTest {

    MetodoProbabilistico metodo;
    String doc1;
    String doc2;
    String doc3;
    String doc4;
    String palabrasVacias;

    public MetodoProbabilisticoTest() {
        this.doc1 = "C:\\Users\\kero\\Documents\\NetBeansProjects\\recuperacion\\src\\test\\java\\com\\mycompany\\recuperacion\\documentos\\Documento1.txt";
        this.doc2 = "C:\\Users\\kero\\Documents\\NetBeansProjects\\recuperacion\\src\\test\\java\\com\\mycompany\\recuperacion\\documentos\\Documento2.txt";
        this.doc3 = "C:\\Users\\kero\\Documents\\NetBeansProjects\\recuperacion\\src\\test\\java\\com\\mycompany\\recuperacion\\documentos\\Documento3.txt";
        this.doc4 = "C:\\Users\\kero\\Documents\\NetBeansProjects\\recuperacion\\src\\test\\java\\com\\mycompany\\recuperacion\\documentos\\Documento4.txt";
        this.palabrasVacias = "C:\\Users\\kero\\Documents\\NetBeansProjects\\recuperacion\\src\\test\\java\\com\\mycompany\\recuperacion\\archivos\\palabrasVacias.txt";
    }

    @Before
    public void setUp() {
        metodo = new MetodoProbabilistico();
        metodo.agregarPalabrasVacias(palabrasVacias);
        metodo.agregarDocumento(doc1);
        metodo.agregarDocumento(doc2);
        metodo.agregarDocumento(doc3);
        metodo.agregarDocumento(doc4);
        metodo.nombrarDocumentos();
        metodo.cargarTerminos();
    }

    @Test
    public void testAgregarDocumento() {
        String docExt = "C:\\Users\\kero\\Documents\\NetBeansProjects\\recuperacion\\src\\test\\java\\com\\mycompany\\recuperacion\\documentos\\DocumentoExtendido.txt";
        metodo.agregarDocumento(docExt);
        assertEquals(5, metodo.getDocumentos().size());
    }

    @Test
    public void testCargarTerminos() {
        List<String> result = metodo.cargarTerminos();
        List<String> expected = new ArrayList<>(Arrays.asList(
                "historia", "mundos", "anillo", "unico",
                "parte", "vivo", "residia",
                "conocido", "necesario", "dramatica"));
        assertArrayEquals(expected.toArray(), result.toArray());
    }

    @Test
    public void testNombrarDocumentos() {
        metodo.nombrarDocumentos();
        List<Documento> result = metodo.getDocumentos();
        assertEquals("D3", result.get(2).getName());
    }

    @Test
    public void testCrearMatrizBinaria() {
        int[][] result = metodo.crearMatrizBinaria();
        int[][] expected = {
            {1, 1, 0, 0},
            {1, 0, 0, 0},
            {1, 1, 0, 0},
            {1, 0, 0, 0},
            {0, 1, 1, 1},
            {0, 0, 1, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 1},
            {0, 0, 0, 1},
            {0, 0, 0, 1}};
        assertArrayEquals(result, expected);
    }

    @Test
    public void testCargarConsulta() {
        String consulta = "un ser vivo que residia en una parte del anillo y dramatica";
        metodo.cargarConsulta(consulta);
        Map<String, Integer> result = metodo.getFreqTermConsultas();
        Object[] expected = {1,1,2,3,1};
        String[] terminos = {"dramatica","residia","anillo","parte", "vivo"};
        assertArrayEquals(expected,result.values().toArray());
        assertArrayEquals(terminos, result.keySet().toArray());
    }
    
    @Test
    public void testCalcularPeso() {
        String consulta = "un ser vivo que residia en una parte del anillo y dramatica";
        metodo.crearMatrizBinaria();
        metodo.cargarConsulta(consulta);
        metodo.calcularPeso();
        List<Float> pesos = metodo.getPesos();
        List<Float> expected = new ArrayList<>(Arrays.asList(0.477f,0.477f,0.0f,-0.477f,0.477f));
        assertArrayEquals(expected.toArray(), pesos.toArray());
    }
    @Test
    public void testCalcSimProb() {
        String consulta = "un ser vivo que residia en una parte del anillo y dramatica";
        metodo.crearMatrizBinaria();
        metodo.cargarConsulta(consulta);
        metodo.calcularPeso();
        List<String> resultado = new ArrayList<>();
        metodo.calcSimProb().forEach((doc) -> {
            resultado.add(doc.getName());
        });
        String[] expected = {"D3","D2"};
        assertArrayEquals(expected, resultado.toArray());
    }
    @Test
    public void testCalcSimProb1() {
        String consulta = "la universidad de unamumo";
        metodo.crearMatrizBinaria();
        metodo.cargarConsulta(consulta);
        metodo.calcularPeso();
        List<String> resultado = new ArrayList<>();
        metodo.calcSimProb().forEach((doc) -> {
            resultado.add(doc.getName());
        });
        String[] expected = {};
        assertArrayEquals(expected, resultado.toArray());
        
    }
    @Test
    public void testCalcSimProb2() {
        String consulta = "dramatica dramatica dramatica";
        metodo.crearMatrizBinaria();
        metodo.cargarConsulta(consulta);
        metodo.calcularPeso();
        List<String> resultado = new ArrayList<>();
        metodo.calcSimProb().forEach((doc) -> {
            resultado.add(doc.getName());
        });
        String[] expected = {"D4"};
        assertArrayEquals(expected, resultado.toArray());
        
    }
    @Test
    public void testCalcSimProb3() {
        String consulta = "el anillo tenia un tinte dramatico";
        metodo.crearMatrizBinaria();
        metodo.cargarConsulta(consulta);
        metodo.calcularPeso();
        List<String> resultado = new ArrayList<>();
        metodo.calcSimProb().forEach((doc) -> {
            resultado.add(doc.getName());
        });
        String[] expected = {};
        assertArrayEquals(expected, resultado.toArray());
        
    }
    @Test
    public void testCalcSimProb4() {
        String consulta = "los seres vivos vivos vivos residian dramatica";
        metodo.crearMatrizBinaria();
        metodo.cargarConsulta(consulta);
        metodo.calcularPeso();
        List<String> resultado = new ArrayList<>();
        metodo.calcSimProb().forEach((doc) -> {
            resultado.add(doc.getName());
        });
        String[] expected = {"D4"};
        assertArrayEquals(expected, resultado.toArray());
        
    }
}
