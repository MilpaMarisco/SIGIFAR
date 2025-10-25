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
    private int id_lote;
    private int cantidad;
    private Date fecha;
    private int id_usuario;

    public EntradasBean(int id_entrada, int id_lote, int cantidad, Date fecha, int id_usuario) {
        this.id_entrada = id_entrada;
        this.id_lote = id_lote;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.id_usuario = id_usuario;
    }

    public EntradasBean(int id_lote, int cantidad, Date fecha, int id_usuario) {
        this.id_lote = id_lote;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.id_usuario = id_usuario;
    }

    public int getId_entrada() {
        return id_entrada;
    }

    public void setId_entrada(int id_entrada) {
        this.id_entrada = id_entrada;
    }

    public int getId_lote() {
        return id_lote;
    }

    public void setId_lote(int id_lote) {
        this.id_lote = id_lote;
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
        return "id_entrada=" + id_entrada + ", id_lote=" + id_lote + ", fecha="
                + fecha + ", cantidad=" + cantidad + ", id_usuario=" + id_usuario;
    }

}
