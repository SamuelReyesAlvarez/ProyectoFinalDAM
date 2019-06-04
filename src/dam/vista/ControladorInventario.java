/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.vista;

import dam.DAO.InventarioDAO;
import dam.MainApp;
import dam.modelo.Estado;
import dam.modelo.Inventario;
import dam.modelo.Jugador;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

/**
 *
 * @author Samuel Reyes Alvarez
 *
 */
public class ControladorInventario implements Initializable {

    private InventarioDAO inventarioDao = new InventarioDAO();
    private Jugador jugador;
    private ControladorPrincipal controlPrincipal;

    @FXML
    private ProgressBar barraFuerza;
    @FXML
    private ProgressBar barraDestreza;
    @FXML
    private ProgressBar barraArmadura;
    @FXML
    private ProgressBar barraConstitucion;
    @FXML
    private ProgressBar barraTierra;
    @FXML
    private ProgressBar barraAgua;
    @FXML
    private ProgressBar barraFuego;
    @FXML
    private ProgressBar barraViento;
    @FXML
    private Label fuerza;
    @FXML
    private Label destreza;
    @FXML
    private Label armadura;
    @FXML
    private Label constitucion;
    @FXML
    private Label puntosSinAsignar;
    @FXML
    private Label tierra;
    @FXML
    private Label agua;
    @FXML
    private Label fuego;
    @FXML
    private Label viento;
    @FXML
    private Label pendiente;
    @FXML
    private Label casco;
    @FXML
    private Label collar;
    @FXML
    private Label pulsera;
    @FXML
    private Label chaleco;
    @FXML
    private Label capa;
    @FXML
    private Label escudo;
    @FXML
    private Label pantalon;
    @FXML
    private Label arma;
    @FXML
    private Label cinturon;
    @FXML
    private Label botas;
    @FXML
    private Label anillo;
    @FXML
    private Label inv01;
    @FXML
    private Label inv02;
    @FXML
    private Label inv03;
    @FXML
    private Label inv04;
    @FXML
    private Label inv05;
    @FXML
    private Label inv06;
    @FXML
    private Label inv07;
    @FXML
    private Label inv08;
    @FXML
    private Label inv09;
    @FXML
    private Label inv10;
    @FXML
    private Label inv11;
    @FXML
    private Label inv12;
    @FXML
    private Label inv13;
    @FXML
    private Label inv14;
    @FXML
    private Label inv15;
    @FXML
    private Label inv16;
    @FXML
    private Label inv17;
    @FXML
    private Label inv18;
    @FXML
    private Label inv19;
    @FXML
    private Label inv20;
    @FXML
    private Label inv21;
    @FXML
    private Label inv22;
    @FXML
    private Label inv23;
    @FXML
    private Label inv24;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Iniciar etiquetas Equipado y Almacenado
        iniciarEtiquetas();

        // Obtener el jugador de la partida
        this.jugador = (new MainApp()).getJugador();

        // Cargar estado del jugador
        cargarEstado();

        puntosSinAsignar.setText(String.valueOf(jugador.getPuntosNoUsados()));

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

    @FXML
    public void asignarPuntosFuerza() {

    }

    @FXML
    public void asignarPuntosDestreza() {

    }

    @FXML
    public void asignarPuntosArmadura() {

    }

    @FXML
    public void asignarPuntosConstitucion() {

    }

    private void cargarEstado() {
        for (Estado estado : jugador.getEstadoJugador()) {
            switch (estado.getTipoAtributo()) {
                case AGUA:
                    agua.setText(String.valueOf(estado.getPotenciado()));
                    break;
                case ARMADURA:
                    armadura.setText(String.valueOf(estado.getPotenciado()));
                    controlPrincipal.cambiarDefensaMin(estado.getPotenciado() * jugador.VALOR_ARMADURA);
                    controlPrincipal.cambiarDefensaMax(estado.getPotenciado() * jugador.VALOR_ARMADURA);
                    break;
                case CONSTITUCION:
                    constitucion.setText(String.valueOf(estado.getPotenciado()));
                    controlPrincipal.cambiarVida(estado.getPotenciado() * jugador.VALOR_CONSTITUCION);
                    break;
                case DESTREZA:
                    destreza.setText(String.valueOf(estado.getPotenciado()));
                    controlPrincipal.cambiarAtaqueMax(estado.getPotenciado() * jugador.VALOR_DESTREZA);
                    controlPrincipal.cambiarDefensaMax(estado.getPotenciado() * jugador.VALOR_DESTREZA);
                    break;
                case FUEGO:
                    fuego.setText(String.valueOf(estado.getPotenciado()));
                    break;
                case FUERZA:
                    fuerza.setText(String.valueOf(estado.getPotenciado()));
                    controlPrincipal.cambiarAtaqueMin(estado.getPotenciado() * jugador.VALOR_FUERZA);
                    controlPrincipal.cambiarAtaqueMax(estado.getPotenciado() * jugador.VALOR_FUERZA);
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
                    crear columna en base de datos, en la tabla inventario
                    , para asignar el atributo al que afecta cada objeto
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
        } else {

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
}
