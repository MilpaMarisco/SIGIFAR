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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.sigifar.beans.EntradasBean;
import com.sigifar.beans.LotesBean;
import com.sigifar.beans.ProductosBean;
import com.sigifar.beans.ProveedoresBean;
import com.sigifar.beans.SalidasBean;
import com.sigifar.beans.TransporteBean;
import com.sigifar.dao.EntradasDAO;
import com.sigifar.dao.LotesDAO;
import com.sigifar.dao.ProductosDAO;
import com.sigifar.dao.ProveedoresDAO;
import com.sigifar.dao.SalidasDAO;
import com.sigifar.dao.TransporteDAO;
import com.sigifar.util.Utils;

import javafx.scene.control.DatePicker;

/**
 *
 * @author amilp
 */
public class SalidaProductoController implements Initializable {

    @FXML
    private Label lblNombreUsuario;

    @FXML
    private Label lblCorreoUsuario;

    @FXML
    private TextField tfEPClave;

    @FXML
    private TextField tfEPCantidad;

    @FXML
    private TextField tfDestino;

    @FXML
    private ComboBox<TransporteBean> cbTransporte;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UsuariosBean usuario = Sesion.getUsuarioActual();

        TransporteDAO transporteDAO = new TransporteDAO();
        List<TransporteBean> transporteBean = transporteDAO.consultaTransporte();

        cbTransporte.getItems().addAll(transporteBean);

        if (usuario != null) {
            lblNombreUsuario.setText(usuario.getNombres() + " " + usuario.getApellidos());
            lblCorreoUsuario.setText(usuario.getCorreo());
        }

        cbTransporte.setCellFactory(param -> new ListCell<TransporteBean>() {
            @Override
            protected void updateItem(TransporteBean item, boolean empty) {
                super.updateItem(item, empty);
                setText((item == null || empty) ? null : item.getTransportista());
            }
        });

        cbTransporte.setButtonCell(new ListCell<TransporteBean>() {
            @Override
            protected void updateItem(TransporteBean item, boolean empty) {
                super.updateItem(item, empty);
                setText((item == null || empty) ? null : item.getTransportista());
            }
        });

    }

    @FXML
    public void aceptar() {

        UsuariosBean usuario = Sesion.getUsuarioActual();
        ProductosDAO productosDAO = new ProductosDAO();
        EntradasDAO entradasDAO = new EntradasDAO();
        SalidasDAO salidasDAO = new SalidasDAO();
        LotesDAO lotesDAO = new LotesDAO();

        String clave_producto = tfEPClave.getText().trim();
        int cantidad;
        String destino = tfDestino.getText().trim();
        TransporteBean transporteSeleccionado = cbTransporte.getValue();

        try {
            cantidad = Integer.parseInt(tfEPCantidad.getText().trim());
        } catch (NumberFormatException e) {
            Utils.mostrarAlerta("Campo inválido", "La cantidad debe ser un número válido.", Alert.AlertType.WARNING);
            return;
        }

        if (clave_producto == null || cantidad <= 0) {
            Utils.mostrarAlerta("Campos inválidos", "Por favor, ingresa valores válidos para todos los campos.", Alert.AlertType.WARNING);
            return;
        }

        try {
            ProductosBean producto = productosDAO.consultaProducto(clave_producto);

            if (producto == null) {
                Utils.mostrarAlerta("Producto no encontrado",
                        "No se encontró ningún producto con la clave proporcionada.",
                        Alert.AlertType.WARNING);
                return;
            }

            List<LotesBean> lotes = lotesDAO.consultaLotesFIFO(producto.getId_producto());

            if (lotes == null || lotes.isEmpty()) {
                Utils.mostrarAlerta("Sin lotes disponibles",
                        "No hay lotes registrados para este producto.",
                        Alert.AlertType.WARNING);
                return;
            }

            int cantidadSolicitada = Integer.parseInt(tfEPCantidad.getText().trim());
            int cantidadPendiente = cantidadSolicitada;
            Date fechaSalida = new Date();

            for (LotesBean lote : lotes) {
                int entradasLote = entradasDAO.consultaEntradasPorLote(lote.getId_lote());
                int salidasLote = salidasDAO.consultaSalidaPorLote(lote.getId_lote());
                System.out.println("Entradas Lote " + lote.getId_lote() + ": " + entradasLote + ", Salidas Lote: " + salidasLote);
                int disponible = entradasLote - salidasLote;

                if (disponible <= 0) {
                    continue;
                }

                int cantidadASacar = Math.min(cantidadPendiente, disponible);

                SalidasBean salida = new SalidasBean(
                        lote.getId_lote(),
                        cantidadASacar,
                        fechaSalida,
                        destino,
                        usuario.getId_usuario(),
                        transporteSeleccionado != null ? cbTransporte.getSelectionModel().getSelectedIndex() + 1 : 0
                );
                salidasDAO.insertaSalida(salida);

                cantidadPendiente -= cantidadASacar;

                if (cantidadPendiente <= 0) {
                    break;
                }
            }

            if (cantidadPendiente > 0) {
                Utils.mostrarAlerta("Stock insuficiente",
                        "No hay suficiente cantidad disponible. Faltan " + cantidadPendiente + " unidades.",
                        Alert.AlertType.WARNING);
            } else {
                Utils.mostrarAlerta("Salida registrada",
                        "La salida se registró correctamente.",
                        Alert.AlertType.INFORMATION);
            }

        } catch (Exception e) {
            Utils.mostrarAlerta("Error",
                    "Ocurrió un error al registrar la salida del producto.",
                    Alert.AlertType.ERROR);
        }

    }

    @FXML
    public void cancelar() {

        try {
            tfEPClave.setText("");
            tfEPCantidad.setText("");
            tfDestino.setText("");
            cbTransporte.getSelectionModel().select(null);
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
