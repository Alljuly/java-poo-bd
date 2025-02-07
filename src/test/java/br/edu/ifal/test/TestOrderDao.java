package br.edu.ifal.test;

import br.edu.ifal.dao.OrderDao;
import br.edu.ifal.domain.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestOrderDao {
    OrderDao orderDao;

    @BeforeEach
    public void setup(){
        this.orderDao = new OrderDao();
    }

    @Test
    public void testAddNewOrder() {
        Order newOrder = new Order("04881132105", "12365897412", 1256);

        int resID = orderDao.addOrder(newOrder);
        Order o = orderDao.getOrderById(resID);
        assertTrue(orderDao.getAllOrders().stream().anyMatch(order -> {
            return order.getId() == o.getId();
        }));
    }

    @Test
    public void testDeleteOrder(){
        Order newOrder = new Order("04881132105", "12365897412", 1256);
        int res = orderDao.addOrder(newOrder);
        int deletedOrder = orderDao.deleteOrder(res);
        assertEquals(1, deletedOrder);
        Order deleteOrder = orderDao.getOrderById(res);
        assertNull(deleteOrder);
    }


@Test
public void testUpdateOrder(){
    Order newOrder = new Order("04881132105", "12365897412", 1256);
    int resID = orderDao.addOrder(newOrder);
    Order order = orderDao.getOrderById(resID);
    if(order != null){
        Order update = new Order(resID,"04881132105", "24080075693", 265);
        int res = orderDao.updateOrder(update);
        assertEquals(1, res);
    } else {
        System.out.println("Nenhum resultado para esse id");
    }
}
}
