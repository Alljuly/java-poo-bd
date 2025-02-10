package br.edu.ifal.test.dao;

import br.edu.ifal.dao.ProductDao;
import br.edu.ifal.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestProductDao {
    private ProductDao productDao;

    @BeforeEach
    public void setup() {
        productDao = new ProductDao();
    }

    @Test
    public void testAddProduct() {
        Product newProduct = new Product("Smartphone Samsung S22 128gb", 3999, 63);

        int generatedId = productDao.addProduct(newProduct);
        Product productFromDb = productDao.getProductById(generatedId);

        assertNotNull(productFromDb);
        assertEquals(newProduct.getName(), productFromDb.getName());
        assertEquals(newProduct.getUnitPrice(), productFromDb.getUnitPrice());
        assertEquals(newProduct.getQuantity(), productFromDb.getQuantity());
    }

    @Test
    public void testUpdateProduct() {
        Product newProduct = new Product("Smartphone Samsung S22 128gb", 3999, 63);
        int generatedId = productDao.addProduct(newProduct);

        Product updatedProduct = new Product(generatedId, "SMARTPHONE SAMSUNG S22 128GB", 3999, 46);
        int updateResult = productDao.updateProduct(updatedProduct);

        assertEquals(1, updateResult);
        Product productFromDb = productDao.getProductById(generatedId);
        assertNotNull(productFromDb);
        assertEquals(updatedProduct.getName(), productFromDb.getName());
        assertEquals(updatedProduct.getUnitPrice(), productFromDb.getUnitPrice());
        assertEquals(updatedProduct.getQuantity(), productFromDb.getQuantity());
    }

    @Test
    public void testDeleteProduct() {
        Product newProduct = new Product("Smartphone Samsung S22 128gb", 3999, 63);
        int generatedId = productDao.addProduct(newProduct);

        int deleteResult = productDao.deleteProduct(generatedId);

        assertEquals(1, deleteResult);
        Product deletedProduct = productDao.getProductById(generatedId);
        assertNull(deletedProduct);
    }
}
