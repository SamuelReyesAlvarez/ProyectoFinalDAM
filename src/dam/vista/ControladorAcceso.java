/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.vista;

import dam.DAO.AccesoDAO;
import dam.DAO.GenericDAO;
import dam.MainApp;
import dam.modelo.Acceso;
import dam.modelo.MoverVentana;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 *
 * @author Samuel Reyes Alvarez
 *
 */
public class ControladorAcceso implements Initializable, MoverVentana {

    private AccesoDAO accesoDao = new AccesoDAO();
    private GenericDAO genericDao = new GenericDAO();
    private MainApp mainApp;

    @FXML
    private HBox marco;
    @FXML
    private TextField correo;
    @FXML
    private PasswordField clave;
    @FXML
    private Label error;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Establecer la función de arrastrar la ventana de juego al HBox superior
        this.onDraggedScene(marco);

        // Ocultar el mensaje de error
        error.setVisible(false);

        // Escuchar cambios en el foco de los componentes correo y clave
        correo.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                error.setVisible(false);
            }
        });
        clave.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                error.setVisible(false);
            }
        });
    }

    public void setStage(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void acceder() {
        if (correo.getText().trim().length() > 0 && clave.getText().trim().length() > 0) {
            mainApp.mostrarCarga(correo.getText().trim(), clave.getText().trim());
        } else {
            // Informar que hay campos sin rellenar
            error.setText("Rellena todos los campos");
            error.setVisible(true);
        }
    }

    @FXML
    public void registrar() {
        Acceso nuevaCuenta = new Acceso();

        // Crear un nuevo acceso con los datos introducidos
        nuevaCuenta.setCorreo(correo.getText().trim());
        nuevaCuenta.setClave(clave.getText().trim());

    }

    @FXML
    public void salir() {
        // Finalizar la sesión de hibernate con la base de datos
        this.mainApp.cerrarSesion();

        // Salir de la aplicación sin errores
        System.exit(1);
    }

    public void establecerMensaje(String mensaje) {
        error.setText(mensaje);
        error.setVisible(true);

        if (mensaje.equalsIgnoreCase("Conexión perdida")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de conexión");
            alert.setHeaderText("Conexión perdida");
            alert.setContentText("Se ha perdido la conexión con el servicio de persistencia de datos.\n"
                    + "Si esto le ocurre muy a menudo, póngase en contacto con nuestro servicio técnico");
            alert.showAndWait();
        }
    }
}
