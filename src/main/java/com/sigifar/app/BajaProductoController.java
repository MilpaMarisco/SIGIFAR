/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sigifar.app;


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
import java.net.URL;
import java.util.ResourceBundle;

import com.sigifar.beans.ProductosBean;
import com.sigifar.dao.ProductosDAO;
import com.sigifar.dao.UsuariosDAO;
import com.sigifar.util.Utils;


/**
 *
 * @author amilp
 */
public class BajaProductoController implements Initializable {

    @FXML
    private Label lblNombreUsuario;

    @FXML
    private Label lblCorreoUsuario;

    @FXML
    private TextField tfProducto;

    @FXML
    private PasswordField pfClaveAdmin;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UsuariosBean usuario = Sesion.getUsuarioActual();

        if (usuario != null) {
            lblNombreUsuario.setText(usuario.getNombres() + " " + usuario.getApellidos());
            lblCorreoUsuario.setText(usuario.getCorreo());
        }
    }

    @FXML
    public void aceptar() {
        UsuariosBean usuario = Sesion.getUsuarioActual();
        ProductosDAO productosDAO = new ProductosDAO();

        String claveProductoEliminar = tfProducto.getText();
        String claveAdmin = pfClaveAdmin.getText().trim();

        try {
            if (claveProductoEliminar.isEmpty() || claveAdmin.isEmpty()) {
                Utils.mostrarAlerta("Error", "Todos los campos son obligatorios", Alert.AlertType.ERROR);
                return;
            }            

            if (!claveAdmin.equals(String.valueOf(usuario.getClave_unica()))) {
                Utils.mostrarAlerta("Error", "Clave de administrador incorrecta", Alert.AlertType.ERROR);
                return;
            }

            ProductosBean productoEliminar = productosDAO.consultaProducto(claveProductoEliminar);

            if (productoEliminar == null) {
                Utils.mostrarAlerta("Error", "Usuario no encontrado", Alert.AlertType.ERROR);
                return;
            }

            productosDAO.eliminaProducto(claveProductoEliminar);
            
            Utils.mostrarAlerta("Éxito", "Producto [" + productoEliminar.getNombre() + "] eliminado correctamente", Alert.AlertType.CONFIRMATION);

        } catch (Exception e) {
            Utils.mostrarAlerta("Error", "Ocurrió un error al registrar el producto: " + e.getMessage(), Alert.AlertType.ERROR);
            
        }

        
    }

    @FXML
    public void cancelar() {

        try {
            tfProducto.clear();
            pfClaveAdmin.clear();
        } catch (Exception e) {
            Utils.mostrarAlerta("Error", "Ocurrió un error al cancelar la entrada del producto", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void regresar(ActionEvent event) {
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
