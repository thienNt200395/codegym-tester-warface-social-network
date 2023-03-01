FROM eclipse-temurin:8-jdk as build
WORKDIR /workspace/app
COPY app .
RUN ./gradlew build

FROM eclipse-temurin:8-jdk
VOLUME /tmp
ARG DEPENDENCY=build/libs/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","social_network"]
