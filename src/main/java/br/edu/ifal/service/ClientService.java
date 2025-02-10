package br.edu.ifal.service;

import br.edu.ifal.dao.ClientDao;
import br.edu.ifal.service.OrderService;

import br.edu.ifal.domain.Client;

public class ClientService{
    public ClientDao clientDao = new ClientDao();
    private OrderService orderService = new OrderService();

    public ClientService(){

    }

       public boolean clientExists(String cpf){
        if(!cpf.isEmpty()){
            return clientDao.isCpfExisting(cpf);
        }
        throw new IllegalArgumentException("CPF inválido. Verifique e tente novamente.");
    }

    public String addNewClient(Client c){
        if(c.getCpf().isEmpty() || c.getName().isEmpty() || c.getContact().isEmpty()){
            return "Todos os campos obrigatórios precisam ser preenchidos";
        }
            try {
                if(clientExists(c.getCpf())){
                        return "CPF Já Cadastrado";
                }
                boolean res = clientDao.addClient(c);
                return res ? "Cliente adicionado" : "Não foi possível adicionar esse cliente, verifique sua conexão e tente novamente mais tarde";
            } catch (Exception e) {
                e.printStackTrace();
                return "Erro interno ao processar requisição";
            }
    }

    public String updateClient(Client c){
        if(c.getCpf().isEmpty() || c.getName().isEmpty() || c.getContact().isEmpty()){
            return "Todos os campos obrigatórios precisam ser preenchidos";
        }

        try {
            if(!clientExists(c.getCpf())){
                return "CPF não encontrado";
            }
            int res = clientDao.updateClient(c);

            return (res == 1) ? "Cliente atualizado com sucesso." : "Algo deu errado, tente novamente.";
            
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro interno ao processar requisição";
        }
    }

    public String deleteClient(String cpf){
        if(cpf.isEmpty()){
            return "Verifique o CPF novamente";
        }
        if (!clientExists(cpf)) {
            return "CPF não encontrado";
        }
        
        try {
            if(!orderDao.hasOrdersForClient(cpf)){ 
                Client delete = clientDao.getClientById(cpf);
                if(delete instanceof Client){
                    int res = clientDao.deleteClient(cpf);
                    return res == 1 ? "Cliente " + delete.toString() + " removido." : "Algo deu errado, tente novamente.";
                }
            return "Algo deu errado, tente novamente";
            }
            return "Não foi possível apagar esse cliente pois existem pedidos associados.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro interno ao processar requisição";
       }

    }

    public Client getClient(String cpf){
        try {
            if(cpf.isEmpty() || !clientDao.isCpfExistente(cpf)){
                throw new IllegalArgumentException("Verifique o CPF novamente");
            }

            Client res = clientDao.getClientById(cpf);
            return res;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}