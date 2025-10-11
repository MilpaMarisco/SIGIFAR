package com.sigifar.beans;

public class ProveedoresBean {

    private int id_proveedor;
    private String nombre;
    private String contacto;
    private String telefono;
    private String correo;

    public ProveedoresBean(int id_proveedor, String nombre, String contacto, String telefono, String correo) {
        this.id_proveedor = id_proveedor;
        this.nombre = nombre;
        this.contacto = contacto;
        this.telefono = telefono;
        this.correo = correo;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "id_proveedor=" + id_proveedor + ", nombre=" + nombre + ", contacto=" + contacto
                + ", telefono=" + telefono + ", correo=" + correo;
    }
}
