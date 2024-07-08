package Interfaz;

import Logica.ArbolBinarioSimple;
import Logica.Nodo;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PanelRound extends JPanel {
    private ArbolBinarioSimple arbol;
    private List<Integer> recorridoSeleccionado;
    private int indexPintado;
    private Timer timer;

    public PanelRound() {
        indexPintado = 0;
        timer = new Timer(1000, e -> {
            repaint();
            indexPintado++;
            if (indexPintado > recorridoSeleccionado.size()) {
                ((Timer) e.getSource()).stop();
            }
        });
    }

    public void setArbol(ArbolBinarioSimple arbol) {
        this.arbol = arbol;
        this.recorridoSeleccionado = null;
        this.indexPintado = 0;
        repaint();
    }

    public void pintarRecorrido(List<Integer> recorrido) {
        this.recorridoSeleccionado = recorrido;
        this.indexPintado = 0;
        timer.start();
    }

    public void despintarRecorrido() {
        this.recorridoSeleccionado = null;
        this.indexPintado = 0;
        timer.stop();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (arbol != null) {
            dibujarNodo(g, arbol.raiz, getWidth() / 2, 30, getWidth() / 4);
        }
    }

    private void dibujarNodo(Graphics g, Nodo nodo, int x, int y, int xOffset) {
        if (nodo == null) return;

        // Determinar color del nodo
        if (recorridoSeleccionado != null) {
            int index = recorridoSeleccionado.indexOf(nodo.valor);
            if (index == indexPintado - 1) {
                g.setColor(Color.  RED); // Color del nodo actual en el recorrido
            } else if (index < indexPintado - 1) {
                g.setColor(Color.BLUE); // Color de los nodos ya recorridos
            } else {
                g.setColor(Color.BLACK); // Color de los nodos no recorridos
            }
        } else {
            g.setColor(Color.BLACK);
        }

        // Dibujar nodo
        g.fillOval(x - 15, y - 15, 30, 30);
        g.setColor(Color.WHITE);
        g.drawString(String.valueOf(nodo.valor), x - 7, y + 4);

        // Dibujar líneas hacia hijos
        g.setColor(Color.GRAY); // Color de las líneas
        if (nodo.izquierdo != null) {
            g.drawLine(x, y, x - xOffset, y + 50);
            dibujarNodo(g, nodo.izquierdo, x - xOffset, y + 50, xOffset / 2);
        }
        if (nodo.derecho != null) {
            g.drawLine(x, y, x + xOffset, y + 50);
            dibujarNodo(g, nodo.derecho, x + xOffset, y + 50, xOffset / 2);
        }
    }
}
