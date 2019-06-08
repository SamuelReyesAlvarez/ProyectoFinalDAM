/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.vista;

import dam.DAO.GenericDAO;
import dam.MainApp;
import dam.modelo.Acceso;
import dam.modelo.Estadisticas;
import dam.modelo.Estado;
import dam.modelo.JuegoException;
import dam.modelo.Jugador;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 *
 * @author Samuel Reyes Alvarez
 *
 */
public class ControladorCarga implements Initializable {

    private static final String NOMBRE_INICIAL = "NuevoJugador";
    private static final int EXP_ACUMULADA_INICIAL = 0;
    private static final int NIVEL_INICIAL = 1;
    private static final int ORO_INICIAL = 0;
    private static final int PUNTOS_NO_USADOS_INICIAL = 20;
    private static final int ESTADISTICAS_INICIALES = 0;
    private static final int POTENCIADO_ESTADO_INICIAL = 0;

    private GenericDAO genericDao = new GenericDAO();
    private Jugador jugador;
    private MainApp stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setStage(MainApp stage) {
        this.stage = stage;
    }

    public void crearNuevoJugador(Acceso nuevaCuenta) throws JuegoException {
        jugador = new Jugador();
        jugador.setEquipoJugador(new HashSet<>());
        jugador.setEstadoJugador(new HashSet<>());
        jugador.setExpAcumulada(EXP_ACUMULADA_INICIAL);
        jugador.setNivel(NIVEL_INICIAL);
        jugador.setNombre(NOMBRE_INICIAL);
        jugador.setOroActual(ORO_INICIAL);
        jugador.setPuntosNoUsados(PUNTOS_NO_USADOS_INICIAL);
        jugador.setTareaActiva(new ArrayList<>());

        HashSet<Estado> estadoJugador = new HashSet<Estado>();
        Estado estado = new Estado();
        estado.setJugador(jugador);
        estado.setPotenciado(POTENCIADO_ESTADO_INICIAL);

        for (Estado.TipoAtributo tipoEstado : Estado.TipoAtributo.values()) {
            estado.setTipoAtributo(tipoEstado);
            estadoJugador.add(estado);
        }

        jugador.setEstadoJugador(estadoJugador);

        Estadisticas estadisticas = new Estadisticas();
        estadisticas.setDerrotas(ESTADISTICAS_INICIALES);
        estadisticas.setMisionesCompletadas(ESTADISTICAS_INICIALES);
        estadisticas.setPuntosCombate(ESTADISTICAS_INICIALES);
        estadisticas.setTotalAtaque(ESTADISTICAS_INICIALES);
        estadisticas.setTotalDefensa(ESTADISTICAS_INICIALES);
        estadisticas.setTotalRecaudado(ESTADISTICAS_INICIALES);
        estadisticas.setVictorias(ESTADISTICAS_INICIALES);

        jugador.setEstadisticas(estadisticas);

        genericDao.guardarActualizar(jugador);

        nuevaCuenta.setJugador(jugador);
        genericDao.guardarActualizar(nuevaCuenta);

        stage.setJugador(((Acceso) genericDao.obtenerPorId(nuevaCuenta.getClass(),
                nuevaCuenta.getCorreo())).getJugador());

        // Abrir la partida creada
        stage.mostrarPrincipal();
    }
}
