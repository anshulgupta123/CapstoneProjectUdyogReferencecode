FROM openjdk:11-jre-slim
WORKDIR usr/src
ADD ./target/chatservice.jar /usr/src/chatservice.jar
ENTRYPOINT ["java","-jar", "chatservice.jar"]
