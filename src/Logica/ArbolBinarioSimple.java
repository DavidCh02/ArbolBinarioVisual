package Logica;

import java.util.ArrayList;
import java.util.List;

public class ArbolBinarioSimple {
    public Nodo raiz;

    public ArbolBinarioSimple() {
        raiz = null;
    }

    public void agregar(int valor) {
        raiz = agregarRecursivo(raiz, valor);
    }

    private Nodo agregarRecursivo(Nodo actual, int valor) {
        if (actual == null) {
            return new Nodo(valor);
        }
        if (valor < actual.valor) {
            actual.izquierdo = agregarRecursivo(actual.izquierdo, valor);
        } else if (valor > actual.valor) {
            actual.derecho = agregarRecursivo(actual.derecho, valor);
        }
        return actual;
    }

    public void eliminar(int valor) {
        raiz = eliminarRecursivo(raiz, valor);
    }

    private Nodo eliminarRecursivo(Nodo actual, int valor) {
        if (actual == null) {
            return null;
        }
        if (valor == actual.valor) {
            if (actual.izquierdo == null && actual.derecho == null) {
                return null;
            }
            if (actual.izquierdo == null) {
                return actual.derecho;
            }
            if (actual.derecho == null) {
                return actual.izquierdo;
            }
            int menorValor = encontrarMenorValor(actual.derecho);
            actual.valor = menorValor;
            actual.derecho = eliminarRecursivo(actual.derecho, menorValor);
            return actual;
        }
        if (valor < actual.valor) {
            actual.izquierdo = eliminarRecursivo(actual.izquierdo, valor);
            return actual;
        }
        actual.derecho = eliminarRecursivo(actual.derecho, valor);
        return actual;
    }

    private int encontrarMenorValor(Nodo nodo) {
        return nodo.izquierdo == null ? nodo.valor : encontrarMenorValor(nodo.izquierdo);
    }

    public List<Integer> recorrerInOrden() {
        List<Integer> recorrido = new ArrayList<>();
        recorrerInOrdenRecursivo(raiz, recorrido);
        return recorrido;
    }

    private void recorrerInOrdenRecursivo(Nodo nodo, List<Integer> recorrido) {
        if (nodo != null) {
            recorrerInOrdenRecursivo(nodo.izquierdo, recorrido);
            recorrido.add(nodo.valor);
            recorrerInOrdenRecursivo(nodo.derecho, recorrido);
        }
    }

    public List<Integer> recorrerPreOrden() {
        List<Integer> recorrido = new ArrayList<>();
        recorrerPreOrdenRecursivo(raiz, recorrido);
        return recorrido;
    }

    private void recorrerPreOrdenRecursivo(Nodo nodo, List<Integer> recorrido) {
        if (nodo != null) {
            recorrido.add(nodo.valor);
            recorrerPreOrdenRecursivo(nodo.izquierdo, recorrido);
            recorrerPreOrdenRecursivo(nodo.derecho, recorrido);
        }
    }

    public List<Integer> recorrerPostOrden() {
        List<Integer> recorrido = new ArrayList<>();
        recorrerPostOrdenRecursivo(raiz, recorrido);
        return recorrido;
    }

    private void recorrerPostOrdenRecursivo(Nodo nodo, List<Integer> recorrido) {
        if (nodo != null) {
            recorrerPostOrdenRecursivo(nodo.izquierdo, recorrido);
            recorrerPostOrdenRecursivo(nodo.derecho, recorrido);
            recorrido.add(nodo.valor);
        }
    }
}
