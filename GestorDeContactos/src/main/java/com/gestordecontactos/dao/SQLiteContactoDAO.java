package com.gestordecontactos.dao;

import com.gestordecontactos.model.Contacto;
import com.google.inject.Inject;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SQLiteContactoDAO implements ContactoDAO {
    private final DataSource dataSource;

    @Inject
    public SQLiteContactoDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void create(Contacto c) {
        String sql = "INSERT INTO contactos (nombre, email, telefono, fecha_cumple) VALUES (?,?,?,?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, c.nombreProperty().get());
            ps.setString(2, c.emailProperty().get());
            ps.setString(3, c.telefonoProperty().get());
            LocalDate fecha = c.fechaCumpleProperty().get();
            if (fecha != null) {
                ps.setString(4, fecha.toString());
            } else {
                ps.setNull(4, Types.VARCHAR);
            }
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    c.idProperty().set(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Contacto> findById(int id) {
        String sql = "SELECT * FROM contactos WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(map(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<Contacto> findAll() {
        List<Contacto> list = new ArrayList<>();
        String sql = "SELECT * FROM contactos";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(map(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public void update(Contacto c) {
        String sql = "UPDATE contactos SET nombre=?, email=?, telefono=?, fecha_cumple=? WHERE id=?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.nombreProperty().get());
            ps.setString(2, c.emailProperty().get());
            ps.setString(3, c.telefonoProperty().get());
            LocalDate fecha = c.fechaCumpleProperty().get();
            if (fecha != null) {
                ps.setString(4, fecha.toString());
            } else {
                ps.setNull(4, Types.VARCHAR);
            }
            ps.setInt(5, c.idProperty().get());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM contactos WHERE id=?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Contacto map(ResultSet rs) throws SQLException {
        Contacto c = new Contacto();
        c.idProperty().set(rs.getInt("id"));
        c.nombreProperty().set(rs.getString("nombre"));
        c.emailProperty().set(rs.getString("email"));
        c.telefonoProperty().set(rs.getString("telefono"));
        String fecha = rs.getString("fecha_cumple");
        if (fecha != null) {
            c.fechaCumpleProperty().set(LocalDate.parse(fecha));
        }
        return c;
    }
}
