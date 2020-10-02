package com.arep.model;


/**
 * Calse Usuario para verificar autenticación
 */
public class User {

    private String email;
    private String password;


    /**
     * Crea un usaurio dado su correo y contraseña
     * @param email
     * @param password
     */
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * Obtiene el email del usuario
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Cambia el email del usuario
     * @param email Es el nuevo email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene la contraseña del usuario
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Cambia la contraseña del usuario
     * @param password Es la nueva contraseña del usuario
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
