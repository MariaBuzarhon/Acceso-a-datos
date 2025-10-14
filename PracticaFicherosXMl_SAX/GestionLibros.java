package PracticaFicherosXMl_SAX;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class GestionLibros extends DefaultHandler {

    //Variables para almacenar temporalmente los datos de cada libro.
    private String elementoActual = "";
    private String nombreLibro = "";
    private String autorLibro = "";
    private double precioLibro = 0.0;
    private double totalGeneral = 0.0;

    //Primera línea de nuestro XML
    @Override
    public void startDocument() {
        System.out.println("\uD83D\uDCDC Factura iniciada\n");//Emoji de pergamino
    }

    //Guarda el nombre de nuestro libro (elemento) actual.
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        elementoActual = qName;
    }

    //Extrae el elemento entre las etiquetas indicadas y los guarda según de que tipo sea.
    @Override
    public void characters(char[] ch, int start, int length) {
        String contenido = new String(ch, start, length).trim();
        if (contenido.isEmpty()) return;

        switch (elementoActual.toLowerCase()) {
            case "nombre":
                nombreLibro = contenido;
                break;
            case "autor":
                autorLibro = contenido;
                break;
            case "precio":
                try {
                    precioLibro = Double.parseDouble(contenido);
                } catch (NumberFormatException e) {
                    precioLibro = 0.0;
                }
                break;
        }
    }

    //Cuando termina de leer un libro, imprime sus datos y calcula el precio tras impuestos.
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("libro")) {
            double precioConIVA = precioLibro * 1.16;
            System.out.println("════════════════════════════ ❀~✿");
            System.out.println("\uD83D\uDCD6 Libro: " + nombreLibro);//Emoji de libro
            System.out.println("\uD83D\uDC64 Autor: " + autorLibro);
            System.out.printf("   Precio base: %.2f\n", precioLibro);
            System.out.printf("\uD83D\uDCB2 Precio con IVA: %.2f\n\n", precioConIVA);

            totalGeneral += precioConIVA;

            //Limpiar variables para el siguiente libro
            nombreLibro = "";
            autorLibro = "";
            precioLibro = 0.0;
        }
        elementoActual = "";
    }

    //Línea final del documento
    @Override
    public void endDocument() {
        System.out.printf("\uD83D\uDCB6 Total general del pedido: %.2f\n", totalGeneral); //Emoji de dinero
    }
}
