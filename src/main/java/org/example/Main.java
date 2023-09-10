package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.IOException;
public class Main {
    public static void main(String[] args) {
        try {
            // Pedir al usuario que ingrese el nombre de la película
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Ingrese el nombre de la película: ");
            String movieName = reader.readLine(); // Lee la entrada del usuario

            // URL de la API de OMDb con el nombre de la película y tu token de acceso
            String apiKey = "87063ceb"; // Tu clave de API de OMDb
            String apiUrl = "http://www.omdbapi.com/?t=" + movieName + "&apikey=" + apiKey;

            // Crear una URL a partir de la URL de la API
            URL url = new URL(apiUrl);

            // Establecer una conexión HTTP
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            // Verificar el código de respuesta HTTP
            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {
                // Leer la respuesta JSON desde la API
                BufferedReader apiReader = new BufferedReader(new InputStreamReader(url.openStream()));
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = apiReader.readLine()) != null) {
                    response.append(line);
                }
                apiReader.close();

                // Imprimir la respuesta JSON
                System.out.println(response.toString());
            }
        } catch (IOException e) {
            e.printStackTrace(); // Manejo de excepciones en caso de error
        }
    }
}
