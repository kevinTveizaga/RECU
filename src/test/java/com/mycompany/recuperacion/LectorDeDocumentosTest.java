/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.recuperacion;

import java.util.Arrays;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Kero
 */
public class LectorDeDocumentosTest {
    
    String filePath = "C:\\Users\\Kero\\Documents\\NetBeansProjects\\recuperacion\\src\\main\\java\\com\\mycompany\\archivos\\texto.txt";
    LectorDeDocumentos lector;

    @Before
    public void setUp() {
        lector = new LectorDeDocumentos();
    }
    
    @Test
    public void testReadDocument() {
        boolean lectura = lector.readDocument(filePath);
        assertTrue(lectura);
    }   
    
    @Test
    public void testRefinarTexto() {
        String text = "la caida del titanic fue un evento mundial";
        String[] result = lector.refinarDocumento(text); 
        String[] expected = {"caida", "titanic", "fue", "evento", "mundial"};
        Arrays.equals(result, expected);
        
    }
}
