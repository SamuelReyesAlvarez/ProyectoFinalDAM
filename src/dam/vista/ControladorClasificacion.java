/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.vista;

import dam.DAO.JugadorDAO;
import dam.modelo.Estadisticas;
import dam.modelo.Jugador;
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

    private ControladorPrincipal controlPrincipal;
    private JugadorDAO jugadorDAO = new JugadorDAO();
    private ObservableList<Jugador> clasificacion;

    @FXML
    private TableView<Jugador> tabla;
    @FXML
    private TableColumn<Jugador, String> columnaNombre;
    @FXML
    private TableColumn<Jugador, Integer> columnaExperiencia;
    @FXML
    private TableColumn<Estadisticas, Integer> columnaRecaudacion;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clasificacion = FXCollections.observableArrayList();
        List<Jugador> listaJugadores = jugadorDAO.obtenerClasificacion();

        for (Jugador jugador : listaJugadores) {
            clasificacion.add(jugador);
        }

        columnaNombre.setCellValueFactory(new PropertyValueFactory<Jugador, String>("nombre"));
        columnaExperiencia.setCellValueFactory(new PropertyValueFactory<Jugador, Integer>("experiencia"));
        columnaRecaudacion.setCellValueFactory(new PropertyValueFactory<Estadisticas, Integer>("totalRecaudacion"));
        tabla.setItems(clasificacion);
    }

    public void setControladorPrincipal(ControladorPrincipal controlPrincipal) {
        this.controlPrincipal = controlPrincipal;
    }
}
