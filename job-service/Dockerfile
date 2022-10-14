FROM openjdk:11-jre-slim
WORKDIR usr/src
ADD ./target/jobservice.jar /usr/src/jobservice.jar
ENTRYPOINT ["java","-jar", "jobservice.jar"]
