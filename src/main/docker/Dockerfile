FROM openjdk:8
#FROM java:8
MAINTAINER asiainfo <lijx3@asiainfo.com>
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' >/etc/timezone
VOLUME /tmp
ADD spring-boot-demo-0.0.1-SNAPSHOT.jar ln-ocheck-1.0.0.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/ln-ocheck-1.0.0.jar"]