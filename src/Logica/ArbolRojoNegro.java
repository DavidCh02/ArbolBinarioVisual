package Logica;

public class ArbolRojoNegro extends ArbolBinarioSimple {
    private NodoRojoNegro raiz;

    @Override
    public void agregar(int valor) {
        raiz = agregarRecursivo(raiz, valor);
        raiz.esRojo = false;
    }

    private NodoRojoNegro agregarRecursivo(NodoRojoNegro nodo, int valor) {
        if (nodo == null) {
            return new NodoRojoNegro(valor);
        }

        if (valor < nodo.valor) {
            nodo.izquierdo = agregarRecursivo((NodoRojoNegro) nodo.izquierdo, valor);
        } else if (valor > nodo.valor) {
            nodo.derecho = agregarRecursivo((NodoRojoNegro) nodo.derecho, valor);
        }

        if (esRojo((NodoRojoNegro) nodo.derecho) && !esRojo((NodoRojoNegro) nodo.izquierdo)) {
            nodo = rotarIzquierda(nodo);
        }
        if (esRojo((NodoRojoNegro) nodo.izquierdo) && esRojo((NodoRojoNegro) nodo.izquierdo.izquierdo)) {
            nodo = rotarDerecha(nodo);
        }
        if (esRojo((NodoRojoNegro) nodo.izquierdo) && esRojo((NodoRojoNegro) nodo.derecho)) {
            cambiarColores(nodo);
        }

        return nodo;
    }

    private boolean esRojo(NodoRojoNegro nodo) {
        return nodo != null && nodo.esRojo;
    }

    private NodoRojoNegro rotarIzquierda(NodoRojoNegro nodo) {
        NodoRojoNegro x = (NodoRojoNegro) nodo.derecho;
        nodo.derecho = x.izquierdo;
        x.izquierdo = nodo;
        x.esRojo = nodo.esRojo;
        nodo.esRojo = true;
        return x;
    }

    private NodoRojoNegro rotarDerecha(NodoRojoNegro nodo) {
        NodoRojoNegro x = (NodoRojoNegro) nodo.izquierdo;
        nodo.izquierdo = x.derecho;
        x.derecho = nodo;
        x.esRojo = nodo.esRojo;
        nodo.esRojo = true;
        return x;
    }

    private void cambiarColores(NodoRojoNegro nodo) {
        nodo.esRojo = !nodo.esRojo;
        ((NodoRojoNegro) nodo.izquierdo).esRojo = !((NodoRojoNegro) nodo.izquierdo).esRojo;
        ((NodoRojoNegro) nodo.derecho).esRojo = !((NodoRojoNegro) nodo.derecho).esRojo;
    }
}
