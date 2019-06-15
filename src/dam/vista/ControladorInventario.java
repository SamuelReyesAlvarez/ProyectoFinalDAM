/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.vista;

import dam.DAO.GenericDAO;
import dam.MainApp;
import dam.modelo.Estado;
import dam.modelo.Inventario;
import dam.modelo.JuegoException;
import dam.modelo.Jugador;
import java.net.URL;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextInputDialog;

/**
 *
 * @author Samuel Reyes Alvarez
 *
 */
public class ControladorInventario implements Initializable {

    private GenericDAO genericDao = new GenericDAO();
    private Jugador jugador;
    private ControladorPrincipal controlPrincipal;
    private MainApp mainApp;

    @FXML
    private ProgressBar barraFuerza, barraDestreza, barraArmadura, barraConstitucion;
    @FXML
    private ProgressBar barraTierra, barraAgua, barraFuego, barraViento;
    @FXML
    private Button masFuerza, masDestreza, masArmadura, masConstitucion;
    @FXML
    private Label fuerza, destreza, armadura, constitucion, puntosSinAsignar;
    @FXML
    private Label tierra, agua, fuego, viento;
    @FXML
    private Label pendiente, casco, collar, pulsera, chaleco, capa, escudo,
            pantalon, arma, cinturon, botas, anillo;
    @FXML
    private Label inv01, inv02, inv03, inv04, inv05, inv06, inv07, inv08;
    @FXML
    private Label inv09, inv10, inv11, inv12, inv13, inv14, inv15, inv16;
    @FXML
    private Label inv17, inv18, inv19, inv20, inv21, inv22, inv23, inv24;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Iniciar etiquetas Equipado y Almacenado
        iniciarEtiquetas();
    }

    public void cargarEstadoJugador() {
        // Obtener el jugador de la partida
        this.jugador = mainApp.getJugador();

        // Cargar estado del jugador
        cargarEstado();
        puntosSinAsignar.setText(String.valueOf(jugador.getPuntosNoUsados()));
        activarDesactivarBotones();

        // Cargar objetos del inventario
        for (Inventario objeto : jugador.getEquipoJugador()) {
            if (objeto.isEquipado()) {
                cargarEquipado(objeto);
            } else if (!objeto.isEnVenta()) {
                cargarAlmacenado(objeto);
            }
        }
    }

    public void setControladorPrincipal(ControladorPrincipal controlPrincipal) {
        this.controlPrincipal = controlPrincipal;
    }

    public void setStage(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void equiparDesequipar(Inventario objeto) {
        try {
            objeto.setEquipado(!objeto.isEquipado());
            genericDao.guardarActualizar(jugador);
        } catch (JuegoException ex) {
            mostrarAlerta(Alert.AlertType.WARNING, "Error", "Acción no disponible", "No se pudo cambiar el estado del objeto");
            jugador = (Jugador) genericDao.obtenerPorId(Jugador.class, jugador.getIdJugador());
        }
        mainApp.mostrarPrincipal();
    }

    @FXML
    public void mejorarEquipo(Inventario objeto) {
        try {
            // Comprobar coste de mejora
            int coste = objeto.getCostePotenciar();
            // Comprobar oro disponible
            int disponible = jugador.getOroActual();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmar acción");
            alert.setContentText("Mejorar " + objeto.getTipoEquipo() + " nivel: " + objeto.getNivel() + " +" + objeto.getPotenciado() + " requiere " + coste + " de oro\n¿Confirmar acción?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                if (coste > disponible) {
                    throw new JuegoException("No dispones de oro suficiente");
                } else {
                    // Mejorar objeto
                    objeto.aumentarPotenciado();
                    // Restar coste
                    jugador.setOroActual(disponible - coste);
                    // Guardar cambios a través del jugador
                    genericDao.guardarActualizar(jugador);
                }
            }
        } catch (JuegoException ex) {
            mostrarAlerta(Alert.AlertType.WARNING, "Atención", "Acción no disponible", ex.getMessage());
            jugador = (Jugador) genericDao.obtenerPorId(Jugador.class, jugador.getIdJugador());
        }
        mainApp.mostrarPrincipal();
    }

    @FXML
    public void venderEquipo(Inventario objeto) {
        try {
            // Obtener el valor estándar del objeto
            int precio = objeto.getValor();

            // Informar y solicitar confirmación recogiendo precio deseado para el objeto
            TextInputDialog dialog = new TextInputDialog(String.valueOf(precio));
            dialog.setTitle("Confirmar acción");
            dialog.setContentText("Enviar " + objeto.getTipoEquipo() + " nivel: " + objeto.getNivel() + " +" + objeto.getPotenciado() + " con " + objeto.getEstado().getTipoAtributo() + " +" + objeto.getEstado().getPotenciado() + " al bazar con un precio de venta de: ");

            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                objeto.setPrecio(Integer.parseInt(result.get()));
                genericDao.guardarActualizar(jugador);
            }
        } catch (JuegoException ex) {
            mostrarAlerta(Alert.AlertType.WARNING, "Atención", "Acción no disponible", ex.getMessage());
            jugador = (Jugador) genericDao.obtenerPorId(Jugador.class, jugador.getIdJugador());
        } catch (NumberFormatException ex) {
            mostrarAlerta(Alert.AlertType.WARNING, "Atención", "Acción no disponible", "Debes introducir una cantidad válida");
        }
        mainApp.mostrarPrincipal();
    }

    @FXML
    public void asignarPuntosFuerza() {
        try {
            jugador.mejorarAtributo(Estado.TipoAtributo.FUERZA);
            rellenarBarras();
            genericDao.guardarActualizar(jugador);
        } catch (JuegoException ex) {
            mostrarAlerta(Alert.AlertType.WARNING, "Atención", "Función no disponible", "No te quedan puntos para asignar");
            jugador = (Jugador) genericDao.obtenerPorId(Jugador.class, jugador.getIdJugador());
        }
        mainApp.mostrarPrincipal();
    }

    @FXML
    public void asignarPuntosDestreza() {
        try {
            jugador.mejorarAtributo(Estado.TipoAtributo.DESTREZA);
            rellenarBarras();
            genericDao.guardarActualizar(jugador);
        } catch (JuegoException ex) {
            mostrarAlerta(Alert.AlertType.WARNING, "Atención", "Función no disponible", "No te quedan puntos para asignar");
            jugador = (Jugador) genericDao.obtenerPorId(Jugador.class, jugador.getIdJugador());
        }
        mainApp.mostrarPrincipal();
    }

    @FXML
    public void asignarPuntosArmadura() {
        try {
            jugador.mejorarAtributo(Estado.TipoAtributo.ARMADURA);
            rellenarBarras();
            genericDao.guardarActualizar(jugador);
        } catch (JuegoException ex) {
            mostrarAlerta(Alert.AlertType.WARNING, "Atención", "Función no disponible", "No te quedan puntos para asignar");
            jugador = (Jugador) genericDao.obtenerPorId(Jugador.class, jugador.getIdJugador());
        }
        mainApp.mostrarPrincipal();
    }

    @FXML
    public void asignarPuntosConstitucion() {
        try {
            jugador.mejorarAtributo(Estado.TipoAtributo.CONSTITUCION);
            rellenarBarras();
            genericDao.guardarActualizar(jugador);
        } catch (JuegoException ex) {
            mostrarAlerta(Alert.AlertType.WARNING, "Atención", "Función no disponible", "No te quedan puntos para asignar");
            jugador = (Jugador) genericDao.obtenerPorId(Jugador.class, jugador.getIdJugador());
        }
        mainApp.mostrarPrincipal();
    }

    private void mostrarAlerta(Alert.AlertType tipoAlerta, String titulo, String cabecera, String mensaje) {
        Alert alert = new Alert(tipoAlerta);
        alert.setTitle(titulo);
        alert.setHeaderText(cabecera);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void cargarEstado() {
        for (Estado estado : jugador.getEstadoJugador()) {
            switch (estado.getTipoAtributo()) {
                case AGUA:
                    agua.setText(String.valueOf(estado.getPotenciado()));
                    break;
                case ARMADURA:
                    armadura.setText(String.valueOf(estado.getPotenciado()));
                    break;
                case CONSTITUCION:
                    constitucion.setText(String.valueOf(estado.getPotenciado()));
                    break;
                case DESTREZA:
                    destreza.setText(String.valueOf(estado.getPotenciado()));
                    break;
                case FUEGO:
                    fuego.setText(String.valueOf(estado.getPotenciado()));
                    break;
                case FUERZA:
                    fuerza.setText(String.valueOf(estado.getPotenciado()));
                    break;
                case TIERRA:
                    tierra.setText(String.valueOf(estado.getPotenciado()));
                    break;
                case VIENTO:
                    viento.setText(String.valueOf(estado.getPotenciado()));
                    break;
            }
        }
        actualizarControlPrincipal();
        rellenarBarras();
    }

    private void cargarEquipado(Inventario objeto) {
        switch (objeto.getTipoEquipo()) {
            case PENDIENTE:
                if (pendiente.isDisabled()) {
                    pendiente.setText(objeto.toString());
                    pendiente.setDisable(false);
                }
                break;
            case CASCO:
                if (casco.isDisabled()) {
                    casco.setText(objeto.toString());
                    casco.setDisable(false);
                }
                break;
            case COLLAR:
                if (collar.isDisabled()) {
                    collar.setText(objeto.toString());
                    collar.setDisable(false);
                }
                break;
            case PULSERA:
                if (pulsera.isDisabled()) {
                    pulsera.setText(objeto.toString());
                    pulsera.setDisable(false);
                }
                break;
            case CHALECO:
                if (chaleco.isDisabled()) {
                    chaleco.setText(objeto.toString());
                    chaleco.setDisable(false);
                }
                break;
            case CAPA:
                if (capa.isDisabled()) {
                    capa.setText(objeto.toString());
                    capa.setDisable(false);
                }
                break;
            case ESCUDO:
                if (escudo.isDisabled()) {
                    escudo.setText(objeto.toString());
                    escudo.setDisable(false);
                }
                break;
            case PANTALON:
                if (pantalon.isDisabled()) {
                    pantalon.setText(objeto.toString());
                    pantalon.setDisable(false);
                }
                break;
            case ARMA:
                if (arma.isDisabled()) {
                    arma.setText(objeto.toString());
                    arma.setDisable(false);
                }
                break;
            case CINTURON:
                if (cinturon.isDisabled()) {
                    cinturon.setText(objeto.toString());
                    cinturon.setDisable(false);
                }
                break;
            case BOTAS:
                if (botas.isDisabled()) {
                    botas.setText(objeto.toString());
                    botas.setDisable(false);
                }
                break;
            case ANILLO:
                if (anillo.isDisabled()) {
                    anillo.setText(objeto.toString());
                    anillo.setDisable(false);
                }
                break;
        }
        actualizarControlPrincipal();
    }

    private void cargarAlmacenado(Inventario objeto) {
        if (inv01.isDisabled()) {
            inv01.setText(objeto.toString());
            inv01.setDisable(false);
        } else if (inv01.isDisabled()) {
            inv01.setText(objeto.toString());
            inv01.setDisable(false);
        } else if (inv02.isDisabled()) {
            inv02.setText(objeto.toString());
            inv02.setDisable(false);
        } else if (inv03.isDisabled()) {
            inv03.setText(objeto.toString());
            inv03.setDisable(false);
        } else if (inv04.isDisabled()) {
            inv04.setText(objeto.toString());
            inv04.setDisable(false);
        } else if (inv05.isDisabled()) {
            inv05.setText(objeto.toString());
            inv05.setDisable(false);
        } else if (inv06.isDisabled()) {
            inv06.setText(objeto.toString());
            inv06.setDisable(false);
        } else if (inv07.isDisabled()) {
            inv07.setText(objeto.toString());
            inv07.setDisable(false);
        } else if (inv08.isDisabled()) {
            inv08.setText(objeto.toString());
            inv08.setDisable(false);
        } else if (inv09.isDisabled()) {
            inv09.setText(objeto.toString());
            inv09.setDisable(false);
        } else if (inv11.isDisabled()) {
            inv11.setText(objeto.toString());
            inv11.setDisable(false);
        } else if (inv12.isDisabled()) {
            inv12.setText(objeto.toString());
            inv12.setDisable(false);
        } else if (inv13.isDisabled()) {
            inv13.setText(objeto.toString());
            inv13.setDisable(false);
        } else if (inv14.isDisabled()) {
            inv14.setText(objeto.toString());
            inv14.setDisable(false);
        } else if (inv15.isDisabled()) {
            inv15.setText(objeto.toString());
            inv15.setDisable(false);
        } else if (inv16.isDisabled()) {
            inv16.setText(objeto.toString());
            inv16.setDisable(false);
        } else if (inv17.isDisabled()) {
            inv17.setText(objeto.toString());
            inv17.setDisable(false);
        } else if (inv18.isDisabled()) {
            inv18.setText(objeto.toString());
            inv18.setDisable(false);
        } else if (inv19.isDisabled()) {
            inv19.setText(objeto.toString());
            inv19.setDisable(false);
        } else if (inv20.isDisabled()) {
            inv20.setText(objeto.toString());
            inv20.setDisable(false);
        } else if (inv21.isDisabled()) {
            inv21.setText(objeto.toString());
            inv21.setDisable(false);
        } else if (inv22.isDisabled()) {
            inv22.setText(objeto.toString());
            inv22.setDisable(false);
        } else if (inv23.isDisabled()) {
            inv23.setText(objeto.toString());
            inv23.setDisable(false);
        } else if (inv24.isDisabled()) {
            inv24.setText(objeto.toString());
            inv24.setDisable(false);
        }
    }

    private void iniciarEtiquetas() {
        pendiente.setText("PENDIENTE");
        casco.setText("CASCO");
        collar.setText("COLLAR");
        pulsera.setText("PULSERA");
        chaleco.setText("CHALECO");
        capa.setText("CAPA");
        escudo.setText("ESCUDO");
        pantalon.setText("PANTALON");
        arma.setText("ARMA");
        cinturon.setText("CINTURON");
        botas.setText("BOTAS");
        anillo.setText("ANILLO");
        inv01.setText("");
        inv02.setText("");
        inv03.setText("");
        inv04.setText("");
        inv05.setText("");
        inv06.setText("");
        inv07.setText("");
        inv08.setText("");
        inv09.setText("");
        inv10.setText("");
        inv11.setText("");
        inv12.setText("");
        inv13.setText("");
        inv14.setText("");
        inv15.setText("");
        inv16.setText("");
        inv17.setText("");
        inv18.setText("");
        inv19.setText("");
        inv20.setText("");
        inv21.setText("");
        inv22.setText("");
        inv23.setText("");
        inv24.setText("");
        fuerza.setText("0");
        destreza.setText("0");
        armadura.setText("0");
        constitucion.setText("0");
        tierra.setText("0");
        agua.setText("0");
        fuego.setText("0");
        viento.setText("0");
        rellenarBarras();

        pendiente.setDisable(true);
        casco.setDisable(true);
        collar.setDisable(true);
        pulsera.setDisable(true);
        chaleco.setDisable(true);
        capa.setDisable(true);
        escudo.setDisable(true);
        pantalon.setDisable(true);
        arma.setDisable(true);
        cinturon.setDisable(true);
        botas.setDisable(true);
        anillo.setDisable(true);
        inv01.setDisable(true);
        inv02.setDisable(true);
        inv03.setDisable(true);
        inv04.setDisable(true);
        inv05.setDisable(true);
        inv06.setDisable(true);
        inv07.setDisable(true);
        inv08.setDisable(true);
        inv09.setDisable(true);
        inv10.setDisable(true);
        inv11.setDisable(true);
        inv12.setDisable(true);
        inv13.setDisable(true);
        inv14.setDisable(true);
        inv15.setDisable(true);
        inv16.setDisable(true);
        inv17.setDisable(true);
        inv18.setDisable(true);
        inv19.setDisable(true);
        inv20.setDisable(true);
        inv21.setDisable(true);
        inv22.setDisable(true);
        inv23.setDisable(true);
        inv24.setDisable(true);

        pendiente.setContextMenu(new MenuContextual(pendiente));
        casco.setContextMenu(new MenuContextual(casco));
        collar.setContextMenu(new MenuContextual(collar));
        pulsera.setContextMenu(new MenuContextual(pulsera));
        chaleco.setContextMenu(new MenuContextual(chaleco));
        capa.setContextMenu(new MenuContextual(capa));
        escudo.setContextMenu(new MenuContextual(escudo));
        pantalon.setContextMenu(new MenuContextual(pantalon));
        arma.setContextMenu(new MenuContextual(arma));
        cinturon.setContextMenu(new MenuContextual(cinturon));
        botas.setContextMenu(new MenuContextual(botas));
        anillo.setContextMenu(new MenuContextual(anillo));
        inv01.setContextMenu(new MenuContextual(inv01));
        inv02.setContextMenu(new MenuContextual(inv02));
        inv03.setContextMenu(new MenuContextual(inv03));
        inv04.setContextMenu(new MenuContextual(inv04));
        inv05.setContextMenu(new MenuContextual(inv05));
        inv06.setContextMenu(new MenuContextual(inv06));
        inv07.setContextMenu(new MenuContextual(inv07));
        inv08.setContextMenu(new MenuContextual(inv08));
        inv09.setContextMenu(new MenuContextual(inv09));
        inv10.setContextMenu(new MenuContextual(inv10));
        inv11.setContextMenu(new MenuContextual(inv11));
        inv12.setContextMenu(new MenuContextual(inv12));
        inv13.setContextMenu(new MenuContextual(inv13));
        inv14.setContextMenu(new MenuContextual(inv14));
        inv15.setContextMenu(new MenuContextual(inv15));
        inv16.setContextMenu(new MenuContextual(inv16));
        inv17.setContextMenu(new MenuContextual(inv17));
        inv18.setContextMenu(new MenuContextual(inv18));
        inv19.setContextMenu(new MenuContextual(inv19));
        inv20.setContextMenu(new MenuContextual(inv20));
        inv21.setContextMenu(new MenuContextual(inv21));
        inv22.setContextMenu(new MenuContextual(inv22));
        inv23.setContextMenu(new MenuContextual(inv23));
        inv24.setContextMenu(new MenuContextual(inv24));
    }

    private void rellenarBarras() {
        double[] atributos = {Double.parseDouble(fuerza.getText()), Double.parseDouble(destreza.getText()), Double.parseDouble(armadura.getText()), Double.parseDouble(constitucion.getText())};
        double mayorAtributo = Arrays.stream(atributos).max().getAsDouble();

        barraFuerza.setProgress(Double.parseDouble(fuerza.getText()) / mayorAtributo);
        barraDestreza.setProgress(Double.parseDouble(destreza.getText()) / mayorAtributo);
        barraArmadura.setProgress(Double.parseDouble(armadura.getText()) / mayorAtributo);
        barraConstitucion.setProgress(Double.parseDouble(constitucion.getText()) / mayorAtributo);

        double[] elementos = {Double.parseDouble(tierra.getText()), Double.parseDouble(agua.getText()), Double.parseDouble(fuego.getText()), Double.parseDouble(viento.getText())};
        double mayorElemento = Arrays.stream(elementos).max().getAsDouble();

        barraTierra.setProgress(Double.parseDouble(tierra.getText()) / mayorElemento);
        barraAgua.setProgress(Double.parseDouble(agua.getText()) / mayorElemento);
        barraFuego.setProgress(Double.parseDouble(fuego.getText()) / mayorElemento);
        barraViento.setProgress(Double.parseDouble(viento.getText()) / mayorElemento);
    }

    private void activarDesactivarBotones() {
        boolean activado = (jugador.getPuntosNoUsados() < 1);

        masArmadura.setDisable(activado);
        masConstitucion.setDisable(activado);
        masDestreza.setDisable(activado);
        masFuerza.setDisable(activado);
    }

    private void actualizarControlPrincipal() {
        controlPrincipal.cambiarVida();
        controlPrincipal.cambiarAtaqueMin();
        controlPrincipal.cambiarAtaqueMax();
        controlPrincipal.cambiarDefensaMin();
        controlPrincipal.cambiarDefensaMax();
    }

    private class MenuContextual extends ContextMenu {

        public MenuContextual(Label label) {
            boolean isEquipado = !(label.getId().contains("inv"));
            boolean isEnVenta = false;

            if (label.getText().split("N:").length > 1) {
                Inventario.TipoEquipo tipoEquipo = Inventario.TipoEquipo.valueOf(label.getText().split("N:")[0].trim());
                int nivel = Integer.parseInt(label.getText().split("N:")[1].split("P:")[0].trim());
                int potenciado = Integer.parseInt(label.getText().split(": +")[1].split("\n")[0].trim());
                Estado.TipoAtributo tipoAtributo = Estado.TipoAtributo.valueOf(label.getText().split("\\+")[1].split("\n")[1].trim());
                int potenciadoEstado = Integer.parseInt(label.getText().split("\\+")[2].trim());

                Estado estado = new Estado(jugador, tipoAtributo, potenciadoEstado);

                Inventario equipo = jugador.getEquipo(new Inventario(jugador, estado, tipoEquipo, nivel, potenciado, 0, isEquipado, isEnVenta));

                MenuItem item1 = new MenuItem("Des/Equipar");
                item1.setOnAction(event -> {
                    equiparDesequipar(equipo);
                    event.consume();
                });
                MenuItem item2 = new MenuItem("Mejorar");
                item2.setOnAction(event -> {
                    mejorarEquipo(equipo);
                    event.consume();
                });
                MenuItem item3 = new MenuItem("Vender");
                item3.setOnAction(event -> {
                    venderEquipo(equipo);
                    event.consume();
                });

                getItems().addAll(item1, item2, item3);
            }
        }
    }
}
