/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.vista;

import dam.MainApp;
import javafx.fxml.FXML;

/**
 *
 * @author Samuel
 */
public class ControladorPrincipal {

    private MainApp stage;

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
