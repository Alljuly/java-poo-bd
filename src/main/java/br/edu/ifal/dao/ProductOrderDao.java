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

    public List<ProductOrder> getAllOrders(){
        String sql = "SELECT * FROM ITEM_PEDIDO;";

        List<ProductOrder> productOrders = new ArrayList<>();
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int ID = Integer.parseInt(rs.getString("ID"));
                int ID_PEDIDO_FK = Integer.parseInt(rs.getString("ID_PEDIDO_FK"));
                int ID_PRODUTO_FK = Integer.parseInt(rs.getString("ID_PRODUTO_FK"));
                int QUANTIDADE = Integer.parseInt(rs.getString("QUANTIDADE"));
                double VALOR = Double.parseDouble(rs.getString("VALOR"));

                ProductOrder ProductOrder = new ProductOrder(ID, ID_PEDIDO_FK, ID_PRODUTO_FK, QUANTIDADE, VALOR);
                productOrders.add(ProductOrder);
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

    public ProductOrder getOrderById(int id){
        String sql = "SELECT * FROM ITEM_PEDIDO o WHERE o.id = ?;";
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){
                return new ProductOrder(
                    Integer.parseInt(rs.getString("ID")),
                    Integer.parseInt(rs.getString("ID_PEDIDO_FK")),
                    Integer.parseInt(rs.getString("ID_PRODUTO_FK")),
                    Integer.parseInt(rs.getString("QUANTIDADE")),
                    Double.parseDouble(rs.getString("VALOR"))
            );
            }
            pst.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public int addProductOrder(ProductOrder productOrder){
         String sql = "INSERT INTO ITEM_PEDIDO (ID_PEDIDO_FK, ID_PRODUTO_FK, QUANTIDADE, VALOR) VALUES (?,?,?,?);";

        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setInt(1, productOrder.getOrderId());
            pst.setInt(2, productOrder.getProductId());
            pst.setInt(3, productOrder.getQuantity());
            pst.setObject(4, productOrder.getValue());


            int res = pst.executeUpdate();

            pst.close();
            connection.close();

            return res;

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public int updateOrder(ProductOrder productOrder){
        String sql = "UPDATE ITEM_PEDIDO SET ID_PEDIDO_FK = ?, ID_PRODUTO_FK = ?, QUANTIDADE = ?, VALOR = ? WHERE ID = ?;";
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setInt(1, productOrder.getOrderId());
            pst.setInt(2, productOrder.getProductId());
            pst.setInt(3, productOrder.getQuantity());
            pst.setObject(4, productOrder.getValue());
            pst.setInt(5, productOrder.getId());

           int res =  pst.executeUpdate();

            pst.close();
            connection.close();

            return res;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }


    }
    
    public int deleteOrder(int id){
        String sql = "Delete from ITEM_PEDIDO WHERE id = ?;";
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
}
