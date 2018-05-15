# Java Time (Java 8)


The **Calories** project demonstrates how to use Date and time in java8
and tests using Maven. 

### Installing

Clone a repository from git

```
bash-3.2$ git clone -b calories https://github.com/vKozik/java8.git
                                            
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
mvn compile exec:java -Dexec.mainClass="com.grow.java8.calories.CaloriesMain" -Dexec.args="10000 test.json 2018-02-02 2018-06-04"  
```

#parameters: 
 1. The norm of calories per day
 2. JSON file with foods by days
 3. Start date interval
 4. Finish date interval