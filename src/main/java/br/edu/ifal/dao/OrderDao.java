package br.edu.ifal.dao;

import br.edu.ifal.db.ConnectionHelper;
import br.edu.ifal.domain.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {

    public List<Order> getAllOrders(){
        String sql = "SELECT * FROM Pedido;";

        List<Order> orders = new ArrayList<>();
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = Integer.parseInt(rs.getString("ID"));
                String cpf_client = rs.getString("CPF_CLIENTE_FK");
                String cpf_employee = rs.getString("CPF_FUNCIONARIO_FK");
                double value = Double.parseDouble(rs.getString("VALOR_TOTAL"));

                Order Order = new Order(id, cpf_client, cpf_employee, value);
                orders.add(Order);
            }

            pst.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }

    public boolean hasOrdersForEmployee(String cpf){
        String sql = "SELECT * FROM ITEM_PEDIDO o WHERE o.CPF_FUNCIONARIO_FK = ?;";
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setString(1, cpf);

            ResultSet rs = pst.executeQuery();
            boolean exists = rs.next();
            
            rs.close();
            pst.close();
            connection.close();
            
            return exists;
        
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Erro ao verificar pedidos associados ao funcionário.", e);
        }
    }

    public boolean hasOrdersForClient(String cpf){
        String sql = "SELECT * FROM ITEM_PEDIDO o WHERE o.CPF_CLIENTE_FK = ?;";
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setString(1, cpf);

            ResultSet rs = pst.executeQuery();
            boolean exists = rs.next();
            
            rs.close();
            pst.close();
            connection.close();
            
            return exists;
        
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Erro ao verificar pedidos associados ao cliente.", e);
        }
    }

    public Order getOrderById(int id){
        String sql = "SELECT * FROM Pedido p WHERE p.id = ?;";
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();

            if(rs.next()){
                return new Order(
                        Integer.parseInt(rs.getString("ID")),
                        rs.getString("CPF_CLIENTE_FK"),
                        rs.getString("CPF_FUNCIONARIO_FK"),
                        Double.parseDouble(rs.getString("VALOR_TOTAL"))
                );
            }
            pst.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public int addOrder(Order order)  {
         String sql = "INSERT INTO Pedido (CPF_CLIENTE_FK, CPF_FUNCIONARIO_FK, VALOR_TOTAL) VALUES (?,?,?);";

        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);


            pst.setString(1, order.getClientCpf());
            pst.setString(2, order.getEmployeeCpf());
            pst.setObject(3, order.getTotalValue());

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
    
    public int updateOrder(Order order){
        String sql = "UPDATE Pedido p SET p.VALOR_TOTAL = ? WHERE p.id = ?;";
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);


            pst.setObject(1, order.getTotalValue());
            pst.setLong(2, order.getId());

           int res = pst.executeUpdate();

            pst.close();
            connection.close();
            return res;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }


    }
    
    public int deleteOrder(int id){
        String sql = "Delete from Pedido WHERE id = ?;";
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

    public boolean isOrderExists(int id){
        String query = "SELECT COUNT(*) FROM PEDIDO WHERE id = ?";
        try (Connection conn = ConnectionHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

}
