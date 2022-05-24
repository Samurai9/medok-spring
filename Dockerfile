# build project
FROM maven:3.6.0-jdk-8-slim AS build

#non-root user for postgres initdb
ARG USERNAME=new_user
ARG USER_UID=1001
ARG USER_GID=$USER_UID
ARG APP_PATH=/home/app

#change to a non-root user and change owner of folder
RUN groupadd --gid $USER_GID $USERNAME \
    && useradd --uid $USER_UID --gid $USER_GID -m $USERNAME \
    && mkdir -p $APP_PATH \
    && chown $USERNAME $APP_PATH
USER $USERNAME

COPY src $APP_PATH/src
COPY pom.xml $APP_PATH
RUN mvn -f $APP_PATH/pom.xml clean package

# create image
FROM openjdk:8-jdk-alpine
EXPOSE 8080
ADD target/medok-spring-1.0.jar medok-spring-1.0.jar
ENTRYPOINT ["java","-jar","/medok-spring-1.0.jar"]
