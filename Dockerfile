FROM openjdk:8-jdk-alpine
EXPOSE 8081
ADD target/springBootTask1-0.0.1-SNAPSHOT.jar task1.jar
ENTRYPOINT ["java","-jar","task1.jar"]