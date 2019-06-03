/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.vista;

import dam.DAO.GenericDAO;
import dam.MainApp;
import dam.modelo.Acceso;
import dam.modelo.EnvioCorreos;
import dam.modelo.JuegoException;
import dam.modelo.Jugador;
import dam.modelo.MoverVentana;
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
 * @author Samuel Reyes Alvarez
 *
 */
public class ControladorAcceso implements Initializable, MoverVentana {

    private static final String REMITENTE = "knight.fight.pi@gmail.com";
    private GenericDAO genericDao = new GenericDAO();
    private Jugador jugador;
    private MainApp stage;

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

    public void setStage(MainApp stage) {
        this.stage = stage;
    }

    @FXML
    public void acceder() {
        if (correo.getText().trim().length() > 0 && clave.getText().trim().length() > 0) {
            // Comprobar que los datos de acceso son correctos
            Acceso acceder = new Acceso();
            acceder = (Acceso) genericDao.obtenerPorId(acceder.getClass(), correo.getText().trim());

            if (acceder != null && acceder.getClave().equals(clave.getText().trim())) {
                // Establecer el jugador para la partida
                jugador = (Jugador) genericDao.obtenerPorId(Jugador.class, acceder.getJugador().getIdJugador());
                stage.setJugador(jugador);

                // Continuar la partida para ese jugador
                stage.mostrarPrincipal();

                // Limpiar los textos de los componentes de la vista de acceso
                correo.setText("");
                clave.setText("");
            } else {
                // Informar que se han introducido datos incorrectos para acceder
                // En este caso no se informa de los errores exactos para dificultar
                // el robo de cuentas
                error.setText("Datos de acceso incorrectos");
                error.setVisible(true);
            }
        } else {
            // Informar que hay campos sin rellenar
            error.setText("Rellena todos los campos");
            error.setVisible(true);
        }
    }

    @FXML
    public void registrar() {
        // Crear un nuevo acceso con los datos introducidos
        Acceso nuevaCuenta = new Acceso(correo.getText().trim(), clave.getText().trim(), null);

        Acceso registrar = new Acceso();
        // Comprobar que el correo introducido no está ya registrado
        registrar = (Acceso) genericDao.obtenerPorId(registrar.getClass(), correo.getText().trim());

        if (registrar == null) {
            try {
                // Guardar la nueva cuenta en la base de datos para validar los campos
                genericDao.guardarActualizar(nuevaCuenta);

                Acceso cuentaKF = new Acceso();
                // Obtener los datos de la cuenta de gmail del juego
                cuentaKF = (Acceso) genericDao.obtenerPorId(registrar.getClass(), REMITENTE);

                if (cuentaKF != null) {
                    EnvioCorreos codigoVerificacion = new EnvioCorreos();
                    // Enviar el correo a la dirección introducida por el jugador
                    String codigo = codigoVerificacion.enviarCodigoVerificacion(correo.getText().trim(), REMITENTE, cuentaKF.getClave());

                    if (codigo != null) {
                        // Pedir al jugador el código que debe haber recibido por correo
                        TextInputDialog dialog = new TextInputDialog();
                        dialog.setTitle("Registro Knight Fight");
                        dialog.setHeaderText("Confirmación de cuenta");
                        dialog.setContentText("Introduce el código aquí:");
                        Optional<String> result = dialog.showAndWait();

                        if (result.isPresent() && result.get().trim().equals(codigo)) {
                            // Crear la partida para el nuevo jugador
                            // TODO

                            // Abrir la partida creada
                            stage.mostrarPrincipal();
                        } else {
                            // Informar que el código introducido no es correcto
                            error.setText("Codigo incorrecto");
                            error.setVisible(true);
                            genericDao.borrar(nuevaCuenta);
                        }
                    } else {
                        // Informar que no se pudo enviar el correo con el código de verificación
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Correo no enviado");
                        alert.setContentText("Se produjo un error en el envío del correo de confirmación.");

                        alert.showAndWait();
                        genericDao.borrar(nuevaCuenta);
                    }
                } else {
                    // Informar que no se pudo obtener los datos del gmail del juego
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Función no disponible");
                    alert.setContentText("Actualmente no se dispone de la información "
                            + "necesaria para llevar a cabo el proceso de registro. "
                            + "Por favor, comunique este fallo al equipo técnico para "
                            + "que procedan a su correción.");

                    alert.showAndWait();
                    genericDao.borrar(nuevaCuenta);
                }
            } catch (JuegoException ex) {
                // Informar los errores cometidos en los datos introducidos
                error.setText(ex.getMessage());
                error.setVisible(true);
                genericDao.borrar(nuevaCuenta);
            }
        } else {
            // Informar que ya existe una cuenta con el correo introducido
            error.setText("Correo ya vinculado a una cuenta");
            error.setVisible(true);
        }
    }

    @FXML
    public void salir() {
        // Finalizar la sesión de hibernate con la base de datos
        this.stage.cerrarSesion();

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
