package com.sigifar.beans;

public class UbicacionesBean {

    private int id_ubicacion;
    private String almacen;
    private String pasillo;
    private String estanteria;
    private String nivel;
    private String posicion;
    private String indicaciones_especiales;

    public UbicacionesBean(int id_ubicacion, String almacen, String pasillo, String estanteria, String nivel, String posicion, String indicaciones_especiales) {
        this.id_ubicacion = id_ubicacion;
        this.almacen = almacen;
        this.pasillo = pasillo;
        this.estanteria = estanteria;
        this.nivel = nivel;
        this.posicion = posicion;
        this.indicaciones_especiales = indicaciones_especiales;
    }

    public int getId_ubicacion() {
        return id_ubicacion;
    }

    public void setId_ubicacion(int id_ubicacion) {
        this.id_ubicacion = id_ubicacion;
    }

    public String getAlmacen() {
        return almacen;
    }

    public void setAlmacen(String almacen) {
        this.almacen = almacen;
    }

    public String getPasillo() {
        return pasillo;
    }

    public void setPasillo(String pasillo) {
        this.pasillo = pasillo;
    }

    public String getEstanteria() {
        return estanteria;
    }

    public void setEstanteria(String estanteria) {
        this.estanteria = estanteria;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public String getIndicaciones_especiales() {
        return indicaciones_especiales;
    }

    public void setIndicaciones_especiales(String indicaciones_especiales) {
        this.indicaciones_especiales = indicaciones_especiales;
    }

    @Override
    public String toString() {
        return "id_ubicacion=" + id_ubicacion + ", almacen=" + almacen + ", pasillo=" + pasillo
                + ", estanteria=" + estanteria + ", nivel=" + nivel + ", posicion=" + posicion
                + ", indicaciones=" + indicaciones_especiales;
    }
}
