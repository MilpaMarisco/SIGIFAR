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
public class LotesBean {

    private int id_lote;
    private int id_producto;
    private String numero_lote;
    private Date fecha_caducidad;

    public LotesBean(int id_lote, int id_producto, String numero_lote, Date fecha_caducidad) {
        this.id_lote = id_lote;
        this.id_producto = id_producto;
        this.numero_lote = numero_lote;
        this.fecha_caducidad = fecha_caducidad;
    }

    public LotesBean(int id_producto, String numero_lote, Date fecha_caducidad) {
        this.id_producto = id_producto;
        this.numero_lote = numero_lote;
        this.fecha_caducidad = fecha_caducidad;
    }

    public int getId_lote() {
        return id_lote;
    }

    public void setId_lote(int id_lote) {
        this.id_lote = id_lote;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNumero_lote() {
        return numero_lote;
    }

    public void setNumero_lote(String numero_lote) {
        this.numero_lote = numero_lote;
    }

    public Date getFecha_caducidad() {
        return fecha_caducidad;
    }

    public void setFecha_caducidad(Date fecha_caducidad) {
        this.fecha_caducidad = fecha_caducidad;
    }

    @Override
    public String toString() {
        return "Lote{" + "id_lote=" + id_lote + ", id_producto=" + id_producto + ", numero_lote=" + numero_lote + ", fecha_caducidad=" + fecha_caducidad + '}';
    }
}
