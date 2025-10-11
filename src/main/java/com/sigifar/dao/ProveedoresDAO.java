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

import com.sigifar.beans.ProveedoresBean;
import com.sigifar.util.DBConnection;

/**
 *
 * @author amilp
 */
public class ProveedoresDAO {

    public void insertaProveedor(ProveedoresBean proveedor) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement stmt = null;

        String sql = "INSERT INTO Proveedores (id_proveedor, nombre, contacto, telefono, correo) VALUES (?, ?, ?, ?, ?)";

        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, proveedor.getId_proveedor());
            stmt.setString(2, proveedor.getNombre());
            stmt.setString(3, proveedor.getContacto());
            stmt.setString(4, proveedor.getTelefono());
            stmt.setString(5, proveedor.getCorreo());

            int filas = stmt.executeUpdate();

            if (filas > 0) {
                System.out.println("Proveedor insertado correctamente.");
            } else {
                System.out.println("No se insertó ninguna fila.");
            }

        } catch (SQLException e) {
            System.err.println("Error al insertar proveedor: " + e.getMessage());
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

    public void eliminaProveedor(int id_proveedor) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement stmt = null;

        String sql = "DELETE FROM Proveedores WHERE id_proveedor = ?";

        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id_proveedor);

            int filas = stmt.executeUpdate();

            if (filas > 0) {
                System.out.println("Proveedor eliminado correctamente.");
            } else {
                System.out.println("No se eliminó ninguna fila.");
            }

        } catch (SQLException e) {
            System.err.println("Error al eliminar proveedor: " + e.getMessage());
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

    public void actualizaProveedor(ProveedoresBean proveedor) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement stmt = null;

        String sql = "UPDATE Proveedores SET nombre = ?, contacto = ?, telefono = ?, correo = ? WHERE id_proveedor = ?";

        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, proveedor.getNombre());
            stmt.setString(2, proveedor.getContacto());
            stmt.setString(3, proveedor.getTelefono());
            stmt.setString(4, proveedor.getCorreo());
            stmt.setInt(5, proveedor.getId_proveedor());

            int filas = stmt.executeUpdate();

            if (filas > 0) {
                System.out.println("Proveedor actualizado correctamente.");
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

    public List<ProveedoresBean> consultaProveedores() {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM Proveedores";
        List<ProveedoresBean> proveedores = new ArrayList<>();

        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                ProveedoresBean proveedor = new ProveedoresBean(
                        rs.getInt("id_proveedor"),
                        rs.getString("nombre"),
                        rs.getString("contacto"),
                        rs.getString("telefono"),
                        rs.getString("correo")
                );
                proveedores.add(proveedor);
            }
        
        } catch (SQLException e) {
            System.err.println("Error al consultar proveedores: " + e.getMessage());
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

        return proveedores;
    }

    public ProveedoresBean consultaProveedor(int id_proveedor) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ProveedoresBean proveedor = null;

        String sql = "SELECT * FROM Proveedores WHERE id_proveedor = ?";


        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id_proveedor);
            rs = stmt.executeQuery();

            if (rs.next()) {
                proveedor = new ProveedoresBean(
                        rs.getInt("id_proveedor"),
                        rs.getString("nombre"),
                        rs.getString("contacto"),
                        rs.getString("telefono"),
                        rs.getString("correo")
                );
            }

        } catch (SQLException e) {
            System.err.println("Error al consultar proveedor: " + e.getMessage());
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

        return proveedor;
    }

}
