/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.recuperacion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kero
 */
public class LectorDeDocumentos {
    private String readText;
    public boolean readDocument(String filePath) {
        boolean result = false; 
        readText = "";
        if (!filePath.isEmpty()) {
            File file = new File(filePath);
            if (file.isFile()) {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    String text="";
                    while((text = reader.readLine()) != null) {
                        readText += text;
                    }                    
                } catch (IOException ex) {
                    Logger.getLogger(LectorDeDocumentos.class.getName()).log(Level.SEVERE, null, ex);
                }
                result = true;
            }
        }
        return result;
    }    
    
    public String getReadText() {
        return readText;
    }

    public String[] refinarDocumento(String readText) {
        return readText.trim().split(" ");
    }
    
    
}
