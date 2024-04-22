FROM openjdk:21-ea-slim

WORKDIR /app

COPY target/Hillel_SpringHomework-0.0.1-SNAPSHOT.jar /app/Hillel_SpringHomework-0.0.1-SNAPSHOT.jar

ENV PORT=8181

CMD ["java", "-jar", "Hillel_SpringHomework-0.0.1-SNAPSHOT.jar"]

FROM postgres:latest

ENV POSTGRES_DB=manager_db
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=vldr

EXPOSE 5432




