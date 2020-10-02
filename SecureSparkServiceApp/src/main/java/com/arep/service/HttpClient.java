package com.arep.service;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyStore;

public class HttpClient {
    private String url;


    /**
     * Crea la conexión SSL segura, y la url dada una variable de entorno o localmente.
     */
    public HttpClient() {
        this.url = "https://"+getHostPort();
        //this.url = "https://ec2-54-196-253-253.compute-1.amazonaws.com:7000";
        //this.url = "https://localhost:4444";
        validateSSL();
    }

    /**
     * Obtiene la ip y puerto (ip:port) de una variable de entorno o retorna localhost:4444 por defecto
     * @return El nuevo host con el que se va a trabajar
     */
    private static String getHostPort() {
        if (System.getenv("HOSTPORT") != null) {
            System.out.println(System.getenv("HOSTPORT"));
            return System.getenv("HOSTPORT");
        }
        System.out.println("localhost:4444");
        return "localhost:4444";
    }

    /**
     * Obtiene los mensajes de la url asociada
     * @return Los mensajes de la url
     */
    public String getMessages() {
        try {
            URL siteURL = new URL(this.url + "/messages");
            URLConnection urlConnection = siteURL.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String inputLine = null;
            String res = "";
            while ((inputLine = reader.readLine()) != null) {
                res += inputLine + "\n";
            }
            reader.close();
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Añade un mensaje a la url asociada
     * @return El mensaje de respuesta al añadir
     */
    public String addMessage(String message) {
        try {
            URL siteURL = new URL(this.url + "/messages");
            HttpURLConnection urlConnection =  (HttpURLConnection) siteURL.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type","application/json");
            urlConnection.setDoOutput(true);
            PrintWriter printWriter = new PrintWriter(urlConnection.getOutputStream(), true);
            printWriter.write(message);
            printWriter.close();
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String inputLine = null;
            String res = "";
            while ((inputLine = reader.readLine()) != null) {
                res += inputLine + "\n";
            }
            reader.close();
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Realiza la conexión SSL de manera segura
     */
    public void validateSSL() {
        try {
            // Create a file and a password representation
            File trustStoreFile = new File("keystores/ServTrustStore");
            char[] trustStorePassword = "prueba123".toCharArray();

            // Load the trust store, the default type is "pkcs12", the alternative is "jks"
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(new FileInputStream(trustStoreFile), trustStorePassword);

            // Get the singleton instance of the TrustManagerFactory
            TrustManagerFactory tmf = TrustManagerFactory
                    .getInstance(TrustManagerFactory.getDefaultAlgorithm());

            // Itit the TrustManagerFactory using the truststore object
            tmf.init(trustStore);

            //Print the trustManagers returned by the TMF
            //only for debugging
            for(TrustManager t: tmf.getTrustManagers()){
                System.out.println(t);
            }

            //Set the default global SSLContext so all the connections will use it
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, tmf.getTrustManagers(), null);
            SSLContext.setDefault(sslContext);
            /*javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
                    (hostname, sslSession) -> true);*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
