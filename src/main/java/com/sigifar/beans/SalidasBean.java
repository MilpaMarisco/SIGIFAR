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
public class SalidasBean {

    private int id_salida;
    private int id_lote;
    private Date fecha;
    private int cantidad;
    private String destino;
    private int id_usuario;
    private int id_transporte;

    public SalidasBean(int id_salida, int id_lote, int cantidad, Date fecha, String destino, int id_usuario, int id_transporte) {
        this.id_salida = id_salida;
        this.id_lote = id_lote;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.destino = destino;
        this.id_usuario = id_usuario;
        this.id_transporte = id_transporte;
    }

    public SalidasBean(int id_lote, int cantidad, Date fecha, String destino, int id_usuario, int id_transporte) {
        this.id_lote = id_lote;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.destino = destino;
        this.id_usuario = id_usuario;
        this.id_transporte = id_transporte;
    }

    public int getId_salida() {
        return id_salida;
    }

    public void setId_salida(int id_salida) {
        this.id_salida = id_salida;
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

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public int getId_transporte() {
        return id_transporte;
    }

    public void setId_transporte(int id_transporte) {
        this.id_transporte = id_transporte;
    }

    @Override
    public String toString() {
        return "id_salida=" + id_salida + ", id_lote=" + id_lote + ", fecha="
                + fecha + ", cantidad=" + cantidad + ", destino=" + destino + ", id_usuario=" + id_usuario + ", id_transporte=" + id_transporte;
    }

}
