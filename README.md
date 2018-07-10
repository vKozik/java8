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

### Run

```
docker build -t calories-app .

docker run -p 8087:8082 -t calories-app
```

### Request examples

http://localhost:8087/api/test
http://localhost:8087/api/stat?fromDate=2018-05-02&toDate=2018-05-05
http://localhost:8087/api/checkLimit?norm=1200&fromDate=2018-05-02&toDate=2018-05-05


