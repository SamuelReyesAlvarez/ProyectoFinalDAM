/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.vista;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 *
 * @author Samuel Reyes Alvarez
 *
 */
public class ControladorCombatir implements Initializable {

    private static final int MAX_LISTA_DESAFIAR = 5;

    private ControladorPrincipal controlPrincipal;

    @FXML
    private Label nombre0, nombre1, nombre2, nombre3, nombre4;
    @FXML
    private ImageView imagen0, imagen1, imagen2, imagen3, imagen4;
    @FXML
    private Label nivel0, nivel1, nivel2, nivel3, nivel4;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Cargar lista con 5 jugadores con puntuación aproximada
    }

    public void setControladorPrincipal(ControladorPrincipal controlPrincipal) {
        this.controlPrincipal = controlPrincipal;
    }

    @FXML
    public void desafiar0() {
        desafiar(0);
    }

    @FXML
    public void desafiar1() {
        desafiar(1);
    }

    @FXML
    public void desafiar2() {
        desafiar(2);
    }

    @FXML
    public void desafiar3() {
        desafiar(3);
    }

    @FXML
    public void desafiar4() {
        desafiar(4);
    }

    private void desafiar(int id) {
        // Realizar combate entre el jugador de la partida y el seleccionado de la lista
    }
}
