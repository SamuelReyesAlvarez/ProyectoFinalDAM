/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.vista;

import dam.DAO.JugadorDAO;
import dam.MainApp;
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
 * @author Samuel
 */
public class ControladorClasificacion implements Initializable {

    private JugadorDAO jugadorDAO = new JugadorDAO();
    private ObservableList<Jugador> clasificacion;
    private MainApp stage;

    @FXML
    private TableView<Jugador> tabla;
    @FXML
    private TableColumn<Jugador, String> nombre;
    @FXML
    private TableColumn<Jugador, Integer> experiencia;
    @FXML
    private TableColumn<Jugador, Integer> recaudacion;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clasificacion = FXCollections.observableArrayList();
        List<Jugador> listaJugadores = jugadorDAO.obtenerOrdenadoPorExperiencia();

        for (Jugador jugador : clasificacion) {
            listaJugadores.add(jugador);
        }

        nombre.setCellValueFactory(new PropertyValueFactory<Jugador, String>("nombre"));
        experiencia.setCellValueFactory(new PropertyValueFactory<Jugador, Integer>("experiencia"));
        recaudacion.setCellValueFactory(new PropertyValueFactory<Jugador, Integer>("totalRecaudacion"));
    }

    public void setStage(MainApp mainApp) {
        this.stage = mainApp;
    }

}
