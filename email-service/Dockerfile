FROM openjdk:11-jre-slim
WORKDIR usr/src
ADD ./target/emailservice.jar /usr/src/emailservice.jar
ENTRYPOINT ["java","-jar", "emailservice.jar"]
