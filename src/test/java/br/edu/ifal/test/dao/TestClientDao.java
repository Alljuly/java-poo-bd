package br.edu.ifal.test.dao;

import br.edu.ifal.dao.ClientDao;
import br.edu.ifal.domain.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class TestClientDao {

    private ClientDao clientDao;

    @BeforeEach
    public void setup() {
        clientDao = new ClientDao();
    }

    @Test
    public void testAddClient() throws SQLException {
        Client newClient = new Client("12312312315", "Cliente Teste", "Endereco", "123451231");

        if (!clientDao.isCpfExisting("12312312315")) {
            boolean added = clientDao.addClient(newClient);
            assertTrue(added);
        } else {
            assertThrows(RuntimeException.class, () -> clientDao.addClient(newClient));
        }

        assertTrue(clientDao.isCpfExisting("12312312315"));
    }

    @Test
    public void testUpdateClient() throws SQLException {
        String cpf = "12312312315";
        if (!clientDao.isCpfExisting(cpf)) {
            clientDao.addClient(new Client(cpf, "Cliente Teste", "Endereco", "123451231"));
        }

        Client update = new Client(cpf, "Teste da Silva", "Rua das Flores", "123451231");
        int updatedRows = clientDao.updateClient(update);
        assertEquals(1, updatedRows);

        Client updatedClient = clientDao.getClientById(cpf);
        assertNotNull(updatedClient);
        assertEquals("Teste da Silva", updatedClient.getName());
        assertEquals("Rua das Flores", updatedClient.getAddress());
    }

    @Test
    public void testDeleteClient() throws SQLException {
        String cpf = "56879845612";
        Client client = new Client(cpf, "José Silva", "Rua das Flores", "123456531");

        if (!clientDao.isCpfExisting(cpf)) {
            clientDao.addClient(client);
        }

        int deletedRows = clientDao.deleteClient(cpf);
        assertEquals(1, deletedRows);
        assertFalse(clientDao.isCpfExisting(cpf));
    }
}
