public class TesteClient{
    ClientDao clientDao ;
    public TesteClient(ClientDao c){
        this.clientDao = c;
    }

    void teste(){
        Client newClient = new Client("12312312315",
                     "José da Silva",
                     "Endereco",
                     "123451231");

            clientDao.addClient(newClient);

            List<Client> listClients = clientDao.getAllClients();
            for (Client c : listClients) {
                System.out.println(c);
            }

            Client update = new Client("12312312315",
                     "José Silva",
                     "Rua das Flores",
                     "123451231");

            clientDao.updateClient(update);

            String cpf = "43185649168";

            clientDao.deleteClient(cpf);
    }
}