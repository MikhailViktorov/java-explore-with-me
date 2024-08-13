FROM amazoncorretto:21
COPY service/target/*.jar ewm-server.jar
ENTRYPOINT ["java","-jar","/ewm-server.jar"]