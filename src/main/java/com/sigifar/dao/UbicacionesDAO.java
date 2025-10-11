package com.sigifar.dao;

import com.sigifar.beans.UbicacionesBean;
import com.sigifar.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UbicacionesDAO {

    public void insertaUbicacion(UbicacionesBean ubicacion) {
        DBConnection db = new DBConnection();
        String sql = "INSERT INTO ubicaciones (almacen, pasillo, estanteria, nivel, posicion, indicaciones_especiales) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = db.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, ubicacion.getAlmacen());
            stmt.setString(2, ubicacion.getPasillo());
            stmt.setString(3, ubicacion.getEstanteria());
            stmt.setString(4, ubicacion.getNivel());
            stmt.setString(5, ubicacion.getPosicion());
            stmt.setString(6, ubicacion.getIndicaciones_especiales());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al insertar ubicación: " + e.getMessage());
        }
    }

    public List<UbicacionesBean> consultaUbicaciones() {
        DBConnection db = new DBConnection();
        List<UbicacionesBean> lista = new ArrayList<>();
        String sql = "SELECT * FROM ubicaciones";
        try (Connection conn = db.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                UbicacionesBean u = new UbicacionesBean(
                        rs.getInt("id_ubicacion"),
                        rs.getString("almacen"),
                        rs.getString("pasillo"),
                        rs.getString("estanteria"),
                        rs.getString("nivel"),
                        rs.getString("posicion"),
                        rs.getString("indicaciones_especiales")
                );
                lista.add(u);
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar ubicaciones: " + e.getMessage());
        }
        return lista;
    }
}
