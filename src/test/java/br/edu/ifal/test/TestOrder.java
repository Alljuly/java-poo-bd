package br.edu.ifal.test;

import br.edu.ifal.dao.OrderDao;
import br.edu.ifal.domain.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestOrder{
    OrderDao orderDao;

    @BeforeEach
    public void setup(){
       this.orderDao = new OrderDao();
    }

   @Test
    public void testAddNewOrder() {
        Order newOrder = new Order("12312312315", "12365897412", 1256);

        int resID = orderDao.addOrder(newOrder);
        Order o = orderDao.getOrderById(resID);
        assertTrue(orderDao.getAllOrders().stream().anyMatch(order -> {
            return order.getId() == o.getId();
        }));
   }

   @Test
    public void testDeleteOrder(){
        int id = 5;
        Order order = orderDao.getOrderById(id);
        int res = orderDao.deleteOrder(id);
       if(order != null){
          assertEquals(1, res);
          System.out.println("Uma linha afetada");
          Order deleteOrder = orderDao.getOrderById(id);
          assertNull(deleteOrder);
       }
       else {
           System.out.println("Nenhuma linha afetada");
           assertEquals(0, res);
       }

   }

   @Test
    public void testUpdateOrder(){
        int id = 7;
        Order order = orderDao.getOrderById(id);
       if(order != null){
           Order newOrder = new Order(id,"12312312315", "12365897412", 265);
           int res = orderDao.updateOrder(newOrder);
           System.out.println("Uma linha afetada");
           assertEquals(1, res);
       } else {
           System.out.println("Nenhum resultado para esse id");
       }
   }
}
