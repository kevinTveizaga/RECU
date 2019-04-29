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
import org.junit.Test;
import org.junit.Before;

/**
 *
 * @author kero
 */
public class MetodoBooleanoTest {

    private MetodoBooleano metodo;

    private final String urlPDocumento;
    private final String urlSDocumento;
    private final String urlTDocumento;
    private final String urlCDocumento;
    private final String palabrasVacias;

    public MetodoBooleanoTest() {
        this.urlPDocumento = "C:\\Users\\kero\\Documents\\NetBeansProjects\\recuperacion\\src\\test\\java\\com\\mycompany\\recuperacion\\documentos\\Documento1.txt";
        this.urlSDocumento = "C:\\Users\\kero\\Documents\\NetBeansProjects\\recuperacion\\src\\test\\java\\com\\mycompany\\recuperacion\\documentos\\Documento2.txt";
        this.urlTDocumento = "C:\\Users\\kero\\Documents\\NetBeansProjects\\recuperacion\\src\\test\\java\\com\\mycompany\\recuperacion\\documentos\\Documento3.txt";
        this.urlCDocumento = "C:\\Users\\kero\\Documents\\NetBeansProjects\\recuperacion\\src\\test\\java\\com\\mycompany\\recuperacion\\documentos\\Documento4.txt";
        this.palabrasVacias = "C:\\Users\\kero\\Documents\\NetBeansProjects\\recuperacion\\src\\test\\java\\com\\mycompany\\recuperacion\\archivos\\palabrasVacias.txt";
    }

    @Before
    public void setUp() {
        metodo = new MetodoBooleano();
        metodo.agregarPalabrasVacias(palabrasVacias);
        metodo.agregarDocumento(urlPDocumento);
        metodo.agregarDocumento(urlSDocumento);
        metodo.agregarDocumento(urlTDocumento);
        metodo.agregarDocumento(urlCDocumento);
        metodo.nombrarDocumentos();
    }

    @Test
    public void testAgregarDocumento() {
        int result = metodo.getDocumentos().size();
        assertEquals(4, result);
    }

    @Test
    public void testEliminarRepetidos() {
        List<String> result = metodo.eliminarRepetidos();
        List<String> expected = new ArrayList<>(Arrays.asList(
                "historia", "mundos", "anillo", "unico",
                "parte", "vivo", "residia",
                "conocido", "necesario", "dramatica"));
        assertArrayEquals(result.toArray(), expected.toArray());
    }

    @Test
    public void testAgregarPalabrasVacias() {
        assertTrue(metodo.agregarPalabrasVacias(palabrasVacias));
    }

    @Test
    public void testCrearMatriz() {
        metodo.eliminarRepetidos();
        int[][] result = metodo.crearMatriz();
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
    public void testConsulta() {
        metodo.eliminarRepetidos();
        metodo.crearMatriz();
        String consulta = "anillo and unico";
        String[] resultado = metodo.consultar(consulta);
        String[] expected = {"D1"};
        assertArrayEquals(expected, resultado);
    }
   
    @Test
    public void testConsulta1() {
        metodo.eliminarRepetidos();
        metodo.crearMatriz();
        String consulta = "anillo and ( unico or historia )";
        String[] resultado = metodo.consultar(consulta);
        String[] expected = {"D1","D2"};
        assertArrayEquals(expected, resultado);
    }
    
    @Test
    public void testConsulta2() {
        metodo.eliminarRepetidos();
        metodo.crearMatriz();
        String consulta = "( anillo and parte ) or ( unico or historia )";
        String[] resultado = metodo.consultar(consulta);
        String[] expected = {"D1","D2"};
        assertArrayEquals(expected, resultado);
    }
    
    @Test
    public void testConsulta3() {
        metodo.eliminarRepetidos();
        metodo.crearMatriz();
        String consulta = "vivo and residia";
        String[] resultado = metodo.consultar(consulta);
        String[] expected = {"D3"};
        assertArrayEquals(expected, resultado);
    }
    
    @Test
    public void testConsulta4() {
        metodo.eliminarRepetidos();
        metodo.crearMatriz();
        String consulta = "anillo and ( unico or parte ) and mundos";
        String[] resultado = metodo.consultar(consulta);
        String[] expected = {"D1"};
        assertArrayEquals(expected, resultado);
    }
  
    @Test
    public void testCalcularExpresionAnd() {
        String result = metodo.calcularExpresion("1100","1010","and");
        assertEquals("1000",result);
    }
  
    @Test
    public void testCalcularExpresionOr() {
        String result = metodo.calcularExpresion("1100","1010","or");
        assertEquals("1110",result);
    }
    
    @Test
    public void testCalcularAnd() {
        String result = metodo.calcularAnd("1101", "1001");
        assertEquals("1001", result);
    }
    
    @Test
    public void testCalcularAnd1() {
        String result = metodo.calcularAnd("1100", "1001");
        assertEquals("1000", result);
    }
    @Test
    public void testCalcularAnd2() {
        String result = metodo.calcularAnd("1111", "0111");
        assertEquals("0111", result);
    }
    
    @Test
    public void testCalcularOr() {
        String result = metodo.calcularOr("1100","1010");
        assertEquals("1110", result);
    }
    @Test
    public void testCalcularOr2() {
        String result = metodo.calcularOr("1100","1000");
        assertEquals("1100", result);
    }
    
    @Test
    public void testClasificarSigno() {
        metodo.eliminarRepetidos();
        metodo.crearMatriz();
        metodo.clasificarSigno(new String[]{"historia", "and", "anillo"});
        assertEquals("and", metodo.getSignos().peek());
        assertEquals("1100", metodo.getNumeros().peek());
    }
    
    @Test
    public void testEscogerResultado() {
        String[] resultado = metodo.escogerDocumento("1010");
        String[] expected = new String[] {"D1","D3"};
        assertArrayEquals(expected, resultado);
    }
}
