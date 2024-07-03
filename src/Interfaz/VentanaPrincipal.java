package Interfaz;

import Logica.ArbolAVL;
import Logica.ArbolBinarioSimple;
import Logica.ArbolRojoNegro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class VentanaPrincipal extends JFrame {
    private ArbolBinarioSimple arbol;
    private JTextField campoValor;
    private PanelRound panelDibujo;
    private JComboBox<String> tipoArbolComboBox;
    private JTextArea areaRecorridos;

    public VentanaPrincipal() {
        setTitle("Estructuras de Datos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel de control
        JPanel panelControl = new JPanel();
        panelControl.setLayout(new FlowLayout());

        campoValor = new JTextField(5);
        panelControl.add(new JLabel("Valor:"));
        panelControl.add(campoValor);

        tipoArbolComboBox = new JComboBox<>(new String[]{"Simple", "AVL", "Rojo-Negro"});
        tipoArbolComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (tipoArbolComboBox.getSelectedItem().toString()) {
                    case "AVL":
                        arbol = new ArbolAVL();
                        break;
                    case "Rojo-Negro":
                        arbol = new ArbolRojoNegro();
                        break;
                    default:
                        arbol = new ArbolBinarioSimple();
                        break;
                }
                panelDibujo.setArbol(arbol);
                actualizarDibujo();
            }
        });
        panelControl.add(tipoArbolComboBox);

        JButton botonAgregar = new JButton("Agregar");
        botonAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int valor = Integer.parseInt(campoValor.getText());
                arbol.agregar(valor);
                actualizarDibujo();
            }
        });
        panelControl.add(botonAgregar);

        JButton botonEliminar = new JButton("Eliminar");
        botonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int valor = Integer.parseInt(campoValor.getText());
                arbol.eliminar(valor);
                actualizarDibujo();
            }
        });
        panelControl.add(botonEliminar);

        JButton botonInOrden = new JButton("In-Orden");
        botonInOrden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Integer> recorrido = arbol.recorrerInOrden();
                mostrarRecorrido("In-Orden", recorrido);
                panelDibujo.pintarRecorrido(recorrido);
            }
        });
        panelControl.add(botonInOrden);

        JButton botonPreOrden = new JButton("Pre-Orden");
        botonPreOrden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Integer> recorrido = arbol.recorrerPreOrden();
                mostrarRecorrido("Pre-Orden", recorrido);
                panelDibujo.pintarRecorrido(recorrido);
            }
        });
        panelControl.add(botonPreOrden);

        JButton botonPostOrden = new JButton("Post-Orden");
        botonPostOrden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Integer> recorrido = arbol.recorrerPostOrden();
                mostrarRecorrido("Post-Orden", recorrido);
                panelDibujo.pintarRecorrido(recorrido);
            }
        });
        panelControl.add(botonPostOrden);

        JButton botonDetener = new JButton("Detener Pintado");
        botonDetener.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelDibujo.despintarRecorrido();
            }
        });
        panelControl.add(botonDetener);

        add(panelControl, BorderLayout.NORTH);

        // Panel de dibujo
        panelDibujo = new PanelRound();
        add(panelDibujo, BorderLayout.CENTER);

        // Área de texto para mostrar recorridos
        areaRecorridos = new JTextArea(5, 20);
        areaRecorridos.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaRecorridos);
        add(scrollPane, BorderLayout.SOUTH);

        // Inicializar con Árbol Binario Simple
        arbol = new ArbolBinarioSimple();
        panelDibujo.setArbol(arbol);
    }

    private void actualizarDibujo() {
        panelDibujo.repaint();
    }

    private void mostrarRecorrido(String tipo, List<Integer> recorrido) {
        areaRecorridos.setText(tipo + ": " + recorrido.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });
    }
}
