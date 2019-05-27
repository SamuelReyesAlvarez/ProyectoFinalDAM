/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.vista;

import dam.DAO.InventarioDAO;
import dam.modelo.Equipo;
import dam.modelo.Inventario;
import dam.modelo.Jugador;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Samuel
 */
public class ControladorBazar implements Initializable {

    private InventarioDAO inventarioDao = new InventarioDAO();
    private ObservableList<Inventario> ofertas;

    @FXML
    private TableView<Inventario> tabla;
    @FXML
    private TableColumn<Inventario, Equipo.TipoEquipo> tipoEquipo;
    @FXML
    private TableColumn<Equipo, Integer> nivel;
    @FXML
    private TableColumn<Inventario, Integer> potenciado;
    @FXML
    private TableColumn<Jugador, String> propietario;
    @FXML
    private TableColumn<Inventario, Integer> precio;
    @FXML
    private ComboBox<Equipo.TipoEquipo> comboTipoObjeto;
    @FXML
    private ComboBox<Integer> comboNivel;
    @FXML
    private ComboBox<Integer> comboPotenciado;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ofertas = FXCollections.observableArrayList();
        List<Inventario> listaObjetos = inventarioDao.obtenerObjetosEnVenta();

        for (Inventario objeto : listaObjetos) {
            ofertas.add(objeto);
        }

        tipoEquipo.setCellValueFactory(new PropertyValueFactory<Inventario, Equipo.TipoEquipo>("tipoEquipo"));
        nivel.setCellValueFactory(new PropertyValueFactory<Equipo, Integer>("nivel"));
        potenciado.setCellValueFactory(new PropertyValueFactory<Inventario, Integer>("potenciado"));
        propietario.setCellValueFactory(new PropertyValueFactory<Jugador, String>("nombre"));
        precio.setCellValueFactory(new PropertyValueFactory<Inventario, Integer>("precio"));
        tabla.setItems(ofertas);
    }
}
