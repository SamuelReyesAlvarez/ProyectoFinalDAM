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

    public ControladorAcceso() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.onDraggedScene(marco);
        error.setVisible(false);
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
            // Comprobar datos de acceso
            Acceso acceder = new Acceso();
            acceder = (Acceso) genericDao.obtenerPorId(acceder.getClass(), correo.getText().trim());

            if (acceder != null && acceder.getClave().equals(clave.getText().trim())) {
                // Establecer el jugador para la partida
                //jugador = (Jugador) genericDao.obtenerPorId(Jugador.class, acceder.getJugador().getIdJugador());
                //stage.setJugador(jugador);

                // Continuar la partida para ese jugador
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
        Acceso nuevaCuenta = new Acceso(correo.getText().trim(), clave.getText().trim(), null);

        try {
            Acceso registrar = new Acceso();
            // comprueba que el correo introducido no esta ya registrado
            registrar = (Acceso) genericDao.obtenerPorId(registrar.getClass(), correo.getText().trim());

            if (registrar == null) {
                // guarda la nueva cuenta en la base de datos para validar los campos
                genericDao.guardarActualizar(nuevaCuenta);

                Acceso cuentaKF = new Acceso();
                // obtiene los datos de la cuenta de gmail del juego
                cuentaKF = (Acceso) genericDao.obtenerPorId(registrar.getClass(), REMITENTE);

                if (cuentaKF == null) {
                    // informa que no se pudo obtener los datos del gmail del juego
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Función no disponible");
                    alert.setContentText("Actualmente no se dispone de la información "
                            + "necesaria para llevar a cabo el proceso de registro. "
                            + "Por favor, comunique este fallo al equipo técnico para "
                            + "que procedan a su correción.");

                    alert.showAndWait();
                } else {
                    EnvioCorreos codigoVerificacion = new EnvioCorreos();
                    // envia el correo a la direccion introducida por el jugador
                    String codigo = codigoVerificacion.enviarCodigoVerificacion(correo.getText().trim(), REMITENTE, cuentaKF.getClave());

                    if (codigo == null) {
                        // informa que no se pude enviar el correo con el codigo de verificacion
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Correo no enviado");
                        alert.setContentText("Se produjo un error en el envío del correo de confirmación.");

                        alert.showAndWait();
                    } else {
                        // pide al jugador el codigo que debe haber recibido por correo
                        TextInputDialog dialog = new TextInputDialog();
                        dialog.setTitle("Registro Knight Fight");
                        dialog.setHeaderText("Confirmación de cuenta");
                        dialog.setContentText("Introduce el código aquí:");
                        Optional<String> result = dialog.showAndWait();

                        if (result.isPresent() && result.get().trim().equals(codigo)) {
                            // crea la partida para el nuevo jugador
                            // TODO

                            // abre la partida creada
                            stage.mostrarPrincipal();
                        } else {
                            // informa que el codigo introducido no es correcto
                            error.setText("Codigo incorrecto");
                            error.setVisible(true);
                            genericDao.borrar(nuevaCuenta);
                        }
                    }
                }
            } else {
                // informa que ya existe una cuenta con el correo introducido
                error.setText("Correo ya vinculado a una cuenta");
                error.setVisible(true);
            }
        } catch (JuegoException ex) {
            // informa los errores cometidos en los datos introducidos
            error.setText(ex.getMessage());
            error.setVisible(true);
            genericDao.borrar(nuevaCuenta);
        }
    }

    @FXML
    public void salir() {
        this.stage.cerrarSesion();
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
