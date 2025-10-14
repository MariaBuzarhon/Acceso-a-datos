package PracticaFicherosXMl_SAX;

import ejemplo.GestionSax;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;

import static org.xml.sax.helpers.XMLReaderFactory.createXMLReader;

public class Principal {
    public static void main(String[] args) throws SAXException, IOException {
        XMLReader procesador = createXMLReader();
        GestionLibros gestionLibros = new GestionLibros();
        procesador.setContentHandler(gestionLibros);
        InputSource fichero = new InputSource("src/PracticaFicherosXMl_SAX/documentopedidos.xml");

        procesador.parse(fichero);
    }
}
