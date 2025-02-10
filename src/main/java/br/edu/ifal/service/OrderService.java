package br.edu.ifal.service;

import br.edu.ifal.domain.Order;
import br.edu.ifal.dao.OrderDao;
import br.edu.ifal.service.Client;
import br.edu.ifal.service.Employee;
import br.edu.ifal.service.ProductOrderService;

import java.util.ArrayList;
import java.util.List;

public class OrderService{
    OrderDao orderDao = new OrderDao();
    ClientService clientService = new ClientService();
    EmployeeService employeeService = new EmployeeService();
    ProductOrderService productOrderService = new ProductOrderService();

    public OrderService(){

    }

    public boolean orderExists(int id){
        if(id >= 0){
            return orderDao.isOrderExists(id);
        }
        throw new IllegalArgumentException("ID inválido. Verifique e tente novamente.");
    }

    public String addNewOrder(Order order){
        if(order.getClientCpf().isEmpty || order.getEmployeeCpf().isEmpty() || (order.getTotalValue() <= 0)){
            return "Todos os campos obrigatórios precisam ser preenchidos";
        }

        try {
            String client = order.getClientCpf();
            String employee = order.getEmployeeCpf();

            if(!employeeService.employeeExists(employee) || !clientService.clientExists(client)){
                return "Verifique os dados do cliente e do funcionário";
            }

            int res = orderDao.addOrder(order);
            return (res != -1) ? "Pedido nº " + res + " adicionado." : "Algo deu errado, tente novamente em alguns minutos.";

        } catch (Exception e) {
            e.printStackTrace();
            return "Falha interna ao processar requisição";
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

    public List<Order> getOrders(){
        try {
            ArrayList<Order> orders = new ArrayList<>();
            orders = orderDao.getAllOrders();
            if(orders != null){
                return orders;
            }
            return null;
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

    public String deleteOrder(int id){
        if(id < 0){
            return "Verifique as informações";
        }
        try {
            if(!productOrderService.hasProductOrdersForOrder(id)){
                Order deleted = getOrder(id);
                if(deleted != null){
                    int res = orderDao.deleteOrder(id);
                    return res == 1 ? "Pedido nº " + deleted.toString() + "removido." : "Algo deu errado.";
                }
                return "Pedido não localizado.";
            }
            return "Pedido não pode ser apagado pois possue itens associados.";

        } catch (Exception e) {
            e.printStackTrace();
            return "Erro interno ao processar requisição.";
        }
    }

    public boolean isProductExists(int id){
        try{
            return orderDao.productExistsForOrder(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
    }
}