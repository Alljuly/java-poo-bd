package br.edu.ifal.dao;

import br.edu.ifal.db.ConnectionHelper;
import br.edu.ifal.domain.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {

    public List<Employee> getAllEmployees(){
        String sql = "SELECT * FROM Funcionario;";

        List<Employee> lista = new ArrayList<>();
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String cpf = rs.getString("CPF");
                String name = rs.getString("NOME");
                String address = rs.getString("ENDERECO");
                String contact = rs.getString("TELEFONE");

                Employee Employee = new Employee(cpf, name, address, contact);
                lista.add(Employee);
            }

            pst.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public Employee getEmployeeById(String id){
        String sql = "SELECT * FROM Funcionario f WHERE f.cpf = ?;";
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setString(1, id);

            ResultSet rs = pst.executeQuery();

            if(rs.next()){
                return new Employee(
                    rs.getString("CPF"),
                    rs.getString("NOME"),
                    rs.getString("ENDERECO"),
                    rs.getString("TELEFONE")
            );
            }
            pst.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public void addEmployee(Employee employee) throws SQLException {
         String sql = "INSERT INTO Funcionario VALUES (?,?,?,?);";

        if(!isCpfExistente(employee.getCpf())){
            try {
                Connection connection = ConnectionHelper.getConnection();
                PreparedStatement pst = connection.prepareStatement(sql);

                pst.setString(1, employee.getCpf());
                pst.setString(2, employee.getName());
                pst.setString(3, employee.getAddress());
                pst.setString(4, employee.getContact());

                pst.execute();

                pst.close();
                connection.close();

            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    
    public int updateEmployee(Employee employee){
        String sql = "UPDATE Funcionario SET CPF = ?, NOME = ?, ENDERECO = ?, TELEFONE = ? WHERE CPF = ?;";
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setString(1, employee.getCpf());
            pst.setString(2, employee.getName());
            pst.setString(3, employee.getAddress());
            pst.setString(4, employee.getContact());
            pst.setString(5, employee.getCpf());

            int res = pst.executeUpdate();

            pst.close();
            connection.close();
            return res;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }


    }
    
    public int deleteEmployee(String id){
        String sql = "Delete from Funcionario WHERE CPF = ?;";
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setString(1, id);
            int res = pst.executeUpdate();

            pst.close();
            connection.close();
            return res;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean isCpfExistente(String cpf) throws SQLException {
        String query = "SELECT COUNT(*) FROM funcionario WHERE cpf = ?";
        try (Connection conn = ConnectionHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, cpf);
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
