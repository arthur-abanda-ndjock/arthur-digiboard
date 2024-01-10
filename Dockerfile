# minimum base image size
FROM eclipse-temurin:17.0.9_9-jre-jammy
RUN apt-get update 
RUN apt-get install -y procps
RUN apt-get install -y dumb-init

# label the images
LABEL image.authors="arthur.ndjock"
LABEL io.github.repo="my git repo handle"
LABEL version="1.0"
LABEL description="Digiboard and metrics Monitoring"


RUN adduser --disabled-password --gecos "" arthur
#RUN usermod -aG arthur arthur
RUN 
RUN mkdir -p /usr/local/cross-soft
RUN chown -R :arthur /usr/local/cross-soft
RUN chmod -R g+rw /usr/local/cross-soft


USER arthur

WORKDIR /usr/local/cross-soft

COPY ./arthur-digiboard-backend/target/arthur-digiboard.jar /usr/local/cross-soft/arthur-digiboard.jar

EXPOSE 8080

ENTRYPOINT ["/usr/bin/dumb-init", "--", "java", "-jar", "/usr/local/cross-soft/arthur-digiboard.jar", "--spring.profiles.active=local"]