FROM openjdk:8-jdk-alpine
ADD target/ness-fse-userservice-0.0.1.jar user.jar
ENV JAVA_OPTS=""
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /user.jar
