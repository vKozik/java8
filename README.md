# Java 8 Stream API maven


The `StreamAPI` project demonstrates how to use strema API in java8
and tests using Maven. 

comand for run project
mvn compile exec:java -Dexec.mainClass="com.grow.java8.streams.StreamsMain" -Dexec.args="[Report name]"  

Report names: 
    All - to select all employees,
    AllDepartments - to select all departmens,
    AllMen - to select all men,
    AllWomen - to select all women,
    AllChildrenYounger16 - to select all children younger 16,
    TopSalay5 - to select top 5 salay,
    Statistics - employee statistics,
    WithChildren - to select employee with children,
    EmployeeByDepartment - to select departments with employees
