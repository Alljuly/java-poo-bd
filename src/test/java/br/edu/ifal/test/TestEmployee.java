package br.edu.ifal.test;

import br.edu.ifal.dao.EmployeeDao;
import br.edu.ifal.domain.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class TestEmployee{
    private EmployeeDao employeeDao;

    @BeforeEach
    public void setup() {

        this.employeeDao = new EmployeeDao();
    }

    @Test
    public void testAddEmployee() throws SQLException{
        Employee newEmployee = new Employee("12365897412",
                "Emanuel Vilela",
                "Endereco",
                "99999999999");

        employeeDao.addEmployee(newEmployee);
        List<Employee> listEmployees = employeeDao.getAllEmployees();
        assertTrue(listEmployees.stream().anyMatch(employee -> employee.getCpf().equals("12365897412")));
    }

    @Test
    public void testGetEmployee() throws  SQLException{
        String cpf = "12365897412";

        Employee res = employeeDao.getEmployeeById(cpf);
        if(res != null){
        assertEquals(res.getCpf(), cpf);
        } else {
            System.out.println("Funcionario não existe");
        }
    }

    @Test
    public void testUpdateEmployee(){
        Employee update = new Employee("12365897412",
                   "José Silva",
                   "Rua das Flores",
                   "123456531");
        int res = employeeDao.updateEmployee(update);

        System.out.println(res);
        assertTrue(res > 0);
    }

    @Test
    public void testDeleteEmployee(){
        String cpf = "12365897412";

        int res = employeeDao.deleteEmployee(cpf);
        assertTrue(res > 0);

    }
}
