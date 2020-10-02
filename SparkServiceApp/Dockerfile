FROM openjdk:8

WORKDIR /ServiceSparkApp/bin

ENV PORT 6000
ENV TZ America/Bogota

COPY /target/classes /ServiceSparkApp/bin/classes
COPY /target/dependency /ServiceSparkApp/bin/dependency
RUN mkdir keystores
COPY keystores/* /ServiceSparkApp/bin/keystores/

CMD ["java","-cp","./target/classes:./target/dependency/*","com.arep.SparkWebServer"]