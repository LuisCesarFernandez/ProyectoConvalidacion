package com.proyectotraslado;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArbolConvalidaciones metodosConvalidaciones = new ArbolConvalidaciones();

        Estudiante estudiante1 = new Estudiante(101, 20, 87, "Luis", "UNI", "Fisica");
        Estudiante estudiante2 = new Estudiante(102, 22, 70, "Ana", "PUCP", "Ingenieria de Sistemas");
        Estudiante estudiante3 = new Estudiante(103, 19, 74, "Carlos", "UNMSM", "Derecho");
        Estudiante estudiante4 = new Estudiante(104, 21, 81, "Sofia", "UTP", "Arquitectura");
        Estudiante estudiante5 = new Estudiante(105, 23, 92, "Mateo", "UNI", "Matematicas");

        metodosConvalidaciones.insertar(estudiante1);
        metodosConvalidaciones.insertar(estudiante2);
        metodosConvalidaciones.insertar(estudiante3);
        metodosConvalidaciones.insertar(estudiante4);
        metodosConvalidaciones.insertar(estudiante5);
        
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("#################################################################################");
            System.out.println("¡Sistema de convalidación de la Universidad Sideral Carrión!");
            System.out.println("1. Mostrar.");
            System.out.println("2. Insertar.");
            System.out.println("3. Ordenar.");
            System.out.println("4. Buscar.");
            System.out.println("5. Eliminar.");
            System.out.println("6. Salir.");
            System.out.println("#################################################################################");

            System.out.print("Elija que acción realizará: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("----------LISTA DE LOS ESTUDIANTES----------");
                    metodosConvalidaciones.mostrarEstudiantes();
                    break;

                case 2:
                    System.out.println("----------INSERTAR ESTUDIANTE-----------");
                    System.out.print("Ingrese el código del estudiante: ");
                    int codigo = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Ingrese el nombre completo: ");
                    String nombre = sc.nextLine();

                    System.out.print("Ingrese la universidad de origen: ");
                    String universidad = sc.nextLine();

                    System.out.print("Ingresa la carrera: ");
                    String carrera = sc.nextLine();

                    System.out.print("Ingrese la edad del estudiante: ");
                    int edad = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Ingrese los créditos del estudiante: ");
                    int creditos = sc.nextInt();
                    sc.nextLine();

                    if (creditos >= 70) {
                        Estudiante nuevo = new Estudiante(codigo, edad, creditos, nombre, universidad, carrera);
                        metodosConvalidaciones.insertar(nuevo);
                        System.out.println("¡Estudiante convalidado con éxito!");
                        break;
                    } else {
                        System.out.println(
                                "El estudiante no tiene los créditos necesarios por lo que la convalidación no procederá.");
                    }

                case 3:
                    System.out.println("----------LISTA DE ESTUDIANTES ORDENADA POR CREDITOS----------");
                    metodosConvalidaciones.ordenarPorCreditosBurbuja();
                    break;

                case 4:
                    System.out.println("----------BUSQUEDA DE ESTUDIANTE----------");
                    System.out.print("Ingrese el código del estudiante que quiere buscar: ");
                    int codigoBuscar = sc.nextInt();
                    sc.nextLine();
                    
                    metodosConvalidaciones.buscar(codigoBuscar);
                    break;

                case 5:
                    System.out.println("----------ELIMINACION DE ESTUDIANTE----------");
                    System.out.print("Ingrese el código del estudiante que quiere eliminar: ");
                    int codigoEliminar = sc.nextInt();
                    metodosConvalidaciones.eliminar(codigoEliminar);

                    System.out.println("Estudiante eliminado correctamente");
                    break;

                case 6:
                    System.out.println("Saliendo del sistema......");
                    break;

                default:
                    System.out.println("¡Opción no valida!");
            }
        } while (opcion != 6);

        sc.close();
    }
}
