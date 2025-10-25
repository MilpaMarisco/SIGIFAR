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
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import com.sigifar.beans.EntradasBean;
import com.sigifar.beans.ProductosBean;
import com.sigifar.dao.EntradasDAO;
import com.sigifar.dao.ProductosDAO;
import com.sigifar.util.Utils;

/**
 *
 * @author amilp
 */
public class EntradaProductoController implements Initializable {

    @FXML
    private Label lblNombreUsuario;

    @FXML
    private Label lblCorreoUsuario;

    @FXML
    private TextField tfEPClave;

    @FXML
    private TextField tfEPLote;

    @FXML
    private TextField tfEPCantidad;

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
        String clave_producto = tfEPClave.getText().trim();
        int numero_lote = Integer.parseInt(tfEPLote.getText().trim());
        int cantidad = Integer.parseInt(tfEPCantidad.getText().trim());
        Date fecha = new Date();

        if (clave_producto == null || numero_lote <= 0 || cantidad <= 0) {
            Utils.mostrarAlerta("Campos inválidos", "Por favor, ingresa valores válidos para todos los campos.", Alert.AlertType.WARNING);
            return;
        }

        try {
            ProductosBean productosBean = productosDAO.consultaProducto(clave_producto);

            if (productosBean == null) {
                Utils.mostrarAlerta("Producto no encontrado", "No se encontró ningún producto con la clave proporcionada.", Alert.AlertType.WARNING);
                return;
            }

            EntradasBean entrada = new EntradasBean(productosBean.getId_producto(), numero_lote, fecha, cantidad, usuario.getId_usuario());

            //se inserta la entrada de productos a la base
            EntradasDAO entradasDAO = new EntradasDAO();
            entradasDAO.insertaEntrada(entrada);

            //se actualiza el inventario existente
            productosBean.setCantidad(productosBean.getCantidad() + cantidad);
            productosDAO.actualizaProducto(productosBean);

            Utils.mostrarAlerta("Entrada registrada", "La entrada del producto se ha registrado exitosamente.", Alert.AlertType.INFORMATION);
        } catch (Exception e) {
            Utils.mostrarAlerta("Error", "Ocurrió un error al insertar la entrada del producto", Alert.AlertType.ERROR);
        }

    }

    @FXML
    public void cancelar() {

        try {
            tfEPClave.setText("");
            tfEPLote.setText("");
            tfEPCantidad.setText("");
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
    public void home(ActionEvent event) {
        regresar(event);
    }

}
