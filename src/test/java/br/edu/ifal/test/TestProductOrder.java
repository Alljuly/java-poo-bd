package br.edu.ifal.test;

import br.edu.ifal.dao.ProductOrderDao;
import br.edu.ifal.domain.ProductOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TestProductOrder{
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

}