/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.recuperacion;

import java.util.List;

/**
 *
 * @author kero
 */
public interface Metodo {
    
    public List<Documento> getDocumentos();
    public void agregarDocumento(String documento);
    public void nombrarDocumentos();
    public boolean agregarPalabrasVacias(String archivo);
    public List<String> getTerminos();
}
