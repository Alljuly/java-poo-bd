import src.main.java.br.edu.ifal.dao.EmployeeDao;

public class TestEmployee{
    EmployeeDao employeeDao ;
    public TesteEmployee(EmplyeeDao e){
        this.employeeDao = e;
    }

    void teste(){
        Employee newEmployee = new Employee("12312312315",
                     "José da Silva",
                     "Endereco",
                     "123451231");

            employeeDao.addEmployee(newEmployee);

            List<Employee> listEmployees = employeeDao.getAllEmployees();
            for (Employee c : listEmployees) {
                System.out.println(c);
            }

            Employee update = new Employee("12312312315",
                     "José Silva",
                     "Rua das Flores",
                     "123456531");

            employeeDao.updateEmployee(update);

            String cpf = "43185649168";

            employeeDao.deleteEmployee(cpf);
    }
}