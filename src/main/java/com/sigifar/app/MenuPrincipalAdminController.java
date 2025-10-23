/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sigifar.app;

import java.io.IOException;

import com.sigifar.beans.UsuariosBean;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author amilp
 */
public class MenuPrincipalAdminController {

    @FXML
    private Label lblNombreUsuario;

    @FXML
    private Label lblCorreoUsuario;

    private UsuariosBean usuarioActual;

    public void setUsuarioActual(UsuariosBean usuario) {
        this.usuarioActual = usuario;
        mostrarDatosUsuario();
    }

    private void mostrarDatosUsuario() {
        if (usuarioActual != null) {
            lblNombreUsuario.setText(usuarioActual.getNombres() + " " + usuarioActual.getApellidos());
            lblCorreoUsuario.setText(usuarioActual.getCorreo());
        }
    }

    @FXML
    private void entrada(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/sigifar/views/entradaProducto.fxml"));
            Parent root = loader.load();

            EntradaProductoController controller = loader.getController();
            controller.setUsuarioActual(usuario);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            mostrarAlerta("Error", "No se pudo cargar la entrada de productos." + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void cerrarSesion(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/sigifar/views/login.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}
