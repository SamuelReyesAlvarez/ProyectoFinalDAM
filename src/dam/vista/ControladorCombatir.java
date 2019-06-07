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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 *
 * @author Samuel Reyes Alvarez
 *
 */
public class ControladorCombatir implements Initializable {

    private static final int MAX_LISTA_DESAFIAR = 5;
    private static final int RANGO_MAX_BUSQUEDA = 5;

    private JugadorDAO jugadorDao = new JugadorDAO();
    private List<Jugador> clasificacion;
    private ArrayList<Jugador> listaDesafio;
    private MainApp stage;

    @FXML
    private Label nombre0, nombre1, nombre2, nombre3, nombre4;
    @FXML
    private ImageView imagen0, imagen1, imagen2, imagen3, imagen4;
    @FXML
    private Label nivel0, nivel1, nivel2, nivel3, nivel4;
    @FXML
    private Label vida0, vida1, vida2, vida3, vida4;
    @FXML
    private Label ataqueMin0, ataqueMin1, ataqueMin2, ataqueMin3, ataqueMin4;
    @FXML
    private Label ataqueMax0, ataqueMax1, ataqueMax2, ataqueMax3, ataqueMax4;
    @FXML
    private Label defensaMin0, defensaMin1, defensaMin2, defensaMin3, defensaMin4;
    @FXML
    private Label defensaMax0, defensaMax1, defensaMax2, defensaMax3, defensaMax4;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarListaDesafio();
    }

    public void setStage(MainApp stage) {
        this.stage = stage;
    }

    @FXML
    public void desafiar0() {
        desafiar(0);
    }

    @FXML
    public void desafiar1() {
        desafiar(1);
    }

    @FXML
    public void desafiar2() {
        desafiar(2);
    }

    @FXML
    public void desafiar3() {
        desafiar(3);
    }

    @FXML
    public void desafiar4() {
        desafiar(4);
    }

    private void desafiar(int id) {
        // Realizar combate entre el jugador de la partida y el seleccionado de la lista
    }

    private void cargarListaDesafio() {
        // Cargar clasificacion por puntos de combate
        clasificacion = jugadorDao.filtroDesafio();

        // Cargar lista con 5 jugadores con puntuación aproximada
        HashSet<Jugador> prelistado = new HashSet<>();
        int posicionJugador = clasificacion.indexOf(stage.getJugador());
        Random r = new Random();
        int i = 0;

        while (prelistado.size() < MAX_LISTA_DESAFIAR) {
            try {
                if (i % 2 == 0) {
                    prelistado.add(clasificacion.get(posicionJugador + r.nextInt(RANGO_MAX_BUSQUEDA) + 1));
                } else {
                    prelistado.add(clasificacion.get(posicionJugador - r.nextInt(RANGO_MAX_BUSQUEDA) - 1));
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                // En este caso no es necesario hacer nada
            }
            i++;
        }

        for (Jugador jugador : prelistado) {
            listaDesafio.add(jugador);
        }

        // Cargar datos de los jugadores elegidos en la vista
        cargarDatosEnVista();
    }

    private void cargarDatosEnVista() {
        nombre0.setText(listaDesafio.get(0).getNombre());
        imagen0.setImage(null);
        nivel0.setText(String.valueOf(listaDesafio.get(0).getNivel()));
        vida0.setText(String.valueOf(listaDesafio.get(0).getVidaMax()));
        ataqueMin0.setText(String.valueOf(listaDesafio.get(0).getAtaqueMin()));
        ataqueMax0.setText(String.valueOf(listaDesafio.get(0).getAtaqueMax()));
        defensaMin0.setText(String.valueOf(listaDesafio.get(0).getDefensaMin()));
        defensaMax0.setText(String.valueOf(listaDesafio.get(0).getDefensaMax()));

        nombre1.setText(listaDesafio.get(1).getNombre());
        imagen1.setImage(null);
        nivel1.setText(String.valueOf(listaDesafio.get(1).getNivel()));
        vida1.setText(String.valueOf(listaDesafio.get(1).getVidaMax()));
        ataqueMin1.setText(String.valueOf(listaDesafio.get(1).getAtaqueMin()));
        ataqueMax1.setText(String.valueOf(listaDesafio.get(1).getAtaqueMax()));
        defensaMin1.setText(String.valueOf(listaDesafio.get(1).getDefensaMin()));
        defensaMax1.setText(String.valueOf(listaDesafio.get(1).getDefensaMax()));

        nombre2.setText(listaDesafio.get(2).getNombre());
        imagen2.setImage(null);
        nivel2.setText(String.valueOf(listaDesafio.get(2).getNivel()));
        vida2.setText(String.valueOf(listaDesafio.get(2).getVidaMax()));
        ataqueMin2.setText(String.valueOf(listaDesafio.get(2).getAtaqueMin()));
        ataqueMax2.setText(String.valueOf(listaDesafio.get(2).getAtaqueMax()));
        defensaMin2.setText(String.valueOf(listaDesafio.get(2).getDefensaMin()));
        defensaMax2.setText(String.valueOf(listaDesafio.get(2).getDefensaMax()));

        nombre3.setText(listaDesafio.get(3).getNombre());
        imagen3.setImage(null);
        nivel3.setText(String.valueOf(listaDesafio.get(3).getNivel()));
        vida3.setText(String.valueOf(listaDesafio.get(3).getVidaMax()));
        ataqueMin3.setText(String.valueOf(listaDesafio.get(3).getAtaqueMin()));
        ataqueMax3.setText(String.valueOf(listaDesafio.get(3).getAtaqueMax()));
        defensaMin3.setText(String.valueOf(listaDesafio.get(3).getDefensaMin()));
        defensaMax3.setText(String.valueOf(listaDesafio.get(3).getDefensaMax()));

        nombre4.setText(listaDesafio.get(4).getNombre());
        imagen4.setImage(null);
        nivel4.setText(String.valueOf(listaDesafio.get(4).getNivel()));
        vida4.setText(String.valueOf(listaDesafio.get(4).getVidaMax()));
        ataqueMin4.setText(String.valueOf(listaDesafio.get(4).getAtaqueMin()));
        ataqueMax4.setText(String.valueOf(listaDesafio.get(4).getAtaqueMax()));
        defensaMin4.setText(String.valueOf(listaDesafio.get(4).getDefensaMin()));
        defensaMax4.setText(String.valueOf(listaDesafio.get(4).getDefensaMax()));
    }
}
