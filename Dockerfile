FROM adoptopenjdk/openjdk11:jre-11.0.11_9-alpine
COPY ./build/libs/*.jar /

CMD ["java","-jar","batch-0.0.1-SNAPSHOT.jar","-Duser.timezone=Asia/Tokyo"]
