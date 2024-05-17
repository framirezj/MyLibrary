package com.framirezj.MyLibrary.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoApi {

    public String obtenerDatos(String url){

        //cliente
        HttpClient client = HttpClient.newHttpClient();

        //request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        //response
        HttpResponse<String> response = null;

        try{
            response = client.send(request, HttpResponse.BodyHandlers.ofString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String json = response.body();
        return json;

    }

}
