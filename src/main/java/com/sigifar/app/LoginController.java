/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sigifar.app;

import com.sigifar.dao.UsuariosDAO;
import com.sigifar.util.Sesion;

import java.io.IOException;

import com.sigifar.beans.UsuariosBean;
import com.sigifar.util.Utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author amilp
 */
public class LoginController {

    @FXML
    private TextField tfLCorreo;

    @FXML
    private PasswordField pfLContrasena;

    @FXML
    private Button btnLogin;

    private final UsuariosDAO usuariosDAO = new UsuariosDAO();

    @FXML
    private void login(ActionEvent event) {
        String correo = tfLCorreo.getText().trim();
        String contrasena = pfLContrasena.getText().trim();

        if (correo.isEmpty() || contrasena.isEmpty()) {
            Utils.mostrarAlerta("Campos vacíos", "Por favor, ingresa tu correo y contraseña.", Alert.AlertType.WARNING);
            return;
        }

        UsuariosBean usuario = usuariosDAO.login(correo, contrasena);

        if (usuario != null) {
            Utils.mostrarAlerta("Bienvenido", "Acceso exitoso, hola " + usuario.getNombres() + "!", Alert.AlertType.INFORMATION);

            Sesion.setUsuarioActual(usuario);

            try {

                if (usuario.getRol().equals("normal")) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/sigifar/views/menuPrincipalNormal.fxml"));
                    Parent root = loader.load();

                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.show();

                } else {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/sigifar/views/menuPrincipalAdmin.fxml"));
                    Parent root = loader.load();

                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.show();
                }

            } catch (IOException e) {
                Utils.mostrarAlerta("Error", "No se pudo cargar el menú principal." + e.getMessage(), Alert.AlertType.ERROR);
            }

        } else {
            Utils.mostrarAlerta("Error de autenticación", "Correo o contraseña incorrectos.", Alert.AlertType.ERROR);
        }
    }


}
