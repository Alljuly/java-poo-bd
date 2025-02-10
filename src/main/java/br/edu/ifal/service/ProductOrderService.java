package br.edu.ifal.service;

import br.edu.ifal.domain.ProductOrder;
import br.edu.ifal.dao.ProductOrderDao;
import br.edu.ifal.service.ClientService;
import br.edu.ifal.service.EmployeeService;

import java.util.ArrayList;
import java.util.List;



public class ProductOrderService{
    public ProductOrderDao productOrderDao = new ProductOrderDao();
    private OrderService orderService = new OrderService();
    private ProductService productService = new ProductService();


    public ProductOrderService(){

    }

    public String addNewProductOrder(ProductOrder po){
        if (po == null || po.getOrderId() <= 0 || po.getProductId() <= 0 || po.getValue() <= 0 || po.getQuantity() <= 0) {
            return "Verifique as informações.";
        }
    
        if (!orderService.orderExists(po.getOrderId()) || !productService.productExists(po.getProductId())) {
            return "Pedido ou produto não encontrado.";
        }
    
        try{
            int res = productOrderDao.addProductOrder(po);
            return res > 0 ? "Item adicionado ao pedido." : "Algo deu errado, tente novamente.";
            
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro interno ao processar requisição.";
        }
    }

    public boolean productOrderExistsForOrder(int id){
        if(id < 0){
            throw new IllegalArgumentException("ID inválido. Verifique e tente novamente.");
        }
        try {
            return orderService.isOrderExists(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } 
    }

    public boolean productOrderExistsForProduct(int id){
        if(id < 0){
            throw new IllegalArgumentException("ID inválido. Verifique e tente novamente.");
        }
        try {
            return productService.isProductExists(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } 
    }

    public ProductOrder getProductOrder(int id){
        if(productOrderExistsForOrder(id)){
            try {
                ProductOrder po = productOrderDao.getProductOrderById(id);
                return po;                
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public List<ProductOrder> getAllProductOrders(int id){
        if(productOrderExistsForOrder(id)){
            try {
                ArrayList<ProductOrder> po = productOrderDao.getAllOrders(id);
                return po;                
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public String deleteProductOrder(int id) {
        if (id <= 0) {
            return "ID inválido. Verifique e tente novamente.";
        }
    
        if (!productOrderExistsForOrder(id)) {
            return "O item do pedido não existe.";
        }
    
        try {
            int res = productOrderDao.deleteProductOrder(id);
            return res > 0 ? "Item do pedido removido com sucesso." : "Não foi possível remover o item do pedido.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro interno ao processar requisição.";
        }
    }


}