FROM openjdk:11-jre-slim
WORKDIR usr/src
ADD ./target/authenticationservice.jar /usr/src/authenticationservice.jar
ENTRYPOINT ["java","-jar", "authenticationservice.jar"]
