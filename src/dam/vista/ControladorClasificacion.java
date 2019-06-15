/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.vista;

import dam.DAO.JugadorDAO;
import dam.MainApp;
import dam.modelo.Estadisticas;
import dam.modelo.Jugador;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Samuel Reyes Alvarez
 *
 */
public class ControladorClasificacion implements Initializable {

    private JugadorDAO jugadorDAO = new JugadorDAO();
    private ObservableList<Jugador> clasificacion;
    private MainApp stage;

    @FXML
    private TableView<Jugador> tabla;
    @FXML
    private TableColumn<Jugador, String> columnaNombre;
    @FXML
    private TableColumn<Jugador, Integer> columnaExperiencia;
    @FXML
    private TableColumn<Estadisticas, Integer> columnaPuntos;
    @FXML
    private TableColumn<Estadisticas, Integer> columnaRecaudacion;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        columnaNombre.setCellValueFactory(new PropertyValueFactory<Jugador, String>("nombre"));
        columnaExperiencia.setCellValueFactory(new PropertyValueFactory<Jugador, Integer>("experiencia"));
        columnaPuntos.setCellValueFactory(new PropertyValueFactory<Estadisticas, Integer>("puntosCombate"));
        columnaRecaudacion.setCellValueFactory(new PropertyValueFactory<Estadisticas, Integer>("totalRecaudacion"));
    }

    public void cargarTabla() {
        clasificacion = FXCollections.observableArrayList();
        List<Jugador> listaJugadores = jugadorDAO.clasificacion();

        for (Jugador jugador : listaJugadores) {
            clasificacion.add(jugador);
        }

        tabla.setItems(clasificacion);

        tabla.getSelectionModel().select(stage.getJugador());
    }

    public void setStage(MainApp stage) {
        this.stage = stage;
    }

    @FXML
    public void verDetalles() {
        Jugador seleccionado = tabla.getSelectionModel().getSelectedItem();
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(seleccionado.getNombre() + " - Detalles");
        alerta.setContentText(seleccionado.toString());
        alerta.showAndWait();
    }
}
