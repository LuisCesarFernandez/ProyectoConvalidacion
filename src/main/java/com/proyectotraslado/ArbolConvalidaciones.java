package com.proyectotraslado;

import java.util.ArrayList;
import java.util.List;

public class ArbolConvalidaciones {
    private Nodo raiz;

    public ArbolConvalidaciones(){
        this.raiz = null;
    }

    //Método para insertar datos.
    public void insertar (Estudiante estudiante){
        raiz = insertarRec(raiz, estudiante);
    }

    //Método para insertar manteniendo la propiedad el BST.
    private Nodo insertarRec(Nodo actual, Estudiante estudiante) {
        if (actual == null) {
            return new Nodo(estudiante);
        }

        if (estudiante.codigo < actual.estudiante.codigo){
            actual.izquierdo = insertarRec(actual.izquierdo, estudiante);
        } else if(estudiante.codigo > actual.estudiante.codigo){
            actual.derecho = insertarRec(actual.derecho, estudiante);
        }

        return actual;
    }

    //Método para buscar.
    public void buscar(int codigo){
        Nodo resultado = buscarRec(raiz, codigo);

        if (resultado != null) {
            System.out.println("\n--- ESTUDIANTE ENCONTRADO ---");
            System.out.println(resultado.estudiante);
        } else {
            System.out.println("\nNo se encontró ningún estudiante con el código " + codigo);
        }
    }

    //Método recursivo de búsqueda.
    private Nodo buscarRec(Nodo actual, int codigo) {
        // Si el nodo es nulo o encontramos el código exacto, devolvemos el nodo
        if (actual == null || actual.estudiante.codigo == codigo) {
            return actual;
        }

        // Si el código buscado es menor, va a la izquierda
        if (codigo < actual.estudiante.codigo) {
            return buscarRec(actual.izquierdo, codigo);
        }

        // Si es mayor, va a la derecha
        return buscarRec(actual.derecho, codigo);
    }

    // Método para recorrer el árbol en orden (InOrder) - Muestra estudiantes ordenados por código
    public void mostrarEstudiantes() {
        mostrarInOrder(raiz);
    }

    private void mostrarInOrder(Nodo actual) {
        if (actual != null) {
            mostrarInOrder(actual.izquierdo);
            System.out.println(actual.estudiante);
            mostrarInOrder(actual.derecho);
        }
    }

    // Método para eliminar
    public void eliminar(int codigo) {
        raiz = eliminarRec(raiz, codigo);
    }

    private Nodo eliminarRec(Nodo actual, int codigo) {
        if (actual == null) {
            return null; // No encontrado
        }

        // Buscar el nodo a eliminar
        if (codigo < actual.estudiante.codigo) {
            actual.izquierdo = eliminarRec(actual.izquierdo, codigo);
        } else if (codigo > actual.estudiante.codigo) {
            actual.derecho = eliminarRec(actual.derecho, codigo);
        } else {

            // Caso 1: Nodo sin hijos o con un solo hijo
            if (actual.izquierdo == null) {
                return actual.derecho;
            } else if (actual.derecho == null) {
                return actual.izquierdo;
            }

            // Caso 2: Nodo con dos hijos
            actual.estudiante = encontrarMinimo(actual.derecho);

            // Eliminamos el sucesor duplicado del subárbol derecho
            actual.derecho = eliminarRec(actual.derecho, actual.estudiante.codigo);
        }
        return actual;
    }

    private Estudiante encontrarMinimo(Nodo raiz) {
        Estudiante min = raiz.estudiante;
        while (raiz.izquierdo != null) {
            min = raiz.izquierdo.estudiante;
            raiz = raiz.izquierdo;
        }
        return min;
    }

    //Ordenar por Burbuja.
    private void obtenerListaRec(Nodo actual, List<Estudiante> lista) {
        if (actual != null) {
            obtenerListaRec(actual.izquierdo, lista);
            lista.add(actual.estudiante);
            obtenerListaRec(actual.derecho, lista);
        }
    }

    public void ordenarPorCreditosBurbuja() {
        List<Estudiante> lista = new ArrayList<>();
        obtenerListaRec(raiz, lista);

        int n = lista.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (lista.get(j).creditos < lista.get(j + 1).creditos) {
                    Estudiante temp = lista.get(j);
                    lista.set(j, lista.get(j + 1));
                    lista.set(j + 1, temp);
                }
            }
        }

        // Mostramos el resultado ordenado por Burbuja
        System.out.println("\n--- ESTUDIANTES ORDENADOS POR CRÉDITOS (Método Burbuja - Mayor a Menor) ---");
        for (Estudiante e : lista) {
            System.out.println(e);
        }
    }
}
