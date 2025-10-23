/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sigifar.util;

import com.sigifar.beans.UsuariosBean;

/**
 *
 * @author amilp
 */
public class Sesion {

    private static UsuariosBean usuarioActual;

    public static void setUsuarioActual(UsuariosBean usuario) {
        usuarioActual = usuario;
    }

    public static UsuariosBean getUsuarioActual() {
        return usuarioActual;
    }

    public static void cerrarSesion() {
        usuarioActual = null;
    }
}
