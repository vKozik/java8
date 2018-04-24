package com.grow.myjava8.streams;

import com.grow.myjava8.streams.data.Employee;
import com.grow.myjava8.streams.data.Person;
import com.grow.myjava8.streams.enums.Gender;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

public class EmployeeReporter {
    private Collection<Employee> employees;

    public EmployeeReporter(Collection<Employee> employees){
        this.employees = employees;
    }

    public void printAll(){
        employees.forEach(System.out::println);
    }

    public void printAllDepartments(){
        employees.stream()
                .map(employee -> employee.getDepartment())
                .distinct()
                .sorted()
                .forEach(System.out::println);
    }

    public void printOneGender(Gender gender){
        employees.stream()
                .filter(employee -> employee.getGender() == gender)
                .sorted(comparing(Person::getFullName))
                .forEach(System.out::println);
    }

    public void printAllChildrenYounger16(){
        employees.stream()
                .flatMap(employee -> employee.getChildren().stream())
                .filter(child -> child.getAge() < 16)
                .forEach(System.out::println);
    }

    public void printTopSalay(int count){
        employees.stream()
                .sorted(((e1, e2) -> e2.getSalary().compareTo(e1.getSalary())))
                .limit(count)
                .forEach(System.out::println);
    }

    public void printStats(){
        DoubleSummaryStatistics statistics = employees.stream()
                .collect(Collectors.summarizingDouble(employee -> employee.getSalary()));

        System.out.println("Average salary: " + statistics.getAverage());
        System.out.println("Max salary: " + statistics.getMax());
        System.out.println("Min salary: " + statistics.getMin());
    }

    public Map<Boolean, List<Employee>> doesHaveChildren(){
        return employees.stream()
                .collect(Collectors.partitioningBy(employee -> !employee.getChildren().isEmpty()));
    }

    public void printEmployeeByDepartment(){
        employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment))
                .entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .forEach(entry -> {
                    System.out.println(entry.getKey());
                    entry.getValue().stream()
                            .sorted(Comparator.comparing(Employee::getSalary).reversed())
                            .forEach(System.out::println);
                    System.out.println("------------------------------------------------");

                    DoubleSummaryStatistics statistics = entry.getValue().stream()
                            .collect(Collectors.summarizingDouble(employee -> employee.getSalary()));
                    System.out.println("Average salary: " + statistics.getAverage());
                    System.out.println("Max salary: " + statistics.getMax());
                    System.out.println("Min salary: " + statistics.getMin() );
                    System.out.println("Count: " + statistics.getCount() + "\n");
        });
    }
}
