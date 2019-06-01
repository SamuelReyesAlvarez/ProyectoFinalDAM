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

/**
 *
 * @author Samuel Reyes Alvarez
 *
 */
public class ControladorInventario implements Initializable {

    private ControladorPrincipal controlPrincipal;

    @FXML
    private Label fuerza;
    @FXML
    private Label destreza;
    @FXML
    private Label armadura;
    @FXML
    private Label constitucion;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Cargar datos del jugador
    }

    public void setControladorPrincipal(ControladorPrincipal controlPrincipal) {
        this.controlPrincipal = controlPrincipal;
    }

    @FXML
    public void asignarPuntos() {

    }
}
