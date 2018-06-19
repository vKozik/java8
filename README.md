# Java Time (Java 8)


The **Calories** project demonstrates how to use Date and time in java8
and tests using Maven. 

### Installing

Clone a repository from git

```
bash-3.2$ git clone -b calories https://github.com/vKozik/java8.git calories
                                            
```

enter the project folder
```
bash-3.2$ cd calories
```

build the app with maven
```
bash-3.2$ mvn clean install
```

### Run

```
java -jar target/Calories-0.0.1-SNAPSHOT.jar 2018-02-02 2018-06-04 100
```

#parameters: 
 1. Start date interval
 2. Finish date interval
 3. The norm of calories per day