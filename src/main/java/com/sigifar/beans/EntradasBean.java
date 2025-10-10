/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sigifar.beans;

import java.util.Date;

/**
 *
 * @author amilp
 */
public class EntradasBean {

    private int id_entrada;
    private int id_producto;
    private int numero_lote;
    private Date fecha;
    private int cantidad;
    private int id_usuario;

    public EntradasBean(int id_entrada, int id_producto, int numero_lote, Date fecha, int cantidad, int id_usuario) {
        this.id_entrada = id_entrada;
        this.id_producto = id_producto;
        this.numero_lote = numero_lote;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.id_usuario = id_usuario;
    }

    public int getId_entrada() {
        return id_entrada;
    }

    public void setId_entrada(int id_entrada) {
        this.id_entrada = id_entrada;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getNumero_lote() {
        return numero_lote;
    }

    public void setNumero_lote(int numero_lote) {
        this.numero_lote = numero_lote;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    @Override
    public String toString() {
        return "id_entrada=" + id_entrada + ", id_producto=" + id_producto + ", numero_lote=" + numero_lote + ", fecha="
                + fecha + ", cantidad=" + cantidad + ", id_usuario=" + id_usuario;
    }

}
