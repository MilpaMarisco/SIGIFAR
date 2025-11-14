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
import javafx.scene.control.ListCell;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import com.sigifar.beans.ProductosBean;
import com.sigifar.beans.ProveedoresBean;
import com.sigifar.dao.ProductosDAO;
import com.sigifar.dao.ProveedoresDAO;
import com.sigifar.dao.UsuariosDAO;
import com.sigifar.util.Utils;

import javafx.scene.control.ComboBox;

/**
 *
 * @author amilp
 */
public class AltaProductoController implements Initializable {

    @FXML
    private Label lblNombreUsuario;

    @FXML
    private Label lblCorreoUsuario;

    @FXML
    private TextField tfClaveProducto;

    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfMarca;

    @FXML
    private TextField tfPresentacion;

    @FXML
    private TextField tfCantidad;

    @FXML
    private ComboBox<ProveedoresBean> cbProveedor;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UsuariosBean usuario = Sesion.getUsuarioActual();

        ProveedoresDAO ProveedoresDAO = new ProveedoresDAO();
        List<ProveedoresBean> proveedoresBean = ProveedoresDAO.consultaProveedores();

        cbProveedor.getItems().addAll(proveedoresBean);

        if (usuario != null) {
            lblNombreUsuario.setText(usuario.getNombres() + " " + usuario.getApellidos());
            lblCorreoUsuario.setText(usuario.getCorreo());
        }

        cbProveedor.setCellFactory(param -> new ListCell<ProveedoresBean>() {
            @Override
            protected void updateItem(ProveedoresBean item, boolean empty) {
                super.updateItem(item, empty);
                setText((item == null || empty) ? null : item.getNombre());
            }
        });

        cbProveedor.setButtonCell(new ListCell<ProveedoresBean>() {
            @Override
            protected void updateItem(ProveedoresBean item, boolean empty) {
                super.updateItem(item, empty);
                setText((item == null || empty) ? null : item.getNombre());
            }
        });

    }

    @FXML
    public void aceptar() {
        UsuariosBean usuario = Sesion.getUsuarioActual();
        ProductosDAO productosDAO = new ProductosDAO();

        String claveProducto = tfClaveProducto.getText();
        String nombre = tfNombre.getText();
        String marca = tfMarca.getText();
        String presentacion = tfPresentacion.getText();
        String cantidad = tfCantidad.getText();
        ProveedoresBean proveedorSeleccionado = cbProveedor.getValue();
        int idProveedor = proveedorSeleccionado.getId_proveedor();

        try {
            if (nombre.isEmpty() || marca.isEmpty() || presentacion.isEmpty() || cantidad.isEmpty()) {
                Utils.mostrarAlerta("Error", "Todos los campos son obligatorios", Alert.AlertType.ERROR);
                return;
            }

            ProductosBean nuevoProducto = new ProductosBean(claveProducto, nombre, marca, presentacion, Integer.parseInt(cantidad), idProveedor, 1);

            productosDAO.insertaProducto(nuevoProducto);

        } catch (Exception e) {
            Utils.mostrarAlerta("Error", "Ocurrió un error al registrar el usuario: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void cancelar() {

        try {
            tfNombre.clear();
            tfMarca.clear();
            tfPresentacion.clear();
            tfCantidad.clear();
            cbProveedor.getSelectionModel().clearSelection();
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
