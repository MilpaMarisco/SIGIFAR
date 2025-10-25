/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * 
Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sigifar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sigifar.beans.SalidasBean;
import com.sigifar.util.DBConnection;

/**
 *
 * @author amilp
 */
public class SalidasDAO {

    public void insertaSalida(SalidasBean salida) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement stmt = null;

        String sql = "INSERT INTO Salidas (id_lote, cantidad, fecha, destino, id_usuario, id_transporte) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, salida.getId_lote());
            stmt.setInt(2, salida.getCantidad());
            stmt.setDate(3, new java.sql.Date(salida.getFecha().getTime()));
            stmt.setString(4, salida.getDestino());
            stmt.setInt(5, salida.getId_usuario());
            stmt.setInt(6, salida.getId_transporte());

            int filas = stmt.executeUpdate();

            if (filas > 0) {
                System.out.println("Salida insertada correctamente.");
            } else {
                System.out.println("No se insertó ninguna fila.");
            }

        } catch (SQLException e) {
            System.err.println("Error al insertar salida: " + e.getMessage());
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

    public void eliminaSalida(int id_salida) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement stmt = null;

        String sql = "DELETE FROM Salidas WHERE id_salida = ?";

        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id_salida);

            int filas = stmt.executeUpdate();

            if (filas > 0) {
                System.out.println("Salida eliminada correctamente.");
            } else {
                System.out.println("No se eliminó ninguna fila.");
            }

        } catch (SQLException e) {
            System.err.println("Error al eliminar salida: " + e.getMessage());
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

    public void actualizaSalida(SalidasBean salida) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement stmt = null;

        String sql = "UPDATE Salidas SET id_lote = ?, cantidad = ?, fecha = ?, destino = ?, id_usuario = ?, id_transporte = ? WHERE id_salida = ?";

        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, salida.getId_lote());
            stmt.setInt(2, salida.getCantidad());
            stmt.setDate(3, new java.sql.Date(salida.getFecha().getTime()));
            stmt.setString(4, salida.getDestino());
            stmt.setInt(5, salida.getId_usuario());
            stmt.setInt(6, salida.getId_transporte());
            stmt.setInt(7, salida.getId_salida());

            int filas = stmt.executeUpdate();

            if (filas > 0) {
                System.out.println("Salida actualizada correctamente.");
            } else {
                System.out.println("No se actualizó ninguna fila.");
            }

        } catch (SQLException e) {
            System.err.println("Error al actualizar salida: " + e.getMessage());
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

    public List<SalidasBean> consultaSalidas() {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM Salidas";
        List<SalidasBean> salidas = new ArrayList<>();

        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                SalidasBean salida = new SalidasBean(
                        rs.getInt("id_salida"),
                        rs.getInt("id_lote"),
                        rs.getInt("cantidad"),
                        rs.getDate("fecha"),
                        rs.getString("destino"),
                        rs.getInt("id_usuario"),
                        rs.getInt("id_transporte")
                );
                salidas.add(salida);
            }

        } catch (SQLException e) {
            System.err.println("Error al consultar salidas: " + e.getMessage());
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

        return salidas;
    }

    public SalidasBean consultaSalida(int id_salida) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM Salidas WHERE id_salida = ?";
        SalidasBean salida = null;

        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id_salida);
            rs = stmt.executeQuery();

            if (rs.next()) {
                salida = new SalidasBean(
                        rs.getInt("id_salida"),
                        rs.getInt("id_lote"),
                        rs.getInt("cantidad"),
                        rs.getDate("fecha"),
                        rs.getString("destino"),
                        rs.getInt("id_usuario"),
                        rs.getInt("id_transporte")
                );
            }

        } catch (SQLException e) {
            System.err.println("Error al consultar salida: " + e.getMessage());
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

        return salida;
    }

    public int consultaSalidaPorLote(int id_lote) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT IFNULL(SUM(CANTIDAD), 0) AS TOTAL FROM SALIDAS WHERE ID_LOTE = ?";


        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id_lote);
            rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("TOTAL");
            }

        } catch (SQLException e) {
            System.err.println("Error al consultar salida: " + e.getMessage());
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

        return 0;
    }

}
