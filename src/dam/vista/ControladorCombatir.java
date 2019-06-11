/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.vista;

import dam.DAO.GenericDAO;
import dam.DAO.JugadorDAO;
import dam.DAO.MisionDAO;
import dam.MainApp;
import dam.modelo.Combate;
import dam.modelo.JuegoException;
import dam.modelo.Jugador;
import dam.modelo.Mision;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Samuel Reyes Alvarez
 *
 */
public class ControladorCombatir implements Initializable {

    private static final int MAX_LISTA_DESAFIAR = 5;
    private static final int RANGO_MAX_BUSQUEDA = 25;

    private GenericDAO genericDao = new GenericDAO();
    private JugadorDAO jugadorDao = new JugadorDAO();
    private MisionDAO misionDao = new MisionDAO();
    private List<Jugador> clasificacion;
    private ArrayList<Jugador> listaDesafio;
    private MainApp stage;
    private Jugador jugador;

    @FXML
    private Label nombre0, nombre1, nombre2, nombre3, nombre4;
    @FXML
    private ImageView imagen0, imagen1, imagen2, imagen3, imagen4;
    @FXML
    private Label nivel0, nivel1, nivel2, nivel3, nivel4;
    @FXML
    private Label puntos0, puntos1, puntos2, puntos3, puntos4;
    @FXML
    private Label vida0, vida1, vida2, vida3, vida4;
    @FXML
    private Label ataqueMin0, ataqueMin1, ataqueMin2, ataqueMin3, ataqueMin4;
    @FXML
    private Label ataqueMax0, ataqueMax1, ataqueMax2, ataqueMax3, ataqueMax4;
    @FXML
    private Label defensaMin0, defensaMin1, defensaMin2, defensaMin3, defensaMin4;
    @FXML
    private Label defensaMax0, defensaMax1, defensaMax2, defensaMax3, defensaMax4;
    @FXML
    private Label victoria0, victoria1, victoria2, victoria3, victoria4;
    @FXML
    private Label derrota0, derrota1, derrota2, derrota3, derrota4;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        jugador = stage.getJugador();
        cargarListaDesafio();
    }

    public void setStage(MainApp stage) {
        this.stage = stage;
    }

    @FXML
    public void desafiar0() {
        desafiar(0, Integer.parseInt(victoria0.getText()), Integer.parseInt(derrota0.getText()));
    }

    @FXML
    public void desafiar1() {
        desafiar(1, Integer.parseInt(victoria1.getText()), Integer.parseInt(derrota1.getText()));
    }

    @FXML
    public void desafiar2() {
        desafiar(2, Integer.parseInt(victoria2.getText()), Integer.parseInt(derrota2.getText()));
    }

    @FXML
    public void desafiar3() {
        desafiar(3, Integer.parseInt(victoria3.getText()), Integer.parseInt(derrota3.getText()));
    }

    @FXML
    public void desafiar4() {
        desafiar(4, Integer.parseInt(victoria4.getText()), Integer.parseInt(derrota4.getText()));
    }

    private void desafiar(int id, int puntosVictoria, int puntosDerrota) {
        // Comprobar si el jugador está actualmente en una misión
        Mision mision = jugador.getTareaActiva().get(jugador.getTareaActiva().size() - 1);
        int tiempoRestante = misionDao.tiempoRestanteMision(mision);

        if (tiempoRestante > 0) {
            // Informar del tiempo restante en misión
            int horas = (tiempoRestante / 3600);
            int minutos = (tiempoRestante / 60);
            int segundos = (tiempoRestante % 60);
            String tiempo = horas + ":"
                    + ((minutos < 10) ? ("0" + minutos) : minutos) + ":"
                    + ((segundos < 10) ? ("0" + segundos) : segundos);

            mostrarAlerta(Alert.AlertType.WARNING, "Atención", "Acción no disponible",
                    "Actualmente estás en una misión.\nTiempo restante:\n" + tiempo);
        } else if (tiempoRestante < 0) {
            // Notificar que la misión ha concluido pero necesita ser completada
            mostrarAlerta(Alert.AlertType.INFORMATION, "Notificación",
                    "Recompensa de misión no recibida",
                    "Has terminado una misión pero no has recogido la recomepnsa.\n"
                    + "Dirígete a la pantalla de misiones para cobrarla");
        } else {
            // Realizar combate entre el jugador de la partida y el seleccionado de la lista
            Jugador contrario = (Jugador) genericDao.obtenerPorId(Jugador.class, listaDesafio.get(id).getIdJugador());

            simularCombate(contrario, puntosVictoria, puntosDerrota);
        }
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String cabecera, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(cabecera);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    private void cargarListaDesafio() {
        // Cargar clasificacion por puntos de combate
        clasificacion = jugadorDao.filtroDesafio();

        // Cargar lista con 5 jugadores con puntuación aproximada
        HashSet<Jugador> prelistado = new HashSet<>();
        int posicionJugador = clasificacion.indexOf(jugador);
        Random r = new Random();
        int i = 0;

        while (prelistado.size() < MAX_LISTA_DESAFIAR) {
            try {
                if (i % 2 == 0) {
                    prelistado.add(clasificacion.get(posicionJugador + r.nextInt(RANGO_MAX_BUSQUEDA) + 1));
                } else {
                    prelistado.add(clasificacion.get(posicionJugador - r.nextInt(RANGO_MAX_BUSQUEDA) - 1));
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                // En este caso no es necesario hacer nada
            }
            i++;
        }

        for (Jugador jugador : prelistado) {
            listaDesafio.add(jugador);
        }

        // Cargar datos de los jugadores elegidos en la vista
        cargarDatosEnVista();
    }

    private void cargarDatosEnVista() {
        nombre0.setText(listaDesafio.get(0).getNombre());
        imagen0.setImage(new Image(new File(listaDesafio.get(0).getImagen()).toURI().toString()));
        nivel0.setText(String.valueOf(listaDesafio.get(0).getNivel()));
        puntos0.setText(String.valueOf(listaDesafio.get(0).getEstadisticas().getPuntosCombate()));
        vida0.setText(String.valueOf(listaDesafio.get(0).getVidaMax()));
        ataqueMin0.setText(String.valueOf(listaDesafio.get(0).getAtaqueMin()));
        ataqueMax0.setText(String.valueOf(listaDesafio.get(0).getAtaqueMax()));
        defensaMin0.setText(String.valueOf(listaDesafio.get(0).getDefensaMin()));
        defensaMax0.setText(String.valueOf(listaDesafio.get(0).getDefensaMax()));
        victoria0.setText(calcularPuntosPorVD(listaDesafio.get(0), true));
        derrota0.setText(calcularPuntosPorVD(listaDesafio.get(0), false));

        nombre1.setText(listaDesafio.get(1).getNombre());
        imagen1.setImage(new Image(new File(listaDesafio.get(1).getImagen()).toURI().toString()));
        nivel1.setText(String.valueOf(listaDesafio.get(1).getNivel()));
        puntos1.setText(String.valueOf(listaDesafio.get(1).getEstadisticas().getPuntosCombate()));
        vida1.setText(String.valueOf(listaDesafio.get(1).getVidaMax()));
        ataqueMin1.setText(String.valueOf(listaDesafio.get(1).getAtaqueMin()));
        ataqueMax1.setText(String.valueOf(listaDesafio.get(1).getAtaqueMax()));
        defensaMin1.setText(String.valueOf(listaDesafio.get(1).getDefensaMin()));
        defensaMax1.setText(String.valueOf(listaDesafio.get(1).getDefensaMax()));
        victoria1.setText(calcularPuntosPorVD(listaDesafio.get(1), true));
        derrota1.setText(calcularPuntosPorVD(listaDesafio.get(1), false));

        nombre2.setText(listaDesafio.get(2).getNombre());
        imagen2.setImage(new Image(new File(listaDesafio.get(2).getImagen()).toURI().toString()));
        nivel2.setText(String.valueOf(listaDesafio.get(2).getNivel()));
        puntos2.setText(String.valueOf(listaDesafio.get(2).getEstadisticas().getPuntosCombate()));
        vida2.setText(String.valueOf(listaDesafio.get(2).getVidaMax()));
        ataqueMin2.setText(String.valueOf(listaDesafio.get(2).getAtaqueMin()));
        ataqueMax2.setText(String.valueOf(listaDesafio.get(2).getAtaqueMax()));
        defensaMin2.setText(String.valueOf(listaDesafio.get(2).getDefensaMin()));
        defensaMax2.setText(String.valueOf(listaDesafio.get(2).getDefensaMax()));
        victoria2.setText(calcularPuntosPorVD(listaDesafio.get(2), true));
        derrota2.setText(calcularPuntosPorVD(listaDesafio.get(2), false));

        nombre3.setText(listaDesafio.get(3).getNombre());
        imagen3.setImage(new Image(new File(listaDesafio.get(3).getImagen()).toURI().toString()));
        nivel3.setText(String.valueOf(listaDesafio.get(3).getNivel()));
        puntos3.setText(String.valueOf(listaDesafio.get(3).getEstadisticas().getPuntosCombate()));
        vida3.setText(String.valueOf(listaDesafio.get(3).getVidaMax()));
        ataqueMin3.setText(String.valueOf(listaDesafio.get(3).getAtaqueMin()));
        ataqueMax3.setText(String.valueOf(listaDesafio.get(3).getAtaqueMax()));
        defensaMin3.setText(String.valueOf(listaDesafio.get(3).getDefensaMin()));
        defensaMax3.setText(String.valueOf(listaDesafio.get(3).getDefensaMax()));
        victoria3.setText(calcularPuntosPorVD(listaDesafio.get(3), true));
        derrota3.setText(calcularPuntosPorVD(listaDesafio.get(3), false));

        nombre4.setText(listaDesafio.get(4).getNombre());
        imagen4.setImage(new Image(new File(listaDesafio.get(4).getImagen()).toURI().toString()));
        nivel4.setText(String.valueOf(listaDesafio.get(4).getNivel()));
        puntos4.setText(String.valueOf(listaDesafio.get(4).getEstadisticas().getPuntosCombate()));
        vida4.setText(String.valueOf(listaDesafio.get(4).getVidaMax()));
        ataqueMin4.setText(String.valueOf(listaDesafio.get(4).getAtaqueMin()));
        ataqueMax4.setText(String.valueOf(listaDesafio.get(4).getAtaqueMax()));
        defensaMin4.setText(String.valueOf(listaDesafio.get(4).getDefensaMin()));
        defensaMax4.setText(String.valueOf(listaDesafio.get(4).getDefensaMax()));
        victoria4.setText(calcularPuntosPorVD(listaDesafio.get(4), true));
        derrota4.setText(calcularPuntosPorVD(listaDesafio.get(4), false));
    }

    private String calcularPuntosPorVD(Jugador otro, boolean victoria) {
        int diferenciaPuntos = otro.getEstadisticas().getPuntosCombate()
                - jugador.getEstadisticas().getPuntosCombate();
        int premio;

        if (diferenciaPuntos > 24) {
            premio = 25;
        } else if (diferenciaPuntos < -24) {
            premio = 0;
        } else {
            premio = (diferenciaPuntos + 25) / 2;
        }

        return String.valueOf((victoria) ? premio : (premio - 25));
    }

    private void simularCombate(Jugador contrario, int puntosVictoria, int puntosDerrota) {
        int vidaJugador = jugador.getVidaMax();
        int vidaContrario = contrario.getVidaMax();
        int elementoAtaqueJugador = jugador.getElementoDominante().getPotenciado();
        int elementoAtaqueContrario = contrario.getElementoDominante().getPotenciado();
        int elementoDefensaJugador = jugador.getElementoDefensa(contrario.getElementoDominante()).getPotenciado();
        int elementoDefensaContrario = contrario.getElementoDefensa(jugador.getElementoDominante()).getPotenciado();
        int ventajaJugador = elementoAtaqueJugador - elementoDefensaContrario;
        int ventajaContrario = elementoAtaqueContrario - elementoDefensaJugador;

        StringBuilder resumen = new StringBuilder();
        int ataqueTotalJugador = 0;
        int ataqueTotalContrario = 0;
        int defensaTotalJugador = 0;
        int defensaTotalContrario = 0;
        Random r;
        int ataque;
        int defensa;
        int danio;

        int turno = 1;
        while (vidaJugador > 0 && vidaContrario > 0) {
            r = new Random(System.currentTimeMillis());
            if (turno % 2 == 0) {
                // Turno del jugador
                ataque = ventajaJugador + r.nextInt(jugador.getAtaqueMax()
                        - jugador.getAtaqueMin()) + jugador.getAtaqueMin();
                defensa = r.nextInt(contrario.getDefensaMax()
                        - contrario.getDefensaMin()) + contrario.getDefensaMin();
                ataque = ataque < 1 ? 0 : ataque;
                ataqueTotalJugador += ataque;
                defensa = ataque < defensa ? ataque : defensa;
                defensaTotalContrario += defensa;
                danio = ataque - defensa;
                vidaContrario = danio < 1 ? 0 : danio;

                resumen.append(jugador.getNombre() + " ataca con " + ataque
                        + " puntos de golpe\n");
                resumen.append(contrario.getNombre() + " detiene " + defensa
                        + " puntos de golpe perdiendo " + danio + " puntos de vida\n\n");
            } else {
                // Turno del contrario
                ataque = ventajaContrario + r.nextInt(contrario.getAtaqueMax()
                        - contrario.getAtaqueMin()) + contrario.getAtaqueMin();
                defensa = r.nextInt(jugador.getDefensaMax()
                        - jugador.getDefensaMin()) + jugador.getDefensaMin();
                ataque = ataque < 1 ? 0 : ataque;
                ataqueTotalContrario += ataque;
                defensa = ataque < defensa ? ataque : defensa;
                defensaTotalJugador += defensa;
                danio = ataque - defensa;
                vidaJugador = danio < 1 ? 0 : danio;

                resumen.append(contrario.getNombre() + " ataca con " + ataque
                        + " puntos de golpe\n");
                resumen.append(jugador.getNombre() + " detiene " + defensa
                        + " puntos de golpe perdiendo " + danio + " puntos de vida\n\n");
            }
            turno++;
        }

        contrario.getEstadisticas().aumentarTotalAtaque(ataqueTotalContrario);
        contrario.getEstadisticas().aumentarTotalDefensa(defensaTotalContrario);
        jugador.getEstadisticas().aumentarTotalAtaque(ataqueTotalJugador);
        jugador.getEstadisticas().aumentarTotalDefensa(defensaTotalJugador);

        Combate combate = new Combate();
        combate.setJugador(jugador);
        combate.setContrario(contrario);
        combate.setNivelJugador(jugador.getNivel());
        combate.setNivelContrario(contrario.getNivel());
        combate.setVidaJugador(jugador.getVidaMax());
        combate.setVidaContrario(contrario.getVidaMax());
        combate.setAtaqueTotalJugador(ataqueTotalJugador);
        combate.setAtaqueTotalContrario(ataqueTotalContrario);
        combate.setDefensaTotalJugador(defensaTotalJugador);
        combate.setDefensaTotalContrario(defensaTotalContrario);

        if (vidaJugador < 1) {
            contrario.getEstadisticas().aumentarVictorias();
            contrario.getEstadisticas().cambiarPuntosCombate(-puntosDerrota);
            jugador.getEstadisticas().aumentarDerrotas();
            jugador.getEstadisticas().cambiarPuntosCombate(puntosDerrota);

            resumen.append(contrario.getNombre() + " gana el combate y obtiene "
                    + (-puntosDerrota) + " puntos de combate\n");
            resumen.append(jugador.getNombre() + " pierde el combate y "
                    + (puntosDerrota) + " puntos de combate\n");

            combate.setPuntosJugador(puntosDerrota);
            combate.setPuntosContrario(-puntosDerrota);
        } else {
            jugador.getEstadisticas().aumentarVictorias();
            jugador.getEstadisticas().cambiarPuntosCombate(puntosVictoria);
            contrario.getEstadisticas().aumentarDerrotas();
            contrario.getEstadisticas().cambiarPuntosCombate(-puntosVictoria);

            resumen.append(jugador.getNombre() + " gana el combate y obtiene "
                    + (puntosVictoria) + " puntos de combate\n");
            resumen.append(contrario.getNombre() + " pierde el combate y "
                    + (-puntosVictoria) + " puntos de combate\n");

            combate.setPuntosJugador(-puntosVictoria);
            combate.setPuntosContrario(puntosVictoria);
        }

        try {
            genericDao.guardarActualizar(contrario);
            genericDao.guardarActualizar(jugador);

            genericDao.guardarActualizar(combate);
        } catch (JuegoException ex) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Acción no disponible",
                    "Se produjo un error mientras se simulaba el combate y no se guardaron los cambios.");
            stage.mostrarPrincipal();
        }

        mostrarAlerta(Alert.AlertType.INFORMATION,
                jugador.getNombre() + " vs " + contrario.getNombre(),
                "Combate finalizado", resumen.toString());

        stage.mostrarPrincipal();
        stage.mostrarCombatir();
    }
}
