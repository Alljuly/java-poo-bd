package br.edu.ifal.dao;

import br.edu.ifal.db.ConnectionHelper;
import br.edu.ifal.domain.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDao {

    List<Client> getAllClients(){
        String sql = "SELECT * FROM Client;";

        List<Client> lista = new ArrayList<>();
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String cpf = rs.getString("CPF");
                String nome = rs.getString("NOME");
                String endereco = rs.getString("ENDERECO");
                String telefone = rs.getString("TELEFONE");

                Client Client = new Client(cpf, nome, endereco, telefone);
                lista.add(Client);
            }

            pst.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    Client getClientById(String id){
        String sql = "SELECT * FROM Client c WHERE c.cpf = ?;";
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setString(1, id);

            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){
                return new Client(
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

    void addClient(Client client){
         String sql = "INSERT INTO Client VALUES (?,?,?,?);";

        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setString(1, client.getCpf());
            pst.setString(2, client.getName());
            pst.setString(3, client.getAddress());
            pst.setString(4, client.getContact());

            pst.execute();

            pst.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    int updateClient(Client client){
        String sql = "UPDATE Client SET CPF = ?, NOME = ?, ENDERECO = ?, TELEFONE = ? WHERE CPF = ?;";
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setString(1, client.getCpf());
            pst.setString(2, client.getName());
            pst.setString(3, client.getAddress());
            pst.setString(4, client.getContact());
            pst.setString(5, client.getCpf());

           return pst.executeUpdate();

            pst.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }


    }
    
    int deleteClient(String id){
        String sql = "Delete from Client WHERE CPF = ?;";
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
