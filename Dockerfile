FROM openjdk:17-jdk-slim

EXPOSE 5001

COPY /target/crypto-recommendation-service-0.0.1.jar /data/crypto-recommendation-service-0.0.1.jar
WORKDIR /data

ENTRYPOINT ["java", "-jar", "crypto-recommendation-service-0.0.1.jar"]
