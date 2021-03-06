/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.vista;

import dam.DAO.JugadorDAO;
import dam.MainApp;
import dam.modelo.Jugador;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Samuel Reyes Alvarez
 *
 */
public class ControladorClasificacion implements Initializable {

    private MainApp mainApp;
    private JugadorDAO jugadorDAO;
    private ObservableList<Jugador> clasificacion;

    @FXML
    private TableView<Jugador> tabla;
    @FXML
    private TableColumn<Jugador, String> columnaNombre;
    @FXML
    private TableColumn<Jugador, Integer> columnaExperiencia;
    @FXML
    private TableColumn<Jugador, Integer> columnaPuntos;
    @FXML
    private TableColumn<Jugador, Integer> columnaRecaudacion;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        columnaNombre.setCellValueFactory(new PropertyValueFactory<Jugador, String>("nombre"));
        columnaExperiencia.setCellValueFactory(new PropertyValueFactory<Jugador, Integer>("expAcumulada"));
        columnaPuntos.setCellValueFactory(new PropertyValueFactory<Jugador, Integer>("puntosCombate"));
        columnaRecaudacion.setCellValueFactory(new PropertyValueFactory<Jugador, Integer>("totalRecaudado"));
    }

    public void cargarTabla() {
        clasificacion = FXCollections.observableArrayList();
        List<Jugador> listaJugadores = jugadorDAO.clasificacion();

        for (Jugador jugador : listaJugadores) {
            clasificacion.add(jugador);
        }

        tabla.setItems(clasificacion);
        tabla.getSelectionModel().select(mainApp.getJugador());
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        jugadorDAO = new JugadorDAO();
    }

    @FXML
    public void verDetalles() throws IOException {
        Jugador seleccionado = tabla.getSelectionModel().getSelectedItem();
        mainApp.mostrarDialog(seleccionado.getNombre() + " - Detalles", null,
                seleccionado.toString(), null, null, false);
    }
}
