/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam;

import dam.modelo.HibernateUtil;
import dam.vista.ControladorAcceso;
import dam.vista.ControladorBazar;
import dam.vista.ControladorClasificacion;
import dam.vista.ControladorCombatir;
import dam.vista.ControladorInventario;
import dam.vista.ControladorMision;
import dam.vista.ControladorPrincipal;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Samuel Reyes Alvarez
 *
 * @version 1.3.11
 * @modified 30/05/2019
 */
public class MainApp extends Application {

    private Stage stage;
    private BorderPane principal;
    private ControladorPrincipal controlPrincipal;

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        stage.setTitle("Inicio de sesion");
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        mostrarLogin();
        stage.show();
    }

    public void mostrarLogin() {
        try {
            cerrarSesion();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("vista/VistaAcceso.fxml"));

            VBox acceso = (VBox) loader.load();
            Scene scene = new Scene(acceso);
            stage.setScene(scene);
            stage.centerOnScreen();

            ControladorAcceso controlAcceso = loader.getController();
            controlAcceso.setStage(this);

            configurarYAbrirSesion();
        } catch (IOException e) {
            e.printStackTrace();
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarInventario() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("vista/VistaInventario.fxml"));

            BorderPane inventario = (BorderPane) loader.load();
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

            BorderPane combatir = (BorderPane) loader.load();
            principal.setCenter(combatir);

            ControladorCombatir controlCombatir = loader.getController();
            controlCombatir.setControladorPrincipal(controlPrincipal);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarMision() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("vista/VistaMision.fxml"));

            BorderPane mision = (BorderPane) loader.load();
            principal.setCenter(mision);

            ControladorMision controlMision = loader.getController();
            controlMision.setControladorPrincipal(controlPrincipal);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarBazar() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("vista/VistaBazar.fxml"));

            BorderPane bazar = (BorderPane) loader.load();
            principal.setCenter(bazar);

            ControladorBazar controlBazar = loader.getController();
            controlBazar.setControladorPrincipal(controlPrincipal);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarClasificacion() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("vista/VistaClasificacion.fxml"));

            BorderPane clasificacion = (BorderPane) loader.load();
            principal.setCenter(clasificacion);

            ControladorClasificacion controlClasificacion = loader.getController();
            controlClasificacion.setControladorPrincipal(controlPrincipal);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
