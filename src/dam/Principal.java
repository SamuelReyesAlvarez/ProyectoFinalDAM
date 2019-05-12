package dam;

import dam.controlador.ControladorEntrada;
import dam.controlador.ControladorInicio;
import dam.controlador.ControladorJuego;
import dam.modelo.BasesDeDatos;
import dam.modelo.FlujoJuego;
import dam.vista.panel.PanelEntrada;
import dam.vista.ventana.VentanaCarga;
import dam.vista.ventana.VentanaEntrada;
import dam.vista.ventana.VentanaInicio;
import dam.vista.ventana.VentanaJuego;

/**
 *
 * @author Samuel Reyes
 *
 * @version 1.0.0
 *
 * Ultima actualizacion 12/05/2019
 *
 */
public class Principal {

    // Localizacion del icono distintivo de la aplicacion
    private static final String RUTA_ICONO = "src/imagenes/KnightFightLogoAplicacion.png";

    // Variables para gestionar los mismos datos entre diferentes pantallas
    private static FlujoJuego flujo;
    private static BasesDeDatos bd;
    private static String nombre;
    private static boolean nueva;

    public static void main(String[] args) {
        // Ventana con video de presentacion
        VentanaInicio vInicio = new VentanaInicio();
        ControladorInicio ctrInicio = new ControladorInicio(vInicio);
        vInicio.addControlador(ctrInicio);

        do {
            // Menu de entrada
            VentanaEntrada vEntrada = new VentanaEntrada(RUTA_ICONO);
            PanelEntrada pEntrada = new PanelEntrada();
            ControladorEntrada ctrEntrada = new ControladorEntrada(vEntrada, pEntrada);
            pEntrada.addControlador(ctrEntrada);
            vEntrada.setContentPane(pEntrada);

            // Provoca que la siguiente ventana no se cargue hasta que todos los
            // datos tengan valores
            while (ctrEntrada.getBD() == null || ctrEntrada.getNombre() == null) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    // espera
                }
            }

            // Obtiene los valores de los datos requeridos de la ventana anterior
            nombre = ctrEntrada.getNombre();
            bd = ctrEntrada.getBD();
            nueva = ctrEntrada.getNueva();

            // Prepara las ventanas para que la barra de progreso las gestione
            VentanaCarga vCarga = new VentanaCarga(RUTA_ICONO);
            VentanaJuego vJuego = new VentanaJuego(RUTA_ICONO);

            // Inicia la carga de los datos de la partida
            ctrEntrada.iniciarCarga(vCarga, vJuego, bd, nombre, nueva);
            flujo = ctrEntrada.getFlujo();

            // Gestiona las acciones del jugador sobre el desarrollo del juego
            ControladorJuego ctrJuego = new ControladorJuego(bd, vJuego, flujo);

            // Controla la visibilidad de la ventana de juego hasta que el usuario
            // la cierra sin finalizar la aplicacion
            while (bd != null) {
                try {
                    bd = ctrJuego.getBD();
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    // espera
                }
            }

            // Regresa a la ventana de entrada si el usuario no cierra la aplicacion
        } while (true);
    }
}
