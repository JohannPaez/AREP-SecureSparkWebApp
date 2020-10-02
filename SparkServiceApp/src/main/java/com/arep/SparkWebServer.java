package com.arep;


import static spark.Spark.port;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.secure;

import java.util.Date;
import java.util.concurrent.CopyOnWriteArrayList;

import com.arep.model.Message;
import com.google.gson.Gson;

/**
 * Clase principal que mantiene el servidor web corriendo
 * @author SebastianPaez
 *
 */
public class SparkWebServer {

	/**
	 * Ejecuta la aplicación para poder utilizar los servicios
	 * @param args Son los parametros al momento de ejecutar.
	 */
	public static void main(String... args) {
		CopyOnWriteArrayList<Message> messages = new CopyOnWriteArrayList<>();
		port(getPort());
		System.out.println("Puerto corriendo actualmente: "  + getPort());
		// Local
		//secure("keystores/SparkServiceAppKeyStore.p12", "prueba123", null, null);
		// AWS
		secure("keystores/SparkServiceAppKeyStore.p12", "prueba123", "keystores/SecureTrustStore", "prueba123");
		System.out.println("Paso secure");
		get("/service", (req, res) -> {
			System.out.println("Get /service");
			return "Este es el servicio de respuesta de mensajes!";
		});
		
		get("/messages", (req, res) -> {	
			System.out.println("Get /messages");
			return new Gson().toJson(messages);
		});
		
		post("/messages", (req, res) -> {			
			Gson g = new Gson(); 
			Message message = g.fromJson(req.body(), Message.class);
			Date date = new Date();
			message.setDate(date.toString());
			messages.add(message);
			return "El mensaje ha sido añadido con éxito!";
		});
	}

	/**
	 * Funcion que retorna el número del puerto por el cual se correrá el servicio.
	 * @return El número de puerto del servicio.
	 */
	private static int getPort() {
		if (System.getenv("PORT") != null) {
			return Integer.parseInt(System.getenv("PORT"));
		}
		return 4444;
	}

}