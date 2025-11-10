package org.example;

import dao.LibroDAO;
import dao.LibroDAOImpl;
import model.Libro;
import service.BibliotecaService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LibroDAO libroDAO = new LibroDAOImpl();
        BibliotecaService servicio = new BibliotecaService(libroDAO);
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n===== MENÚ BIBLIOTECA =====");
            System.out.println("1. Añadir libro");
            System.out.println("2. Listar libros");
            System.out.println("3. Modificar título");
            System.out.println("4. Eliminar libro");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt(); sc.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.print("Título: ");
                    String titulo = sc.nextLine();
                    servicio.registrarLibro(titulo);
                }
                case 2 -> servicio.listarLibros().forEach(System.out::println);
                case 3 -> {
                    System.out.print("ID del libro: ");
                    int id = sc.nextInt(); sc.nextLine();
                    System.out.print("Nuevo título: ");
                    String nuevo = sc.nextLine();
                    servicio.cambiarTitulo(id, nuevo);
                }
                case 4 -> {
                    System.out.print("ID del libro a eliminar: ");
                    int id = sc.nextInt(); sc.nextLine();
                    servicio.eliminarLibro(id);
                }
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida");
            }
        } while (opcion != 0);
    }
}
