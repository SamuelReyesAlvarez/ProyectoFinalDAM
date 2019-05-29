package dam.modelo;

/**
 *
 * @author Samuel Reyes Alvarez
 *
 * Se necesita de un lanzador de excepciones propio para la transimision de
 * mensajes importantes
 *
 */
public class JuegoException extends Exception {

    public JuegoException(String mensaje) {
        super(mensaje);
    }
}
