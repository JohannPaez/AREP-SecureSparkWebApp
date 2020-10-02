package com.arep;


import com.arep.model.User;
import com.arep.service.HttpClient;
import com.google.gson.Gson;
import org.bouncycastle.util.encoders.Hex;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import spark.Request;
import spark.Response;
import spark.staticfiles.StaticFilesConfiguration;

import static spark.Spark.*;

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
		HttpClient services = new HttpClient();
		int puerto = getPort();
		port(getPort());
		System.out.println("Puerto corriendo actualmente: "  + puerto);
		//staticFiles.location("/public");
		secure("keystores/SecureSparkServiceApp.p12", "prueba123", null, null);
		//secure("keystoresLocal/ecikeystore.p12", "prueba123", null, null);
		System.out.println("Paso secure");
		before("protected/*", (request, response) -> {
			System.out.println("protected/*");
			handle(request);
			boolean logged = request.session().attribute("isLogin");
			if (!logged) {
				halt(401, "<h1> 401 Unauthorized </h1>");
				//response.redirect("/login.html");
			}
		});

		before("/login.html",((request, response) -> {
			System.out.println("Get /Login.html");
			handle(request);
			boolean isLogged =request.session().attribute("isLogin");
			if (isLogged) {
				response.redirect("protected/messages.html");
			}
		}));

		StaticFilesConfiguration staticHandler = new StaticFilesConfiguration();
		staticHandler.configure("/public");
		before((request, response) ->
				staticHandler.consume(request.raw(), response.raw()));

		get("/isLogin", (request, response) -> {
			handle(request);
			boolean session = request.session().attribute("isLogin");
			return String.valueOf(session);
		});

		get("/protected/logout", (request, response) -> {
			request.session().attribute("isLogin", false);
			return "Ha cerrado sesión satisfactoriamente!";
		});

		get("/", (request, response) -> {
			response.redirect("/login.html");
			return "";
		});

		post("/login", (request, response) -> {
			User user = new Gson().fromJson(request.body(), User.class);
			String res = "Error, el usuario o la contraseña son incorrectos";
			if (user.getEmail().equals("johann@mail.com") && encryptPassword(user.getPassword()).equals(encryptPassword("johann123"))) {
				request.session(true).attribute("isLogin", true);
				res = "Se ha logeado satisfactoriamente!";
			} else {
				response.status(400);
			}
			return res;
		});

		get("/protected/messages", (request, response) -> services.getMessages());
		post("/protected/messages", (request, response) -> services.addMessage(request.body()));


	}

	/**
	 * Cifra un texto con SHA-256 y la retorna
	 * @param password Es la contraseña a Cifrar
	 * @return La contraseña cifrada
	 */
	private static String encryptPassword(String password) {
		MessageDigest digest = null;
		String sha256hex = password;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(
					password.getBytes(StandardCharsets.UTF_8));
			sha256hex = new String(Hex.encode(hash));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return sha256hex;
	}

	/**
	 * Funcion que retorna el número del puerto por el cual se correrá el servicio.
	 * @return El número de puerto del servicio.
	 */
	private static int getPort() {
		if (System.getenv("PORT") != null) {
			return Integer.parseInt(System.getenv("PORT"));
		}
		return 4567;
	}

	/**
	 * Crea una nueva sesisión y crea el atribute login si la sesión es nueva.
	 * @param request Es la solicitud a mirar
	 */
	private static void handle(Request request) {
		System.out.println("REQUEST " + request.pathInfo());
		request.session(true);
		if (request.session().isNew()) {
			request.session().attribute("isLogin", false);
		}
	}
}