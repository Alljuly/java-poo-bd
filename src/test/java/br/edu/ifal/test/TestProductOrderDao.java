package br.edu.ifal.test;

import br.edu.ifal.dao.ProductOrderDao;
import static org.junit.jupiter.api.Assertions.*;

import br.edu.ifal.domain.ProductOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TestProductOrderDao {
    ProductOrderDao productOrderDao;

    @BeforeEach
    public void setup(){
        productOrderDao = new ProductOrderDao();
    }

    @Test
    public void testAddProductOrder(){
        ProductOrder newProductOrder = new ProductOrder(3,5,2, 7398);

        int res = productOrderDao.addProductOrder(newProductOrder);
    }

    @Test
    public void testUpdateProductOrder(){
        int id = 16;

        ProductOrder p = productOrderDao.getOrderById(id);

        if(p != null){
            ProductOrder update = new ProductOrder(id,4,6,8,4569631);
            int res = productOrderDao.updateOrder(update);
            System.out.println(res + " Linha Afetada");
            assertEquals(1, res);

        }
    }

    @Test
    public void testDeleteProductOrder(){
        int id = 17;

        ProductOrder p = productOrderDao.getOrderById(id);
        int res = 0;
        if(p != null){
            res = productOrderDao.deleteOrder(id);
            ProductOrder deleteProductOrder = productOrderDao.getOrderById(id);
            assertNull(deleteProductOrder);
        }
        else {
            System.out.println("Nenhuma linha afetada");
            assertEquals(0, res);
        }
    }

}