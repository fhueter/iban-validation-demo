FROM eclipse-temurin:25-jdk
USER 1000
COPY /backend/target/ibanvalidator-backend.jar ibanvalidator.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/ibanvalidator.jar"]