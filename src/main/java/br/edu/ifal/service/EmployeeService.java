package br.edu.ifal.service;

import br.edu.ifal.dao.EmployeeDao;
import br.edu.ifal.domain.Employee;

import java.sql.SQLException;


public class EmployeeService{
    public EmployeeDao employeeDao = new EmployeeDao();


    public EmployeeService(){

    }

    public String addNewEmployee(Employee employee) {
        String validationResponse = validateEmployeeFields(employee);
        if (!validationResponse.isEmpty()) {
            return validationResponse;
        }

        try {
            if (doesEmployeeExists(employee.getCpf())) {
                return "CPF já cadastrado.";
            }

            boolean isAdded = employeeDao.addEmployee(employee);
            return isAdded ? "Funcionário adicionado com sucesso." : "Não foi possível adicionar o funcionário, verifique a conexão e tente novamente mais tarde.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro interno ao processar requisição.";
        }
    }

    public String updateEmployee(Employee employee) {
        String validationResponse = validateEmployeeFields(employee);
        if (!validationResponse.isEmpty()) {
            return validationResponse;
        }

        try {
            if (!doesEmployeeExists(employee.getCpf())) {
                return "CPF não encontrado.";
            }

            int result = employeeDao.updateEmployee(employee);
            return result == 1 ? "Funcionário atualizado com sucesso." : "Algo deu errado, tente novamente.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro interno ao processar requisição.";
        }
    }

    public String deleteEmployee(String cpf) {
        OrderService orderService = new OrderService();
        try {
            if (cpf.isEmpty() || !doesEmployeeExists(cpf)) {
                return "Verifique o CPF novamente.";
            }
            if (orderService.hasOrdersForEmployee(cpf)) {
                return "Não foi possível apagar o funcionário, pois há pedidos associados.";
            }

            Employee employeeToDelete = employeeDao.getEmployeeById(cpf);
            if (employeeToDelete != null) {
                int result = employeeDao.deleteEmployee(cpf);
                return result == 1 ? "Funcionário " + employeeToDelete.toString() + " removido com sucesso." : "Algo deu errado, tente novamente.";
            }
            return "Algo deu errado, tente novamente.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro interno ao processar requisição.";
        }
    }

    public Employee getEmployee(String cpf) throws SQLException {
        if (cpf.isEmpty() || !doesEmployeeExists(cpf)) {
            throw new IllegalArgumentException("CPF inválido. Verifique e tente novamente.");
        }

        try {
            return employeeDao.getEmployeeById(cpf);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean doesEmployeeExists(String cpf) {
        if (!cpf.isEmpty()) {
            try {
                return employeeDao.isCpfExisting(cpf);
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao verificar se o CPF existe: " + e.getMessage());
            }
        }
        throw new IllegalArgumentException("CPF inválido. Verifique e tente novamente.");
    }

    private String validateEmployeeFields(Employee employee) {
        if (employee.getCpf().isEmpty() || employee.getName().isEmpty() || employee.getContact().isEmpty()) {
            return "Todos os campos obrigatórios precisam ser preenchidos.";
        }
        return "";
    }
}