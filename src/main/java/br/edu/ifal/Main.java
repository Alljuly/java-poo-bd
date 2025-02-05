package br.edu.ifal;

import br.edu.ifal.db.ConnectionHelper;
import br.edu.ifal.domain.Client;
import br.edu.ifal.dao.ClientDao;
import src.main.java.br.edu.ifal.dao.ProductDao;
import src.main.java.br.edu.ifal.dao.EmployeeDao;
import src.main.java.br.edu.ifal.dao.ProductOrderDao;
import src.main.java.br.edu.ifal.dao.Order;



import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ClientDao clientDao = new ClientDao();
        EmployeeDao employeeDao = new EmployeeDao();
        ProductDao productDao = new ProductDao();
        OrderDao orderDao = new OrderDao();
        ProductOrderDao productOrderDao = new ProductOrderDao();

        try {
            TestClient testeClient = new TesteClient(clientDao);
            TestEmployee testeEmployee = new TesteClient(employeeDao);
            TestProduct testeProduct = new TesteClient(productDao);
            TestOrder testeOrder = new TesteClient(orderDao);
            TestProductOrder testeProductOrder = new TesteClient(productOrderDao);

            testeClient.teste();
            testeEmployee.teste();
            testeProduct.teste();
            testeOrder.teste();
            testeProductOrder.teste();

            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
