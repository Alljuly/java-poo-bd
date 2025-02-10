package br.edu.ifal.service;

import br.edu.ifal.dao.ClientDao;
import br.edu.ifal.domain.Client;

import java.sql.SQLException;

public class ClientService {
    public static final String CPF_NOT_FOUND = "CPF não encontrado";
    public static final String CPF_ALREADY_REGISTERED = "CPF Já Cadastrado";
    public static final String MISSING_REQUIRED_FIELDS = "Todos os campos obrigatórios precisam ser preenchidos";
    public static final String INVALID_CPF = "CPF inválido. Verifique e tente novamente.";
    public static final String INTERNAL_ERROR = "Erro interno ao processar requisição";
    public static final String ASSOCIATED_ORDERS_ERROR = "Não foi possível apagar esse cliente pois existem pedidos associados.";

    private final ClientDao clientDao = new ClientDao();
    private final OrderService orderService;

    public ClientService() {
        this.orderService = new OrderService();
    }



    public boolean doesClientExist(String cpf) throws SQLException {
        validateCpf(cpf, INVALID_CPF);
        return clientDao.isCpfExisting(cpf);
    }

    public String addClient(Client client) {
        if (isMissingRequiredFields(client)) {
            return MISSING_REQUIRED_FIELDS;
        }

        try {
            if (doesClientExist(client.getCpf())) {
                return CPF_ALREADY_REGISTERED;
            }

            boolean isAdded = clientDao.addClient(client);
            return isAdded ? "Cliente adicionado com sucesso" : "Não foi possível adicionar esse cliente, verifique sua conexão.";
        } catch (Exception e) {
            e.printStackTrace();
            return INTERNAL_ERROR;
        }
    }

    public String updateClient(Client client) {
        if (isMissingRequiredFields(client)) {
            return MISSING_REQUIRED_FIELDS;
        }

        try {
            if (!doesClientExist(client.getCpf())) {
                return CPF_NOT_FOUND;
            }

            int rowsUpdated = clientDao.updateClient(client);
            return rowsUpdated == 1 ? "Cliente atualizado com sucesso." : "Algo deu errado, tente novamente.";
        } catch (Exception e) {
            e.printStackTrace();
            return INTERNAL_ERROR;
        }
    }

    public String deleteClient(String cpf) throws SQLException {
        validateCpf(cpf, "Verifique o CPF novamente");

        if (!doesClientExist(cpf)) {
            return CPF_NOT_FOUND;
        }

        try {
            if (!orderService.hasOrdersForClient(cpf)) {
                Client clientToDelete = clientDao.getClientById(cpf);

                if (clientToDelete != null) {
                    int rowsDeleted = clientDao.deleteClient(cpf);
                    return rowsDeleted == 1 ? "Cliente " + clientToDelete + " removido." : "Algo deu errado, tente novamente.";
                }
                return "Algo deu errado, tente novamente";
            }
            return ASSOCIATED_ORDERS_ERROR;
        } catch (Exception e) {
            e.printStackTrace();
            return INTERNAL_ERROR;
        }
    }

    public Client getClient(String cpf) {
        try {
            validateCpf(cpf, "Verifique o CPF novamente");

            if (!clientDao.isCpfExisting(cpf)) {
                throw new IllegalArgumentException(CPF_NOT_FOUND);
            }

            return clientDao.getClientById(cpf);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean isMissingRequiredFields(Client client) {
        return client.getCpf().isEmpty() || client.getName().isEmpty() || client.getContact().isEmpty();
    }

    private void validateCpf(String cpf, String errorMessage) {
        if (cpf == null || cpf.isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}