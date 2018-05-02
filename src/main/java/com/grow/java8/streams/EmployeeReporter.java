package com.grow.java8.streams;

import com.grow.java8.streams.data.Child;
import com.grow.java8.streams.data.Employee;
import com.grow.java8.streams.data.Person;
import com.grow.java8.streams.enums.Gender;

import java.util.*;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.Comparator.comparing;

public class EmployeeReporter {
    private final Logger logger = LoggerFactory.getLogger(EmployeeReporter.class);
    private Collection<Employee> employees;

    public EmployeeReporter(Collection<Employee> employees){
        this.employees = employees;
    }

    public List<Employee> getAll(){
        return new ArrayList<>(employees);
    }

    public Set<String> getAllDepartments(){
        return employees.stream()
                .map(Employee::getDepartment)
                .distinct()
                .collect(Collectors.toSet());
    }

    public List<Employee> getOneGender(Gender gender){
        return employees.stream()
                .filter(employee -> employee.getGender() == gender)
                .sorted(comparing(Person::getFullName))
                .collect(Collectors.toList());
    }

    public List<Child> getAllChildrenYounger16(){
        return employees.stream()
                .flatMap(employee -> employee.getChildren().stream())
                .filter(child -> child.getAge() < 16)
                .collect(Collectors.toList());
    }

    public List<Employee> getTopSalay(int count){
         return employees.stream()
                 .sorted(((e1, e2) -> e2.getSalary().compareTo(e1.getSalary())))
                 .limit(count)
                 .collect(Collectors.toList());
    }

    public DoubleSummaryStatistics getStats(){
        return employees.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));
        }

    public Map<Boolean, List<Employee>> haveChildren(){
        return employees.stream()
                .collect(Collectors.partitioningBy(employee -> !employee.getChildren().isEmpty()));
    }

    public void printEmployeeByDepartment(){
        employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment))
                .entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .forEach(entry -> {
                    logger.info(entry.getKey());
                    entry.getValue().stream()
                            .sorted(Comparator.comparing(Employee::getSalary).reversed())
                            .map(String::valueOf)
                            .forEach(logger::info);
                    logger.info("------------------------------------------------");

                    DoubleSummaryStatistics statistics = entry.getValue().stream()
                            .collect(Collectors.summarizingDouble(Employee::getSalary));
                    logger.info("Average salary: " + statistics.getAverage());
                    logger.info("Max salary: " + statistics.getMax());
                    logger.info("Min salary: " + statistics.getMin() );
                    logger.info("Count: " + statistics.getCount() + "\n");
        });
    }
}
