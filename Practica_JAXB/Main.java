package Practica_JAXB;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws JAXBException {
        Scanner sc = new Scanner(System.in);

        libreria lib = new libreria("Las Hojas", "Getafe");
        lib.getLibros().add(new libro("Cervantes", "Quijote", "89658", "Anaya", "Novela clásica / Caballeresca", 1605));
        lib.getLibros().add(new libro("Virgilio", "La Eneida", "12344", "Anaya", "Épica / Poesía clásica", -19));
        lib.getLibros().add(new libro("Homero", "La Iliada", "88800", "Gredos", "Épica / Mitología", -750));
        lib.getLibros().add(new libro("Homero", "La Odisea", "95632", "Gredos", "Épica / Aventuras mitológicas", -725));
        lib.getLibros().add(new libro("Santiago Posteguillo", "Los Tres Mundos", "98651", "B", "Histórica / Aventuras", 2024));
        lib.getLibros().add(new libro("Javier Castillo", "El Susurro Del Fuego", "82156", "Suma", "Thriller / Misterio", 2024));
        lib.getLibros().add(new libro("Lauren Roberts", "Fearful", "59741", "Alfaguara", "Fantasía / Romance", 2024));
        lib.getLibros().add(new libro("Stephen King", "No Tengas Miedo", "87453", "Plaza Janés", "Terror / Suspense", 2025));
        lib.getLibros().add(new libro("George R. R. Martin", "El Caballero De Los Siete Reinos", "46387", "Plaza Janés", "Fantasía épica", 1998));
        lib.getLibros().add(new libro("J. D. Barker", "Las abandonadas", "95130", "Destino", "Thriller psicológico / Terror", 2023));
        lib.getLibros().add(new libro("Cassandra Clare", "El Caballero Muerte", "89370", "CrossBooks", "Fantasía / Juvenil", 2024));
        lib.getLibros().add(new libro("Rebecca Yarros", "Alas De Sangre", "56886", "Planeta", "Fantasía romántica", 2023));
        lib.getLibros().add(new libro("Rebecca Yarros", "Alas De Hierro", "56876", "Planeta", "Fantasía romántica", 2023));
        lib.getLibros().add(new libro("Rebecca Yarros", "Alas De Onix", "59876", "Planeta", "Fantasía romántica", 2024));
        lib.getLibros().add(new libro("Freida McFadden", "La Asistenta", "97530", "Suma", "Thriller / Suspenso doméstico", 2022));

        JAXBContext contexto = JAXBContext.newInstance(libreria.class);
        Marshaller marshall = contexto.createMarshaller();
        marshall.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        marshall.marshal(lib, new File("libros.xml"));
        System.out.println("📁 Archivo XML 'libros.xml' generado correctamente.\n");
        System.out.println("Bienvenido a su Biblioteca Personal ☺");

        int opcion;
        do {
            System.out.println("""
            \nPor favor seleccione cuál de las siguientes operaciones desea realizar:
            1. Mostrar libros almacenados.
            2. Buscar información detallada de un libro.
            3. Buscar libro por autor.
            4. Buscar libro por género.
            5. Añadir nuevo libro y guardar XML.
            6. Salir.
            """);

            System.out.print("Opción: ");
            while (!sc.hasNextInt()) {
                System.out.print("Por favor ingrese un número válido: ");
                sc.next();
            }
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1 -> {
                    System.out.println("⋆.˚ ☾⭒.˚ Libros almacenados actualmente en su biblioteca:");
                    for (libro book : lib.getLibros()) {
                        System.out.println("- " + book.getTitulo());
                    }
                }
                case 2 -> {
                    System.out.print("Introduzca el título del libro: ");
                    String libroDetallado = sc.nextLine().toLowerCase();
                    boolean existe = false;

                    for (libro book : lib.getLibros()) {
                        if (book.getTitulo().toLowerCase().equals(libroDetallado)) {
                            System.out.println("\nInformación detallada:");
                            System.out.println("✧˖°.Título: " + book.getTitulo());
                            System.out.println("Autor: " + book.getAutor());
                            System.out.println("Editorial: " + book.getEditorial());
                            System.out.println("ISBN: " + book.getIsbn());
                            System.out.println("Género: " + book.getGenero());
                            System.out.println("Año: " + book.getAnio());
                            existe = true;
                            break;
                        }
                    }

                    if (!existe) System.out.println("✧ Libro no encontrado.");
                }
                case 3 -> {
                    System.out.print("Introduzca el nombre del autor: ");
                    String autor = sc.nextLine().toLowerCase().trim();
                    boolean encontrado = false;

                    System.out.println("\n⋆.˚ ☾⭒.˚ Libros de " + autor + ":");
                    for (libro book : lib.getLibros()) {
                        if (book.getAutor().toLowerCase().contains(autor)) {
                            System.out.println("- " + book.getTitulo() + " (" + book.getAnio() + ")");
                            encontrado = true;
                        }
                    }

                    if (!encontrado)
                        System.out.println("✦ El autor no tiene libros almacenados en su biblioteca personal.");
                }
                case 4 -> {
                    System.out.print("Introduzca el género que desea buscar: ");
                    String generoBuscado = sc.nextLine().trim().toLowerCase();
                    boolean hayGenero = false;

                    System.out.println("\n⋆.˚ ☾⭒.˚ Libros del género " + generoBuscado + ":");
                    for (libro l : lib.getLibros()) {
                        if (l.getGenero().toLowerCase().contains(generoBuscado)) {
                            System.out.println("- " + l.getTitulo() + " de " + l.getAutor());
                            hayGenero = true;
                        }
                    }

                    if (!hayGenero)
                        System.out.println("✦ No he encontrado ningún libro de ese género en su biblioteca.");
                }
                case 5 -> {
                    System.out.println("\n✏ Añadir un nuevo libro:");
                    System.out.print("Autor: ");
                    String autor = sc.nextLine();
                    System.out.print("Título: ");
                    String titulo = sc.nextLine();
                    System.out.print("ISBN: ");
                    String isbn = sc.nextLine();
                    System.out.print("Editorial: ");
                    String editorial = sc.nextLine();
                    System.out.print("Género: ");
                    String genero = sc.nextLine();
                    System.out.print("Año: ");
                    int anio = sc.nextInt();
                    sc.nextLine();

                    libro nuevo = new libro(autor, titulo, isbn, editorial, genero, anio);
                    lib.getLibros().add(nuevo);

                    marshall.marshal(lib, new File("libros.xml"));
                    System.out.println("\n⋆⭒˚｡⋆ Nuevo libro añadido y XML actualizado correctamente.");
                }
                case 6 -> System.out.println("❤\uFE0E Gracias por usar la Biblioteca Personal. ¡Hasta luego!⋆\n˚☆˖°⋆｡° ✮˖ ࣪ ⊹⋆.˚");
                default -> System.out.println("✧ Opción no válida. Asegúrese de introducir un número del 1 al 6.");
            }

        } while (opcion != 6);

        sc.close();

    }
}
