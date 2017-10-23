FROM openjdk:8-jdk-alpine
ADD target/ness-fse-registryservice-0.0.1.jar registry.jar
ENV JAVA_OPTS=""
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /registry.jar