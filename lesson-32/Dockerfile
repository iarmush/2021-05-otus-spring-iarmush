FROM openjdk:11-jre-slim

WORKDIR /opt/app
COPY target/*.jar app.jar

EXPOSE 8080

COPY docker-entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh
ENTRYPOINT [ "/entrypoint.sh" ]