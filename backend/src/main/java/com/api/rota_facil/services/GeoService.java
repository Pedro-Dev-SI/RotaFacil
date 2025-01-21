package com.api.rota_facil.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GeoService {

    public Map<String, Double> getCoordinates(String endereco) {
        String url = UriComponentsBuilder.fromHttpUrl("https://nominatim.openstreetmap.org/search?")
                .queryParam("q", endereco)
                .queryParam("format", "json") // Adicione o parâmetro para especificar o formato JSON
                .queryParam("addressdetails", 1) // Adicione detalhes do endereço, se necessário
                .toUriString();

        // Criando o RestTemplate e definindo o cabeçalho User-Agent
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "RotaFacil/1.0"); // Substitua com o nome do seu app ou serviço
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Fazendo a requisição com o cabeçalho correto
        String response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();

        // Verificando a resposta
        if (response != null && !response.startsWith("<")) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                List<Map<String, Object>> results = objectMapper.readValue(response, List.class);
                if (!results.isEmpty()) {
                    Map<String, Double> coordinates = new HashMap<>();
                    coordinates.put("latitude", Double.parseDouble(results.get(0).get("lat").toString()));
                    coordinates.put("longitude", Double.parseDouble(results.get(0).get("lon").toString()));
                    return coordinates;
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("A resposta não é um JSON válido: " + response);
        }

        return null;
    }
}
