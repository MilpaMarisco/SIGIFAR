/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sigifar.app;

import com.sigifar.dao.UsuariosDAO;
import com.sigifar.beans.UsuariosBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
            mostrarAlerta("Campos vacíos", "Por favor, ingresa tu correo y contraseña.", Alert.AlertType.WARNING);
            return;
        }

        UsuariosBean usuario = usuariosDAO.login(correo, contrasena);

        if (usuario != null) {
            mostrarAlerta("Bienvenido", "Acceso exitoso, hola " + usuario.getNombres() + "!", Alert.AlertType.INFORMATION);
            // TODO: navegar a la siguiente pantalla
            // App.setRoot("menuPrincipal");
        } else {
            mostrarAlerta("Error de autenticación", "Correo o contraseña incorrectos.", Alert.AlertType.ERROR);
        }
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
