package dao;

import model.Libro;
import java.util.List;

public interface LibroDAO {
    void addLibro(Libro libro) throws Exception;
    List<Libro> getAllLibros() throws Exception;
    Libro getLibroById(int id) throws Exception;
    void updateLibro(Libro libro) throws Exception;
    void deleteLibro(int id) throws Exception;
}
