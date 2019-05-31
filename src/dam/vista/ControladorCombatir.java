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
import javafx.scene.layout.HBox;

/**
 *
 * @author Samuel Reyes Alvarez
 *
 */
public class ControladorCombatir implements Initializable {

    private ControladorPrincipal controlPrincipal;

    @FXML
    private HBox listaCombatir;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Cargar lista de jugadores con la misma puntuacion
    }

    public void setControladorPrincipal(ControladorPrincipal controlPrincipal) {
        this.controlPrincipal = controlPrincipal;
    }

    @FXML
    public void desafiar() {

    }
}
