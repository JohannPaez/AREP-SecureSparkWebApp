# AREP-SecureSparkServiceApp

## Proyecto Principal
El proyecto principal se encuentra disponible [aquí](https://github.com/JohannPaez/AREP-SecureSparkWebApp).

## Integración Continua

[![CircleCI](https://circleci.com/gh/JohannPaez/AREP-SecureSparkServiceApp.svg?style=svg)](https://circleci.com/gh/JohannPaez/AREP-SecureSparkServiceApp)

## Docker

Para correr el servidor de autenticación, que se comunicará con el servidor de mensajes, se utiliza el siguiente comando:

    docker run -dp port_machine:port_container -e HOSTPORT="ip_other_machine"+ ":" + "port_other_machine" najoh2907/securesparkserviceapp
    
## Ejemplo
														
    docker run -dp 8000:6000 -e HOSTPORT=192.168.4.82:7000 najoh2907/securesparkserviceapp

## Construir Proyecto

    docker build -t najoh2907/securesparkserviceapp .

## Subir Proyecto

    docker push najoh2907/securesparkserviceapp
	
		
