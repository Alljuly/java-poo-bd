package br.edu.ifal.dao;

import br.edu.ifal.db.ConnectionHelper;
import br.edu.ifal.domain.ProductOrder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductOrderDao {

    List<ProductOrder> getAllOrders(){
        String sql = "SELECT * FROM ITEM_PEDIDO;";

        List<ProductOrder> productOrders = new ArrayList<>();
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int ID = Int.ParseInt(rs.getString("ID"));
                int ID_PEDIDO_FK = Int.ParseInt(rs.getString("ID_PEDIDO_FK"));
                int ID_PRODUTO_FK = Int.ParseInt(rs.getString("ID_PRODUTO_FK"));
                int QUANTIDADE = Int.ParseInt(rs.getString("QUANTIDADE"));
                double VALOR = Double.ParseDouble(rs.getString("VALOR"));

                ProductOrder ProductOrder = new ProductOrder(ID, ID_PEDIDO_FK, ID_PRODUTO_FK, QUANTIDADE, VALOR);
                lista.add(ProductOrder);
            }

            pst.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productOrders;
    }

    ProductOrder getOrderById(String id){
        String sql = "SELECT * FROM ITEM_PEDIDO o WHERE o.id = ?;";
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setString(1, id);

            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){
                return new ProductOrder(
                    Int.ParseInt(rs.getString("ID")),
                    Int.ParseInt(rs.getString("ID_PEDIDO_FK")),
                    Int.ParseInt(rs.getString("ID_PRODUTO_FK")),
                    Int.ParseInt(rs.getString("QUANTIDADE")),
                    Double.ParseDouble(rs.getString("VALOR"))
            );
            }
            pst.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    void addOrder(ProductOrder productOrder){
         String sql = "INSERT INTO ITEM_PEDIDO VALUES (?,?,?,?);";

        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setString(1, productOrder.getID());
            pst.setString(2, productOrder.getCPFEmployee());
            pst.setString(3, productOrder.getCPFClient());
            pst.setString(4, productOrder.getValue());

            pst.execute();

            pst.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    int updateOrder(ProductOrder productOrder){
        String sql = "UPDATE ITEM_PEDIDO SET ID = ?, ID_PEDIDO_FK = ?, ID_PRODUTO_FK = ?, QUANTIDADE = ?, VALOR = ? WHERE CPF = ?;";
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setString(1, productOrder.getCpf());
            pst.setString(2, productOrder.getNome());
            pst.setString(3, productOrder.getEndereco());
            pst.setString(4, productOrder.getTelefone());
            pst.setString(5, productOrder.getCpf());

           return pst.executeUpdate();

            pst.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }


    }
    
    int deleteOrder(String id){
        String sql = "Delete from ITEM_PEDIDO WHERE id = ?;";
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
