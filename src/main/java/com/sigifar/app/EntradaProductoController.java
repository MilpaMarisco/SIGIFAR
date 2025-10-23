/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sigifar.app;

import com.sigifar.beans.UsuariosBean;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 *
 * @author amilp
 */
public class EntradaProductoController {

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
}
