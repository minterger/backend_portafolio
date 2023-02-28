FROM eclipse-temurin:17-jdk-alpine
MAINTAINER tunombre_o_alias
COPY backend_portafolio-0.0.1-SNAPSHOT.jar backend_portafolio-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/backend_portafolio-0.0.1-SNAPSHOT.jar"]