FROM openjdk:21-ea-slim
ADD target/Hillel_SpringHomework-0.0.1-SNAPSHOT.jar hillel.jar
CMD ["java", "-jar", "hillel.jar"]



