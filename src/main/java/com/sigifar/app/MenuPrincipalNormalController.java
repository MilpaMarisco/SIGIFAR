/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sigifar.app;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.sigifar.beans.UsuariosBean;
import com.sigifar.util.Sesion;
import com.sigifar.util.Utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
public class MenuPrincipalNormalController implements Initializable{

    @FXML
    private Label lblNombreUsuario;

    @FXML
    private Label lblCorreoUsuario;

    private UsuariosBean usuarioActual;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UsuariosBean usuario = Sesion.getUsuarioActual();

        if (usuario != null) {
            lblNombreUsuario.setText(usuario.getNombres() + " " + usuario.getApellidos());
            lblCorreoUsuario.setText(usuario.getCorreo());
        }
    }

    @FXML
    private void entrada(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/sigifar/views/entradaProducto.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            Utils.mostrarAlerta("Error", "No se pudo cargar la entrada de productos." + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void cerrarSesion(ActionEvent event) throws IOException {

        Sesion.cerrarSesion();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/sigifar/views/login.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
