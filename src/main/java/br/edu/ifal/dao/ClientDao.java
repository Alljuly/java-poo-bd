package br.edu.ifal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifal.db.ConnectionHelper;
import br.edu.ifal.domain.Client;

public class ClientDao {

    public List<Client> getAllClients(){
        String sql = "SELECT * FROM Cliente;";

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
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public Client getClientById(String id){
        String sql = "SELECT * FROM Cliente c WHERE c.cpf = ?;";
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

    public void addClient(Client client) throws SQLException {
         String sql = "INSERT INTO Cliente VALUES (?,?,?,?);";
        if(!isCpfExistente(client.getCpf())){
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
        }}
    }
    
    public int updateClient(Client client){
        String sql = "UPDATE Cliente SET CPF = ?, NOME = ?, ENDERECO = ?, TELEFONE = ? WHERE CPF = ?;";
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setString(1, client.getCpf());
            pst.setString(2, client.getName());
            pst.setString(3, client.getAddress());
            pst.setString(4, client.getContact());
            pst.setString(5, client.getCpf());

           int res = pst.executeUpdate();

            pst.close();
            connection.close();

            return res;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }


    }
    
    public int deleteClient(String id){
        String sql = "Delete from Cliente WHERE CPF = ?;";
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
        String query = "SELECT COUNT(*) FROM cliente WHERE cpf = ?";
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
