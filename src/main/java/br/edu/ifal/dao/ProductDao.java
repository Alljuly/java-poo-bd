package br.edu.ifal.dao;

import br.edu.ifal.db.ConnectionHelper;
import br.edu.ifal.domain.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    public List<Product> getAllProducts() {
        String sql = "SELECT * FROM Produto;";

        List<Product> products = new ArrayList<>();
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = Integer.parseInt(rs.getString("ID"));
                String nome = rs.getString("NOME");
                double valorUnit = Double.parseDouble(rs.getString("VALOR_UNIT"));
                int quantidade = Integer.parseInt(rs.getString("QUANTIDADE"));

                Product product = new Product(id, nome, valorUnit, quantidade);
                products.add(product);
            }

            pst.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    public Product getProductById(int id){
        String sql = "SELECT * FROM Produto p WHERE p.id = ?;";
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);
            
            pst.setInt(1, id);
            
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){

                int ID = Integer.parseInt(rs.getString("ID"));
                String nome = rs.getString("NOME");
                double valorUnit = Double.parseDouble(rs.getString("VALOR_UNIT"));
                int quantidade = Integer.parseInt(rs.getString("QUANTIDADE"));

              

                Product p = new Product(
                    ID,
                    nome,
                    valorUnit,
                    quantidade
                    );

                pst.close();
                connection.close();

                return p;
            }
          
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        
        return null;
    }

    public int addProduct(Product product) {
        String sql = "INSERT INTO Produto (NOME, VALOR_UNIT, QUANTIDADE) VALUES (?,?,?);";

        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            pst.setString(1, product.getName());
            pst.setObject(2, product.getUnitPrice());
            pst.setLong(3, product.getQuantity());

            pst.execute();

            ResultSet rs = pst.getGeneratedKeys();
            int id =-1;
            if(rs.next()){
                id = rs.getInt(1);
            }

            rs.close();
            pst.close();
            connection.close();
            return id;

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int updateProduct(Product product) {
        String sql = "UPDATE Produto SET ID = ?, NOME = ?, VALOR_UNIT = ?, QUANTIDADE = ? WHERE ID = ?;";
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setLong(1, product.getId());
            pst.setString(2, product.getName());
            pst.setObject(3, product.getUnitPrice());
            pst.setLong(4, product.getQuantity());
            pst.setLong(5, product.getId());

            int res = pst.executeUpdate();

            pst.close();
            connection.close();

            return res;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public int deleteProduct(int id) {
        String sql = "Delete from Produto WHERE id = ?;";
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setInt(1, id);
            int res = pst.executeUpdate();

            pst.close();
            connection.close();

            return res;

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean productExists(int id) {
        String sql = "SELECT COUNT(*) FROM Produto WHERE id = ?;";

        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            pst.close();
            connection.close();
            return false;

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
