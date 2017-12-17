FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/ness-fse-libraryservice-0.0.1.jar library.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-DskipTests=true","-Dspring.profiles.active=docker","-jar","/library.jar"]