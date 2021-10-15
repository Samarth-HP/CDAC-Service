# Build stage
FROM maven:3.6.0-jdk-11-slim AS build
ENV HOME=/home/app
RUN mkdir -p $HOME
WORKDIR $HOME
ADD pom.xml $HOME
ADD /libs $HOME/libs
RUN mvn dependency:go-offline

ADD /src $HOME/src
RUN ls -lR
RUN mvn package -DskipTests=true

# Package stage
FROM openjdk:11-jre-slim
RUN apt update && apt upgrade
RUN apt install curl -y
ENV HOME=/home/app
WORKDIR $HOME
RUN ls -lR
ARG JAR_FILE=target/*.jar
COPY --from=build $HOME/target/*.jar app.jar

