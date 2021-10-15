# Build stage
FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
COPY libs /home/app/libs
COPY .env /home/app
RUN mvn -f /home/app/pom.xml clean package

# Package stage
FROM openjdk:11-jre-slim
ENV export $(cat /home/app/.env | xargs)
ARG JAR_FILE=target/*.jar
COPY --from=build /home/app/target/cdac-0.0.1-SNAPSHOT.jar /usr/local/lib/myapp.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/myapp.jar"]