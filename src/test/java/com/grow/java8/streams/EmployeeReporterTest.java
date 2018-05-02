package com.grow.java8.streams;

import com.grow.java8.streams.data.Child;
import com.grow.java8.streams.data.Employee;
import com.grow.java8.streams.data.Person;
import com.grow.java8.streams.enums.Gender;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Java8 stream API tests")
class EmployeeReporterTest {
    private EmployeeReporter employeeReporter;
    private ArrayList<Employee> employees;

    {
        employees = new ArrayList<>();

        Employee employee;

        employee = createEmployee(1, LocalDate.of(1956,10,25), 2500d, "dep01", Gender.MALE);
        employee.addChild(new Child(createPerson(101, true, LocalDate.of(2006,10,25), Gender.FEMALE)));
        employee.addChild(new Child(createPerson(102, true, LocalDate.of(1987,10,25), Gender.FEMALE)));
        employees.add(employee);

        employee = createEmployee(2, LocalDate.of(1956,10,25), 3500d, "dep01", Gender.MALE);
        employees.add(employee);

        employee = createEmployee(3, LocalDate.of(1966,07,25), 7500d, "dep02", Gender.FEMALE);
        employee.addChild(new Child(createPerson(301, true, LocalDate.of(2000,10,25), Gender.FEMALE)));
        employees.add(employee);

        employee = createEmployee(4, LocalDate.of(1986,10,25), 6500d, "dep02", Gender.MALE);
        employees.add(employee);

        employee = createEmployee(5, LocalDate.of(1926,10,25), 1500d, "dep03", Gender.FEMALE);
        employee.addChild(new Child(createPerson(501, true, LocalDate.of(2000,10,25), Gender.FEMALE)));
        employee.addChild(new Child(createPerson(502, true, LocalDate.of(2007,10,25), Gender.FEMALE)));
        employees.add(employee);

        employeeReporter = new EmployeeReporter(employees);
    }

    @Test
    void ShouldReturnAllEmployee() {
        Collection<Employee> result = employeeReporter.getAll();
        assertEquals(employees.size(), result.size());
        assertTrue(result.containsAll(employees));
    }

    @Test
    void ShouldReturnAllDepartments() {
        Collection<String> result = employeeReporter.getAllDepartments();
        assertEquals(3, result.size());
    }

    @Test
    void ShouldReturnOneGender() {
        Collection<Employee> result = employeeReporter.getOneGender(Gender.FEMALE);
        assertEquals(2, result.size());
    }

    @Test
    void ShouldReturntAllChildrenYounger16() {
        Collection<Child> result = employeeReporter.getAllChildrenYounger16();
        assertEquals(2, result.size());
    }

    @Test
    void ShouldReturntTopSalay() {
        List<Employee> result = employeeReporter.getTopSalay(2);
        assertEquals(2, result.size());
        assertEquals(Double.valueOf(7500), result.get(0).getSalary());
        assertEquals(Double.valueOf(6500), result.get(1).getSalary());
    }

    @Test
    void ShouldReturntWhoHaveChildren() {
        Map<Boolean, List<Employee>> result = employeeReporter.haveChildren();
        assertEquals(3, result.get(Boolean.TRUE).size());
        assertEquals(2, result.get(Boolean.FALSE).size());
    }


    private  static Employee createEmployee(int id, LocalDate birthday, Double salary, String department, Gender gender){
        final Employee employee = new Employee(id, createPerson(id, false, birthday, gender));
        employee.setSalary(salary);
        employee.setDepartment(department);
        return employee;
    }

    private static Person createPerson(int id, boolean ischild, LocalDate birthday, Gender gender){
        final String firstName = "name_" + id;
        final String surname = "surname_" + id;
        return new Person(firstName, surname, birthday, gender);
    }

}