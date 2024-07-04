package Logica;

public class NodoRojoNegro extends Nodo {
    public boolean esRojo;

    public NodoRojoNegro(int valor) {
        super(valor);
        this.esRojo = true; // Por defecto, los nuevos nodos son rojos
    }
}
