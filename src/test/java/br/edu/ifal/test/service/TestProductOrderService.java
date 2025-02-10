package br.edu.ifal.test.service;

import br.edu.ifal.service.ProductOrderService;
import br.edu.ifal.domain.ProductOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestProductOrderService {

    private ProductOrderService productOrderService;

    @BeforeEach
    public void setup() {
        productOrderService = new ProductOrderService();
    }

    @Test
    public void testAddProductOrder_Success() {
        ProductOrder productOrder = new ProductOrder(1, 10, 2, 50.00);
        String response = productOrderService.addNewProductOrder(productOrder);
        assertEquals("Item adicionado ao pedido.", response);
    }

    @Test
    public void testAddProductOrder_InvalidOrderOrProduct() {
        ProductOrder productOrder = new ProductOrder(999, 888, 1, 30.00);
        String response = productOrderService.addNewProductOrder(productOrder);
        assertEquals("Pedido ou produto não encontrado.", response);
    }

    @Test
    public void testGetProductOrder_NotFound() {
        ProductOrder productOrder = productOrderService.getProductOrder(999);
        assertNull(productOrder);
    }

    @Test
    public void testDeleteProductOrder_Success() {
        ProductOrder productOrder = new ProductOrder(2, 20, 3, 75.00);
        productOrderService.addNewProductOrder(productOrder);
        String response = productOrderService.deleteProductOrder(2);
        assertEquals("Item do pedido removido com sucesso.", response);
    }
}
