package com.sigifar.beans;

public class ProductosBean {

    private int id_producto;
    private String clave_producto;
    private String nombre;
    private String marca;
    private String presentacion;
    private int cantidad;
    private int id_proveedor;
    private int id_ubicacion;

    public ProductosBean(int id_producto, String clave_producto, String nombre, String marca, String presentacion, int cantidad, int id_proveedor, int id_ubicacion) {
        this.id_producto = id_producto;
        this.clave_producto = clave_producto;
        this.nombre = nombre;
        this.marca = marca;
        this.presentacion = presentacion;
        this.cantidad = cantidad;
        this.id_proveedor = id_proveedor;
        this.id_ubicacion = id_ubicacion;
    }

        public ProductosBean(String clave_producto, String nombre, String marca, String presentacion, int cantidad, int id_proveedor, int id_ubicacion) {
        this.clave_producto = clave_producto;
        this.nombre = nombre;
        this.marca = marca;
        this.presentacion = presentacion;
        this.cantidad = cantidad;
        this.id_proveedor = id_proveedor;
        this.id_ubicacion = id_ubicacion;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getClave_producto() {
        return clave_producto;
    }

    public void setClave_producto(String clave_producto) {
        this.clave_producto = clave_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public int getId_ubicacion() {
        return id_ubicacion;
    }

    public void setId_ubicacion(int id_ubicacion) {
        this.id_ubicacion = id_ubicacion;
    }

    @Override
    public String toString() {
        return "id_producto=" + id_producto + ", clave_producto=" + clave_producto + ", nombre=" + nombre + ", marca=" + marca + ", presentacion=" + presentacion + ", cantidad=" + cantidad
                + ", id_proveedor=" + id_proveedor + ", id_ubicacion=" + id_ubicacion;
    }

}
