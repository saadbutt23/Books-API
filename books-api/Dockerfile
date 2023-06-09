FROM openjdk:17

# Copy the jar file into the docker image
COPY ./out/artifacts/books_api_jar/books-api.jar book-api.jar

# Run the Jar file
ENTRYPOINT ["java","-jar", "/book-api.jar"]

