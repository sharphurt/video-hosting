FROM gradle:7.6.4-jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle clean build --no-daemon -x test

FROM openjdk:17
EXPOSE 8181
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/*.jar
ENTRYPOINT ["java", "-jar", "/app/*.jar"]