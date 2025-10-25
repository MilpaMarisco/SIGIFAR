package com.sigifar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sigifar.beans.LotesBean;
import com.sigifar.beans.SalidasBean;
import com.sigifar.util.DBConnection;

public class LotesDAO {

    public void insertaLote(LotesBean lote) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement stmt = null;

        String sql = "INSERT INTO Lotes (id_lote, id_producto, numero_lote, fecha_caducidad) VALUES (?, ?, ?, ?)";

        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, lote.getId_lote());
            stmt.setInt(2, lote.getId_producto());
            stmt.setString(3, lote.getNumero_lote());
            stmt.setDate(4, new java.sql.Date(lote.getFecha_caducidad().getTime()));

            int filas = stmt.executeUpdate();

            if (filas > 0) {
                System.out.println("Lote insertado correctamente.");
            } else {
                System.out.println("No se insertó ninguna fila.");
            }

        } catch (SQLException e) {
            System.err.println("Error al insertar lote: " + e.getMessage());
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

    public void eliminaLote(String numero_lote) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement stmt = null;

        String sql = "DELETE FROM Lotes WHERE numero_lote = ?";

        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, numero_lote);

            int filas = stmt.executeUpdate();

            if (filas > 0) {
                System.out.println("Lote eliminado correctamente.");
            } else {
                System.out.println("No se eliminó ninguna fila.");
            }

        } catch (SQLException e) {
            System.err.println("Error al eliminar lote: " + e.getMessage());
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

    public void actualizaLote(LotesBean lote) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement stmt = null;

        String sql = "UPDATE Lotes SET id_producto = ?, numero_lote = ?, fecha_caducidad = ? WHERE id_lote = ?";

        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, lote.getId_producto());
            stmt.setString(2, lote.getNumero_lote());
            stmt.setDate(3, new java.sql.Date(lote.getFecha_caducidad().getTime()));
            stmt.setInt(4, lote.getId_lote());

            int filas = stmt.executeUpdate();

            if (filas > 0) {
                System.out.println("Lote actualizado correctamente.");
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

    public List<LotesBean> consultaLotes() {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM Lotes";
        List<LotesBean> lotes = new ArrayList<>();

        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                LotesBean lote = new LotesBean(
                        rs.getInt("id_lote"),
                        rs.getInt("id_producto"),
                        rs.getString("numero_lote"),
                        rs.getDate("fecha_caducidad")
                );
                lotes.add(lote);
            }

        } catch (SQLException e) {
            System.err.println("Error al consultar lotes: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }

            } catch (SQLException e) {
                System.err.println("Error al consultar lotes: " + e.getMessage());

            }
        }
        return lotes;
    }

    public List<LotesBean> consultaLotesFIFO(int id_producto) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT L.ID_LOTE, L.ID_PRODUCTO, L.NUMERO_LOTE, L.FECHA_CADUCIDAD, "
                + "MIN(E.FECHA) AS FECHA_LLEGADA "
                + "FROM LOTES L "
                + "JOIN ENTRADAS E ON L.ID_LOTE = E.ID_LOTE "
                + "WHERE L.ID_PRODUCTO = ? "
                + "GROUP BY L.ID_LOTE, L.ID_PRODUCTO, L.NUMERO_LOTE, L.FECHA_CADUCIDAD "
                + "ORDER BY L.FECHA_CADUCIDAD ASC, MIN(E.FECHA) ASC";

        List<LotesBean> lotes = new ArrayList<>();

        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id_producto);

            rs = stmt.executeQuery();

            while (rs.next()) {
                LotesBean lote = new LotesBean(
                        rs.getInt("id_lote"),
                        rs.getInt("id_producto"),
                        rs.getString("numero_lote"),
                        rs.getDate("fecha_caducidad")
                );
                lotes.add(lote);
            }

        } catch (SQLException e) {
            System.err.println("Error al consultar lotes: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }

            } catch (SQLException e) {
                System.err.println("Error al consultar lotes: " + e.getMessage());

            }
        }
        return lotes;
    }

    public LotesBean consultaLote(String numero_lote) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM Lotes WHERE numero_lote = ?";
        LotesBean lote = null;

        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, numero_lote);
            rs = stmt.executeQuery();

            if (rs.next()) {
                lote = new LotesBean(
                        rs.getInt("id_lote"),
                        rs.getInt("id_producto"),
                        rs.getString("numero_lote"),
                        rs.getDate("fecha_caducidad")
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

        return lote;
    }

    public LotesBean consultaLotePK(String numero_lote, int id_producto) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM Lotes WHERE numero_lote = ? AND id_producto = ?";
        LotesBean lote = null;

        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, numero_lote);
            stmt.setInt(2, id_producto);
            rs = stmt.executeQuery();

            if (rs.next()) {
                lote = new LotesBean(
                        rs.getInt("id_lote"),
                        rs.getInt("id_producto"),
                        rs.getString("numero_lote"),
                        rs.getDate("fecha_caducidad")
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

        return lote;
    }

}
