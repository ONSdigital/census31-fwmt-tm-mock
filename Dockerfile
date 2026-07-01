FROM eclipse-temurin:25-jre
ARG jar
COPY $jar /opt/mock.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "java", "-jar", "/opt/mock.jar" ]
