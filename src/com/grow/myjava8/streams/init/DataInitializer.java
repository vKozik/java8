package com.grow.myjava8.streams.init;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.grow.myjava8.streams.data.Child;
import com.grow.myjava8.streams.data.Employee;
import com.grow.myjava8.streams.data.Person;
import com.grow.myjava8.streams.enums.Gender;

public class DataInitializer {
    final static Random random = new Random();


    public static List<Employee> init(int count){
        final SimpleDateFormat format = new SimpleDateFormat("dd.mm.yyyy");
        final List<Employee> employees = new ArrayList<>();

        Stream.iterate(0, n -> n + 1)
                .limit(count)
                .forEach((id)->employees.add(generateEmployee(id)));

        return employees;
    }

    private  static Employee generateEmployee(int id){
        final Employee employee = new Employee(id, generatePerson(id, false));
        employee.setSalary(Math.round(random.nextDouble() * 1000000) / 100d);
        employee.setDepartment("Dep_" + random.nextInt(10));

        final int countChildren = random.nextInt(4);
        IntStream.range(1, countChildren)
                .forEach((n)->employee.addChild(new Child(generatePerson(id * 100 + n, true))));

        return employee;
    }

    private static Person generatePerson(int id, boolean ischild){
        final String firstName = "name_" + id;
        final String surname = "surname_" + id;
        final LocalDate birthday = ischild ? getRandomDate(2005, 2007) : getRandomDate(1960, 2005);

        return new Person(firstName, surname, birthday, Gender.getRandom());
    }

    private static LocalDate getRandomDate(int fromYear, int toYear){
        final int minDay = (int) LocalDate.of(fromYear, 1, 1).toEpochDay();
        final int maxDay = (int) LocalDate.of(toYear, 1, 1).toEpochDay();
        final long randomDay = minDay + random.nextInt(maxDay - minDay);

        return LocalDate.ofEpochDay(randomDay);
    }

}
