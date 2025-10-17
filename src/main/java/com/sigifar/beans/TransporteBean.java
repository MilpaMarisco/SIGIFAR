package com.sigifar.beans;

public class TransporteBean {

    private int id_transporte;
    private String numero_unidad;
    private String transportista;

    public TransporteBean(int id_transporte, String numero_unidad, String transportista) {
        this.id_transporte = id_transporte;
        this.numero_unidad = numero_unidad;
        this.transportista = transportista;
    }

    public int getId_transporte() {
        return id_transporte;
    }

    public void setId_transporte(int id_transporte) {
        this.id_transporte = id_transporte;
    }

    public String getNumero_unidad() {
        return numero_unidad;
    }

    public void setNumero_unidad(String numero_unidad) {
        this.numero_unidad = numero_unidad;
    }

    public String getTransportista() {
        return transportista;
    }

    public void setTranportista(String transportista) {
        this.transportista = transportista;
    }

    @Override
    public String toString() {
        return "id_transporte=" + id_transporte + ", numero_unidad=" + numero_unidad + ", transportista="
                + transportista;
    }
}
