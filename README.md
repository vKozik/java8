# Isolation levels


The **Ticket** project shows the difference between the levels of isolation in the database  
               
### Installing

Clone a repository from git

```
git clone -b tickets https://github.com/vKozik/java8.git tickets                                            
```

enter the project folder
```
cd tickets
```

build the app with maven
```
mvn clean install 

```


### Run

```
java -jar target/Tickets-0.0.1-SNAPSHOT.war
```

### Request examples

Buy a ticket
http://localhost:8082/tickets/uncommited/buyTicket?buyer=buyerNamre

Buy a ticket (and throw exception for "Dirty read" test )
http://localhost:8082/tickets/uncommited/buyTicket?buyer=exception

Add a free ticket
http://localhost:8082/tickets/uncommited/addFree

Number of tickets sold 
http://localhost:8082/tickets/uncommited/sold

