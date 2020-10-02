package com.arep.model;

/**
 * Clase message que guardará la información de los mensajes
 * @author SebastianPaez
 *
 */
public class Message {
	private String message;
	private String date;
	
	/**
	 * Crea un nuevo message con la descripción y la date de creación
	 * @param message
	 * @param date
	 */
	public Message(String message, String date) {
		setMessage(message);
		setDate(date);
	}

	/**
	 * Retorna el message
	 * @return message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Cambia el contenido del message
	 * @param message Es el nuevo contenido del message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Retorna la date
	 * @return date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Cambia el contenido de la date
	 * @param date Es la nueva date
	 */
	public void setDate(String date) {
		this.date = date;
	}
	
	/**
	 * Retorna el contenido del message en formato JSON
	 */
	public String toString() {
		return "{\"message\": " + "\"" + message +  "\"" + ", \"date\": " + "\"" + date  +  "\"" + "}";
	}
}
