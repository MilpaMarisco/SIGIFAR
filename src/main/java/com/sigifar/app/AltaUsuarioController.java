/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sigifar.app;

import java.util.ResourceBundle;

import com.sigifar.beans.UsuariosBean;
import com.sigifar.util.Sesion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

import com.sigifar.beans.EntradasBean;
import com.sigifar.beans.LotesBean;
import com.sigifar.beans.ProductosBean;
import com.sigifar.dao.EntradasDAO;
import com.sigifar.dao.LotesDAO;
import com.sigifar.dao.ProductosDAO;
import com.sigifar.dao.UsuariosDAO;
import com.sigifar.util.Utils;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

/**
 *
 * @author amilp
 */
public class AltaUsuarioController implements Initializable {

    @FXML
    private Label lblNombreUsuario;

    @FXML
    private Label lblCorreoUsuario;

    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfApellidos;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfConfirmarEmail;

    @FXML
    private PasswordField pfContrasena;

    @FXML
    private PasswordField pfConfirmarContrasena;

    @FXML
    private ComboBox<String> cbRol;

    @FXML
    private PasswordField pfClaveAdmin;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UsuariosBean usuario = Sesion.getUsuarioActual();

        cbRol.getItems().addAll("Normal", "SuperAdmin");

        if (usuario != null) {
            lblNombreUsuario.setText(usuario.getNombres() + " " + usuario.getApellidos());
            lblCorreoUsuario.setText(usuario.getCorreo());
        }
    }

    @FXML
    public void aceptar() {
        UsuariosBean usuario = Sesion.getUsuarioActual();
        UsuariosDAO usuariosDAO = new UsuariosDAO();

        String nombre = tfNombre.getText();
        String apellidos = tfApellidos.getText();
        String correo = tfEmail.getText();
        String confirmarCorreo = tfConfirmarEmail.getText();
        String contrasena = pfContrasena.getText();
        String confirmarContrasena = pfConfirmarContrasena.getText();
        String rol = cbRol.getValue();
        String claveAdmin = pfClaveAdmin.getText();

        try {
            if (nombre.isEmpty() || apellidos.isEmpty() || correo.isEmpty() || confirmarCorreo.isEmpty()
                    || contrasena.isEmpty() || confirmarContrasena.isEmpty() || cbRol.getValue() == null || claveAdmin.isEmpty()) {
                Utils.mostrarAlerta("Error", "Todos los campos son obligatorios", Alert.AlertType.ERROR);
                return;
            }

            if (!correo.equals(confirmarCorreo)) {
                Utils.mostrarAlerta("Error", "Los correos no coinciden", Alert.AlertType.ERROR);
                return;
            }

            if (!contrasena.equals(confirmarContrasena)) {
                Utils.mostrarAlerta("Error", "Las contraseñas no coinciden", Alert.AlertType.ERROR);
                return;
            }

            if (!claveAdmin.equals(String.valueOf(usuario.getClave_unica()))) {
                Utils.mostrarAlerta("Error", "Clave de administrador incorrecta", Alert.AlertType.ERROR);
                return;
            }

            UsuariosBean nuevoUsuario = new UsuariosBean(nombre, apellidos, correo, contrasena, rol, new byte[0]);

            usuariosDAO.insertaUsuario(nuevoUsuario);

            Utils.mostrarAlerta("Éxito", "Usuario registrado exitosamente", Alert.AlertType.INFORMATION);

        } catch (Exception e) {
            Utils.mostrarAlerta("Error", "Ocurrió un error al registrar el usuario: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void cancelar() {

        try {
            tfNombre.clear();
            tfApellidos.clear();
            tfEmail.clear();
            tfConfirmarEmail.clear();
            pfContrasena.clear();
            pfConfirmarContrasena.clear();
            cbRol.getSelectionModel().clearSelection();
            pfClaveAdmin.clear();
        } catch (Exception e) {
            Utils.mostrarAlerta("Error", "Ocurrió un error al cancelar la entrada del producto", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void regresar(ActionEvent event
    ) {
        try {
            UsuariosBean usuario = Sesion.getUsuarioActual();
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
            Utils.mostrarAlerta("Error", "No se pudo regresar al menú" + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void home(ActionEvent event
    ) {
        regresar(event);
    }

}
