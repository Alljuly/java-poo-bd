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
        String sql = "SELECT * FROM Order;";

        List<Order> orders = new ArrayList<>();
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String id = rs.getString("ID");
                String name = rs.getString("CPF_CLIENTE_FK");
                String cpf_employee = rs.getString("CPF_FUNCIONARIO_FK");
                double value = Double.ParseDouble(rs.getString("VALOR_TOTAL"));

                Order Order = new Order(cpf, name, cpf_employee, value);
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
        String sql = "SELECT * FROM Order o WHERE o.id = ?;";
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
         String sql = "INSERT INTO Order VALUES (?,?,?,?);";

        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setString(1, order.getID());
            pst.setString(2, order.getCPFEmployee());
            pst.setString(3, order.getCPFClient());
            pst.setString(4, order.getValue());

            pst.execute();

            pst.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    int updateOrder(Order order){
        String sql = "UPDATE Order SET CPF = ?, NOME = ?, ENDERECO = ?, TELEFONE = ? WHERE CPF = ?;";
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setString(1, order.getCpf());
            pst.setString(2, order.getNome());
            pst.setString(3, order.getEndereco());
            pst.setString(4, order.getTelefone());
            pst.setString(5, order.getCpf());

           return pst.executeUpdate();

            pst.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }


    }
    
    int deleteOrder(String id){
        String sql = "Delete from Order WHERE CPF = ?;";
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
