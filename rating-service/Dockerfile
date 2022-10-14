FROM openjdk:11-jre-slim
WORKDIR usr/src
ADD ./target/ratingservice.jar /usr/src/ratingservice.jar
ENTRYPOINT ["java","-jar", "ratingservice.jar"]
