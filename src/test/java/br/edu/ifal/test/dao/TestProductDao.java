package br.edu.ifal.test;

import br.edu.ifal.dao.ProductDao;
import br.edu.ifal.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestProductDao {
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
    public void testUpdateProduct(){
        Product newProduct = new Product("Smartphone Samsung S22 128gb", 3999, 63);

        int resID = productDao.addProduct(newProduct);
        Product update = new Product(resID,"SMARTPHONE SAMSUNG S22 128GB", 3999, 46);
        int resUpdate = productDao.updateProduct(update);
        assertEquals(1, resUpdate);

    }

    @Test
    public void testDeleteProduct(){
        Product newProduct = new Product("Smartphone Samsung S22 128gb", 3999, 63);

        int resID = productDao.addProduct(newProduct);
        int res = productDao.deleteProduct(resID);
        assertEquals(1, res);
        Product deleteProduct = productDao.getProductById(resID);
        assertNull(deleteProduct);

    }

}