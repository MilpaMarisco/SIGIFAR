/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sigifar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sigifar.beans.EntradasBean;
import com.sigifar.util.DBConnection;

/**
 *
 * @author amilp
 */
public class EntradasDao {

    public void insertaEntrada(EntradasBean entrada) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement stmt = null;

        String sql = "INSERT INTO Entradas (id_producto, numero_lote, cantidad, fecha, id_usuario) VALUES (?, ?, ?, ?, ?)";

        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, entrada.getId_producto());
            stmt.setInt(2, entrada.getNumero_lote());
            stmt.setDate(3, new java.sql.Date(entrada.getFecha().getTime()));
            stmt.setInt(4, entrada.getCantidad());
            stmt.setInt(5, entrada.getId_usuario());

            int filas = stmt.executeUpdate();

            if (filas > 0) {
                System.out.println("Entrada insertada correctamente.");
            } else {
                System.out.println("No se insertó ninguna fila.");
            }

        } catch (SQLException e) {
            System.err.println("Error al insertar entrada: " + e.getMessage());
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

    public void eliminaEntrada(int id_entrada) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement stmt = null;

        String sql = "DELETE FROM Entradas WHERE id_entrada = ?";

        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id_entrada);

            int filas = stmt.executeUpdate();

            if (filas > 0) {
                System.out.println("Entrada eliminada correctamente.");
            } else {
                System.out.println("No se eliminó ninguna fila.");
            }

        } catch (SQLException e) {
            System.err.println("Error al eliminar entrada: " + e.getMessage());
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

    public void actualizaEntrada(EntradasBean entrada) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement stmt = null;

        String sql = "UPDATE Entradas SET id_producto = ?, numero_lote = ?, cantidad = ?, fecha = ?, id_usuario = ? WHERE id_entrada = ?";

        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, entrada.getId_producto());
            stmt.setInt(2, entrada.getNumero_lote());
            stmt.setDate(3, new java.sql.Date(entrada.getFecha().getTime()));
            stmt.setInt(4, entrada.getCantidad());
            stmt.setInt(5, entrada.getId_usuario());
            stmt.setInt(6, entrada.getId_entrada());

            int filas = stmt.executeUpdate();

            if (filas > 0) {
                System.out.println("Entrada actualizada correctamente.");
            } else {
                System.out.println("No se actualizó ninguna fila.");
            }

        } catch (SQLException e) {
            System.err.println("Error al actualizar entrada: " + e.getMessage());
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

    public List<EntradasBean> consultaEntrada(int id_entrada) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM Entradas WHERE id_entrada = ?";
        List<EntradasBean> entradas = new ArrayList<>();

        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id_entrada);
            rs = stmt.executeQuery();

            while (rs.next()) {
                EntradasBean entrada = new EntradasBean(
                        rs.getInt("id_entrada"),
                        rs.getInt("id_producto"),
                        rs.getInt("numero_lote"),
                        rs.getDate("fecha"),
                        rs.getInt("cantidad"),
                        rs.getInt("id_usuario")
                );
                entradas.add(entrada);
            }

        } catch (SQLException e) {
            System.err.println("Error al consultar entrada: " + e.getMessage());
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

        return entradas;
    }


}
