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

    private String respuesta;

    public void contenido(String titulo, String cabecera, String resumen, String pregunta) {
        etiquetaTitulo.setText(titulo);

        if (cabecera.length() < 1 || cabecera == null) {
            etiquetaCabecera.setVisible(false);
        } else {
            etiquetaCabecera.setText(cabecera);
        }

        if (resumen.length() < 1 || resumen == null) {
            areaResumen.setVisible(false);
        } else {
            areaResumen.setText(resumen);
            etiquetaPregunta.setVisible(false);
            campoRespuesta.setVisible(false);
            botonCancelar.setVisible(false);
        }

        if (pregunta.length() < 1 || pregunta == null) {
            etiquetaPregunta.setVisible(false);
        } else {
            etiquetaPregunta.setText(pregunta);
            areaResumen.setVisible(false);
        }
    }

    public void setString(String respuesta) {
        this.respuesta = respuesta;
    }

    @FXML
    public void aceptar(ActionEvent event) {
        respuesta = campoRespuesta.getText().trim();

        cancelar(event);
    }

    @FXML
    public void cancelar(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
