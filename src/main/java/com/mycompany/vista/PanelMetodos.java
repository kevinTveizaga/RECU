/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vista;

import com.mycompany.recuperacion.MetodoBooleano;
import com.mycompany.recuperacion.MetodoProbabilistico;
import com.mycompany.recuperacion.MetodoVectorial;
import com.mycompany.vista.acciones.AgregarDocumento;
import com.mycompany.vista.acciones.AgregarPalabrasVacias;
import com.mycompany.vista.acciones.BuscarConsulta;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 *
 * @author kero
 */
public class PanelMetodos implements ItemListener{

    JPanel cards;
    final static String BOOLEANO = "Metodo booleano";
    final static String VECTORIAL = "Metodo vectorial";
    final static String PROBABILISTICO = "Metodo probabilistico";
    final static String AGREGAR = "Agregar";
    final static String BUSCAR = "Buscar";
    final static String AGREGAR_PALABRAS_VACIAS = "Vacias";
    
    MetodoBooleano booleano;
    MetodoVectorial vectorial;
    MetodoProbabilistico probabilistico;
    
    public PanelMetodos () {
        booleano = new MetodoBooleano();
        vectorial = new MetodoVectorial();
        probabilistico = new MetodoProbabilistico();
    }
    
    public void addComponentToPane(Container pane) {
        //Panel de busqueda
        JPanel comboBoxPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        String comboBoxItems[] = { BOOLEANO, VECTORIAL, PROBABILISTICO };
        JComboBox cb = new JComboBox(comboBoxItems);
        cb.setEditable(false);
        cb.addItemListener(this);
        comboBoxPane.add(cb);
        comboBoxPane.setBorder(new TitledBorder("Seleccionar metodo de busqueda"));
        
        //Create the elements.
        JPanel pnlBooleano = new JPanel(new BorderLayout());
        JPanel pnlBuscarBool = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        //componentes para buscar la conulta
        JLabel lblBuscarBool = new JLabel("Buscar: ");
        JTextField txtFBuscarBool = new JTextField(20);
        JButton buscarDocumento = new JButton(BUSCAR);
        buscarDocumento.addActionListener(new BuscarConsulta(booleano));
        
        //panel de la lista
        JPanel pnlList = new JPanel();
        pnlList.setLayout(new BoxLayout(pnlList, BoxLayout.Y_AXIS));
        pnlList.setBorder(new TitledBorder("Documentos"));
        pnlList.setPreferredSize(new Dimension(200, 200));
        
        //lista de documentos
        JList docList = new JList();
        ModeloListaDocumentos modLista = new ModeloListaDocumentos(this.booleano);
        docList.setModel(modLista);
        //panel scroll de la lista
        JScrollPane listScroller = new JScrollPane(docList);
        listScroller.setPreferredSize(new Dimension(200, 190));
        
        //Panel de botones izquierda
        JPanel pnlBotones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlBotones.setPreferredSize(new Dimension(200,50));
        //boton agregar palabras vacias
        JButton btnPalabrasVacias = new JButton(AGREGAR_PALABRAS_VACIAS);
        btnPalabrasVacias.setToolTipText("agregar palabras vacias");
        btnPalabrasVacias.addActionListener(new AgregarPalabrasVacias(booleano, btnPalabrasVacias));
        //boton agregar documento
        JButton agregarDocumentoBool = new JButton(AGREGAR);
        agregarDocumentoBool.addActionListener(new AgregarDocumento(booleano, modLista));
        //agregar al panel de botones izquierda
        pnlBotones.add(agregarDocumentoBool);
        pnlBotones.add(btnPalabrasVacias);
        
        //Panel de botones centro
        JPanel pnlTerminos = new JPanel();
        pnlTerminos.setLayout(new BoxLayout(pnlTerminos, BoxLayout.Y_AXIS));
        pnlTerminos.setBorder(new TitledBorder("Terminos"));
        pnlTerminos.setPreferredSize(new Dimension(200, 200));
        
        //lista de documentos
        JList termList = new JList();
        ModeloListaTerminos modListaTerm = new ModeloListaTerminos(booleano);
        termList.setModel(modListaTerm);
        //panel scroll de la lista
        JScrollPane listScrollerTerm = new JScrollPane(termList);
        listScrollerTerm.setPreferredSize(new Dimension(200, 190));
        pnlTerminos.add(listScrollerTerm);
        
       
        //agregar al panel de busqueda
        pnlBuscarBool.add(lblBuscarBool);
        pnlBuscarBool.add(txtFBuscarBool);
        pnlBuscarBool.add(buscarDocumento);
        
        //agregar al panel de la lista
        pnlList.add(listScroller);
        pnlList.add(pnlBotones);
        //agregar al panel del metodo
        pnlBooleano.add(pnlBuscarBool, BorderLayout.PAGE_START);
        pnlBooleano.add(pnlList, BorderLayout.LINE_START);
        pnlBooleano.add(pnlTerminos, BorderLayout.CENTER);
        
        
        
        
        
        
        
        
        
        
        JPanel pnlVectorial = new JPanel(new BorderLayout());
        JPanel pnlBuscarVecto = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblBuscarVect = new JLabel("Buscar: ");
        JTextField txtFBuscarVect = new JTextField(20);
        JButton agregarDocumentoVect = new JButton(AGREGAR);
        //agregarDocumentoVect.addActionListener(new AgregarDocumento(vectorial));
        //panel de busqueda vectorial
        pnlBuscarVecto.add(lblBuscarVect);
        pnlBuscarVecto.add(txtFBuscarVect);
        pnlBuscarVecto.add(agregarDocumentoVect);
        pnlVectorial.add(pnlBuscarVecto);
        
        JPanel pnlProb = new JPanel(new BorderLayout());
        JPanel pnlBuscarProb = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblBuscarProb = new JLabel("Buscar: ");
        JTextField txtFBuscarProb = new JTextField(20);
        JButton agregarDocumentoProb = new JButton(AGREGAR);
        //agregarDocumentoProb.addActionListener(new AgregarDocumento(probabilistico));
        //panel de busqueda probabilistico
        pnlBuscarProb.add(lblBuscarProb);
        pnlBuscarProb.add(txtFBuscarProb);
        pnlBuscarProb.add(agregarDocumentoProb);
        pnlProb.add(pnlBuscarProb);
        
        //Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());
        cards.add(pnlBooleano, BOOLEANO);
        cards.add(pnlVectorial, VECTORIAL);
        cards.add(pnlProb, PROBABILISTICO);
        
        pane.add(comboBoxPane, BorderLayout.PAGE_START);
        pane.add(cards, BorderLayout.CENTER);
    }
    
    @Override
    public void itemStateChanged(ItemEvent evt) {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, (String)evt.getItem());
    }
}
