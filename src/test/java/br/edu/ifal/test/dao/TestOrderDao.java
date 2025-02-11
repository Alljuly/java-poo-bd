//package br.edu.ifal.test.dao;
//
//import br.edu.ifal.dao.OrderDao;
//import br.edu.ifal.domain.Order;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class TestOrderDao {
//    private OrderDao orderDao;
//
//    @BeforeEach
//    public void setup() {
//        this.orderDao = new OrderDao();
//    }
//
//    @Test
//    public void testAddNewOrder() {
//        Order newOrder = new Order("04881132105", "12365897412", 1256);
//
//        int generatedId = orderDao.addOrder(newOrder);
//        Order orderFromDb = orderDao.getOrderById(generatedId);
//
//        assertNotNull(orderFromDb);
//        assertEquals(newOrder.getClientCpf(), orderFromDb.getClientCpf());
//        assertEquals(newOrder.getEmployeeCpf(), orderFromDb.getEmployeeCpf());
//        assertEquals(newOrder.getTotalValue(), orderFromDb.getTotalValue());
//    }
//
//    @Test
//    public void testDeleteOrder() {
//        Order newOrder = new Order("04881132105", "12365897412", 1256);
//        int orderId = orderDao.addOrder(newOrder);
//
//        int deleteResult = orderDao.deleteOrder(orderId);
//
//        assertEquals(1, deleteResult);
//        Order deletedOrder = orderDao.getOrderById(orderId);
//        assertNull(deletedOrder);
//    }
//
//    @Test
//    public void testUpdateOrder() {
//        Order newOrder = new Order("04881132105", "12365897412", 1256);
//        int orderId = orderDao.addOrder(newOrder);
//        Order orderFromDb = orderDao.getOrderById(orderId);
//
//        if (orderFromDb != null) {
//            Order updatedOrder = new Order(orderId, "04881132105", "24080075693", 265);
//            int updateResult = orderDao.updateOrder(updatedOrder);
//
//            assertEquals(1, updateResult);
//            Order updatedOrderFromDb = orderDao.getOrderById(orderId);
//            assertNotNull(updatedOrderFromDb);
//            assertEquals(updatedOrder.getTotalValue(), updatedOrderFromDb.getTotalValue());
//        } else {
//            fail("Order not found for update.");
//        }
//    }
//}
