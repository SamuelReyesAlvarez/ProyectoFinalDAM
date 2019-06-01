/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.vista;

import dam.modelo.Mision;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author Samuel Reyes Alvarez
 *
 */
public class ControladorMision implements Initializable {

    private ControladorPrincipal controlPrincipal;

    @FXML
    private TableView<Mision> tabla;
    @FXML
    private TableColumn<Mision, String> descripcion;
    @FXML
    private TableColumn<Mision, Integer> duracion;
    @FXML
    private TableColumn<Mision, Integer> recompensa;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Cargar la lista de misiones disponibles en la tabla
    }

    public void setControladorPrincipal(ControladorPrincipal controlPrincipal) {
        this.controlPrincipal = controlPrincipal;
    }

    @FXML
    public void aceptar() {
        // Comprobar la mision seleccionada en la tabla
        // Asignar la mision al jugador
        // Comenzar la mision
    }
}
