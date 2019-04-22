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
import org.junit.Test;
import org.junit.Before;

/**
 *
 * @author kero
 */
public class MetodoBooleanoTest {
    
    private MetodoBooleano metodo;
    private List<String> pDocumento;
    private List<String> sDocumento;
    private List<String> tDocumento;
    private List<String> cDocumento;
    
    private String urlPDocumento;
    private String urlSDocumento;
    private String urlTDocumento;
    private String urlCDocumento;
    private String palabrasVacias;

    public MetodoBooleanoTest() {
        this.urlPDocumento = "C:\\Users\\kero\\Documents\\NetBeansProjects\\recuperacion\\src\\test\\java\\com\\mycompany\\recuperacion\\documentos\\Documento1.txt";
        this.urlSDocumento = "C:\\Users\\kero\\Documents\\NetBeansProjects\\recuperacion\\src\\test\\java\\com\\mycompany\\recuperacion\\documentos\\Documento2.txt";
        this.urlTDocumento = "C:\\Users\\kero\\Documents\\NetBeansProjects\\recuperacion\\src\\test\\java\\com\\mycompany\\recuperacion\\documentos\\Documento3.txt";
        this.urlCDocumento = "C:\\Users\\kero\\Documents\\NetBeansProjects\\recuperacion\\src\\test\\java\\com\\mycompany\\recuperacion\\documentos\\Documento4.txt";
        this.palabrasVacias = "C:\\Users\\kero\\Documents\\NetBeansProjects\\recuperacion\\src\\test\\java\\com\\mycompany\\recuperacion\\archivos\\palabrasVacias.txt";
    }
    
    @Before
    public void setUp() {
        metodo = new MetodoBooleano(palabrasVacias);
        metodo.agregarDocumento(urlPDocumento);
        metodo.agregarDocumento(urlSDocumento);
        metodo.agregarDocumento(urlTDocumento);
        metodo.agregarDocumento(urlCDocumento);
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
                "historia", "mundos","anillo","unico",
                "parte","vivo", "residia",
                "conocido","necesario","dramatica"));
        assertArrayEquals(result.toArray(), expected.toArray());
    }
    
    @Test
    public void testCrearMatriz() {
        metodo.eliminarRepetidos();
        int[][] result = metodo.crearMatriz();
        int[][] expected = {
            {1,1,0,0},
            {1,0,0,0},
            {1,1,0,0},
            {1,0,0,0},
            {0,1,1,1},
            {0,0,1,0},
            {0,0,1,0},
            {0,0,0,1},
            {0,0,0,1},
            {0,0,0,1}};
        assertArrayEquals(result,expected);
    }
}
