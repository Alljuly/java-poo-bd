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
    
 List<Product> getAllOrders(){
    String sql = "SELECT * FROM Produto;";
    
    List<Product> products = new ArrayList<>();
    try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);
            
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int ID         = rs.getString("ID");
                String NOME       = rs.getString("NOME");
                double VALOR_UNIT = rs.getString("VALOR_UNIT");
                int QUANTIDADE = Double.ParseDouble(rs.getString("QUANTIDADE"));
                
                Product product = new Product(ID, NOME, VALOR_UNIT, QUANTIDADE);
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
    
    Order getProductById(String id){
        String sql = "SELECT * FROM Produto p WHERE p.id = ?;";
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);
            
            pst.setString(1, id);
            
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){
                return new Product(
                    rs.getString("ID"),
                    rs.getString("NOME"),
                    rs.getString("VALOR_UNIT"),
                    rs.getString("QUANTIDADE")
                    );
            }
            pst.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        
        return null;
    }
    
    void addProduct(Product product){
         String sql = "INSERT INTO Produto VALUES (?,?,?,?);";

        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);
            
            pst.setString(1, product.getID());
            pst.setString(2, product.getCPFEmployee());
            pst.setString(3, product.getCPFClient());
            pst.setString(4, product.getValue());
            
            pst.execute();
            
            pst.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    int updateProduct(Product product){
        String sql = "UPDATE Produto SET ID = ?, NOME = ?, VALOR_UNIT = ?, QUANTIDADE = ? WHERE ID = ?;";
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);
            
            pst.setString(1, product.getID());
            pst.setString(2, product.getCPFEmployee());
            pst.setString(3, product.getCPFClient());
            pst.setString(4, product.getValue());
            pst.setString(5, product.getID());
            
            return pst.executeUpdate();
            
            pst.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        
    }
    
    int deleteProduct(String id){
        String sql = "Delete from Produto WHERE id = ?;";
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);
            
            pst.setString(1, id);
            return pst.executeUpdate();
            
            pst.close();
            connection.close();
            
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        
    }
}

