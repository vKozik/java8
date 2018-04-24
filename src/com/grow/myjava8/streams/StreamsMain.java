package com.grow.myjava8.streams;

import com.grow.myjava8.streams.data.Employee;
import com.grow.myjava8.streams.init.DataInitializer;

import java.util.List;
import java.util.Map;

public class StreamsMain {
    public static void main(String[] args) {
        EmployeeReporter reports = new EmployeeReporter(DataInitializer.init(50));

        //reports.printAll();
        //reports.printAllDepartments();
        //reports.printOneGender(Gender.FEMALE);
        //reports.printAllChildrenYounger16();
        //reports.printTopSalay(5);
        //reports.printStats();
        Map<Boolean, List<Employee>> doesHaveChildren = reports.doesHaveChildren();
        reports.printEmployeeByDepartment();
    }
}
