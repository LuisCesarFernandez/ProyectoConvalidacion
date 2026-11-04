package com.proyectotraslado;

import java.util.ArrayList;
import java.util.List;

public class ArbolConvalidaciones {
    private Nodo raiz;
    private int contadorEstudiantes; // MEJORA 1: Atributo global para contar alumnos

    public ArbolConvalidaciones(){
        this.raiz = null;
        this.contadorEstudiantes = 0;
    }

    // Retorna la cantidad actual de estudiantes convalidados en el árbol
    public int getContadorEstudiantes() {
        return this.contadorEstudiantes;
    }

    // MÉTODOS DE INSERCIÓN
    public boolean insertar(Estudiante estudiante) {
        if (existeCodigo(raiz, estudiante.codigo)) {
            return false;               
        }
        raiz = insertarRec(raiz, estudiante);
        contadorEstudiantes++; // Incrementa el contador al insertar con éxito
        return true;                    
    }

    private Nodo insertarRec(Nodo actual, Estudiante estudiante) {
        if (actual == null) {
            return new Nodo(estudiante);
        }
        if (estudiante.codigo < actual.estudiante.codigo) {
            actual.izquierdo = insertarRec(actual.izquierdo, estudiante);
        } else {
            actual.derecho = insertarRec(actual.derecho, estudiante);
        }
        return actual;
    }

    // MÉTODO AUXILIAR SILENCIOSO 
    private boolean existeCodigo(Nodo actual, int codigo) {
        if (actual == null) {
            return false;
        }
        if (actual.estudiante.codigo == codigo) {
            return true;
        }
        if (codigo < actual.estudiante.codigo) {
            return existeCodigo(actual.izquierdo, codigo);
        }
        return existeCodigo(actual.derecho, codigo);
    }

    public boolean verificarDuplicado(int codigo) {
        return existeCodigo(raiz, codigo);
    }

    // MÉTODOS DE BÚSQUEDA 
    // Retorna el objeto Estudiante directamente para poder mostrarlo antes de eliminar
    public Estudiante obtenerEstudiante(int codigo) {
        Nodo res = buscarRecSilencioso(raiz, codigo);
        return (res != null) ? res.estudiante : null;
    }

    private Nodo buscarRecSilencioso(Nodo actual, int codigo) {
        if (actual == null || actual.estudiante.codigo == codigo) {
            return actual;
        }
        if (codigo < actual.estudiante.codigo) {
            return buscarRecSilencioso(actual.izquierdo, codigo);
        }
        return buscarRecSilencioso(actual.derecho, codigo);
    }

    public boolean buscar(int codigo) {
        Nodo resultado = buscarRec(raiz, codigo);
        if (resultado != null) {
            System.out.println("\n--- ESTUDIANTE ENCONTRADO ---");
            System.out.println(resultado.estudiante);
            return true;                
        }
        System.out.println("\nNo se encontró ningún estudiante con el código " + codigo);
        return false;                   
    }

    private Nodo buscarRec(Nodo actual, int codigo) {
        if (actual == null || actual.estudiante.codigo == codigo) {
            return actual;
        }
        if (codigo < actual.estudiante.codigo) {
            System.out.println("  " + codigo + " < " + actual.estudiante.codigo + " -> IZQUIERDA");
            return buscarRec(actual.izquierdo, codigo);
        }
        System.out.println("  " + codigo + " > " + actual.estudiante.codigo + " -> DERECHA");
        return buscarRec(actual.derecho, codigo);
    }
    // MÉTODOS DE ELIMINACIÓN
    public boolean eliminar(int codigo) {
        if (!existeCodigo(raiz, codigo)) {
            return false;               
        }
        raiz = eliminarRec(raiz, codigo);
        contadorEstudiantes--; // Decrementa el contador al eliminar con éxito
        return true;                    
    }

    private Nodo eliminarRec(Nodo actual, int codigo) {
        if (actual == null) {
            return null;
        }

        if (codigo < actual.estudiante.codigo) {
            actual.izquierdo = eliminarRec(actual.izquierdo, codigo);
        } else if (codigo > actual.estudiante.codigo) {
            actual.derecho = eliminarRec(actual.derecho, codigo);
        } else {
            if (actual.izquierdo == null) {
                return actual.derecho;
            } else if (actual.derecho == null) {
                return actual.izquierdo;
            }
            actual.estudiante = encontrarMinimo(actual.derecho);
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

    // MÉTODOS DE RECORRIDO Y ORDENAMIENTO
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

    private void obtenerListaRec(Nodo actual, List<Estudiante> lista) {
        if (actual != null) {
            obtenerListaRec(actual.izquierdo, lista);
            lista.add(actual.estudiante);
            obtenerListaRec(actual.derecho, lista);
        }
    }
    // Ordena por créditos. ascendente = true (menor a mayor), false (mayor a menor)
    public void ordenarPorCreditos(boolean ascendente) {
        List<Estudiante> lista = new ArrayList<>();
        obtenerListaRec(raiz, lista);

        int n = lista.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                boolean cambiar = ascendente
                        ? lista.get(j).creditos > lista.get(j + 1).creditos
                        : lista.get(j).creditos < lista.get(j + 1).creditos;
                if (cambiar) {
                    Estudiante temp = lista.get(j);
                    lista.set(j, lista.get(j + 1));
                    lista.set(j + 1, temp);
                }
            }
        }

        System.out.println("\n--- ESTUDIANTES ORDENADOS POR CRÉDITOS ("
                + (ascendente ? "Menor a Mayor" : "Mayor a Menor") + ") ---");
        for (Estudiante e : lista) {
            System.out.println(e);
        }
    }

    // Ordena por ID. ascendente = true (menor a mayor), false (mayor a menor)
    public void ordenarPorId(boolean ascendente) {
        List<Estudiante> lista = new ArrayList<>();
        obtenerListaRec(raiz, lista);

        int n = lista.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                boolean cambiar = ascendente
                        ? lista.get(j).codigo > lista.get(j + 1).codigo
                        : lista.get(j).codigo < lista.get(j + 1).codigo;
                if (cambiar) {
                    Estudiante temp = lista.get(j);
                    lista.set(j, lista.get(j + 1));
                    lista.set(j + 1, temp);
                }
            }
        }

        System.out.println("\n--- ESTUDIANTES ORDENADOS POR ID ("
                + (ascendente ? "Menor a Mayor" : "Mayor a Menor") + ") ---");
        for (Estudiante e : lista) {
            System.out.println(e);
        }
    }
}
