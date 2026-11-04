package com.proyectotraslado;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArbolConvalidaciones metodosConvalidaciones = new ArbolConvalidaciones();

        // Carga inicial automática
        metodosConvalidaciones.insertar(new Estudiante(101, 20, 87, "Luis", "UNI", "Fisica"));
        metodosConvalidaciones.insertar(new Estudiante(102, 22, 70, "Ana", "PUCP", "Ingenieria de Sistemas"));
        metodosConvalidaciones.insertar(new Estudiante(103, 19, 74, "Carlos", "UNMSM", "Derecho"));
        metodosConvalidaciones.insertar(new Estudiante(104, 21, 81, "Sofia", "UTP", "Arquitectura"));
        metodosConvalidaciones.insertar(new Estudiante(105, 23, 92, "Mateo", "UNI", "Matematicas"));
        
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n#################################################################################");
            System.out.println("¡Sistema de convalidación de la Universidad Sideral Carrión!");
            System.out.println("1. Mostrar.");
            System.out.println("2. Insertar.");
            System.out.println("3. Ordenar.");
            System.out.println("4. Buscar.");
            System.out.println("5. Eliminar.");
            System.out.println("6. Salir.");
            System.out.println("#################################################################################");

            opcion = leerEntero(sc, "Elija que acción realizará: ");
            System.out.println(); 

            switch (opcion) {
                case 1:
                    System.out.println("----------LISTA DE LOS ESTUDIANTES----------");
                    System.out.println("Total de estudiantes convalidados: " + metodosConvalidaciones.getContadorEstudiantes() + "\n");
                    metodosConvalidaciones.mostrarEstudiantes();
                    break;

                case 2:
                    System.out.println("----------INSERTAR ESTUDIANTE-----------");
                    int codigo;
                    
                    while (true) {
                        codigo = leerEntero(sc, "Ingrese el código del estudiante: ");
                        if (metodosConvalidaciones.verificarDuplicado(codigo)) {
                            System.out.println("-> [ERROR]: ID ya existente o duplicada. Ingrese otra ID para continuar.\n");
                        } else {
                            break; 
                        }
                    }

                    int creditos = leerEntero(sc, "Ingrese los créditos del estudiante: ");
                    if (creditos < 70) {
                        System.out.println("\nEl estudiante no tiene los créditos necesarios (mínimo 70), por lo que la convalidación no procederá.");
                        break; 
                    }

                    System.out.print("Ingrese el nombre completo: ");
                    String nombre = sc.nextLine();

                    System.out.print("Ingrese la universidad de origen: ");
                    String universidad = sc.nextLine();

                    System.out.print("Ingresa la carrera: ");
                    String carrera = sc.nextLine();

                    int edad = leerEntero(sc, "Ingrese la edad del estudiante: ");

                    Estudiante nuevo = new Estudiante(codigo, edad, creditos, nombre, universidad, carrera);
                    metodosConvalidaciones.insertar(nuevo);
                    System.out.println("\n¡Estudiante convalidado con éxito!");
                    break; 

                case 3:
                    System.out.println("----------ORDENAR ESTUDIANTES----------");
                    System.out.println("1. Por créditos");
                    System.out.println("2. Por ID");
                    int criterio = leerEntero(sc, "Elija el criterio: ");

                    if (criterio == 1 || criterio == 2) {
                        System.out.println("\n1. Ascendente");
                        System.out.println("2. Descendente");
                        int direccion = leerEntero(sc, "Elija el orden: ");

                        if (direccion == 1 || direccion == 2) {
                            boolean asc = (direccion == 1);
                            if (criterio == 1) {
                                metodosConvalidaciones.ordenarPorCreditos(asc);
                            } else {
                                metodosConvalidaciones.ordenarPorId(asc);
                            }
                        } else {
                            System.out.println("¡Orden no válido!");
                        }
                    } else {
                        System.out.println("¡Criterio no válido!");
                    }
                    break;

                case 4:
                    System.out.println("----------BUSQUEDA DE ESTUDIANTE----------");
                    int codigoBuscar = leerEntero(sc, "Ingrese el código del estudiante que quiere buscar: ");
                    metodosConvalidaciones.buscar(codigoBuscar);
                    break;

                case 5:
                    System.out.println("----------ELIMINACION DE ESTUDIANTE----------");
                    int codigoEliminar = leerEntero(sc, "Ingrese el código del estudiante que quiere eliminar: ");
                    
                    Estudiante victima = metodosConvalidaciones.obtenerEstudiante(codigoEliminar);
                    if (victima != null) {
                        System.out.println("\nSe encontró al siguiente estudiante:");
                        System.out.println(victima);
                        System.out.print("\n¿Está seguro que desea eliminar a este estudiante? (S/N): ");
                        String confirmacion = sc.nextLine().trim().toUpperCase();
                        
                        if (confirmacion.equals("S")) {
                            metodosConvalidaciones.eliminar(codigoEliminar);
                            System.out.println("Estudiante eliminado correctamente.");
                        } else {
                            System.out.println("Operación cancelada. El estudiante no fue eliminado.");
                        }
                    } else {
                        System.out.println("No existe un estudiante con ese código.");
                    }
                    break;

                case 6:
                    System.out.println("Saliendo del sistema......");
                    break;

                default:
                    System.out.println("¡Opción no válida!");
            }
            
            if (opcion != 6) {
                System.out.println("\nPresione Enter para continuar...");
                sc.nextLine();
            }
        } while (opcion != 6);

        sc.close();
    }

    // El método está aquí, justo abajo del flujo principal (main)
    private static int leerEntero(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("-> [ERROR]: Ingrese un número válido.");
            }
        }
    }
}
