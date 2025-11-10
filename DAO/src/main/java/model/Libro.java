package model;

public class Libro {
    private int id;
    private String titulo;

    public Libro() {}
    public Libro(int id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }

    public void setId(int id) { this.id = id; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    @Override
    public String toString() {
        return "ID=" + id + ", Título='" + titulo + "'";
    }
}
