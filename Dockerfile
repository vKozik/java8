FROM openjdk:8

ADD target/Calories-0.0.1-SNAPSHOT.jar Calories-0.0.1-SNAPSHOT.jar

EXPOSE 8082

ENTRYPOINT ["java","-jar","Calories-0.0.1-SNAPSHOT.jar"]