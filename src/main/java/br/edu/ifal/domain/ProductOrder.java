package br.edu.ifal.domain;

public class ProductOrder{
    private int id;
    private int orderId;
    private int productId;
    private int quantity;
    private double value;

    public ProductOrder(int id, int orderId, int productId, int quantity, double value) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.value = value;
    }

    public ProductOrder(int orderId, int productId, int quantity, double value) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.value = value;
    }
    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}