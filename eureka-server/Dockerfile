FROM openjdk:11-jre-slim
WORKDIR usr/src
ADD ./target/eurekaserver.jar /usr/src/eurekaserver.jar
ENTRYPOINT ["java","-jar", "eurekaserver.jar"]
