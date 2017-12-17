FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/ness-fse-registryservice-0.0.1.jar registry.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-DskipTests=true","-Dspring.profiles.active=docker","-jar","/registry.jar"]