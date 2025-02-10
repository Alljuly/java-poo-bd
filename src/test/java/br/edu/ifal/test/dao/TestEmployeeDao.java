package br.edu.ifal.test.dao;

import br.edu.ifal.dao.EmployeeDao;
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
    public void testAddEmployee() throws SQLException {
        Employee newEmployee = new Employee("12365897412",
                "Emanuel Vilela",
                "Endereco",
                "99999999999");

        if(!employeeDao.isCpfExisting(newEmployee.getCpf())){
            boolean added = employeeDao.addEmployee(newEmployee);
            assertTrue(added);
        }
        else {
            boolean added = employeeDao.addEmployee(newEmployee);
            assertFalse(added);
        }
        assertTrue(employeeDao.isCpfExisting("12365897412"));
    }


    @Test
    public void testGetEmployee() throws SQLException {
        String cpf = "12365897412";
        Employee res = employeeDao.getEmployeeById(cpf);

        assertNotNull(res, "Funcionário não encontrado!");
        assertEquals(res.getCpf(), cpf);
    }

    @Test
    public void testUpdateEmployee() {
        Employee update = new Employee("12365897412",
                "José Silva",
                "Rua das Flores",
                "123456531");
        int res = employeeDao.updateEmployee(update);

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

        assertEquals(1, deletedEmployee);
        assertNull(employeeDao.getEmployeeById(cpf));
    }
}
