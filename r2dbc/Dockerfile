FROM gradle:7.6-jdk17-alpine as build

ENV APP_HOME=/apps
WORKDIR $APP_HOME

COPY gradlew $APP_HOME
COPY gradle $APP_HOME/gradle
COPY build.gradle.kts $APP_HOME
COPY settings.gradle.kts $APP_HOME
COPY src $APP_HOME/src

RUN chmod +x ./gradlew
RUN ./gradlew clean build


FROM openjdk:17-alpine

ENV APP_HOME=/apps
ARG JAR_FILE_PATH=./build/libs/*.jar
WORKDIR $APP_HOME
COPY --from=build $APP_HOME/${JAR_FILE_PATH} app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]