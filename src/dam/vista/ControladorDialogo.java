/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.vista;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Samuel Reyes Alvarez
 *
 */
public class ControladorDialogo {

    @FXML
    private Label etiquetaTitulo;

    @FXML
    private Label etiquetaCabecera;

    @FXML
    private Label etiquetaPregunta;

    @FXML
    private TextArea areaResumen;

    @FXML
    private TextField campoRespuesta;

    @FXML
    private Button botonCancelar;

    private String[] respuesta;

    public void contenido(String titulo, String cabecera, String resumen, String pregunta) {
        etiquetaTitulo.setText(titulo);

        if (cabecera == null || cabecera.length() < 1) {
            etiquetaCabecera.setVisible(false);
            etiquetaCabecera.setMaxSize(0, 0);
        } else {
            etiquetaCabecera.setText(cabecera);
        }

        if (resumen == null || resumen.length() < 1) {
            areaResumen.setVisible(false);
            areaResumen.setMaxSize(0, 0);
        } else {
            areaResumen.setText(resumen);
            etiquetaPregunta.setVisible(false);
            etiquetaPregunta.setMaxSize(0, 0);
            campoRespuesta.setVisible(false);
            campoRespuesta.setMaxSize(0, 0);
            botonCancelar.setVisible(false);
            botonCancelar.setMaxSize(0, 0);
        }

        if (pregunta == null || pregunta.length() < 1) {
            etiquetaPregunta.setVisible(false);
            etiquetaPregunta.setMaxSize(0, 0);
        } else {
            etiquetaPregunta.setText(pregunta);
            areaResumen.setVisible(false);
            areaResumen.setMaxSize(0, 0);
        }
    }

    public void setRespuesta(String[] respuesta) {
        this.respuesta = respuesta;
    }

    @FXML
    public void aceptar(ActionEvent event) {
        respuesta[0] = campoRespuesta.getText().trim();

        cancelar(event);
    }

    @FXML
    public void cancelar(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
