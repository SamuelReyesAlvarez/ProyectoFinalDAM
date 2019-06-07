/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.vista;

import dam.MainApp;
import dam.modelo.Jugador;
import dam.modelo.MoverVentana;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;

/**
 *
 * @author Samuel Reyes Alvarez
 *
 */
public class ControladorPrincipal implements Initializable, MoverVentana {

    private Jugador jugador;
    private MainApp stage;

    @FXML
    private HBox marco;
    @FXML
    private Label nombre;
    @FXML
    private Label nivel;
    @FXML
    private Label oro;
    @FXML
    private Label vida;
    @FXML
    private ProgressBar barraVida;
    @FXML
    private Label experienciaActual;
    @FXML
    private Label experienciaNivel;
    @FXML
    private ProgressBar barraExperiencia;
    @FXML
    private Label ataqueMin;
    @FXML
    private Label ataqueMax;
    @FXML
    private Label defensaMin;
    @FXML
    private Label defensaMax;
    @FXML
    private Label creditos;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        creditos.setText("Knight Fight 1.4.1 - @SamuelReyesAlvarez 2019");
        this.onDraggedScene(marco);

        // Cargar datos del jugador en los componentes
        jugador = stage.getJugador();
        nombre.setText(jugador.getNombre());
        nivel.setText(String.valueOf(jugador.getNivel()));
        oro.setText(String.valueOf(jugador.getOroActual()));
        vida.setText(String.valueOf(jugador.getVidaMax()));
        barraVida.setProgress(1);
        experienciaActual.setText(String.valueOf(jugador.getExpAcumuladaNivelActual()));
        experienciaNivel.setText(String.valueOf(jugador.getExpTotalNivelActual()));
        barraExperiencia.setProgress(jugador.getProgresoExp());
        ataqueMin.setText(String.valueOf(jugador.getAtaqueMin()));
        ataqueMax.setText(String.valueOf(jugador.getAtaqueMax()));
        defensaMin.setText(String.valueOf(jugador.getDefensaMin()));
        defensaMax.setText(String.valueOf(jugador.getDefensaMax()));
    }

    public void setStage(MainApp stage) {
        this.stage = stage;
    }

    @FXML
    public void salirPartida() {
        stage.mostrarLogin(null);
    }

    @FXML
    public void salirJuego() {
        stage.cerrarSesion();
        System.exit(1);
    }

    @FXML
    public void inventario() {
        stage.mostrarInventario();
    }

    @FXML
    public void combatir() {
        stage.mostrarCombatir();
    }

    @FXML
    public void mision() {
        stage.mostrarMision();
    }

    @FXML
    public void bazar() {
        stage.mostrarBazar();
    }

    @FXML
    public void clasificacion() {
        stage.mostrarClasificacion();
    }

    @FXML
    public void tutorial() {
        File file = new File("archivos/Tutorial.pdf");
        stage.getHostServices().showDocument(file.getPath());
    }

    @FXML
    public void manual() {
        File file = new File("archivos/ManualUsuario.pdf");
        stage.getHostServices().showDocument(file.getPath());
    }

    @FXML
    public void ayuda() {
        File file = new File("archivos/GuiaRapida.pdf");
        stage.getHostServices().showDocument(file.getPath());
    }

    public void cambiarNombre() {
        nombre.setText(jugador.getNombre());
    }

    public void cambiarNivel() {
        nivel.setText(String.valueOf(jugador.getNivel()));
    }

    public void cambiarOro() {
        oro.setText(String.valueOf(jugador.getOroActual()));
    }

    public void cambiarVida() {
        vida.setText(String.valueOf(jugador.getVidaMax()));
    }

    public void cambiarBarraVida() {
        barraVida.setProgress(1);
    }

    public void cambiarExperienciaActual() {
        experienciaActual.setText(String.valueOf(jugador.getExpAcumuladaNivelActual()));
    }

    public void cambiarExperienciaNivel() {
        experienciaNivel.setText(String.valueOf(jugador.getExpTotalNivelActual()));
    }

    public void cambiarBarraExperiencia() {
        barraExperiencia.setProgress(jugador.getProgresoExp());
    }

    public void cambiarAtaqueMin() {
        ataqueMin.setText(String.valueOf(jugador.getAtaqueMin()));
    }

    public void cambiarAtaqueMax() {
        ataqueMax.setText(String.valueOf(jugador.getAtaqueMax()));
    }

    public void cambiarDefensaMin() {
        defensaMin.setText(String.valueOf(jugador.getDefensaMin()));
    }

    public void cambiarDefensaMax() {
        defensaMax.setText(String.valueOf(jugador.getDefensaMax()));
    }
}
