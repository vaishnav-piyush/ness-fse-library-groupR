FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/ness-fse-auditservice-0.0.1.jar auditservice.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-DskipTests=true","-Dspring.profiles.active=docker","-jar","/auditservice.jar"]