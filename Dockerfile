FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
WORKDIR /applications
COPY target/*.jar /applications/app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 9002