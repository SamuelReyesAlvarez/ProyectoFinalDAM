/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.vista;

import dam.MainApp;
import dam.modelo.MoverVentana;
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

    private MainApp stage;

    @FXML
    private HBox marco;
    @FXML
    private Label nivel;
    @FXML
    private Label oro;
    @FXML
    private Label vida;
    @FXML
    private ProgressBar barraVida;
    @FXML
    private Label experiencia;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.onDraggedScene(marco);
    }

    public void setStage(MainApp stage) {
        this.stage = stage;
    }

    @FXML
    public void salirPartida() {
        stage.mostrarLogin();
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

    }

    @FXML
    public void manual() {

    }

    @FXML
    public void ayuda() {

    }
}
