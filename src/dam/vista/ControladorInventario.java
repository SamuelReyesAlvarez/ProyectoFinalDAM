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
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;

/**
 *
 * @author Samuel Reyes Alvarez
 *
 */
public class ControladorInventario implements Initializable {

    private MainApp mainApp;
    private GenericDAO genericDao;
    private Jugador jugador;
    private ControladorPrincipal controlPrincipal;

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
            if (!objeto.isEnVenta()) {
                if (objeto.isEquipado()) {
                    cargarEquipado(objeto);
                } else if (!objeto.isEnVenta()) {
                    cargarAlmacenado(objeto);
                }
            }
        }
    }

    public void setControladorPrincipal(ControladorPrincipal controlPrincipal) {
        this.controlPrincipal = controlPrincipal;
    }

    public void setStage(MainApp mainApp) {
        this.mainApp = mainApp;
        genericDao = new GenericDAO(mainApp);
    }

    @FXML
    public void equiparDesequipar(Inventario objeto) throws IOException {
        try {
            objeto.setEquipado(!objeto.isEquipado());
            genericDao.guardarActualizar(jugador);
            mainApp.setJugador(jugador);
        } catch (JuegoException ex) {
            mainApp.mostrarDialog("Error", "Acción no disponible", "No se pudo cambiar el estado del objeto", null, null, false);
            jugador = (Jugador) genericDao.obtenerPorId(Jugador.class, jugador.getIdJugador());
        }
        mainApp.mostrarPrincipal();
    }

    @FXML
    public void mejorarEquipo(Inventario objeto) throws IOException {
        try {
            // Comprobar coste de mejora
            int coste = objeto.getCostePotenciar();
            // Comprobar oro disponible
            int disponible = jugador.getOroActual();

            String[] respuesta = new String[1];

            mainApp.mostrarDialog("Confirmar acción", null,
                    "Mejorar " + objeto.getTipoEquipo() + " nivel: "
                    + objeto.getNivel() + " +" + objeto.getPotenciado() + " requiere "
                    + coste + " de oro\n¿Confirmar acción?", null, respuesta, true);

            if (respuesta[0].equals("aceptado")) {
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
            mainApp.mostrarDialog("Atención", "Acción no disponible", ex.getMessage(), null, null, false);
            jugador = (Jugador) genericDao.obtenerPorId(Jugador.class, jugador.getIdJugador());
        }
        mainApp.mostrarPrincipal();
    }

    @FXML
    public void venderEquipo(Inventario objeto) throws IOException {
        try {
            // Obtener el valor estándar del objeto
            int precio = objeto.getValor();

            // Informar y solicitar confirmación recogiendo precio deseado para el objeto
            String[] respuesta = new String[1];
            mainApp.mostrarDialog("Confirmar acción", null, null, "Enviar "
                    + objeto.getTipoEquipo() + " nivel: " + objeto.getNivel()
                    + " +" + objeto.getPotenciado() + " con "
                    + objeto.getEstado().getTipoAtributo()
                    + " +" + objeto.getEstado().getPotenciado()
                    + " al bazar con un precio de venta de: ",
                    respuesta, true);

            if (respuesta[0] != null) {
                objeto.setPrecio(Integer.parseInt(respuesta[0]));
                genericDao.guardarActualizar(objeto);
            }
        } catch (JuegoException ex) {
            mainApp.mostrarDialog("Atención", "Acción no disponible", ex.getMessage(), null, null, false);
            jugador = (Jugador) genericDao.obtenerPorId(Jugador.class, jugador.getIdJugador());
        } catch (NumberFormatException ex) {
            mainApp.mostrarDialog("Atención", "Acción no disponible", "Debes introducir una cantidad válida", null, null, false);
        }
        mainApp.mostrarPrincipal();
    }

    @FXML
    public void asignarPuntosFuerza() throws IOException {
        try {
            jugador.mejorarAtributo(Estado.TipoAtributo.FUERZA);
            rellenarBarras();
            genericDao.guardarActualizar(jugador);
        } catch (JuegoException ex) {
            mainApp.mostrarDialog("Atención", "Función no disponible", "No te quedan puntos para asignar", null, null, false);
            jugador = (Jugador) genericDao.obtenerPorId(Jugador.class, jugador.getIdJugador());
        }
        mainApp.mostrarPrincipal();
    }

    @FXML
    public void asignarPuntosDestreza() throws IOException {
        try {
            jugador.mejorarAtributo(Estado.TipoAtributo.DESTR);
            rellenarBarras();
            genericDao.guardarActualizar(jugador);
        } catch (JuegoException ex) {
            mainApp.mostrarDialog("Atención", "Función no disponible", "No te quedan puntos para asignar", null, null, false);
            jugador = (Jugador) genericDao.obtenerPorId(Jugador.class, jugador.getIdJugador());
        }
        mainApp.mostrarPrincipal();
    }

    @FXML
    public void asignarPuntosArmadura() throws IOException {
        try {
            jugador.mejorarAtributo(Estado.TipoAtributo.ARMAD);
            rellenarBarras();
            genericDao.guardarActualizar(jugador);
        } catch (JuegoException ex) {
            mainApp.mostrarDialog("Atención", "Función no disponible", "No te quedan puntos para asignar", null, null, false);
            jugador = (Jugador) genericDao.obtenerPorId(Jugador.class, jugador.getIdJugador());
        }
        mainApp.mostrarPrincipal();
    }

    @FXML
    public void asignarPuntosConstitucion() throws IOException {
        try {
            jugador.mejorarAtributo(Estado.TipoAtributo.CONST);
            rellenarBarras();
            genericDao.guardarActualizar(jugador);
        } catch (JuegoException ex) {
            mainApp.mostrarDialog("Atención", "Función no disponible", "No te quedan puntos para asignar", null, null, false);
            jugador = (Jugador) genericDao.obtenerPorId(Jugador.class, jugador.getIdJugador());
        }
        mainApp.mostrarPrincipal();
    }

    private void cargarEstado() {
        for (Estado estado : jugador.getEstadoJugador()) {
            switch (estado.getTipoAtributo()) {
                case AGUA:
                    agua.setText(String.valueOf(estado.getPotenciado()));
                    break;
                case ARMAD:
                    armadura.setText(String.valueOf(estado.getPotenciado()));
                    break;
                case CONST:
                    constitucion.setText(String.valueOf(estado.getPotenciado()));
                    break;
                case DESTR:
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
    }

    private void cargarEquipado(Inventario objeto) {
        switch (objeto.getTipoEquipo()) {
            case PENDIENTE:
                if (pendiente.isDisabled()) {
                    pendiente.setText(objeto.toString());
                    pendiente.setDisable(false);
                    pendiente.setContextMenu(new MenuContextual(pendiente));
                }
                break;
            case CASCO:
                if (casco.isDisabled()) {
                    casco.setText(objeto.toString());
                    casco.setDisable(false);
                    casco.setContextMenu(new MenuContextual(casco));
                }
                break;
            case COLLAR:
                if (collar.isDisabled()) {
                    collar.setText(objeto.toString());
                    collar.setDisable(false);
                    collar.setContextMenu(new MenuContextual(collar));
                }
                break;
            case PULSERA:
                if (pulsera.isDisabled()) {
                    pulsera.setText(objeto.toString());
                    pulsera.setDisable(false);
                    pulsera.setContextMenu(new MenuContextual(pulsera));
                }
                break;
            case CHALECO:
                if (chaleco.isDisabled()) {
                    chaleco.setText(objeto.toString());
                    chaleco.setDisable(false);
                    chaleco.setContextMenu(new MenuContextual(chaleco));
                }
                break;
            case CAPA:
                if (capa.isDisabled()) {
                    capa.setText(objeto.toString());
                    capa.setDisable(false);
                    capa.setContextMenu(new MenuContextual(capa));
                }
                break;
            case ESCUDO:
                if (escudo.isDisabled()) {
                    escudo.setText(objeto.toString());
                    escudo.setDisable(false);
                    escudo.setContextMenu(new MenuContextual(escudo));
                }
                break;
            case PANTALON:
                if (pantalon.isDisabled()) {
                    pantalon.setText(objeto.toString());
                    pantalon.setDisable(false);
                    pantalon.setContextMenu(new MenuContextual(pantalon));
                }
                break;
            case ARMA:
                if (arma.isDisabled()) {
                    arma.setText(objeto.toString());
                    arma.setDisable(false);
                    arma.setContextMenu(new MenuContextual(arma));
                }
                break;
            case CINTURON:
                if (cinturon.isDisabled()) {
                    cinturon.setText(objeto.toString());
                    cinturon.setDisable(false);
                    cinturon.setContextMenu(new MenuContextual(cinturon));
                }
                break;
            case BOTAS:
                if (botas.isDisabled()) {
                    botas.setText(objeto.toString());
                    botas.setDisable(false);
                    botas.setContextMenu(new MenuContextual(botas));
                }
                break;
            case ANILLO:
                if (anillo.isDisabled()) {
                    anillo.setText(objeto.toString());
                    anillo.setDisable(false);
                    anillo.setContextMenu(new MenuContextual(anillo));
                }
                break;
        }
    }

    private void cargarAlmacenado(Inventario objeto) {
        if (inv01.isDisabled()) {
            inv01.setText(objeto.toString());
            inv01.setDisable(false);
            inv01.setContextMenu(new MenuContextual(inv01));
        } else if (inv02.isDisabled()) {
            inv02.setText(objeto.toString());
            inv02.setDisable(false);
            inv02.setContextMenu(new MenuContextual(inv02));
        } else if (inv03.isDisabled()) {
            inv03.setText(objeto.toString());
            inv03.setDisable(false);
            inv03.setContextMenu(new MenuContextual(inv03));
        } else if (inv04.isDisabled()) {
            inv04.setText(objeto.toString());
            inv04.setDisable(false);
            inv04.setContextMenu(new MenuContextual(inv04));
        } else if (inv05.isDisabled()) {
            inv05.setText(objeto.toString());
            inv05.setDisable(false);
            inv05.setContextMenu(new MenuContextual(inv05));
        } else if (inv06.isDisabled()) {
            inv06.setText(objeto.toString());
            inv06.setDisable(false);
            inv06.setContextMenu(new MenuContextual(inv06));
        } else if (inv07.isDisabled()) {
            inv07.setText(objeto.toString());
            inv07.setDisable(false);
            inv07.setContextMenu(new MenuContextual(inv07));
        } else if (inv08.isDisabled()) {
            inv08.setText(objeto.toString());
            inv08.setDisable(false);
            inv08.setContextMenu(new MenuContextual(inv08));
        } else if (inv09.isDisabled()) {
            inv09.setText(objeto.toString());
            inv09.setDisable(false);
            inv09.setContextMenu(new MenuContextual(inv09));
        } else if (inv10.isDisabled()) {
            inv10.setText(objeto.toString());
            inv10.setDisable(false);
            inv10.setContextMenu(new MenuContextual(inv10));
        } else if (inv11.isDisabled()) {
            inv11.setText(objeto.toString());
            inv11.setDisable(false);
            inv11.setContextMenu(new MenuContextual(inv11));
        } else if (inv12.isDisabled()) {
            inv12.setText(objeto.toString());
            inv12.setDisable(false);
            inv12.setContextMenu(new MenuContextual(inv12));
        } else if (inv13.isDisabled()) {
            inv13.setText(objeto.toString());
            inv13.setDisable(false);
            inv13.setContextMenu(new MenuContextual(inv13));
        } else if (inv14.isDisabled()) {
            inv14.setText(objeto.toString());
            inv14.setDisable(false);
            inv14.setContextMenu(new MenuContextual(inv14));
        } else if (inv15.isDisabled()) {
            inv15.setText(objeto.toString());
            inv15.setDisable(false);
            inv15.setContextMenu(new MenuContextual(inv15));
        } else if (inv16.isDisabled()) {
            inv16.setText(objeto.toString());
            inv16.setDisable(false);
            inv16.setContextMenu(new MenuContextual(inv16));
        } else if (inv17.isDisabled()) {
            inv17.setText(objeto.toString());
            inv17.setDisable(false);
            inv17.setContextMenu(new MenuContextual(inv17));
        } else if (inv18.isDisabled()) {
            inv18.setText(objeto.toString());
            inv18.setDisable(false);
            inv18.setContextMenu(new MenuContextual(inv18));
        } else if (inv19.isDisabled()) {
            inv19.setText(objeto.toString());
            inv19.setDisable(false);
            inv19.setContextMenu(new MenuContextual(inv19));
        } else if (inv20.isDisabled()) {
            inv20.setText(objeto.toString());
            inv20.setDisable(false);
            inv20.setContextMenu(new MenuContextual(inv20));
        } else if (inv21.isDisabled()) {
            inv21.setText(objeto.toString());
            inv21.setDisable(false);
            inv21.setContextMenu(new MenuContextual(inv21));
        } else if (inv22.isDisabled()) {
            inv22.setText(objeto.toString());
            inv22.setDisable(false);
            inv22.setContextMenu(new MenuContextual(inv22));
        } else if (inv23.isDisabled()) {
            inv23.setText(objeto.toString());
            inv23.setDisable(false);
            inv23.setContextMenu(new MenuContextual(inv23));
        } else if (inv24.isDisabled()) {
            inv24.setText(objeto.toString());
            inv24.setDisable(false);
            inv24.setContextMenu(new MenuContextual(inv24));
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
    }

    private void rellenarBarras() {
        double[] atributos = {Double.parseDouble(fuerza.getText()), Double.parseDouble(destreza.getText()), Double.parseDouble(armadura.getText()), Double.parseDouble(constitucion.getText())};
        double mayorAtributo = Arrays.stream(atributos).max().getAsDouble();

        barraFuerza.setProgress(Double.parseDouble(fuerza.getText()) / (double) mayorAtributo);
        barraDestreza.setProgress(Double.parseDouble(destreza.getText()) / (double) mayorAtributo);
        barraArmadura.setProgress(Double.parseDouble(armadura.getText()) / (double) mayorAtributo);
        barraConstitucion.setProgress(Double.parseDouble(constitucion.getText()) / (double) mayorAtributo);

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

    private class MenuContextual extends ContextMenu {

        public MenuContextual(Label label) {
            boolean isEquipado = !(label.getId().contains("inv"));
            boolean isEnVenta = false;

            if (label.getText().split("N:").length > 1) {
                Inventario.TipoEquipo tipoEquipo = Inventario.TipoEquipo.valueOf(label.getText().split("N:")[0].trim());
                int nivel = Integer.parseInt(label.getText().split("N:")[1].split("P:")[0].trim());
                int potenciado = Integer.parseInt(label.getText().split(": \\+")[1].split("\n")[0].trim());
                Estado.TipoAtributo tipoAtributo = Estado.TipoAtributo.valueOf(label.getText().split("\\+")[1].split("\n")[1].trim());
                int potenciadoEstado = Integer.parseInt(label.getText().split("\\+")[2].trim());

                Estado estado = new Estado(jugador, tipoAtributo, potenciadoEstado);

                Inventario equipo = jugador.getEquipo(new Inventario(jugador, estado, tipoEquipo, nivel, potenciado, 0, isEquipado, isEnVenta));

                MenuItem item1 = new MenuItem("Des/Equipar");
                item1.setOnAction(event -> {
                    try {
                        equiparDesequipar(equipo);
                        event.consume();
                    } catch (IOException ex) {
                        Logger.getLogger(ControladorInventario.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                MenuItem item2 = new MenuItem("Mejorar");
                item2.setOnAction(event -> {
                    try {
                        mejorarEquipo(equipo);
                        event.consume();
                    } catch (IOException ex) {
                        Logger.getLogger(ControladorInventario.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                MenuItem item3 = new MenuItem("Vender");
                item3.setOnAction(event -> {
                    try {
                        venderEquipo(equipo);
                        event.consume();
                    } catch (IOException ex) {
                        Logger.getLogger(ControladorInventario.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });

                getItems().addAll(item1, item2, item3);
            }
        }
    }
}
