FROM openjdk:8-jdk-alpine
ADD target/ness-fse-gatewayservice-0.0.1.jar gateway.jar
ENV JAVA_OPTS=""
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /gateway.jar