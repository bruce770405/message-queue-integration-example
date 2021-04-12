FROM maven:3.8.1-openjdk-11 AS build
MAINTAINER BruceHsu bruce770405@gmail.com
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM openjdk:11.0.7-jre-slim
RUN mkdir /app
EXPOSE 8000
COPY --from=0 /home/app/target/producer-0.0.1-SNAPSHOT.jar /app/producer.jar
ENTRYPOINT ["java","-jar","/app/producer.jar"]