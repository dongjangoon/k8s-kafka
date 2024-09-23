FROM openjdk:21-jdk-slim
WORKDIR /app
COPY build/libs/word-processor.jar app.jar
EXPOSE 8082
ENTRYPOINT [ "java","-jar","app.jar" ]