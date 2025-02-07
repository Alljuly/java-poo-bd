package br.edu.ifal.test;

import br.edu.ifal.dao.ProductDao;
import br.edu.ifal.domain.Order;
import br.edu.ifal.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class TestProduct{
    ProductDao productDao;

    @BeforeEach
    public void setup() {
        productDao = new ProductDao();
    }

    @Test
    public void testAddProduct(){
        Product newProduct = new Product("Smartphone Samsung S22 128gb", 3999, 63);

        int resID = productDao.addProduct(newProduct);

        Product product = productDao.getProductById(resID);

        assertTrue(productDao.getAllProducts().stream().anyMatch(p -> {
            return product.getId() == p.getId();
        }));

    }

    @Test
    public void testDeleteProduct(){
        int id = 17;

        Product product = productDao.getProductById(id);
        int res = productDao.deleteProduct(id);

        if(product != null){
            assertEquals(1, res);
            System.out.println("Uma linha afetada");
            Product deleteProduct = productDao.getProductById(id);
            assertNull(deleteProduct);
        }
        else {
            System.out.println("Nenhuma linha afetada");
            assertEquals(0, res);
        }
    }

    @Test
    public void testUpdateProduct(){
        int id = 17;
        Product product = productDao.getProductById(id);
        if(product != null){
            Product newProduct = new Product(id,"SMARTPHONE SAMSUNG S22 128GB", 3999, 46);
            int res = productDao.updateProduct(newProduct);
            System.out.println("Uma linha afetada");
            assertEquals(1, res);
        } else {
            System.out.println("Nenhum resultado para esse id");
        }
    }
}