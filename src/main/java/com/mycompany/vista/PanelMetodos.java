/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vista;

import com.mycompany.vista.acciones.BuscarConsultaProb;
import com.mycompany.recuperacion.MetodoBooleano;
import com.mycompany.recuperacion.MetodoProbabilistico;
import com.mycompany.recuperacion.MetodoVectorial;
import com.mycompany.vista.acciones.AgregarDocumento;
import com.mycompany.vista.acciones.AgregarPalabrasVacias;
import com.mycompany.vista.acciones.BuscarConsultaBool;
import com.mycompany.vista.acciones.BuscarConsultaVecto;
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
public class PanelMetodos implements ItemListener {

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

    public PanelMetodos() {
        booleano = new MetodoBooleano();
        vectorial = new MetodoVectorial();
        probabilistico = new MetodoProbabilistico();
    }

    public void addComponentToPane(Container pane) {
        //Panel de busqueda
        JPanel comboBoxPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        String comboBoxItems[] = {BOOLEANO, VECTORIAL, PROBABILISTICO};
        JComboBox cb = new JComboBox(comboBoxItems);
        cb.setEditable(false);
        cb.addItemListener(this);
        comboBoxPane.add(cb);
        comboBoxPane.setBorder(new TitledBorder("Seleccionar metodo de busqueda"));

        JPanel pnlBooleano = createBooleanCard();
        JPanel pnlVectorial = createVectorialMethod();
        JPanel pnlProb = createProbCard();

        //Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());
        cards.add(pnlBooleano, BOOLEANO);
        cards.add(pnlVectorial, VECTORIAL);
        cards.add(pnlProb, PROBABILISTICO);
        pane.add(comboBoxPane, BorderLayout.PAGE_START);
        pane.add(cards, BorderLayout.CENTER);
    }

    private JPanel createBooleanCard() {
        //Create the elements.
        JPanel pnlBooleano = new JPanel(new BorderLayout());
        pnlBooleano.setBorder(new TitledBorder(BOOLEANO));
        JPanel pnlBuscarBool = new JPanel(new FlowLayout(FlowLayout.LEFT));
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
        //lista de terminos
        JList termList = new JList();
        ModeloLista modListaTerm = new ModeloLista();
        termList.setModel(modListaTerm);
        //panel scroll de la lista
        JScrollPane listScrollerTerm = new JScrollPane(termList);
        listScrollerTerm.setPreferredSize(new Dimension(200, 190));
        //boton agregar documento
        JButton agregarDocumentoBool = new JButton(AGREGAR);
        AgregarDocumento accionAgregarDocumento = new AgregarDocumento(booleano, modLista);
        accionAgregarDocumento.setTerminosList(modListaTerm);
        agregarDocumentoBool.addActionListener(accionAgregarDocumento);
        //agregar al panel de botones izquierda
        pnlBotones.add(agregarDocumentoBool);
        pnlBotones.add(btnPalabrasVacias);
        //Panel de terminos centro
        JPanel pnlTerminos = new JPanel();
        pnlTerminos.setLayout(new BoxLayout(pnlTerminos, BoxLayout.Y_AXIS));
        pnlTerminos.setBorder(new TitledBorder("Terminos"));
        pnlTerminos.setPreferredSize(new Dimension(200, 200));
        pnlTerminos.add(listScrollerTerm);
        //Panel de resultados derecha
        JPanel pnlResultados = new JPanel();
        pnlResultados.setLayout(new BoxLayout(pnlResultados, BoxLayout.Y_AXIS));
        pnlResultados.setBorder(new TitledBorder("Resultados"));
        pnlResultados.setPreferredSize(new Dimension(200, 200));
        //lista de resultados
        JList resultList = new JList();
        ModeloLista modListaResult = new ModeloLista();
        resultList.setModel(modListaResult);
        //panel scroll de la lista
        JScrollPane listScrollerResult = new JScrollPane(resultList);
        listScrollerResult.setPreferredSize(new Dimension(200, 190));
        pnlResultados.add(listScrollerResult);
        //componentes para buscar la conulta
        JLabel lblBuscarBool = new JLabel("Consulta: ");
        JTextField txtFBuscarBool = new JTextField(40);
        JButton buscarDocumento = new JButton(BUSCAR);
        BuscarConsultaBool accionBuscarConsulta = new BuscarConsultaBool(booleano, txtFBuscarBool);
        accionAgregarDocumento.setTerminosList(modListaTerm);
        accionBuscarConsulta.setResultList(modListaResult);
        buscarDocumento.addActionListener(accionBuscarConsulta);
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
        pnlBooleano.add(pnlResultados, BorderLayout.LINE_END);
        return pnlBooleano;
    }

    private JPanel createVectorialMethod() {
        JPanel pnlVectorial = new JPanel(new BorderLayout());
        pnlVectorial.setBorder(new TitledBorder(VECTORIAL));
        JPanel pnlBuscarVecto = new JPanel(new FlowLayout(FlowLayout.LEFT));
        //panel de la lista de documentos
        JPanel pnlList = new JPanel();
        pnlList.setLayout(new BoxLayout(pnlList, BoxLayout.Y_AXIS));
        pnlList.setBorder(new TitledBorder("Documentos"));
        pnlList.setPreferredSize(new Dimension(200, 200));
        //lista de documentos
        JList docList = new JList();
        ModeloListaDocumentos modLista = new ModeloListaDocumentos(this.vectorial);
        docList.setModel(modLista);
        //panel scroll de la lista
        JScrollPane listScroller = new JScrollPane(docList);
        listScroller.setPreferredSize(new Dimension(200, 190));
        //Panel de botones izquierda
        JPanel pnlBotones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlBotones.setPreferredSize(new Dimension(200, 50));
        //boton agregar palabras vacias
        JButton btnPalabrasVacias = new JButton(AGREGAR_PALABRAS_VACIAS);
        btnPalabrasVacias.setToolTipText("agregar palabras vacias");
        btnPalabrasVacias.addActionListener(new AgregarPalabrasVacias(vectorial, btnPalabrasVacias));
        //lista de terminos
        JList termList = new JList();
        ModeloLista modListaTerm = new ModeloLista();
        termList.setModel(modListaTerm);
        //panel scroll de la lista
        JScrollPane listScrollerTerm = new JScrollPane(termList);
        listScrollerTerm.setPreferredSize(new Dimension(200, 190));
        //boton agregar documento
        JButton agregarDocumento = new JButton(AGREGAR);
        AgregarDocumento actAgregarDocumento = new AgregarDocumento(vectorial, modLista);
        actAgregarDocumento.setTerminosList(modListaTerm);
        agregarDocumento.addActionListener(actAgregarDocumento);
        //agregar al panel de botones izquierda
        pnlBotones.add(agregarDocumento);
        pnlBotones.add(btnPalabrasVacias);
        //Panel de terminos centro
        JPanel pnlTerminos = new JPanel();
        pnlTerminos.setLayout(new BoxLayout(pnlTerminos,BoxLayout.Y_AXIS));
        pnlTerminos.setBorder(new TitledBorder("Terminos"));
        pnlTerminos.setPreferredSize(new Dimension(200, 200));
        pnlTerminos.add(listScrollerTerm);
        //Panel de resultados derecha
        JPanel pnlResultados = new JPanel();
        pnlResultados.setLayout(new BoxLayout(pnlResultados, BoxLayout.Y_AXIS));
        pnlResultados.setBorder(new TitledBorder("Resultados"));
        pnlResultados.setPreferredSize(new Dimension(200, 200));
        //lista de resultados
        JList resultList = new JList();
        ModeloLista modListaResult = new ModeloLista();
        resultList.setModel(modListaResult);
        //panel scroll de los resultados 
        JScrollPane listScrollerResult = new JScrollPane(resultList);
        listScrollerResult.setPreferredSize(new Dimension(200, 190));
        pnlResultados.add(listScrollerResult);
        //componentes para buscar la consulta
        JLabel lblBuscarVect = new JLabel("Consulta: ");
        JTextField txtFBuscarVect = new JTextField(40);
        JButton buscarConsulta = new JButton(BUSCAR);
        BuscarConsultaVecto actVecto = new BuscarConsultaVecto(vectorial, txtFBuscarVect);
        actAgregarDocumento.setTerminosList(modListaTerm);
        actVecto.setResultList(modListaResult);
        buscarConsulta.addActionListener(actVecto);
        //panel de busqueda vectorial
        pnlBuscarVecto.add(lblBuscarVect);
        pnlBuscarVecto.add(txtFBuscarVect);
        pnlBuscarVecto.add(buscarConsulta);
        //agregar al panel de la lista
        pnlList.add(listScroller);
        pnlList.add(pnlBotones);
             
        pnlVectorial.add(pnlBuscarVecto, BorderLayout.PAGE_START);
        pnlVectorial.add(pnlList, BorderLayout.LINE_START);
        pnlVectorial.add(pnlTerminos, BorderLayout.CENTER);
        pnlVectorial.add(pnlResultados, BorderLayout.LINE_END);
        return pnlVectorial;
    }

    private JPanel createProbCard() {
        JPanel pnlProbabilistico =  new JPanel(new BorderLayout());
        pnlProbabilistico.setBorder(new TitledBorder(PROBABILISTICO));
        JPanel pnlBuscarProb = new JPanel(new FlowLayout(FlowLayout.LEFT));
        //panel de la lista de documentos
        JPanel pnlList = new JPanel();
        pnlList.setLayout(new BoxLayout(pnlList, BoxLayout.Y_AXIS));
        pnlList.setBorder(new TitledBorder("Documentos"));
        pnlList.setPreferredSize(new Dimension(200, 200));
        //lista de documentos
        JList docList = new JList();
        ModeloListaDocumentos modLista = new ModeloListaDocumentos(this.probabilistico);
        docList.setModel(modLista);
        //panel scroll de la lista
        JScrollPane listScroller = new JScrollPane(docList);
        listScroller.setPreferredSize(new Dimension(200, 190));
        //Panel de botones izquierda
        JPanel pnlBotones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlBotones.setPreferredSize(new Dimension(200, 50));
        //boton agregar palabras vacias
        JButton btnPalabrasVacias = new JButton(AGREGAR_PALABRAS_VACIAS);
        btnPalabrasVacias.setToolTipText("agregar palabras vacias");
        btnPalabrasVacias.addActionListener(new AgregarPalabrasVacias(probabilistico, btnPalabrasVacias));
        //lista de terminos
        JList termList = new JList();
        ModeloLista modListaTerm = new ModeloLista();
        termList.setModel(modListaTerm);
        //panel scroll de la lista
        JScrollPane listScrollerTerm = new JScrollPane(termList);
        listScrollerTerm.setPreferredSize(new Dimension(200, 190));
        //boton agregar documento
        JButton agregarDocumento = new JButton(AGREGAR);
        AgregarDocumento actAgregarDocumento = new AgregarDocumento(probabilistico, modLista);
        actAgregarDocumento.setTerminosList(modListaTerm);
        agregarDocumento.addActionListener(actAgregarDocumento);
        //agregar al panel de botones izquierda
        pnlBotones.add(agregarDocumento);
        pnlBotones.add(btnPalabrasVacias);
        //Panel de terminos centro
        JPanel pnlTerminos = new JPanel();
        pnlTerminos.setLayout(new BoxLayout(pnlTerminos,BoxLayout.Y_AXIS));
        pnlTerminos.setBorder(new TitledBorder("Terminos"));
        pnlTerminos.setPreferredSize(new Dimension(200, 200));
        pnlTerminos.add(listScrollerTerm);
        //Panel de resultados derecha
        JPanel pnlResultados = new JPanel();
        pnlResultados.setLayout(new BoxLayout(pnlResultados, BoxLayout.Y_AXIS));
        pnlResultados.setBorder(new TitledBorder("Resultados"));
        pnlResultados.setPreferredSize(new Dimension(200, 200));
        //lista de resultados
        JList resultList = new JList();
        ModeloLista modListaResult = new ModeloLista();
        resultList.setModel(modListaResult);
        //panel scroll de los resultados 
        JScrollPane listScrollerResult = new JScrollPane(resultList);
        listScrollerResult.setPreferredSize(new Dimension(200, 190));
        pnlResultados.add(listScrollerResult);
        //componentes para buscar la consulta
        JLabel lblBuscarProb = new JLabel("Consulta: ");
        JTextField txtFBuscarProb = new JTextField(40);
        JButton buscarConsulta = new JButton(BUSCAR);
        BuscarConsultaProb actProb=  new BuscarConsultaProb(probabilistico, txtFBuscarProb);
        actAgregarDocumento.setTerminosList(modListaTerm);
        actProb.setResultList(modListaResult);
        buscarConsulta.addActionListener(actProb);
        //panel de busqueda vectorial
        pnlBuscarProb.add(lblBuscarProb);
        pnlBuscarProb.add(txtFBuscarProb);
        pnlBuscarProb.add(buscarConsulta);
        //agregar al panel de la lista
        pnlList.add(listScroller);
        pnlList.add(pnlBotones);
             
        pnlProbabilistico.add(pnlBuscarProb, BorderLayout.PAGE_START);
        pnlProbabilistico.add(pnlList, BorderLayout.LINE_START);
        pnlProbabilistico.add(pnlTerminos, BorderLayout.CENTER);
        pnlProbabilistico.add(pnlResultados, BorderLayout.LINE_END);
        return pnlProbabilistico;
    }

    @Override
    public void itemStateChanged(ItemEvent evt) {
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, (String) evt.getItem());
    }
}
