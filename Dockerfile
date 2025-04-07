FROM openjdk:17-jdk-alpine

ADD target/teamRavanan-application-mangodb.jar teamRavanan-application-mangodb.jar

EXPOSE 2020

ENTRYPOINT ["java","-jar","teamRavanan-application-mangodb.jar"]
