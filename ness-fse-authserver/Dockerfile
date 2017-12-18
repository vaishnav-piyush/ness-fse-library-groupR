FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/ness-fse-authserver-0.0.1.jar authserver.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-DskipTests=true","-Dspring.profiles.active=docker","-jar","/authserver.jar"]