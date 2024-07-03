package Logica;

public class ArbolAVL extends ArbolBinarioSimple {
    @Override
    public void agregar(int valor) {
        raiz = agregarRecursivo((NodoAVL) raiz, valor);
    }

    private NodoAVL agregarRecursivo(NodoAVL nodo, int valor) {
        if (nodo == null) {
            return new NodoAVL(valor);
        }

        if (valor < nodo.valor) {
            nodo.izquierdo = agregarRecursivo((NodoAVL) nodo.izquierdo, valor);
        } else if (valor > nodo.valor) {
            nodo.derecho = agregarRecursivo((NodoAVL) nodo.derecho, valor);
        } else {
            return nodo;
        }

        nodo.altura = 1 + Math.max(altura(nodo.izquierdo), altura(nodo.derecho));

        return balancear(nodo);
    }

    private NodoAVL balancear(NodoAVL nodo) {
        int balance = obtenerBalance(nodo);

        if (balance > 1) {
            if (obtenerBalance((NodoAVL) nodo.izquierdo) >= 0) {
                return rotarDerecha(nodo);
            } else {
                nodo.izquierdo = rotarIzquierda((NodoAVL) nodo.izquierdo);
                return rotarDerecha(nodo);
            }
        }

        if (balance < -1) {
            if (obtenerBalance((NodoAVL) nodo.derecho) <= 0) {
                return rotarIzquierda(nodo);
            } else {
                nodo.derecho = rotarDerecha((NodoAVL) nodo.derecho);
                return rotarIzquierda(nodo);
            }
        }

        return nodo;
    }

    private int altura(Nodo nodo) {
        return nodo == null ? 0 : ((NodoAVL) nodo).altura;
    }

    private int obtenerBalance(NodoAVL nodo) {
        return nodo == null ? 0 : altura(nodo.izquierdo) - altura(nodo.derecho);
    }

    private NodoAVL rotarDerecha(NodoAVL y) {
        NodoAVL x = (NodoAVL) y.izquierdo;
        Nodo T2 = x.derecho;
        x.derecho = y;
        y.izquierdo = T2;
        y.altura = Math.max(altura(y.izquierdo), altura(y.derecho)) + 1;
        x.altura = Math.max(altura(x.izquierdo), altura(x.derecho)) + 1;
        return x;
    }

    private NodoAVL rotarIzquierda(NodoAVL x) {
        NodoAVL y = (NodoAVL) x.derecho;
        Nodo T2 = y.izquierdo;
        y.izquierdo = x;
        x.derecho = T2;
        x.altura = Math.max(altura(x.izquierdo), altura(x.derecho)) + 1;
        y.altura = Math.max(altura(y.izquierdo), altura(y.derecho)) + 1;
        return y;
    }
}
