# Digiboard: demo fullstack app (Java + React) with Github Actions CI/CD pipeline

1. [Introduction](#Introduction)
2. [Quick start](#quick-start)
3. [Github Actions CI/CD pipeline](#github-actions-ci-cd-pipeline)
   - [Branching strategies & Worflow](#branching-strategies--worflow)
     1. [feature branch](#features-branches)
     2. [main branch](#main-branch)
   - [Infrastructure](#infrastructure)
4. [App structure](#App-structure)
   - [Frontend](#Frontend)
   - [Backend](#Backend)

## Introduction

This demo project features a fullstack app, built as an single artifact and ready to be deployed in different contexts(local, docker environment as well as local/aws Kubernetes clusters).Though the app is runnable and deployable, some of its feature are still to be completed.

## Quick start

In order to run the project locally, kindly follow these steps:

1. checkout the project from the repo

   ```
    git clone https://github.com/arthur-abanda-ndjock/arthur-digiboard.git
   ```

2. move inside the root folder, build using maven and run it as a spring boot app

   ```
    mvn clean package
    java -jar .\arthur-digiboard-backend\target\arthur-digiboard.jar --spring.profiles.active=local
   ```

   then in the browser call the url: `http://localhost:8080/`

3. (Optional) additionally, if you have a docker environment you can build an image inside the root folder and run it as container.

   ```
    docker build -t <<YOUR_DOCKER_REPO>>/digiboard:1.5 .
    docker push <<YOUR_DOCKER_REPO>>/digiboard:1.5
    docker run -p 8080:8080 <<YOUR_DOCKER_REPO>>/digiboard:1.5
   ```

## Github Actions CI CD pipeline

![CI/CD pipeline](assets/CI_CD.png)

### Branching strategies & Worflow:

For such a small project has 2 branch types: the feature & main branches:

#### 'Features' branches:

The features branches have the code of all the new features being developed and they can receive code directly from a dev (git push). Their [pipeline workflow](.github/workflows/feature-branch.yaml) include the following steps:

1. Compile & the app with the 'default' profile as well as run as the revelant tests
2. Generate & publish test coverage to GitHub Actions
3. Do the same with linting results.
4. Build a docker image using the generate app jar file
5. Scan the image in search for security vulnerabilities & publish scan report to GitHub Actions

#### 'Main' branch:

The main branch cannot have code pushed into, rather have code merged into from other branches. For the merge PR to be allowed, some branch protection checks must passed. If is not the case, the merging will be blocked:

![Branch protection during PR](assets/branch-protections.png)

This contributes keeping the main branch as clean ans safe as possible.

Once the PR is merged, the main branch's [workflow](.github/workflows/main-branch.yaml) start with a similar pattern as the feature branch's (other than building the app using the "dev" profile) and adds following steps:

6. push the docker image to aws ECR
7. connect to the present aws EKS cluster
8. Fetch the image from aws ECR and deploy it to EKS cluster

### Infrastructure

The target infrastructure of the app is AWS where all the necessary elements (VPC, subnets, EKS-cluster, ECR and RDS, etc...) are provisioned in [this](https://github.com/arthur-abanda-ndjock/arthur-digiboard-terraform) separate terraform project.

## App structure

### Frontend

### Backend

```

```
