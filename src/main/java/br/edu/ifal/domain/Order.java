public class Order{
    private int id;
    private String clientCpf;
    private String employeeCpf;
    private double totalValue;

    public Pedido(int id, String clientCpf, String employeeCpf, double totalValue) {
        this.id = id;
        this.clientCpf = clientCpf;
        this.employeeCpf = employeeCpf;
        this.totalValue = totalValue;
    }

    // Getters and Setters
    public int getId() {
        return id;
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
}