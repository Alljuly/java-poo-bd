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

    List<Order> getAllOrders(){
        String sql = "SELECT * FROM Pedido;";

        List<Order> orders = new ArrayList<>();
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String id = rs.getString("ID");
                String cpf_client = rs.getString("CPF_CLIENTE_FK");
                String cpf_employee = rs.getString("CPF_FUNCIONARIO_FK");
                double value = Double.ParseDouble(rs.getString("VALOR_TOTAL"));

                Order Order = new Order(id, cpf_client, cpf_employee, value);
                lista.add(Order);
            }

            pst.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }

    Order getOrderById(String id){
        String sql = "SELECT * FROM Pedido p WHERE p.id = ?;";
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setString(1, id);

            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){
                return new Order(
                    rs.getString("ID"),
                    rs.getString("CPF_CLIENTE_FK"),
                    rs.getString("CPF_FUNCIONARIO_FK"),
                    rs.getString("VALOR_TOTAL")
            );
            }
            pst.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    void addOrder(Order order){
         String sql = "INSERT INTO Pedido VALUES (?,?,?,?);";

        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setString(1, order.getId());
            pst.setString(2, order.getClientCpf());
            pst.setString(3, order.getEmployeeCpf());
            pst.setString(4, order.getTotalValue());

            pst.execute();

            pst.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    int updateOrder(Order order){
        String sql = "UPDATE Pedido SET ID = ?, CPF_CLIENTE_FK = ?, CPF_FUNCIONARIO_FK = ?, VALOR_TOTAL = ? WHERE id = ?;";
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setString(1, order.getId());
            pst.setString(2, order.getClientCpf());
            pst.setString(3, order.getEmployeeCpf());
            pst.setString(4, order.getTotalValue());
            pst.setString(1, order.getId());

           return pst.executeUpdate();

            pst.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }


    }
    
    int deleteOrder(String id){
        String sql = "Delete from Pedido WHERE id = ?;";
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
