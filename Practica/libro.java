package Practica;

import jakarta.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"autor","titulo","editorial","isbn", "genero", "anio"})
public class libro {
    private String autor;
    private String titulo;
    private String isbn;
    private String editorial;
    private String genero;
    private int anio;



    public libro(String autor, String titulo, String isbn, String editorial, String genero, int anio) {
    this.autor = autor;
    this.titulo = titulo;
    this.isbn = isbn;
    this.editorial = editorial;
    this.genero = genero;
    this.anio = anio;
}

    public int getAnio() {
        return anio;
    }
    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditorial() {
        return editorial;
    }
    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

