/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam;

import dam.controlador.ControladorAcceso;
import dam.controlador.ControladorPrincipal;
import dam.modelo.HibernateUtil;
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
 * @author Samuel
 *
 * @version 1.2.3
 * @modified 21/05/2019
 */
public class MainApp extends Application {

    private Stage stage;

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        stage.setTitle("Inicio de sesion");
        mostrarLogin();
        stage.show();
    }

    public void mostrarLogin() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("vista/Acceso.fxml"));

            VBox pane = (VBox) loader.load();
            Scene scene = new Scene(pane);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);

            ControladorAcceso acceso = loader.getController();
            acceso.setStage(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarPrincipal() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("vista/Principal.fxml"));

            BorderPane pane = (BorderPane) loader.load();
            Scene scene = new Scene(pane);
            stage.setScene(scene);

            ControladorPrincipal principal = loader.getController();
            principal.setStage(this);
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
    public void configurarSesion() {
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
