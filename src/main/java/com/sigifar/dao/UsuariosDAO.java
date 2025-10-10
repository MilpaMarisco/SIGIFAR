/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sigifar.dao;

import com.sigifar.beans.EntradasBean;
import com.sigifar.beans.UsuariosBean;
import com.sigifar.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author amilp
 */
public class UsuariosDAO {

    @SuppressWarnings("CallToPrintStackTrace")
    public void insertaUsuario(UsuariosBean usuario) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement stmt = null;

        String sql = "INSERT INTO Usuarios (clave_unica, nombres, apellidos, correo, contrasena, rol, imagen) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, usuario.getClave_unica());
            stmt.setString(2, usuario.getNombres());
            stmt.setString(3, usuario.getApellidos());
            stmt.setString(4, usuario.getCorreo());
            stmt.setString(5, usuario.getContrasena());
            stmt.setString(6, usuario.getRol());
            stmt.setBytes(7, usuario.getImagen());

            int filas = stmt.executeUpdate();

            if (filas > 0) {
                System.out.println("Usuario insertado correctamente.");
            } else {
                System.out.println("No se insertó ninguna fila.");
            }

        } catch (SQLException e) {
            System.err.println("Error al insertar usuario: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }

    public void eliminaUsuario(int clave_unica) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement stmt = null;

        String sql = "DELETE FROM Usuarios WHERE clave_unica = ?";

        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, clave_unica);

            int filas = stmt.executeUpdate();

            if (filas > 0) {
                System.out.println("Usuario eliminado correctamente.");
            } else {
                System.out.println("No se eliminó ninguna fila.");
            }

        } catch (SQLException e) {
            System.err.println("Error al eliminar usuario: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }

    public void actualizaUsuario(UsuariosBean usuario) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement stmt = null;

        String sql = "UPDATE Usuarios SET clave_unica = ?, nombres = ?, apellidos = ?, correo = ?, contrasena = ?, rol = ?, imagen = ? WHERE clave_unica = ?";

        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, usuario.getClave_unica());
            stmt.setString(2, usuario.getNombres());
            stmt.setString(3, usuario.getApellidos());
            stmt.setString(4, usuario.getCorreo());
            stmt.setString(5, usuario.getContrasena());
            stmt.setString(6, usuario.getRol());
            stmt.setBytes(7, usuario.getImagen());
            stmt.setInt(8, usuario.getClave_unica());

            int filas = stmt.executeUpdate();

            if (filas > 0) {
                System.out.println("Usuario actualizado correctamente.");
            } else {
                System.out.println("No se actualizó ninguna fila.");
            }

        } catch (SQLException e) {
            System.err.println("Error al actualizar usuarios: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }

    public List<UsuariosBean> consultaUsuarios() {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM Usuarios";
        List<UsuariosBean> usuarios = new ArrayList<>();

        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                UsuariosBean usuario = new UsuariosBean(
                        rs.getInt("id_usuario"),
                        rs.getInt("clave_unica"),
                        rs.getString("nombres"),
                        rs.getString("apellidos"),
                        rs.getString("correo"),
                        rs.getString("contrasena"),
                        rs.getString("rol"),
                        rs.getBytes("imagen")
                );
                usuarios.add(usuario);
            }

        } catch (SQLException e) {
            System.err.println("Error al consultar usuarios: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }

        return usuarios;
    }

    public UsuariosBean consultaUsuario(int clave_unica) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM Usuarios WHERE clave_unica = ?";
        UsuariosBean usuario = null;

        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, clave_unica);
            rs = stmt.executeQuery();

            if (rs.next()) {
                usuario = new UsuariosBean(
                        rs.getInt("id_usuario"),
                        rs.getInt("clave_unica"),
                        rs.getString("nombres"),
                        rs.getString("apellidos"),
                        rs.getString("correo"),
                        rs.getString("contrasena"),
                        rs.getString("rol"),
                        rs.getBytes("imagen")
                );
                
            }

        } catch (SQLException e) {
            System.err.println("Error al consultar usuarios: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }

        return usuario;
    }

    public UsuariosBean login(String correo, String contrasena) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM Usuarios WHERE correo = ? AND contrasena = ?";
        UsuariosBean usuario = null;

        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, correo);
            stmt.setString(2, contrasena);
            rs = stmt.executeQuery();

            if (rs.next()) {
                usuario = new UsuariosBean(
                        rs.getInt("id_usuario"),
                        rs.getInt("clave_unica"),
                        rs.getString("nombres"),
                        rs.getString("apellidos"),
                        rs.getString("correo"),
                        rs.getString("contrasena"),
                        rs.getString("rol"),
                        rs.getBytes("imagen")
                );
            }

        } catch (SQLException e) {
            System.err.println("Error al consultar usuarios: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }

        return usuario;
    }
}
