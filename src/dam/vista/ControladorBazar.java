/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.vista;

import dam.DAO.GenericDAO;
import dam.DAO.InventarioDAO;
import dam.MainApp;
import dam.modelo.Bazar;
import dam.modelo.Inventario;
import dam.modelo.JuegoException;
import dam.modelo.Jugador;
import java.io.IOException;
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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Samuel Reyes Alvarez
 *
 */
public class ControladorBazar implements Initializable {

    private MainApp mainApp;
    private GenericDAO genericDao;
    private InventarioDAO inventarioDao;
    private ObservableList<Inventario> ofertas;
    private ObservableList<String> listaTipoEquipo;
    private ObservableList<String> listaNivel;
    private ObservableList<String> listaPotenciado;
    private List<Inventario> listaEnVenta = new LinkedList<>();
    private Jugador jugador;

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

        columnaTipoEquipo = new TableColumn<>();
        columnaNivel = new TableColumn<>();
        columnaPotenciado = new TableColumn<>();
        columnaPropietario = new TableColumn<>();
        columnaPrecio = new TableColumn<>();

        columnaTipoEquipo.setCellValueFactory(new PropertyValueFactory<Inventario, Inventario.TipoEquipo>("tipoEquipo"));
        columnaNivel.setCellValueFactory(new PropertyValueFactory<Inventario, Integer>("nivel"));
        columnaPotenciado.setCellValueFactory(new PropertyValueFactory<Inventario, Integer>("potenciado"));
        columnaPropietario.setCellValueFactory(new PropertyValueFactory<Jugador, String>("nombre"));
        columnaPrecio.setCellValueFactory(new PropertyValueFactory<Inventario, Integer>("precio"));
    }

    public void cargarCombosYTablas() {
        cargarComboTipoEquipo();
        cargarComboNivelEquipo();
        cargarComboPotenciadoEquipo();

        listaEnVenta = inventarioDao.obtenerObjetosEnVenta(comboTipoObjeto.getValue(), comboNivel.getValue(), comboPotenciado.getValue());
        cargarTabla(listaEnVenta);

        tabla.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        comboTipoObjeto.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != oldValue) {
                if (newValue == "Todos") {
                    cargarTabla(null);
                } else {
                    listaEnVenta = inventarioDao.obtenerObjetosEnVenta(comboTipoObjeto.getValue(), comboNivel.getValue(), comboPotenciado.getValue());
                    cargarTabla(listaEnVenta);
                }
            }
        });

        comboNivel.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != oldValue) {
                if (newValue == "Todos") {
                    cargarTabla(null);
                } else {
                    listaEnVenta = inventarioDao.obtenerObjetosEnVenta(comboTipoObjeto.getValue(), comboNivel.getValue(), comboPotenciado.getValue());
                    cargarTabla(listaEnVenta);
                }
            }
        });

        comboPotenciado.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != oldValue) {
                if (newValue == "Todos") {
                    cargarTabla(null);
                } else {
                    listaEnVenta = inventarioDao.obtenerObjetosEnVenta(comboTipoObjeto.getValue(), comboNivel.getValue(), comboPotenciado.getValue());
                    cargarTabla(listaEnVenta);
                }
            }
        });
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        jugador = mainApp.getJugador();
        genericDao = new GenericDAO(mainApp);
        inventarioDao = new InventarioDAO(mainApp);
    }

    @FXML
    public void comprar() throws IOException {
        Inventario equipo = tabla.getSelectionModel().getSelectedItem();

        if (equipo != null) {
            // Comprobar que se dispone del oro suficiente para comprar el objeto
            if (equipo.getPrecio() > jugador.getOroActual()) {
                mainApp.mostrarDialog("Atención", "Acción no disponible",
                        "No dispones del oro suficiente para comprar este objeto.", null, null);
            } else {
                try {
                    equipo.setEnVenta(false);
                    equipo.setJugador(jugador);
                    genericDao.guardarActualizar(equipo);

                    // Transferir el oro del comprador al vendedor
                    Jugador vendedor = equipo.getJugador();
                    vendedor.actualizarOroActual(equipo.getPrecio());
                    jugador.actualizarOroActual(-equipo.getPrecio());
                    genericDao.guardarActualizar(vendedor);
                    genericDao.guardarActualizar(jugador);

                    // Registrar acción
                    Bazar bazar = new Bazar();
                    bazar.setComprador(jugador);
                    bazar.setVendedor(vendedor);
                    bazar.setTipoEquipo(equipo.getTipoEquipo());
                    bazar.setNivel(equipo.getNivel());
                    bazar.setPotenciado(equipo.getPotenciado());
                    bazar.setTipoAtributo(equipo.getEstado().getTipoAtributo());
                    bazar.setPotenciadoAtributo(equipo.getEstado().getPotenciado());
                    bazar.setPrecio(equipo.getPrecio());

                    genericDao.guardarActualizar(bazar);

                    // Actualizar vista
                    mainApp.mostrarBazar();
                } catch (JuegoException ex) {
                    mainApp.mostrarDialog("Error", "Acción no disponible",
                            "Hubo un fallo mientras se realizaba la transacción.", null, null);
                    mainApp.mostrarPrincipal();
                }
            }
        } else {
            mainApp.mostrarDialog("Atención", "Comprar objeto",
                    "No has seleccionado ningún objeto a comprar.", null, null);
        }
    }

    private void cargarTabla(List<Inventario> listaEnVenta) {
        if (!listaEnVenta.isEmpty()) {
            // Cargar la tabla del bazar con los objetos puestos en venta por los jugadores
            ofertas = FXCollections.observableArrayList();

            for (Inventario objeto : listaEnVenta) {
                ofertas.add(objeto);
            }

            tabla.setItems(ofertas);
            // Fin carga de la tabla del bazar
        }
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

        comboTipoObjeto.getSelectionModel().select(0);
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

        comboNivel.getSelectionModel().select(0);
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

        comboPotenciado.getSelectionModel().select(0);
    }
}
