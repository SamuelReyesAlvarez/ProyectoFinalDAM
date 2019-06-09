/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.vista;

import dam.DAO.MisionDAO;
import dam.MainApp;
import dam.modelo.Mision;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Samuel Reyes Alvarez
 *
 */
public class ControladorMision implements Initializable {

    private MisionDAO misionDao = new MisionDAO();
    private ObservableList<Mision> listaMisiones;
    private List<Mision> prelistado;
    private MainApp stage;

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
        // Cargar la lista de misiones disponibles
        prelistado = misionDao.obtenerParaTablon();
        if (!prelistado.isEmpty()) {
            listaMisiones = FXCollections.observableArrayList();

            tabla.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

            for (Mision mision : prelistado) {
                listaMisiones.add(mision);
            }

            descripcion = new TableColumn<>();
            duracion = new TableColumn<>();
            recompensa = new TableColumn<>();

            descripcion.setCellValueFactory(new PropertyValueFactory<Mision, String>("descripcion"));
            duracion.setCellValueFactory(new PropertyValueFactory<Mision, Integer>("duracion"));
            recompensa.setCellValueFactory(new PropertyValueFactory<Mision, Integer>("recompensa"));
            tabla.setItems(listaMisiones);
        }
    }

    public void setStage(MainApp stage) {
        this.stage = stage;
    }

    @FXML
    public void aceptar() {
        // Comprobar la mision seleccionada en la tabla
        Mision seleccionada = tabla.getSelectionModel().getSelectedItem();

        if (seleccionada != null) {
            // Asignar la mision al jugador
            seleccionada.setJugador(stage.getJugador());
            stage.getJugador().getTareaActiva().add(seleccionada);

            // Comenzar la mision
            misionDao.comenzarMision(seleccionada);
        }
    }
}
