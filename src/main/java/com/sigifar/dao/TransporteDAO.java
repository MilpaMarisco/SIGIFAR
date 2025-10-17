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

import com.sigifar.beans.TransporteBean;
import com.sigifar.util.DBConnection;

/**
 *
 * @author amilp
 */
public class TransporteDAO {

    public void insertaTransporte(TransporteBean transporte) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement stmt = null;

        String sql = "INSERT INTO Transporte (numero_unidad, transportista) VALUES (?, ?)";

        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, transporte.getNumero_unidad());
            stmt.setString(2, transporte.getTransportista());

            int filas = stmt.executeUpdate();

            if (filas > 0) {
                System.out.println("Transporte insertado correctamente.");
            } else {
                System.out.println("No se insertó ninguna fila.");
            }

        } catch (SQLException e) {
            System.err.println("Error al insertar transporte: " + e.getMessage());
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

    public void eliminaTransporte(int id_transporte) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement stmt = null;

        String sql = "DELETE FROM Transporte WHERE id_transporte = ?";

        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id_transporte);

            int filas = stmt.executeUpdate();

            if (filas > 0) {
                System.out.println("Transporte eliminado correctamente.");
            } else {
                System.out.println("No se eliminó ninguna fila.");
            }

        } catch (SQLException e) {
            System.err.println("Error al eliminar transporte: " + e.getMessage());
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

    public void actualizaTransporte(TransporteBean transporte) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement stmt = null;

        String sql = "UPDATE Transporte SET numero_unidad = ?, transportista = ? WHERE id_transporte = ?";

        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, transporte.getNumero_unidad());
            stmt.setString(2, transporte.getTransportista());
            stmt.setInt(3, transporte.getId_transporte());

            int filas = stmt.executeUpdate();

            if (filas > 0) {
                System.out.println("Transporte actualizado correctamente.");
            } else {
                System.out.println("No se actualizó ninguna fila.");
            }

        } catch (SQLException e) {
            System.err.println("Error al actualizar transporte: " + e.getMessage());
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

    public List<TransporteBean> consultaTransporte() {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM Transporte";
        List<TransporteBean> transportes = new ArrayList<>();

        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                TransporteBean transporte = new TransporteBean(
                        rs.getInt("id_transporte"),
                        rs.getString(2),
                        rs.getString(3)
                );
                transportes.add(transporte);
            }

        } catch (SQLException e) {
            System.err.println("Error al consultar transportes: " + e.getMessage());
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

        return transportes;
    }

    public TransporteBean consultaEntrada(int id_transporte) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM Trranporte WHERE id_transporte = ?";
        TransporteBean transporte = null;

        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id_transporte);
            rs = stmt.executeQuery();

            if (rs.next()) {
                transporte = new TransporteBean(
                        rs.getInt("id_transporte"),
                        rs.getString(2),
                        rs.getString(3)
                );
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

        return transporte;
    }

}
