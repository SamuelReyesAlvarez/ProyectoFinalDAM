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
import javafx.scene.layout.HBox;

/**
 *
 * @author Samuel
 */
public class ControladorPrincipal implements Initializable, MoverVentana {

    private MainApp stage;

    @FXML
    private HBox marco;

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
}
