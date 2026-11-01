package com.proyectotraslado;

public class Nodo {
    Estudiante estudiante;
    Nodo izquierdo;
    Nodo derecho;

    public Nodo(Estudiante estudiante){
        this.estudiante = estudiante;
        this. izquierdo = null;
        this.derecho = null;
    }
}
