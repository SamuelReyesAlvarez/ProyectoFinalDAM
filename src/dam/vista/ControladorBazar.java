/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.vista;

import dam.DAO.GenericDAO;
import dam.DAO.InventarioDAO;
import dam.modelo.Inventario;
import dam.modelo.Jugador;
import java.net.URL;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
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

    private GenericDAO genericDao = new GenericDAO();
    private InventarioDAO inventarioDao = new InventarioDAO();
    private ObservableList<Inventario> ofertas;
    private ObservableList<String> listaTipoEquipo;
    private ObservableList<String> listaNivel;
    private ObservableList<String> listaPotenciado;
    private List<Inventario> listaEnVenta = new LinkedList<>();

    @FXML
    private TableView<Inventario> tabla;
    @FXML
    private TableColumn<Inventario, Inventario.TipoEquipo> columnaTipoEquipo;
    @FXML
    private TableColumn<Inventario, Integer> columnaNivel;
    @FXML
    private TableColumn<Inventario, Integer> columnaPotenciado;
    @FXML
    private TableColumn<Jugador, String> columnaPropietario;
    @FXML
    private TableColumn<Inventario, Integer> columnaPrecio;
    @FXML
    private ComboBox<String> comboTipoObjeto;
    @FXML
    private ComboBox<String> comboNivel;
    @FXML
    private ComboBox<String> comboPotenciado;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarComboTipoEquipo();
        cargarComboNivelEquipo();
        cargarComboPotenciadoEquipo();

        listaEnVenta = inventarioDao.obtenerObjetosEnVenta(comboTipoObjeto.getValue(), comboNivel.getValue(), comboPotenciado.getValue());
        cargarTabla(listaEnVenta);

        comboTipoObjeto.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != oldValue) {
                if (newValue == "Todos") {
                    cargarTabla(null);
                } else {
                    cargarTabla(listaEnVenta);
                }
            }
        });
    }

    private void cargarTabla(List<Inventario> listaEnVenta) {
        // Cargar la tabla del bazar con los objetos puestos en venta por los jugadores
        ofertas = FXCollections.observableArrayList();

        for (Inventario objeto : listaEnVenta) {
            ofertas.add(objeto);
        }

        columnaTipoEquipo.setCellValueFactory(new PropertyValueFactory<Inventario, Inventario.TipoEquipo>("tipoEquipo"));
        columnaNivel.setCellValueFactory(new PropertyValueFactory<Inventario, Integer>("nivel"));
        columnaPotenciado.setCellValueFactory(new PropertyValueFactory<Inventario, Integer>("potenciado"));
        columnaPropietario.setCellValueFactory(new PropertyValueFactory<Jugador, String>("nombre"));
        columnaPrecio.setCellValueFactory(new PropertyValueFactory<Inventario, Integer>("precio"));
        tabla.setItems(ofertas);
        // Fin carga de la tabla del bazar
    }

    private void cargarComboTipoEquipo() {
        // Cargar el combobox de los tipos de equipo disponibles en el bazar
        listaTipoEquipo = FXCollections.observableArrayList();
        HashSet<Inventario.TipoEquipo> tipos = new HashSet<>();

        for (Inventario equipo : listaEnVenta) {
            tipos.add(equipo.getTipoEquipo());
        }

        listaTipoEquipo.add("Todos");

        for (Inventario.TipoEquipo tipo : tipos) {
            listaTipoEquipo.add(tipo.toString());
        }

        comboTipoObjeto.setItems(listaTipoEquipo);
        // Fin carga del combobox de tipos de equipo
    }

    private void cargarComboNivelEquipo() {
        // Cargar el combobox de los niveles de equipo disponibles en el bazar
        listaNivel = FXCollections.observableArrayList();
        HashSet<Integer> niveles = new HashSet<>();

        for (Inventario equipo : listaEnVenta) {
            niveles.add(equipo.getNivel());
        }

        listaNivel.add("Todos");

        for (Integer nivel : niveles) {
            listaNivel.add(nivel.toString());
        }

        Collections.sort(listaNivel);
        comboNivel.setItems(listaNivel);
        // Fin carga del combobox de niveles de equipo
    }

    private void cargarComboPotenciadoEquipo() {
        // Cargar combobox de potenciado de los equipos en el bazar
        listaPotenciado = FXCollections.observableArrayList();
        HashSet<Integer> potenciados = new HashSet<>();

        for (Inventario equipo : listaEnVenta) {
            potenciados.add(equipo.getPotenciado());
        }

        listaPotenciado.add("Todos");

        for (Integer potenciado : potenciados) {
            listaPotenciado.add(potenciado.toString());
        }

        Collections.sort(listaPotenciado);
        comboPotenciado.setItems(listaPotenciado);
        // Fin carga del combobox del potenciado de equipos
    }
}
