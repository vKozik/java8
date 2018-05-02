package com.grow.java8.streams.data;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Employee extends Person{
    private Integer id;
    private Set<Child> children = new HashSet<>();
    private String department;
    private Double salary;

    public Employee(Integer id, Person person) {
        super(person.getFirstName(), person.getSurname(), person.getBirthday(), person.getGender());
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Set<Child> getChildren() {
        return new HashSet<>(children);
    }

    public void addChild(Child child) {
        child.setParent(this);
        children.add(child);
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(final String department) {
        this.department = department;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append(id)
                .append(". ")
                .append(getFullName())
                .append(" Gender: ")
                .append(getGender())
                .append(" Birthday: ")
                .append(getBirthday())
                .append(" Salary: ")
                .append(salary)
                .append(" department: ")
                .append(department)
                .append(" children:")
                .append("children [")
                .append(
                        children.stream().map(Child::toString).collect(Collectors.joining("; ")))
                .append("]");

        return builder.toString();
    }
}
