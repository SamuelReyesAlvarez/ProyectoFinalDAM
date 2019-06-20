/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.vista;

import dam.DAO.GenericDAO;
import dam.DAO.MisionDAO;
import dam.MainApp;
import dam.modelo.Estado;
import dam.modelo.Inventario;
import dam.modelo.JuegoException;
import dam.modelo.Jugador;
import dam.modelo.Mision;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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

    private static final String FICHERO = "archivos/misiones.txt";
    private static final int LIMITE_MISIONES_TABLON = 30;

    private MainApp mainApp;
    private GenericDAO genericDao;
    private MisionDAO misionDao;
    private ObservableList<Mision> listaMisiones;
    private List<Mision> prelistado;
    private Jugador jugador;
    private Inventario objeto;
    private Estado estado;

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
        descripcion.setCellValueFactory(new PropertyValueFactory<Mision, String>("descripcion"));
        duracion.setCellValueFactory(new PropertyValueFactory<Mision, Integer>("duracion"));
        recompensa.setCellValueFactory(new PropertyValueFactory<Mision, Integer>("recompensa"));
    }

    public void comprobarEstadoMision() {
        jugador = mainApp.getJugador();

        if (jugador.getTareaActiva().size() > 0) {
            // Comprobar si el jugador está actualmente en una misión
            Mision mision = jugador.getTareaActiva().get(jugador.getTareaActiva().size() - 1);
            int tiempoRestante = misionDao.tiempoRestanteMision(mision);

            if (tiempoRestante > 0) {
                // Informar del tiempo restante en misión
                int horas = (tiempoRestante / 3600);
                int minutos = (tiempoRestante / 60);
                int segundos = (tiempoRestante % 60);
                String tiempo = horas + ":"
                        + ((minutos < 10) ? ("0" + minutos) : minutos) + ":"
                        + ((segundos < 10) ? ("0" + segundos) : segundos);

                mostrarAlerta(Alert.AlertType.WARNING, "Atención", "Acción no disponible",
                        "Actualmente estás en una misión.\nTiempo restante:\n" + tiempo);

                mainApp.mostrarInventario();
            } else if (tiempoRestante < 0) {
                try {
                    Inventario nuevoObjeto = crearObjeto();
                    String stringObjeto = ((nuevoObjeto != null) ? nuevoObjeto.toString() : "");
                    int experiencia = (jugador.getNivel() + mision.getDuracion());

                    // Cobrar la recompensa por la misión cumplida
                    mostrarAlerta(Alert.AlertType.INFORMATION, "Cobro de misión",
                            "Por completar la misión recibes las siguientes recompensas:",
                            mision.getRecompensa() + " oro\n"
                            + experiencia + " experiencia\n"
                            + stringObjeto);

                    mision.setCompletada(true);
                    jugador.getEstadisticas().aumentarMisiones();
                    jugador.getEstadisticas().aumentarTotalRecaudado(mision.getRecompensa());
                    jugador.actualizarOroActual(mision.getRecompensa());
                    jugador.subirExpAcumulada(experiencia);

                    if (nuevoObjeto != null) {
                        jugador.getEquipoJugador().add(nuevoObjeto);
                    }

                    genericDao.guardarActualizar(jugador);
                } catch (JuegoException ex) {
                    mostrarAlerta(Alert.AlertType.ERROR, "Error",
                            "Error al completar misión",
                            "Se produjo un error mientras se guardaba la misión "
                            + "finalizada y los cambios no se llevaron a cabo");
                }
            } else {
                cargarCrearTablonMisiones();
            }
        } else {
            cargarCrearTablonMisiones();
        }
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        genericDao = new GenericDAO(mainApp);
        misionDao = new MisionDAO(mainApp);
    }

    @FXML
    public void aceptar() {
        // Comprobar la mision seleccionada en la tabla
        Mision seleccionada = tabla.getSelectionModel().getSelectedItem();
        int recompensaReal = seleccionada.getRecompensa() * jugador.getNivel();

        if (seleccionada != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmar");
            alert.setContentText("Comenzar una nueva misión de " + seleccionada.getDuracion()
                    + " horas de duración con una recompensa de " + recompensaReal
                    + " monedas de oro.");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                seleccionada.setRecompensa(recompensaReal);

                // Asignar la mision al jugador
                seleccionada.setJugador(jugador);

                // Comenzar la mision
                jugador.getTareaActiva().add(misionDao.comenzarMision(seleccionada));

                mostrarAlerta(Alert.AlertType.INFORMATION, "Mensaje",
                        "Te has embarcado en una nueva misión",
                        "Mientras participas en una misión no puedes elegir nuevas"
                        + " misiones ni retar en combate a otros jugadores hasta"
                        + " finalizarla, aunque ellos sí podrán retarte a ti");

                mainApp.mostrarPrincipal();
            }
        } else {
            mostrarAlerta(Alert.AlertType.WARNING, "Atención", "Misión no seleccionada",
                    "No has seleccionado ninguna misión.\nSelecciona una misión y luego pulsa Aceptar.");
        }
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String cabecera, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(cabecera);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    private Inventario crearObjeto() {
        Random r = new Random(System.currentTimeMillis());
        estado = null;
        this.objeto = null;

        int resultado = r.nextInt(Inventario.PROBABILIDAD_CREAR_EQUIPO);

        if (resultado == 0) {
            objeto = new Inventario();
            objeto.setEnVenta(false);
            objeto.setEquipado(false);
            objeto.setJugador(jugador);
            objeto.setNivel(r.nextInt(jugador.getNivel()) + 1);
            objeto.setPotenciado(0);
            objeto.setTipoEquipo(Inventario.TipoEquipo.values()[r.nextInt(Inventario.TipoEquipo.values().length)]);
            crearEstadoEquipo();
            objeto.setEstado(estado);
        }

        return objeto;
    }

    private void crearEstadoEquipo() {
        switch (objeto.getTipoEquipo()) {
            case PENDIENTE:
                setAtributoEstado(null);
                break;
            case CASCO:
                setAtributoEstado(Estado.TipoAtributo.CONSTITUCION);
                break;
            case COLLAR:
                setAtributoEstado(null);
                break;
            case PULSERA:
                setAtributoEstado(null);
                break;
            case CHALECO:
                setAtributoEstado(Estado.TipoAtributo.CONSTITUCION);
                break;
            case CAPA:
                setAtributoEstado(null);
                break;
            case ESCUDO:
                setAtributoEstado(Estado.TipoAtributo.ARMADURA);
                break;
            case PANTALON:
                setAtributoEstado(Estado.TipoAtributo.DESTREZA);
                break;
            case ARMA:
                setAtributoEstado(Estado.TipoAtributo.FUERZA);
                break;
            case CINTURON:
                setAtributoEstado(null);
                break;
            case BOTAS:
                setAtributoEstado(Estado.TipoAtributo.DESTREZA);
                break;
            case ANILLO:
                setAtributoEstado(null);
                break;
        }
    }

    private void setAtributoEstado(Estado.TipoAtributo tipo) {
        Random r = new Random(System.currentTimeMillis());

        if (tipo == null) {
            estado.setTipoAtributo(Estado.TipoAtributo.values()[r.nextInt(4) + 4]);
        } else {
            estado.setTipoAtributo(tipo);
        }
        estado.setPotenciado(r.nextInt(6) + 5);
    }

    private void cargarCrearTablonMisiones() {
        // Cargar la lista de misiones disponibles
        prelistado = misionDao.obtenerParaTablon();
        if (prelistado.isEmpty()) {
            try {
                Random r = new Random(System.currentTimeMillis());
                LinkedList<String> descripciones = crearListaDescripciones();

                // Crear nueva tanda de misiones
                for (int i = 0; i < LIMITE_MISIONES_TABLON; i++) {
                    Mision nueva = new Mision();
                    nueva.setDescripcion(descripciones.get(i));
                    nueva.setDuracion(r.nextInt(Mision.DURACION_MAXIMA + 1) + Mision.DURACION_MINIMA);
                    nueva.setRecompensa(r.nextInt(Mision.RECOMPENSA_MAX_POR_HORA_Y_NIVEL + 1)
                            + Mision.RECOMPENSA_MIN_POR_HORA_Y_NIVEL);

                    genericDao.guardarActualizar(nueva);
                    cargarCrearTablonMisiones();
                }
            } catch (IOException ex) {
                // Controlar error de carga redirigiendo a vista principal
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "Error de carga",
                        "Se produjo un error mientras se cargaban datos.");
                mainApp.mostrarPrincipal();
            } catch (JuegoException ex) {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "Guardado de datos",
                        "Se produjo un error mientras se guardaba la nueva misión en la base de datos.");
            }
        } else {
            listaMisiones = FXCollections.observableArrayList();

            tabla.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

            for (Mision m : prelistado) {
                listaMisiones.add(m);
            }

            tabla.setItems(listaMisiones);
        }
    }

    private LinkedList<String> crearListaDescripciones() throws IOException {
        BufferedReader lector = new BufferedReader(new FileReader(FICHERO));
        LinkedList<String> listado = new LinkedList<>();

        String linea = lector.readLine();
        while (linea != null) {
            listado.add(linea);
            linea = lector.readLine();
        }

        lector.close();
        Collections.shuffle(listado);
        return listado;
    }
}
