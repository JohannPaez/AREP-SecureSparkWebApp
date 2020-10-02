FROM openjdk:8

WORKDIR /SecureSparkApp/bin

ENV PORT 6000

COPY /target/classes /SecureSparkApp/bin/classes
COPY /target/dependency /SecureSparkApp/bin/dependency
RUN mkdir keystores
COPY keystores/* /SecureSparkApp/bin/keystores

CMD ["java","-cp","./target/classes:./target/dependency/*","com.arep.SparkWebServer"]