package com.api.rota_facil.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GeoService {

    private static final String API_URL = "https://api.distancematrix.ai/maps/api/geocode/json";
    private static final String API_KEY = "Iam0oM7W93LchCowE4lfQOZTv3M155STq4HVNKqbkjuH4GPEqmboqR1buXXd1FxO";

    public Map<String, Double> getCoordinates(String endereco) {
        Map<String, Double> coordinates = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Constrói a URL com o endereço e a chave da API
            String url = String.format("%s?address=%s&key=%s", API_URL, endereco.replace(" ", "+"), API_KEY);

            // Faz a requisição usando RestTemplate
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            // Converte a resposta em JsonNode (usando Jackson)
            JsonNode jsonResponse = objectMapper.readTree(response.getBody());

            // Verifica o status da resposta
            String status = jsonResponse.get("status").asText();
            if ("OK".equals(status)) {
                // Extrai latitude e longitude do primeiro resultado
                JsonNode location = jsonResponse
                        .get("result")
                        .get(0)
                        .get("geometry")
                        .get("location");

                double latitude = location.get("lat").asDouble();
                double longitude = location.get("lng").asDouble();

                // Adiciona ao mapa de coordenadas
                coordinates.put("latitude", latitude);
                coordinates.put("longitude", longitude);
            } else {
                System.out.println("Erro ao obter coordenadas: " + status);
            }
        } catch (Exception e) {
            System.out.println("Erro durante a requisição: " + e.getMessage());
        }

        return coordinates;
    }
}
