package br.edu.ifal.service;

import br.edu.ifal.domain.Order;
import br.edu.ifal.dao.OrderDao;

import java.util.ArrayList;
import java.util.List;

public class OrderService{
    private final OrderDao orderDao = new OrderDao();

    public OrderService() {

    }

    public boolean orderExists(int id){
        if(id >= 0){
            return orderDao.isOrderExists(id);
        }
        throw new IllegalArgumentException("ID inválido. Verifique e tente novamente.");
    }

    public int addNewOrder(Order order){
        if(order.getClientCpf().isEmpty() || order.getEmployeeCpf().isEmpty() || (order.getTotalValue() <= 0)){
            System.out.println("Todos os campos obrigatórios precisam ser preenchidos");
            return -1;
        }
        ClientService clientService = new ClientService();;
        EmployeeService employeeService = new EmployeeService();
        try {
            int res = orderDao.addOrder(order);
            System.out.println((res != -1) ? "Pedido nº " + res + " adicionado." : "Algo deu errado, tente novamente em alguns minutos.");
            return res;
        } catch (Exception e) {
            e.printStackTrace();
             System.out.println("Falha interna ao processar requisição");
             return -1;
        }
    }

    public Order getOrder(int id){
        try {
            if(orderExists(id)){
            Order res = orderDao.getOrderById(id);
            return res;
            } 
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Order> getAllOrders(){
        try {

            return orderDao.getAllOrders();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String updateOrder(Order update){
        if((update.getId() >= 0)|| !update.getClientCpf().isEmpty() || !update.getEmployeeCpf().isEmpty() || (update.getTotalValue() >= 0)){
            return "Todos os campos obrigatórios precisam ser preenchidos.";
        }

        try {
            int id = update.getId();
            if(orderExists(id)){
                int res = orderDao.updateOrder(update);
                return res > 0 ? "Pedido atualizado." : "Algo deu errado, tente novamente mais tarde.";
            }
            return "Pedido não localizado";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro interno ao processar requisição.";
        }

    }

    public String deleteOrder(int id) {
        if (id < 0) {
            return "Verifique as informações.";
        }
        ProductOrderService productOrderService = new ProductOrderService();

        try {
            if (productOrderService.hasProductOrdersForOrder(id)) {
                return "Pedido não pode ser apagado, pois possui itens associados.";
            }

            Order orderToDelete = getOrder(id);
            if (orderToDelete == null) {
                return "Pedido não localizado.";
            }

            int result = orderDao.deleteOrder(id);
            return result == 1 ? "Pedido nº " + orderToDelete.toString() + " removido com sucesso." : "Algo deu errado.";

        } catch (Exception e) {
            e.printStackTrace();
            return "Erro interno ao processar requisição.";
        }
    }

    public boolean hasOrdersForEmployee(String cpf){
        try{
            return orderDao.hasOrdersForEmployee(cpf);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
    }


    public boolean hasOrdersForClient(String cpf){
        try{
        return orderDao.hasOrdersForClient(cpf);
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}