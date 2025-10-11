package Proyectos.TrabajoAccesoADatosUbicadosEnFicheros;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    private String codigo;        // Ej: U100
    private int edad;             // Edad del usuario
    private List<String> aficiones;

    public Usuario(String codigo, int edad, List<String> aficiones) {
        this.codigo = codigo;
        this.edad = edad;
        this.aficiones = aficiones;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getEdad() {
        return edad;
    }

    public List<String> getAficiones() {
        return aficiones;
    }

    @Override
    public String toString() {
        String hobbies = aficiones.stream().collect(Collectors.joining(" | "));
        return codigo + " (" + edad + " años) → [" + hobbies + "]";
    }

    // Devuelve las aficiones comunes entre dos usuarios
    public List<String> aficionesComunes(Usuario otro) {
        return aficiones.stream()
                .filter(otro.aficiones::contains)
                .sorted()
                .toList();
    }

    // Devuelve la diferencia de edad con otro usuario
    public int diferenciaEdad(Usuario otro) {
        return Math.abs(this.edad - otro.edad);
    }
}
