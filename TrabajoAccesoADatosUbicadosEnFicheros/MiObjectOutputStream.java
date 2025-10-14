package Proyectos.TrabajoAccesoADatosUbicadosEnFicheros;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class MiObjectOutputStream extends ObjectOutputStream {

    public MiObjectOutputStream(OutputStream out) throws IOException {
        super(out);
    }

    @Override
    protected void writeStreamHeader() throws IOException {
        //Esto no escribir cabecera de nuevo al añadir objetos
    }
}
