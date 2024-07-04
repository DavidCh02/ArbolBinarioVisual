package Interfaz;

import Logica.ArbolAVL;
import Logica.ArbolBinarioSimple;
import Logica.ArbolRojoNegro;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class VentanaPrincipal extends JFrame {
    private ArbolBinarioSimple arbol;
    private JTextField campoValor;
    private PanelRound panelDibujo;
    private JComboBox<String> tipoArbolComboBox;
    private JTextArea areaRecorridos;
    private List<Integer> valoresNodos;

    public VentanaPrincipal() {
        setTitle("Estructuras de Datos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Inicializar lista de valores de nodos
        valoresNodos = new ArrayList<>();

        // Panel de control
        JPanel panelControl = new JPanel();
        panelControl.setLayout(new BorderLayout());
        panelControl.setBackground(Color.LIGHT_GRAY);

        // Panel de valor y tipo de árbol
        JPanel panelValorTipo = new JPanel();
        panelValorTipo.setLayout(new FlowLayout());
        panelValorTipo.setBackground(Color.LIGHT_GRAY);

        campoValor = new JTextField(5);
        campoValor.setFont(new Font("Arial", Font.PLAIN, 16));
        panelValorTipo.add(new JLabel("Valor:"));
        panelValorTipo.add(campoValor);

        tipoArbolComboBox = new JComboBox<>(new String[]{"Simple", "AVL", "Rojo-Negro"});
        tipoArbolComboBox.setFont(new Font("Arial", Font.PLAIN, 16));
        tipoArbolComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarTipoArbol(tipoArbolComboBox.getSelectedItem().toString());
            }
        });
        panelValorTipo.add(tipoArbolComboBox);
        panelControl.add(panelValorTipo, BorderLayout.NORTH);

        // Panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(2, 2, 10, 10));
        panelBotones.setBorder(new EmptyBorder(20, 20, 20, 20));
        panelBotones.setBackground(Color.LIGHT_GRAY);

        JButton botonAgregar = new JButton("Agregar");
        botonAgregar.setFont(new Font("Arial", Font.BOLD, 16));
        botonAgregar.setBackground(Color.GREEN.darker());
        botonAgregar.setForeground(Color.WHITE);
        botonAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int valor = Integer.parseInt(campoValor.getText());
                valoresNodos.add(valor);
                arbol.agregar(valor);
                actualizarDibujo();
            }
        });
        panelBotones.add(botonAgregar);

        JButton botonEliminar = new JButton("Eliminar");
        botonEliminar.setFont(new Font("Arial", Font.BOLD, 16));
        botonEliminar.setBackground(Color.RED.darker());
        botonEliminar.setForeground(Color.WHITE);
        botonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int valor = Integer.parseInt(campoValor.getText());
                valoresNodos.remove((Integer) valor);
                arbol.eliminar(valor);
                actualizarDibujo();
            }
        });
        panelBotones.add(botonEliminar);

        JButton botonInOrden = new JButton("In-Orden");
        botonInOrden.setFont(new Font("Arial", Font.BOLD, 16));
        botonInOrden.setBackground(Color.BLUE.darker());
        botonInOrden.setForeground(Color.WHITE);
        botonInOrden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Integer> recorrido = arbol.recorrerInOrden();
                mostrarRecorrido("In-Orden", recorrido);
                panelDibujo.pintarRecorrido(recorrido);
            }
        });
        panelBotones.add(botonInOrden);

        JButton botonPreOrden = new JButton("Pre-Orden");
        botonPreOrden.setFont(new Font("Arial", Font.BOLD, 16));
        botonPreOrden.setBackground(Color.ORANGE.darker());
        botonPreOrden.setForeground(Color.WHITE);
        botonPreOrden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Integer> recorrido = arbol.recorrerPreOrden();
                mostrarRecorrido("Pre-Orden", recorrido);
                panelDibujo.pintarRecorrido(recorrido);
            }
        });
        panelBotones.add(botonPreOrden);

        JButton botonPostOrden = new JButton("Post-Orden");
        botonPostOrden.setFont(new Font("Arial", Font.BOLD, 16));
        botonPostOrden.setBackground(Color.MAGENTA.darker());
        botonPostOrden.setForeground(Color.WHITE);
        botonPostOrden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Integer> recorrido = arbol.recorrerPostOrden();
                mostrarRecorrido("Post-Orden", recorrido);
                panelDibujo.pintarRecorrido(recorrido);
            }
        });
        panelBotones.add(botonPostOrden);

        JButton botonDetener = new JButton("Detener Pintado");
        botonDetener.setFont(new Font("Arial", Font.BOLD, 16));
        botonDetener.setBackground(Color.GRAY.darker());
        botonDetener.setForeground(Color.WHITE);
        botonDetener.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelDibujo.despintarRecorrido();
            }
        });
        panelControl.add(panelBotones, BorderLayout.CENTER);
        panelControl.add(botonDetener, BorderLayout.SOUTH);

        // Área de texto para mostrar recorridos
        areaRecorridos = new JTextArea(5, 20);
        areaRecorridos.setFont(new Font("Arial", Font.PLAIN, 16));
        areaRecorridos.setEditable(false);
        areaRecorridos.setBackground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(areaRecorridos);
        panelControl.add(scrollPane, BorderLayout.EAST);

        add(panelControl, BorderLayout.NORTH);

        // Panel de dibujo
        panelDibujo = new PanelRound();
        add(panelDibujo, BorderLayout.CENTER);

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

    private void cambiarTipoArbol(String tipo) {
        switch (tipo) {
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
        reconstruirArbol();
        panelDibujo.setArbol(arbol);
        actualizarDibujo();
    }

    private void reconstruirArbol() {
        for (int valor : valoresNodos) {
            arbol.agregar(valor);
        }
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
