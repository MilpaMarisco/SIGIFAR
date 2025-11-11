/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sigifar.beans;

/**
 *
 * @author amilp
 */
public class UsuariosBean {

    private int id_usuario;
    private int clave_unica;
    private String nombres;
    private String apellidos;
    private String correo;
    private String contrasena;
    private String rol;
    private byte[] imagen;

    public UsuariosBean(int id_usuario, int clave_unica, String nombres, String apellidos, String correo, String contrasena, String rol, byte[] imagen) {
        this.id_usuario = id_usuario;
        this.clave_unica = clave_unica;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.contrasena = contrasena;
        this.rol = rol;
        this.imagen = imagen;
    }

    public UsuariosBean(int clave_unica, String nombres, String apellidos, String correo, String contrasena, String rol, byte[] imagen) {
        this.id_usuario = id_usuario;
        this.clave_unica = clave_unica;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.contrasena = contrasena;
        this.rol = rol;
        this.imagen = imagen;
    }

    public UsuariosBean(String nombres, String apellidos, String correo, String contrasena, String rol, byte[] imagen) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.contrasena = contrasena;
        this.rol = rol;
        this.imagen = imagen;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getClave_unica() {
        return clave_unica;
    }

    public void setClave_unica(int clave_unica) {
        this.clave_unica = clave_unica;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "id_usuario=" + id_usuario + ", clave_unica=" + clave_unica + ", nombres=" + nombres + ", apellidos=" + apellidos + ", correo=" + correo + ", contrasena=" + contrasena + ", rol=" + rol;
    }

}
