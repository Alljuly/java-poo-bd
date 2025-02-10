package br.edu.ifal.test.dao;

import br.edu.ifal.dao.ProductOrderDao;
import br.edu.ifal.domain.ProductOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestProductOrderDao {
    private ProductOrderDao productOrderDao;

    @BeforeEach
    public void setup() {
        productOrderDao = new ProductOrderDao();
    }

    @Test
    public void testAddProductOrder() {
        ProductOrder newProductOrder = new ProductOrder(3, 5, 2, 7398);

        int res = productOrderDao.addProductOrder(newProductOrder);

        assertTrue(res > 0);
    }

    @Test
    public void testUpdateProductOrder() {
        int id = 16;
        ProductOrder existingOrder = productOrderDao.getProductOrderById(id);

        if (existingOrder != null) {
            ProductOrder updatedOrder = new ProductOrder(id, 4, 6, 8, 4569631);

            // Act
            int res = productOrderDao.updateProductOrder(updatedOrder);

            // Assert
            assertEquals(1, res);
        }
    }

    @Test
    public void testDeleteProductOrder() {
        int id = 19;
        ProductOrder existingOrder = productOrderDao.getProductOrderById(id);

        if (existingOrder != null) {
            int res = productOrderDao.deleteProductOrder(id);

            assertEquals(1, res);
            ProductOrder deletedOrder = productOrderDao.getProductOrderById(id);
            assertNull(deletedOrder);
        } else {
            assertEquals(0, 0);
        }
    }
}
