# Java Time (Java 8)


The **Calories** project demonstrates how to use Date and time in java8
and tests using Maven. 

### Installing

Clone a repository from git

```
git clone -b calories https://github.com/vKozik/java8.git calories
                                            
```

enter the project folder
```
cd calories
```

build the app with maven
```
mvn clean install
```
### Run with H2 database profile

```
java -jar -Dspring.profiles.active=h2 target/Calories-0.0.1-SNAPSHOT.war
```

### Run with JSON profile (read only mode)

```
java -jar -Dspring.profiles.active=json target/Calories-0.0.1-SNAPSHOT.war
```

### Run with docker

```
docker build -t calories-app .

docker run -p 8082:8082 -t calories-app
```

### Web page

http://localhost:8082/foods
http://localhost:8082/stat

### Request examples

http://localhost:8082/api/test
http://localhost:8082/api/stat?fromDate=2018-05-02&toDate=2018-05-05
http://localhost:8082/api/checkLimit?norm=1200&fromDate=2018-05-02&toDate=2018-05-05


