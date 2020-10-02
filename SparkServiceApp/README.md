# AREP-SparkServiceApp

## Proyecto Principal
El proyecto principal se encuentra disponible [aquí](https://github.com/JohannPaez/AREP-SecureSparkWebApp).

## Integración Continua

[![CircleCI](https://circleci.com/gh/JohannPaez/AREP-SparkServiceApp.svg?style=svg)](https://circleci.com/gh/JohannPaez/AREP-SparkServiceApp)

## Docker

Para correr el servidor de funcionalidad, se utiliza el siguiente comando:

    docker run -dp port_machine:port_container najoh2907/sparkserviceapp

## Ejemplo

    docker run -dp 7000:6000 najoh2907/sparkserviceapp

## Construir Proyecto

    docker build -t najoh2907/sparkserviceapp .

## Subir Proyecto

    docker push najoh2907/sparkserviceapp
	
