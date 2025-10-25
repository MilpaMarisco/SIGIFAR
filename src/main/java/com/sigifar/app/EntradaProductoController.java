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
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

import com.sigifar.beans.EntradasBean;
import com.sigifar.beans.LotesBean;
import com.sigifar.beans.ProductosBean;
import com.sigifar.dao.EntradasDAO;
import com.sigifar.dao.LotesDAO;
import com.sigifar.dao.ProductosDAO;
import com.sigifar.util.Utils;

import javafx.scene.control.DatePicker;

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

    @FXML
    private DatePicker dpEPCaducidad;

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
        EntradasDAO entradasDAO = new EntradasDAO();
        LotesDAO lotesDAO = new LotesDAO();

        String clave_producto = tfEPClave.getText().trim();
        String numero_lote = tfEPLote.getText().trim();
        int cantidad;
        Date fecha = new Date();

        try {
            cantidad = Integer.parseInt(tfEPCantidad.getText().trim());
        } catch (NumberFormatException e) {
            Utils.mostrarAlerta("Campo inválido", "La cantidad debe ser un número válido.", Alert.AlertType.WARNING);
            return;
        }

        if (clave_producto == null || numero_lote == null || cantidad <= 0) {
            Utils.mostrarAlerta("Campos inválidos", "Por favor, ingresa valores válidos para todos los campos.", Alert.AlertType.WARNING);
            return;
        }

        try {
            ProductosBean productosBean = productosDAO.consultaProducto(clave_producto);

            if (productosBean == null) {
                Utils.mostrarAlerta("Producto no encontrado", "No se encontró ningún producto con la clave proporcionada.", Alert.AlertType.WARNING);
                return;
            }

            LotesBean lote = lotesDAO.consultaLotePK(numero_lote, productosBean.getId_producto());

            if (lote == null) {
                LocalDate fechaSeleccionada = dpEPCaducidad.getValue();

                if (fechaSeleccionada == null) {
                    Utils.mostrarAlerta("Fecha faltante", "Por favor selecciona una fecha de caducidad para el lote.", Alert.AlertType.WARNING);
                    return;
                }

                Date fechaCaducidad = java.sql.Date.valueOf(fechaSeleccionada);

                lote = new LotesBean(productosBean.getId_producto(), numero_lote, fechaCaducidad);

                lotesDAO.insertaLote(lote);

                lote = lotesDAO.consultaLotePK(numero_lote, productosBean.getId_producto());
            }

            EntradasBean entrada = new EntradasBean(lote.getId_lote(), cantidad, fecha, usuario.getId_usuario());

            entradasDAO.insertaEntrada(entrada);

            Utils.mostrarAlerta("Entrada registrada", "La entrada del producto se ha registrado exitosamente.", Alert.AlertType.INFORMATION);

        } catch (Exception e) {
            Utils.mostrarAlerta("Error", "Ocurrió un error al insertar la entrada del producto", Alert.AlertType.ERROR);
            e.printStackTrace();
        }

    }

    @FXML
    public void cancelar() {

        try {
            tfEPClave.setText("");
            tfEPLote.setText("");
            tfEPCantidad.setText("");
            dpEPCaducidad.setValue(null);
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
