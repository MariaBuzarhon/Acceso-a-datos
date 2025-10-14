package Proyectos.TrabajoAccesoADatosUbicadosEnFicheros;

import java.io.*;
import java.util.*;

public class LecturaEscritura {

    private static final String FICHERO_USUARIOS = "usuarios.dat";
    private static final String FICHERO_CONCORDANCIAS = "concordancias.txt";
    private static final String FICHERO_CONCORDANCIAS_EDAD = "concordanciasEdad.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        System.out.println("\n¡Bienvenido al servicio de emparejamiento basado en aficiones!");
        System.out.println("✿︶︶︶︶︶︶︶︶︶︶︶︶︶✿");
        System.out.println("     Menú principal     ");
        System.out.println();

        do {
            System.out.println("""
                | 1. Añadir usuario.
                | 2. Mostrar usuarios introducidos.
                | 3. Generar fichero de concordancias de aficiones.
                | 4. Generar fichero de concordancias por edad.
                | 5. Eliminar fichero de concordancias por aficiones.
                | 6. Eliminar fichero de concordancias por edad.
                | 7. Eliminar todos los usuarios.
                | 8. Mostrar contenido de 'concordancias.txt'.
                | 9. Mostrar contenido de 'concordanciasEdad.txt'.
                | 10. Salir
                """);
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            try {
                switch (opcion) {
                    case 1 -> anadirUsuario(sc);
                    case 2 -> mostrarUsuarios();
                    case 3 -> generarConcordancias(sc);
                    case 4 -> generarConcordanciasEdad(sc);
                    case 5 -> eliminarFichero(FICHERO_CONCORDANCIAS);
                    case 6 -> eliminarFichero(FICHERO_CONCORDANCIAS_EDAD);
                    case 7 -> eliminarTodosLosUsuarios();
                    case 8 -> mostrarContenidoTxt(FICHERO_CONCORDANCIAS);
                    case 9 -> mostrarContenidoTxt(FICHERO_CONCORDANCIAS_EDAD);
                    case 10 -> System.out.println("Saliendo... ¡Adiós!");
                    default -> System.out.println("Opción no válida.\n");
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion != 10);
    }

    // Añadir un usuario
    private static void anadirUsuario(Scanner sc) throws IOException, ClassNotFoundException {
        List<Usuario> usuarios = leerUsuariosDesdeFichero();

        String codigo = "U" + (100 + usuarios.size());
        System.out.println("❀~✿ Código asignado: " + codigo);

        System.out.print("Introduce tu edad: ");
        int edad = sc.nextInt();
        sc.nextLine();

        System.out.println("❀~✿ Introduce tus aficiones separadas por espacios:");
        String linea = sc.nextLine().trim();
        if (linea.isEmpty()) {
            System.out.println("¡Debe introducir al menos una afición!");
            return;
        }

        List<String> aficiones = Arrays.asList(linea.split("\\s+"));
        Usuario nuevo = new Usuario(codigo, edad, aficiones);

        File fichero = new File(FICHERO_USUARIOS);
        try (FileOutputStream fos = new FileOutputStream(fichero, true);
             ObjectOutputStream oos = fichero.exists() && fichero.length() > 0
                     ? new MiObjectOutputStream(fos)
                     : new ObjectOutputStream(fos)) {
            oos.writeObject(nuevo);
            System.out.println("❀~✿ Usuario añadido correctamente. ❀~✿\n");
        }
    }

    // Mostrar usuarios
    private static void mostrarUsuarios() throws IOException, ClassNotFoundException {
        List<Usuario> usuarios = leerUsuariosDesdeFichero();

        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.\n");
            return;
        }

        System.out.println("\nUsuarios registrados ════════════❀~✿");
        usuarios.forEach(System.out::println);
        System.out.println();
    }

    // Generar fichero de concordancias por aficiones
    private static void generarConcordancias(Scanner sc) throws IOException, ClassNotFoundException {
        List<Usuario> usuarios = leerUsuariosDesdeFichero();

        if (usuarios.size() < 2) {
            System.out.println("No hay suficientes usuarios para comparar aficiones.");
            return;
        }

        System.out.print("Introduce el número mínimo de aficiones comunes: ");
        int minimo = sc.nextInt();
        sc.nextLine();

        if (minimo < 1) {
            System.out.println("El número debe ser mayor o igual que 1.");
            return;
        }

        List<String> parejas = new ArrayList<>();

        for (int i = 0; i < usuarios.size(); i++) {
            for (int j = i + 1; j < usuarios.size(); j++) {
                Usuario u1 = usuarios.get(i);
                Usuario u2 = usuarios.get(j);

                List<String> comunes = u1.aficionesComunes(u2);
                if (comunes.size() >= minimo) {
                    parejas.add(u1.getCodigo() + " " + u2.getCodigo() + " " + String.join(" ", comunes));
                }
            }
        }

        if (parejas.isEmpty()) {
            System.out.println("No se encontraron parejas con al menos " + minimo + " aficiones comunes.");
            return;
        }

        parejas.sort((a, b) -> Integer.compare(
                b.split(" ").length - 2,
                a.split(" ").length - 2
        ));

        try (PrintWriter pw = new PrintWriter(new FileWriter(FICHERO_CONCORDANCIAS))) {
            parejas.forEach(pw::println);
        }

        System.out.println("❀~✿ Archivo 'concordancias.txt' generado con " + parejas.size() + " parejas. ❀~✿\n");
    }

    // Generar fichero de concordancias por edad
    private static void generarConcordanciasEdad(Scanner sc) throws IOException, ClassNotFoundException {
        List<Usuario> usuarios = leerUsuariosDesdeFichero();

        if (usuarios.size() < 2) {
            System.out.println("No hay suficientes usuarios para comparar edades.");
            return;
        }

        System.out.print("Introduce la diferencia máxima de edad permitida: ");
        int maxDif = sc.nextInt();
        sc.nextLine();

        List<String> parejasEdad = new ArrayList<>();

        for (int i = 0; i < usuarios.size(); i++) {
            for (int j = i + 1; j < usuarios.size(); j++) {
                Usuario u1 = usuarios.get(i);
                Usuario u2 = usuarios.get(j);

                int dif = Math.abs(u1.getEdad() - u2.getEdad());
                if (dif <= maxDif) {
                    parejasEdad.add(u1.getCodigo() + " " + u2.getCodigo() + " (dif: " + dif + " años)");
                }
            }
        }

        if (parejasEdad.isEmpty()) {
            System.out.println("No se encontraron usuarios con una diferencia de edad igual o menor a " + maxDif + ".");
            return;
        }

        try (PrintWriter pw = new PrintWriter(new FileWriter(FICHERO_CONCORDANCIAS_EDAD))) {
            parejasEdad.forEach(pw::println);
        }

        System.out.println("Archivo 'concordanciasEdad.txt' generado con " + parejasEdad.size() + " parejas.\n");
    }

    // Leer usuarios desde el fichero
    private static List<Usuario> leerUsuariosDesdeFichero() throws IOException, ClassNotFoundException {
        List<Usuario> lista = new ArrayList<>();
        File fichero = new File(FICHERO_USUARIOS);

        if (!fichero.exists() || fichero.length() == 0) {
            return lista;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichero))) {
            while (true) {
                try {
                    Usuario u = (Usuario) ois.readObject();
                    lista.add(u);
                } catch (EOFException e) {
                    break;
                }
            }
        }
        return lista;
    }

    // Eliminar un fichero
    private static void eliminarFichero(String nombreFichero) {
        File f = new File(nombreFichero);
        if (f.exists()) {
            if (f.delete()) {
                System.out.println("Fichero '" + nombreFichero + "' eliminado correctamente.\n");
            } else {
                System.out.println("No se pudo eliminar el fichero.\n");
            }
        } else {
            System.out.println("El fichero no existe.\n");
        }
    }

    // Eliminar todos los usuarios
    private static void eliminarTodosLosUsuarios() {
        File fichero = new File(FICHERO_USUARIOS);
        if (fichero.exists()) {
            if (fichero.delete()) {
                System.out.println("Todos los usuarios han sido eliminados correctamente.\n");
            } else {
                System.out.println("No se pudo eliminar el fichero de usuarios.\n");
            }
        } else {
            System.out.println("No hay fichero de usuarios para eliminar.\n");
        }
    }

    // Mostrar contenido de un archivo .txt
    private static void mostrarContenidoTxt(String nombreFichero) {
        File f = new File(nombreFichero);

        if (!f.exists() || f.length() == 0) {
            System.out.println("El archivo '" + nombreFichero + "' no existe o está vacío.\n");
            return;
        }

        System.out.println("\nContenido del archivo '" + nombreFichero + "':");
        System.out.println("═════════════════ ∘◦❁◦∘ ═════════════════");

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        System.out.println("═════════════════ ∘◦❁◦∘ ═════════════════");
    }
}
