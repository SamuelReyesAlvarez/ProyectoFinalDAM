/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.vista;

import com.sun.javafx.scene.control.behavior.PasswordFieldBehavior;
import com.sun.javafx.scene.control.skin.TextFieldSkin;
import dam.DAO.AccesoDAO;
import dam.DAO.GenericDAO;
import dam.MainApp;
import dam.modelo.Acceso;
import dam.modelo.MoverVentana;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
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
    private PasswordField claveOculta;
    @FXML
    private TextField claveVisible;
    @FXML
    private CheckBox verClave;
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
        claveOculta.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                error.setVisible(false);
            }
        });

        mostrarOcultarClave();
    }

    public void setStage(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void acceder() {
        if (correo.getText().trim().length() > 0 && claveOculta.getText().trim().length() > 0) {
            mainApp.mostrarCarga(correo.getText().trim(), claveOculta.getText().trim());
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
        nuevaCuenta.setClave(claveOculta.getText().trim());

        mainApp.mostrarCarga(nuevaCuenta);
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

        try {
            if (mensaje.equalsIgnoreCase("Conexión perdida")) {
                mainApp.mostrarDialog("Error de conexión", "Conexión perdida",
                        "Se ha perdido la conexión con el servicio de persistencia"
                        + " de datos.\nSi esto le ocurre muy a menudo, póngase en"
                        + " contacto con nuestro servicio técnico", null, null);
            }
        } catch (IOException ex) {

        }
    }

    public void mostrarOcultarClave() {
        // Set initial state
        claveVisible.setManaged(false);
        claveVisible.setVisible(false);

        // Bind properties. Toggle textField and passwordField
        // visibility and managability properties mutually when checkbox's state is changed.
        // Because we want to display only one component (textField or passwordField)
        // on the scene at a time.
        claveVisible.managedProperty().bind(verClave.selectedProperty());
        claveVisible.visibleProperty().bind(verClave.selectedProperty());

        claveOculta.managedProperty().bind(verClave.selectedProperty().not());
        claveOculta.visibleProperty().bind(verClave.selectedProperty().not());

        // Bind the textField and passwordField text values bidirectionally.
        claveVisible.textProperty().bindBidirectional(claveOculta.textProperty());
    }

    public class PasswordFieldSkin extends TextFieldSkin {

        public static final char BULLET = '\u2022';

        public PasswordFieldSkin(PasswordField passwordField) {
            super(passwordField, new PasswordFieldBehavior(passwordField));
        }

        @Override
        protected String maskText(String txt) {
            TextField textField = getSkinnable();

            int n = textField.getLength();
            StringBuilder passwordBuilder = new StringBuilder(n);
            for (int i = 0; i < n; i++) {
                passwordBuilder.append(BULLET);
            }

            return passwordBuilder.toString();
        }
    }
}
