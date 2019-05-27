/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.vista;

import dam.DAO.GenericDAO;
import dam.MainApp;
import dam.modelo.Acceso;
import dam.modelo.MoverVentana;
import dam.modelo.Registro;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;

/**
 *
 * @author Samuel
 */
public class ControladorAcceso implements Initializable, MoverVentana {

    private static final String REMITENTE = "knight.fight.pi@gmail.com";
    private GenericDAO genericDao = new GenericDAO();
    private Acceso acceso = new Acceso();
    private MainApp stage;

    @FXML
    private HBox marco;
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
        this.onDraggedScene(marco);
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
        if (correo.getText().trim().length() > 0 && clave.getText().trim().length() > 0) {
            Acceso acceso = new Acceso();
            acceso = (Acceso) genericDao.obtener(acceso.getClass(), correo.getText().trim());

            if (acceso != null && acceso.getClave().equals(clave.getText().trim())) {
                stage.mostrarPrincipal();
                correo.setText("");
                clave.setText("");
            } else {
                error.setText("Datos de acceso incorrectos");
                error.setVisible(true);
            }
        } else {
            error.setText("Rellena todos los campos");
            error.setVisible(true);
        }
    }

    @FXML
    public void registrar() {
        if (correo.getText().trim().length() > 0 && clave.getText().trim().length() > 0) {
            acceso = (Acceso) genericDao.obtener(acceso.getClass(), correo.getText().trim());

            if (acceso == null) {
                acceso = new Acceso();
                acceso = (Acceso) genericDao.obtener(acceso.getClass(), REMITENTE);

                if (acceso == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Función no disponible");
                    alert.setContentText("Actualmente no se dispone de la información "
                            + "necesaria para llevar a cabo el proceso de registro. "
                            + "Por favor, comunique este fallo al equipo técnico para "
                            + "que procedan a su correción.");

                    alert.showAndWait();
                } else {
                    Registro registro = new Registro();
                    String codigo = registro.enviarcorreo(correo.getText().trim(), REMITENTE, acceso.getClave());

                    if (codigo == null) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Correo no enviado");
                        alert.setContentText("Se produjo un error en el envío del correo de confirmación.");

                        alert.showAndWait();
                    } else {
                        TextInputDialog dialog = new TextInputDialog();
                        dialog.setTitle("Registro Knight Fight");
                        dialog.setHeaderText("Confirmación de cuenta");
                        dialog.setContentText("Introduce el código aquí:");

                        Optional<String> result = dialog.showAndWait();
                        System.out.println(result.get());
                        if (result.isPresent() && result.get().trim().equals(codigo)) {
                            genericDao.guardarActualizar(new Acceso(correo.getText().trim(), clave.getText().trim(), null));
                            stage.mostrarPrincipal();
                        } else {
                            error.setText("Codigo incorrecto");
                            error.setVisible(true);
                        }
                    }
                }
            } else {
                error.setText("Correo ya vinculado a una cuenta");
                error.setVisible(true);
            }
        } else {
            error.setText("Rellena todos los campos");
            error.setVisible(true);
        }
    }

    @FXML
    public void salir() {
        this.stage.cerrarSesion();
        System.exit(1);
    }
}
