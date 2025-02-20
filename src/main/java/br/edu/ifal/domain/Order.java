package br.edu.ifal.domain;

import br.edu.ifal.service.ProductService;

public class Order{
    private int id = 0;
    private String clientCpf;
    private String employeeCpf;
    private double totalValue;

    public Order(int id, String clientCpf, String employeeCpf, double totalValue) {
        this.id = id;
        this.clientCpf = clientCpf;
        this.employeeCpf = employeeCpf;
        this.totalValue = totalValue;
    }

    public Order(String cliente, String vendedor, int idProdutoVenda, int quantity) {
        this.clientCpf = cliente;
        this.employeeCpf = vendedor;
        ProductService productService = new ProductService();
        this.totalValue = productService.getValue(idProdutoVenda) * quantity;
    }


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClientCpf() {
        return clientCpf;
    }

    public void setClientCpf(String clientCpf) {
        this.clientCpf = clientCpf;
    }

    public String getEmployeeCpf() {
        return employeeCpf;
    }

    public void setEmployeeCpf(String employeeCpf) {
        this.employeeCpf = employeeCpf;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", cliente=" + clientCpf +
                ", vendedor=" + employeeCpf +
                ", valor total=" + totalValue +
                '}';
    }
}