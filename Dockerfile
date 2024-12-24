FROM jelastic/maven:3.9.5-openjdk-21
VOLUME /tmp
#WORKDIR /Users/sasha/IdeaProjects/Training
#RUN ./mvnw spring-boot:build-image
#COPY . /context
#ADD .mvn .mvn
#COPY mvnw .
COPY target/training-1.0-SNAPSHOT-exec.jar app.jar
#COPY pom.xml .
#ADD src src
#RUN ./mvnw -B package
#FROM openjdk:23-jdk
#COPY --from=build target/training-1.0-SNAPSHOT-exec.jar .
EXPOSE 8081
ENTRYPOINT ["java","-jar","app.jar"]
#ARG JAR_FILE=target/training-1.0-SNAPSHOT-exec.jar
#WORKDIR /opt/app
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","app.jar"]