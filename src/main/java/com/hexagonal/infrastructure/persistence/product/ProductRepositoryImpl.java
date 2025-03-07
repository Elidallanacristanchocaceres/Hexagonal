package com.hexagonal.infrastructure.persistence.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hexagonal.domain.entity.Product;
import com.hexagonal.domain.repository.ProductRepository;
import com.hexagonal.infrastructure.database.ConnectionDb;

public class ProductRepositoryImpl implements ProductRepository {
    private final ConnectionDb connection;
    public ProductRepositoryImpl(ConnectionDb connection) {
        this.connection = connection;
    }
    @Override
    public void guardar(Product product) {
        String sql = "INSERT INTO product (id, name, stock) VALUES (?, ?, ?)";
        try (Connection conexion = connection.getConexion();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, product.getId());
            stmt.setString(2, product.getName());
            stmt.setInt(3, product.getStock());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Product buscarPorId(int id) {
        String sql = "SELECT * FROM product WHERE id = ?";
        try (Connection conexion = connection.getConexion();
                PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Product(rs.getString("id"), rs.getString("name"), rs.getInt("stock"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> listarTodos() {
        String sql = "SELECT * FROM product";
        List<Product> products = new ArrayList<>();
        try (Connection conexion = connection.getConexion();
                Statement stmt = conexion.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                products.add(new Product(rs.getString("id"), rs.getString("name"), rs.getInt("stock")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public void actualizar(Product product) {
    String sql = "UPDATE product SET name = ?, stock = ? WHERE id = ?";
    try (Connection conexion = connection.getConexion();
        PreparedStatement stmt = conexion.prepareStatement(sql)) {
        stmt.setString(1, product.getName());
        stmt.setInt(2, product.getStock());
        stmt.setString(3, product.getId());
        stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }

    @Override
    public void eliminar(int id) {
    String sql = "DELETE FROM product WHERE id = ?";
    try (Connection conexion = connection.getConexion();
        PreparedStatement stmt = conexion.prepareStatement(sql)) {
        stmt.setInt(1, id);
        stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}