package service;

import dao.LibroDAO;
import model.Libro;
import java.util.List;

public class BibliotecaService {
    private LibroDAO libroDAO;

    public BibliotecaService(LibroDAO libroDAO) {
        this.libroDAO = libroDAO;
    }

    public void registrarLibro(String titulo) {
        try {
            Libro libro = new Libro(0, titulo);
            libroDAO.addLibro(libro);
        } catch (Exception e) {
            System.err.println("Error al registrar libro: " + e.getMessage());
        }
    }

    public List<Libro> listarLibros() {
        try {
            return libroDAO.getAllLibros();
        } catch (Exception e) {
            System.err.println("Error al listar libros: " + e.getMessage());
            return List.of();
        }
    }

    public void cambiarTitulo(int id, String nuevoTitulo) {
        try {
            Libro libro = libroDAO.getLibroById(id);
            if (libro != null) {
                libro.setTitulo(nuevoTitulo);
                libroDAO.updateLibro(libro);
            } else {
                System.out.println("Service: No se encontró el libro con id=" + id);
            }
        } catch (Exception e) {
            System.err.println("Error al actualizar libro: " + e.getMessage());
        }
    }

    public void eliminarLibro(int id) {
        try {
            libroDAO.deleteLibro(id);
        } catch (Exception e) {
            System.err.println("Error al eliminar libro: " + e.getMessage());
        }
    }
}
