# Java 8 Stream API maven


The **StreamAPI** project demonstrates how to use strema API in java8
and tests using Maven. 

### Installing

Clone a repository from git

```
bash-3.2$ git clone https://github.com/vKozik/java8/tree/stream-API
```

enter the project folder
```
bash-3.2$ cd java8
```

build the app with maven
```
bash-3.2$ mvn clean install
```

### Run

```
mvn exec:java -Dexec.mainClass="com.grow.java8.streams.StreamsMain" -Dexec.args="[Report name]"  
```

#Report names: 

    All - to select all employees,         
    AllDepartments - to select all departmens,
    AllMen - to select all men,
    AllWomen - to select all women,
    AllChildrenYounger16 - to select all children younger 16,
    TopSalay5 - to select top 5 salay,
    Statistics - employee statistics,
    WithChildren - to select employee with children,
    EmployeeByDepartment - to select departments with employees
