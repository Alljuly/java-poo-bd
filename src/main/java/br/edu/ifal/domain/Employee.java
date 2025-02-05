package br.edu.ifal.domain;

public class Employee {
    private String cpf;
    private String name;
    private String address;
    private String contact;

    public Employee(String cpf, String name, String address, String contact) {
        this.cpf = cpf;
        this.name = name;
        this.address = address;
        this.contact = contact;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getcontact() {
        return contact;
    }

    public void setcontact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Employee {" +
                "cpf='" + cpf + '\'' +
                ", Name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}
