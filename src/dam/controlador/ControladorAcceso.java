/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.controlador;

import dam.DAO.GenericDAO;
import dam.MainApp;
import dam.modelo.Acceso;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author Samuel
 */
public class ControladorAcceso implements Initializable {

    private GenericDAO genericDao = new GenericDAO();
    private MainApp stage;

    @FXML
    private TextField correo;
    @FXML
    private PasswordField clave;
    @FXML
    private Label error;

    public ControladorAcceso() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        error.setVisible(false);
        correo.focusedProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue) {
                error.setVisible(false);
            }
        }));
        clave.focusedProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue) {
                error.setVisible(false);
            }
        }));
    }

    public void setStage(MainApp stage) {
        this.stage = stage;
    }

    @FXML
    public void acceder() {
        System.out.println("dam.controlador.ControladorAcceso.acceder()");
        if (correo.getText().trim().length() > 0 && clave.getText().trim().length() > 0) {
            Acceso acceso = new Acceso();
            acceso = (Acceso) genericDao.obtener(acceso.getClass(), correo.getText().trim());

            if (acceso.getClave().equals(clave.getText().trim())) {
                stage.mostrarPrincipal();
                correo.setText("");
                clave.setText("");
            } else {
                error.setVisible(true);
            }
        } else {
            error.setVisible(true);
        }
    }

    @FXML
    public void registrar() {
        System.out.println("dam.controlador.ControladorAcceso.registrar()");
        // crear nuevo jugador
    }

    @FXML
    public void salir() {
        System.out.println("dam.controlador.ControladorAcceso.salir()");
        this.stage.cerrarSesion();
        System.exit(1);
    }
}
