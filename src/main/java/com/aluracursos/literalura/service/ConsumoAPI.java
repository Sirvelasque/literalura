package com.aluracursos.literalura.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoAPI {
    public String obtenerDatos(String url) {

        HttpClient client = HttpClient.newBuilder()
                .followRedirects(Redirect.NORMAL)
                .build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Verificar el código de estado de la respuesta
            if (response.statusCode() == 200) {
                // La respuesta fue exitosa
                String responseBody = response.body();
                if (responseBody != null && !responseBody.isEmpty()) {
                    return responseBody;
                } else {
                    System.out.println("Response Body is null or empty");
                    return null;
                }
            } else {
                // La respuesta no fue exitosa
                System.out.println("HTTP Status Code: " + response.statusCode());
                System.out.println("Response Headers: " + response.headers());
                return null;
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

