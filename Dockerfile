FROM gradle:8.6.0-jdk21 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM openjdk:21
EXPOSE 8181
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/*.jar
ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap","-jar", "/app/*.jar"]