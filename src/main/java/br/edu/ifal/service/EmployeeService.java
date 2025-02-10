package br.edu.ifal.service;

import br.edu.ifal.dao.EmployeeDao;
import br.edu.ifal.domain.Employee;
import br.edu.ifal.service.OrderService;


public class EmployeeService{
    public EmployeeDao employeeDao = new EmployeeDao();
    private OrderService orderService = new OrderService();

    public EmployeeService(){

    }

    public String addNewEmployee(Employee c){
        if(c.getCpf().isEmpty() || c.getName().isEmpty() || c.getContact().isEmpty()){
            return "Todos os campos obrigatórios precisam ser preenchidos";
        }
            try {
                if(employeeExists(c.getCpf())){
                        return "CPF Já Cadastrado";
                }
                boolean res = employeeDao.addEmployee(c);
                return res ? "Funcionario adicionado" : "Não foi possível adicionar esse funcionario, verifique sua conexão e tente novamente mais tarde";
            } catch (Exception e) {
                e.printStackTrace();
                return "Erro interno ao processar requisição";
            }
    }

    public String updateEmployee(Employee c){
        if(c.getCpf().isEmpty() || c.getName().isEmpty() || c.getContact().isEmpty()){
            return "Todos os campos obrigatórios precisam ser preenchidos";
        }

        try {
            if(!employeeExists(c.getCpf())){
                return "CPF não encontrado";
            }
            int res = employeeDao.updateEmployee(c);

            return (res == 1) ? "Funcionario atualizado com sucesso." : "Algo deu errado, tente novamente.";
            
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro interno ao processar requisição";
        }
    }

    public boolean employeeExists(String cpf){
        if(!cpf.isEmpty()){
            return employeeDao.isCpfExisting(cpf);
        }
        throw new IllegalArgumentException("CPF inválido. Verifique e tente novamente.");
    }

    public String deleteEmployee(String cpf){
        if(cpf.isEmpty() || !employeeExists(cpf)){
            return "Verifique o CPF novamente";
        }

       try {
            if(!orderService.hasOrdersForEmployee(cpf)){
                Employee delete = employeeDao.getEmployeeById(cpf);

                if(delete instanceof Employee){
                    int res = employeeDao.deleteEmployee(cpf);
                    return res == 1 ? "Funcionario " + delete.toString() + " removido." : "Algo deu errado, tente novamente.";
                }
                return "Algo deu errado, tente novamente";
            }
            return "Não foi possível apagar esse funcionário pois existem pedidos associados.";
       } catch (Exception e) {
        e.printStackTrace();
        return "Erro interno ao processar requisição";
       }

    }

    public Employee getEmployee(String cpf){
        if(cpf.isEmpty() || !employeeExists(cpf)){
            throw new IllegalArgumentException("CPF inválido. Verifique e tente novamente.");
        }
        try {
            Employee res = employeeDao.getEmployeeById(cpf);
            return res;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}