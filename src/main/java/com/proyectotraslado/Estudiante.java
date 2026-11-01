package com.proyectotraslado;

public class Estudiante {
    int codigo, edad, creditos;
    String nombre, universidad, carrera;

    public Estudiante(int codigo, int edad, int creditos, String nombre, String universidad, String carrera) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.universidad = universidad;
        this.carrera = carrera;
        this.edad = edad;
        this.creditos = creditos;
    }

    @Override 
    public String toString() {
        return "ID: " + codigo + " | Nombre: " + nombre + " | Universidad: " + universidad + " | Carrera: " + carrera + " | Edad: " + edad + " | Créditos: " + creditos; 
    }
}
