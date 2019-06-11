/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam;

import dam.modelo.Acceso;
import dam.modelo.HibernateUtil;
import dam.modelo.JuegoException;
import dam.modelo.Jugador;
import dam.vista.ControladorAcceso;
import dam.vista.ControladorBazar;
import dam.vista.ControladorCarga;
import dam.vista.ControladorClasificacion;
import dam.vista.ControladorCombatir;
import dam.vista.ControladorInventario;
import dam.vista.ControladorMision;
import dam.vista.ControladorPrincipal;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Samuel Reyes Alvarez
 *
 * @version 1.10.3
 * @modified 11/06/2019
 */
public class MainApp extends Application {

    public Stage stage;
    private BorderPane principal;
    private ControladorPrincipal controlPrincipal;
    private Jugador jugador;

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        stage.setTitle("Inicio de sesion");
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        mostrarLogin(null);
        stage.show();
    }

    public void mostrarLogin(String mensaje) {
        try {
            cerrarSesion();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("vista/VistaAcceso.fxml"));

            VBox acceso = (VBox) loader.load();
            Scene scene = new Scene(acceso);
            stage.setScene(scene);
            stage.centerOnScreen();

            ControladorAcceso controlAcceso = loader.getController();
            if (mensaje != null) {
                controlAcceso.establecerMensaje(mensaje);
            }
            controlAcceso.setStage(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarCarga(Acceso cuenta) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("vista/VistaCarga.fxml"));

            VBox acceso = (VBox) loader.load();
            Scene scene = new Scene(acceso);
            stage.setScene(scene);
            stage.centerOnScreen();

            ControladorCarga controlCarga = loader.getController();
            controlCarga.setStage(this);

            if (cuenta.getJugador() == null) {
                controlCarga.crearNuevoJugador(cuenta);
            } else {
                controlCarga.cargarPartida(cuenta);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JuegoException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mostrarPrincipal() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("vista/VistaPrincipal.fxml"));

            principal = (BorderPane) loader.load();
            Scene scene = new Scene(principal);
            stage.setScene(scene);
            stage.centerOnScreen();

            controlPrincipal = loader.getController();
            controlPrincipal.setStage(this);
            mostrarInventario();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarInventario() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("vista/VistaInventario.fxml"));

            AnchorPane inventario = (AnchorPane) loader.load();
            principal.setCenter(inventario);

            ControladorInventario controlInventario = loader.getController();
            controlInventario.setControladorPrincipal(controlPrincipal);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarCombatir() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("vista/VistaCombatir.fxml"));

            AnchorPane combatir = (AnchorPane) loader.load();
            principal.setCenter(combatir);

            ControladorCombatir controlCombatir = loader.getController();
            controlCombatir.setStage(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarMision() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("vista/VistaMision.fxml"));

            AnchorPane mision = (AnchorPane) loader.load();
            principal.setCenter(mision);

            ControladorMision controlMision = loader.getController();
            controlMision.setStage(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarBazar() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("vista/VistaBazar.fxml"));

            AnchorPane bazar = (AnchorPane) loader.load();
            principal.setCenter(bazar);

            ControladorBazar controlBazar = loader.getController();
            controlBazar.setStage(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarClasificacion() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("vista/VistaClasificacion.fxml"));

            AnchorPane clasificacion = (AnchorPane) loader.load();
            principal.setCenter(clasificacion);

            ControladorClasificacion controlClasificacion = loader.getController();
            controlClasificacion.setStage(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Abre una nueva sesión de hibernate con mysql para gestionar la
     * persistencia de los datos generados y modificados en la aplicación
     */
    public void configurarYAbrirSesion() {
        HibernateUtil.buildSessionFactory();
        HibernateUtil.openSessionAndBindToThread();
    }

    /**
     * Cierra la sesión de hibernate que fue abierta para gestionar la
     * persistencia de datos con mysql
     */
    public void cerrarSesion() {
        HibernateUtil.closeSessionFactory();
    }
}
