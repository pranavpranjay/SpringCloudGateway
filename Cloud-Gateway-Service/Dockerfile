#FROM adoptopenjdk/openjdk11:jdk-11.0.2.9-slim
FROM openjdk:8-jdk-alpine
EXPOSE 8084
WORKDIR /app
ADD target/*.jar app.jar
#ENV PORT 8080

ENTRYPOINT ["java","-jar","app.jar"]