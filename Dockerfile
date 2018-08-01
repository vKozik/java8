FROM openjdk:8

ADD target/Calories-0.0.1-SNAPSHOT.war Calories-0.0.1-SNAPSHOT.war

EXPOSE 8082

ENTRYPOINT ["java","-jar","Calories-0.0.1-SNAPSHOT.war"]