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

import com.sigifar.beans.ProductosBean;
import com.sigifar.util.DBConnection;

/**
 *
 * @author amilp
 */
public class ProductosDAO {

    public void insertaProducto(ProductosBean producto) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement stmt = null;

        String sql = "INSERT INTO Productos (clave_producto, nombre, marca, presentacion, cantidad, id_proveedor, id_ubicacion) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, producto.getClave_producto());
            stmt.setString(2, producto.getNombre());
            stmt.setString(3, producto.getMarca());
            stmt.setString(4, producto.getPresentacion());
            stmt.setInt(5, producto.getCantidad());
            stmt.setInt(6, producto.getId_proveedor());
            stmt.setInt(7, producto.getId_ubicacion());

            int filas = stmt.executeUpdate();

            if (filas > 0) {
                System.out.println("Producto insertado correctamente.");
            } else {
                System.out.println("No se insertó ninguna fila.");
            }

        } catch (SQLException e) {
            System.err.println("Error al insertar producto: " + e.getMessage());
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

    public void eliminaProducto(int clave_producto) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement stmt = null;

        String sql = "DELETE FROM Productos WHERE clave_producto = ?";

        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, clave_producto);

            int filas = stmt.executeUpdate();

            if (filas > 0) {
                System.out.println("Producto eliminado correctamente.");
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

    public void actualizaProducto(ProductosBean producto) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement stmt = null;

        String sql = "UPDATE Productos SET clave_producto = ?, nombre = ?, marca = ?, presentacion = ?, cantidad = ?, id_proveedor = ?, id_ubicacion = ? WHERE clave_producto = ?";

        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, producto.getClave_producto());
            stmt.setString(2, producto.getNombre());
            stmt.setString(3, producto.getMarca());
            stmt.setString(4, producto.getPresentacion());
            stmt.setInt(5, producto.getCantidad());
            stmt.setInt(6, producto.getId_proveedor());
            stmt.setInt(7, producto.getId_ubicacion());
            stmt.setString(8, producto.getClave_producto());

            int filas = stmt.executeUpdate();

            if (filas > 0) {
                System.out.println("Producto actualizado correctamente.");
            } else {
                System.out.println("No se actualizó ninguna fila.");
            }

        } catch (SQLException e) {
            System.err.println("Error al actualizar producto: " + e.getMessage());
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

    public List<ProductosBean> consultaProductos() {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM Productos";
        List<ProductosBean> productos = new ArrayList<>();

        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                ProductosBean producto = new ProductosBean(
                        rs.getInt("id_producto"),
                        rs.getString("clave_producto"),
                        rs.getString("nombre"),
                        rs.getString("marca"),
                        rs.getString("presentacion"),
                        rs.getInt("cantidad"),
                        rs.getInt("id_proveedor"),
                        rs.getInt("id_ubicacion")
                );
                productos.add(producto);
            }

        } catch (SQLException e) {
            System.err.println("Error al consultar productos: " + e.getMessage());
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

        return productos;
    }

    public ProductosBean consultaProducto(String clave_producto) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ProductosBean producto = null;

        String sql = "SELECT * FROM Productos WHERE clave_producto = ?";

        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, clave_producto);
            rs = stmt.executeQuery();

            if (rs.next()) {
                producto = new ProductosBean(
                        rs.getInt("id_producto"),
                        rs.getString("clave_producto"),
                        rs.getString("nombre"),
                        rs.getString("marca"),
                        rs.getString("presentacion"),
                        rs.getInt("cantidad"),
                        rs.getInt("id_proveedor"),
                        rs.getInt("id_ubicacion")
                );
            }

        } catch (SQLException e) {
            System.err.println("Error al consultar producto: " + e.getMessage());
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

        return producto;
    }

}
