package pro.sky.libraries.service;

import pro.sky.libraries.model.Employee;

import java.util.Collection;

public interface EmployeeBookServiceInterface {

    Employee addEmployee(String name, String surname, double salary, int department);

    Employee removeEmployee(String name, String surname, double salary, int department);

    Employee findEmployee(String name, String surname, double salary, int department);

    Collection<Employee> printAll();
}
