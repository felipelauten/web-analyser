FROM amazoncorretto:17-alpine-jdk

# Copy build
WORKDIR /app
COPY ./rest/target/rest-0.0.1-SNAPSHOT.jar .

# Ports
EXPOSE 8080:8080

# TODO: Volumes (for db)
ENTRYPOINT ["java", "-jar", "rest-0.0.1-SNAPSHOT.jar", "com.devlauten.webanalyzer.WebAnalyzerApplication"]