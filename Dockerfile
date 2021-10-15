#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
COPY libs /home/app/libs
WORKDIR /home/app
RUN ls -lR
RUN mvn clean package

#
# Package stage
#
FROM openjdk:11-jre-slim
WORKDIR /home/app
ARG JAR_FILE=target/*.jar
RUN echo $JAR_FILE
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]

