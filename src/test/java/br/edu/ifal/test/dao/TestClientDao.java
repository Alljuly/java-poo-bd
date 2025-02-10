package br.edu.ifal.test;

import br.edu.ifal.dao.ClientDao;
import br.edu.ifal.domain.Client;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestClientDao {

    private ClientDao clientDao;

    @BeforeEach
    public void setup(){
        clientDao = new ClientDao();
    }

   @Test
   public void testAddClient() throws SQLException {
       Client newClient = new Client("12312312315", "Cliente Teste", "Endereco", "123451231");
       assertDoesNotThrow(() -> clientDao.addClient(newClient));
       List<Client> listClients = clientDao.getAllClients();
       assertTrue(listClients.stream().anyMatch(client -> client.getCpf().equals("12312312315")));
   }
   @Test
   public void testUpdateClient() throws SQLException {
       Client update = new Client("12312312315", "Teste da Silva", "Rua das Flores", "123451231");
       clientDao.updateClient(update);
       Client updatedClient = clientDao.getClientById("12312312315");
       assertEquals("Teste da Silva", updatedClient.getName());
       assertEquals("Rua das Flores", updatedClient.getAddress());
   }
   @Test
   public void testDeleteClient() throws SQLException {
       String cpf = "56879845612";
       Client e = new Client(cpf,
               "José Silva",
               "Rua das Flores",
               "123456531");

       clientDao.addClient(e);

       int deletedClient = clientDao.deleteClient(cpf);
       assertEquals(1,deletedClient);
       Client resID = clientDao.getClientById(cpf);
       assertNull(resID);
   }}
