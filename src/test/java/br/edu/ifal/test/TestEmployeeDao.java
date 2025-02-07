package br.edu.ifal.test;

import br.edu.ifal.dao.EmployeeDao;
import br.edu.ifal.domain.Client;
import br.edu.ifal.domain.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class TestEmployeeDao {
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

        assertDoesNotThrow(() -> employeeDao.addEmployee(newEmployee));
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
    public void testDeleteEmployee() throws SQLException {
        String cpf = "56879845612";
        Employee e = new Employee(cpf,
                "José Silva",
                "Rua das Flores",
                "123456531");

        employeeDao.addEmployee(e);

        int deletedEmployee = employeeDao.deleteEmployee(cpf);
        assertEquals(1,deletedEmployee);

        Employee resID = employeeDao.getEmployeeById(cpf);
        assertNull(resID);


    }
}
