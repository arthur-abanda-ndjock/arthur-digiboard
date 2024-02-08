# Digiboard: demo fullstack app (Java + React) with Github Actions CI/CD pipeline

[Introduction] (#Introduction)
[Quick start] (#Quick-start)
[Github Actions CI/CD pipeline] (#Github-Actions-CI-CD-pipeline)
[App structure](#App-structure)
[Frontend](#Frontend)
[Backend](#Backend)

## Introduction

This demo project features a fullstack app, built as an single artifact and ready to be deployed in different contexts(local, docker environment as well as local/aws Kubernetes clusters)

## Quick start

In order to run the project locally, kindly follow these steps:

1. checkout the project from the repo

   ```
    git clone https://github.com/arthur-abanda-ndjock/arthur-digiboard.git
   ```

2. move inside the root folder, build it using maven and run it as a spring boot app

   ```
    mvn clean package
    java -jar .\arthur-digiboard-backend\target\arthur-digiboard.jar --spring.profiles.active=local
   ```

3. ![[Optional]] if you have a docker environment, you can build an image and run it as container.

   ```
    docker build -t <<YOUR_DOCKER_REPO>>/digiboard:1.5 .
    docker push <<YOUR_DOCKER_REPO>>/digiboard:1.5
    docker run -p 8080:8080 <<YOUR_DOCKER_REPO>>/digiboard:1.5
   ```

## Github Actions CI CD pipeline

![CI/CD pipeline](assets/CI_CD.png)

## App structure

### Frontend

### Backend

```

```
