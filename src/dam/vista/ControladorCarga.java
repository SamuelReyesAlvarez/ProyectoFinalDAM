/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.vista;

import dam.DAO.GenericDAO;
import dam.MainApp;
import dam.modelo.Estadisticas;
import dam.modelo.Estado;
import dam.modelo.JuegoException;
import dam.modelo.Jugador;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;

/**
 *
 * @author Samuel Reyes Alvarez
 *
 */
public class ControladorCarga implements Initializable {

    private static final int EXP_ACUMULADA_INICIAL = 0;
    private static final int NIVEL_INICIAL = 1;
    private static final int ORO_INICIAL = 0;
    private static final int PUNTOS_NO_USADOS_INICIAL = 20;
    private static final int ESTADISTICAS_INICIALES = 0;
    private static final int POTENCIADO_ESTADO_INICIAL = 0;

    private GenericDAO genericDao = new GenericDAO();
    private Jugador jugador;
    private MainApp stage;

    @FXML
    private ProgressBar barraCarga;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        barraCarga.setProgress(0);
    }

    public void setStage(MainApp stage) {
        this.stage = stage;
    }

    public void crearNuevoJugador(String nombre) throws JuegoException {
        jugador = new Jugador();
        jugador.setEquipoJugador(new HashSet<>());
        jugador.setEstadoJugador(new HashSet<>());
        jugador.setExpAcumulada(EXP_ACUMULADA_INICIAL);
        jugador.setNivel(NIVEL_INICIAL);
        jugador.setNombre(nombre);
        jugador.setOroActual(ORO_INICIAL);
        jugador.setPuntosNoUsados(PUNTOS_NO_USADOS_INICIAL);
        jugador.setTareaActiva(new ArrayList<>());

        genericDao.guardarActualizar(jugador);

        Estadisticas estadisticas = new Estadisticas();
        estadisticas.setDerrotas(ESTADISTICAS_INICIALES);
        estadisticas.setJugador(jugador);
        estadisticas.setMisionesCompletadas(ESTADISTICAS_INICIALES);
        estadisticas.setTotalAtaque(ESTADISTICAS_INICIALES);
        estadisticas.setTotalDefensa(ESTADISTICAS_INICIALES);
        estadisticas.setTotalRecaudado(ESTADISTICAS_INICIALES);
        estadisticas.setVictorias(ESTADISTICAS_INICIALES);

        genericDao.guardarActualizar(estadisticas);

        Estado estado = new Estado();
        estado.setJugador(jugador);
        estado.setPotenciado(POTENCIADO_ESTADO_INICIAL);

        for (Estado.TipoAtributo tipoEstado : Estado.TipoAtributo.values()) {
            estado.setTipoAtributo(tipoEstado);
            genericDao.guardarActualizar(jugador);
        }

        new MainApp().setJugador((Jugador) genericDao.obtenerTodo(jugador));
    }
}
