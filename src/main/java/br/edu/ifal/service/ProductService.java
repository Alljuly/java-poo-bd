package br.edu.ifal.service;

import br.edu.ifal.dao.ProductDao;
import br.edu.ifal.domain.Product;

import java.util.List;

public class ProductService {
    private ProductDao productDao = new ProductDao();
    public ProductService() {}

    public String addNewProduct(Product product) {
        if (product == null || product.getName().isEmpty() || product.getUnitPrice() <= 0 || product.getQuantity() < 0) {
            return "Verifique as informações do produto.";
        }

        try {
            int id = productDao.addProduct(product);
            return id > 0 ? "Produto adicionado com sucesso." : "Não foi possível adicionar o produto.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro interno ao processar requisição.";
        }
    }

    public String updateProduct(Product product) {
        if (product == null || product.getId() <= 0 || product.getName().isEmpty() || product.getUnitPrice() <= 0 || product.getQuantity() < 0) {
            return "Verifique as informações do produto.";
        }

        try {
            int res = productDao.updateProduct(product);
            return res > 0 ? "Produto atualizado com sucesso." : "Produto não encontrado.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro interno ao processar requisição.";
        }
    }

    public String deleteProduct(int id) {
        if (id <= 0) {
            return "ID inválido. Verifique e tente novamente.";
        }
        ProductOrderService productOrderService = new ProductOrderService();


        try {
            if (!productDao.productExists(id)) {
                return "Produto não encontrado.";
            }

            if (productOrderService.productOrderExistsForProduct(id)) {
                return "Não é possível excluir o produto, pois ele está associado a um pedido.";
            }

            int res = productDao.deleteProduct(id);
            return res > 0 ? "Produto removido com sucesso." : "Algo deu errado, tente novamente.";

        } catch (Exception e) {
            e.printStackTrace();
            return "Erro interno ao processar requisição.";
        }
    }

    public boolean productExists(int id) {

            try {
                return productDao.productExists(id);
            } catch (Exception e) {
                e.printStackTrace();
                return false;

            }
    }
    public Product getProduct(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido. Verifique e tente novamente.");
        }

        try {
            return productDao.getProductById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Product> getAllProducts() {
        try {
            return productDao.getAllProducts();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
