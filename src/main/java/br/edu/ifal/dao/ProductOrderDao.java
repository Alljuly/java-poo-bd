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

    public List<ProductOrder> getAllProductOrders(){
        String sql = "SELECT * FROM ITEM_PEDIDO;";

        List<ProductOrder> productOrders = new ArrayList<>();
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int ID = rs.getInt("ID");
                int ID_PEDIDO_FK = rs.getInt("ID_PEDIDO_FK");
                int ID_PRODUTO_FK = rs.getInt("ID_PRODUTO_FK");
                int QUANTIDADE = rs.getInt("QUANTIDADE");
                double VALOR = rs.getDouble("VALOR");

                ProductOrder ProductOrder = new ProductOrder(ID_PEDIDO_FK, ID_PRODUTO_FK, QUANTIDADE, VALOR);
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

    public ProductOrder getProductOrderById(int id){
        String sql = "SELECT * FROM ITEM_PEDIDO o WHERE o.id = ?;";
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){
                return new ProductOrder(
                    rs.getInt("ID"),
                    rs.getInt("ID_PEDIDO_FK"),
                    rs.getInt("ID_PRODUTO_FK"),
                    rs.getInt("QUANTIDADE"),
                    rs.getDouble("VALOR")
            );
            }
            pst.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public boolean hasProductOrdersForOrder(int id){
        String sql = "SELECT * FROM ITEM_PEDIDO i WHERE i.ID_PEDIDO_FK = ?;";
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();
            boolean exists = rs.next();
            
            rs.close();
            pst.close();
            connection.close();
            
            return exists;
        
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Erro ao verificar pedidos associados ao item.", e);
        }
    }

    public int addProductOrder(ProductOrder productOrder){
         String sql = "INSERT INTO ITEM_PEDIDO (ID_PEDIDO_FK, ID_PRODUTO_FK, QUANTIDADE, VALOR) VALUES (?,?,?,?);";

        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setInt(1, productOrder.getOrderId());
            pst.setInt(2, productOrder.getProductId());
            pst.setInt(3, productOrder.getQuantity());
            pst.setDouble(4, productOrder.getValue());


            int res = pst.executeUpdate();

            pst.close();
            connection.close();

            return res;

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public int updateProductOrder(ProductOrder productOrder){
        String sql = "UPDATE ITEM_PEDIDO SET ID_PEDIDO_FK = ?, ID_PRODUTO_FK = ?, QUANTIDADE = ?, VALOR = ? WHERE ID = ?;";
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setInt(1, productOrder.getOrderId());
            pst.setInt(2, productOrder.getProductId());
            pst.setInt(3, productOrder.getQuantity());
            pst.setDouble(4, productOrder.getValue());
            pst.setInt(5, productOrder.getId());

           int res =  pst.executeUpdate();

            pst.close();
            connection.close();

            return res;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public int deleteProductOrder(int id){
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

    public boolean hasProductInProductOrder(int id) {
        String query = "SELECT COUNT(*) FROM Item_Pedido WHERE ID_PRODUTO_FK = ?";
        try (Connection conn = ConnectionHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return false;}

}
