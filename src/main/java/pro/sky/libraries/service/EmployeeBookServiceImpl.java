package pro.sky.libraries.service;

import org.springframework.stereotype.Service;
import pro.sky.libraries.exceptions.EmployeeAlreadyAddedException;
import pro.sky.libraries.exceptions.EmployeeCheckFailedException;
import pro.sky.libraries.exceptions.EmployeeNotFoundException;
import pro.sky.libraries.model.Employee;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.*;

@Service
public class EmployeeBookServiceImpl implements EmployeeBookServiceInterface {
    private final Map<String, Employee> employeeMap;

    public EmployeeBookServiceImpl() {
        this.employeeMap = new HashMap<>();
    }

    public Map<String, Employee> getEmployeeMap() {
        return employeeMap;
    }

    @Override
    public Employee addEmployee(String name, String surname, double salary, int department) {
        checkEmployee(name, surname);
        Employee employee = new Employee(name, surname, salary, department);
        if (employeeMap.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException();
        }
        employeeMap.put(employee.getFullName(), employee);
        return employee;
    }

    @Override
    public Employee removeEmployee(String name, String surname, double salary, int department) {
        checkEmployee(name, surname);
        Employee employee = new Employee(name, surname, salary, department);
        if (!employeeMap.containsKey(employee.getFullName())) {
            throw new EmployeeNotFoundException();
        }
        return employeeMap.remove(employee.getFullName());
    }

    @Override
    public Employee findEmployee(String name, String surname, double salary, int department) {
        checkEmployee(name, surname);
        Employee employee = new Employee(name, surname, salary, department);
        if (!employeeMap.containsKey(employee.getFullName())) {
            throw new EmployeeNotFoundException();
        }
        return employeeMap.get(employee.getFullName());

    }

    @Override
    public Collection<Employee> printAll() {
        return Collections.unmodifiableCollection(employeeMap.values());
    }

    private void checkEmployee(String name, String surname) {
        if ((isEmpty(name) || isEmpty(surname))
                || (!isAlpha(name) || !isAlpha(surname))) {
            throw new EmployeeCheckFailedException();
        }
    }
}
