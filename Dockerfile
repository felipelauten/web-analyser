FROM maven:3.9.8-sapmachine-22 AS builder

LABEL authors="DevLauten"
WORKDIR /build
COPY . .

RUN ["mvn", "-B", "package", "--file", "pom.xml"]

FROM ubuntu:latest

WORKDIR /app
COPY --from=builder /build/rest/target/rest-0.0.1-SNAPSHOT.jar .
RUN ["mv", "rest-0.0.1-SNAPSHOT.jar", "pagedoctor.jar"]
ENTRYPOINT ["java", "-jar", "pagedoctor.jar"]
