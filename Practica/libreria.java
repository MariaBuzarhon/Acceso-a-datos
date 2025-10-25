package Practica;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "Libreria")
public class libreria {
    private String nombre;
    private String lugar;
    private List<libro> libros = new ArrayList<>();

    public libreria() {}

    public libreria(String nombre, String lugar) {
        this.nombre = nombre;
        this.lugar = lugar;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    @XmlElementWrapper(name = "Libros")
    @XmlElement(name = "Libro")
    public List<libro> getLibros() {
        return libros;
    }

    public void setLibros(List<libro> libros) {
        this.libros = libros;
    }
}
