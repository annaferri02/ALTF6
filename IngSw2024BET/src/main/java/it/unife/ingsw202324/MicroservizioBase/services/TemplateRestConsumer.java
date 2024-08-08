package it.unife.ingsw202324.MicroservizioBase.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.unife.ingsw202324.MicroservizioBase.models.Biglietto;
import it.unife.ingsw202324.MicroservizioBase.models.Evento;
import it.unife.ingsw202324.MicroservizioBase.models.Luogo;
import it.unife.ingsw202324.MicroservizioBase.models.Transazioni;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SpringBootApplication
public class TemplateRestConsumer {

    // URL base per chiamare Mockoon
    static String uriBaseMock = "http://localhost:3000/api/";

    // Metodo generico per chiamare un servizio REST e ottenere una risposta come stringa
    public static String callREST(String resourceName, String uriBase, boolean useMock) {
        RestClient restClient = RestClient.create();

        // Usa l'URL base di Mockoon se l'impostazione useMock è true
        if (useMock) {
            uriBase = uriBaseMock;
        }

        System.out.println("Request URI: " + uriBase + resourceName);

        return restClient.get()
                .uri(uriBase + resourceName)
                .retrieve()
                .body(String.class);
    }

    // Metodo per chiamare un servizio REST e ottenere un oggetto Biglietto
    public static Biglietto callREST2(String resourceName, String uriBase, boolean useMock) {
        RestClient restClient = RestClient.create();

        if (useMock) {
            uriBase = uriBaseMock;
        }

        System.out.println("Request URI: " + uriBase + resourceName);

        return restClient.get()
                .uri(uriBase + resourceName)
                .retrieve()
                .body(Biglietto.class);
    }

    // Metodo per chiamare un servizio REST e ottenere un oggetto Transazioni
    public static Transazioni callREST3(String resourceName, String uriBase, boolean useMock) {
        RestClient restClient = RestClient.create();

        if (useMock) {
            uriBase = uriBaseMock;
        }

        System.out.println("Request URI: " + uriBase + resourceName);

        return restClient.get()
                .uri(uriBase + resourceName)
                .retrieve()
                .body(Transazioni.class);
    }

    // Metodo per ottenere l'ID dell'organizzatore (mockato per il momento)
    public static String getIdOrg(String resourceName, String uriBase, boolean useMock) {
        RestClient restClient = RestClient.create();
        ObjectMapper objectMapper = new ObjectMapper();
        String idorg = null;

        if (useMock) {
            uriBase = uriBaseMock;
        }

        String fullUri = uriBase + resourceName;

        System.out.println("Request URI: " + uriBase + resourceName);

        try {
            // Recupera la risposta come stringa JSON
            String jsonResponse = restClient.get()
                    .uri(fullUri)
                    .retrieve()
                    .body(String.class);

            // Usa ObjectMapper per leggere il JSON
            JsonNode rootNode = objectMapper.readTree(jsonResponse);

            // Itera attraverso i valori del JSON
            Iterator<JsonNode> elements = rootNode.elements();
            idorg = elements.next().asText();

            System.out.println("Prices retrieved: " + idorg);
        } catch (Exception e) {
            System.err.println("Error while processing JSON: " + e.getMessage());
            e.printStackTrace();
        }

        return idorg;
    }

    // Metodo per ottenere un evento simulato
    public static Evento getEventoMock(String resourceName, String uriBase, boolean useMock) {
        RestClient restClient = RestClient.create();

        if (useMock) {
            uriBase = uriBaseMock;
        }

        String fullUri = uriBase + resourceName;
        System.out.println("Request URI: " + fullUri);

        try {
            // Recupera la risposta come stringa JSON
            String jsonResponse = restClient.get()
                    .uri(fullUri)
                    .retrieve()
                    .body(String.class);

            // Usa ObjectMapper per estrarre l'oggetto "evento"
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode eventoNode = rootNode.path("evento");

            // Deserializza l'oggetto "evento"
            Evento evento = objectMapper.treeToValue(eventoNode, Evento.class);

            System.out.println("Evento retrieved: " + evento);
            return evento;
        } catch (Exception e) {
            System.err.println("Error while fetching Evento: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    // Metodo per ottenere un luogo simulato
    public static Luogo getLuogoMock(String resourceName, String uriBase, boolean useMock) {
        RestClient restClient = RestClient.create();

        if (useMock) {
            uriBase = uriBaseMock;
        }

        String fullUri = uriBase + resourceName;
        System.out.println("Request URI: " + fullUri);

        try {
            // Recupera la risposta come stringa JSON
            String jsonResponse = restClient.get()
                    .uri(fullUri)
                    .retrieve()
                    .body(String.class);

            // Usa ObjectMapper per estrarre l'oggetto "luogo"
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode luogoNode = rootNode.path("luogo");

            // Deserializza l'oggetto "luogo"
            Luogo luogo = objectMapper.treeToValue(luogoNode, Luogo.class);

            System.out.println("Luogo retrieved: " + luogo);
            return luogo;
        } catch (Exception e) {
            System.err.println("Error while fetching Luogo: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    // Metodo per ottenere una lista di prezzi simulati
    public static List<String> getPricesMock(String resourceName, String uriBase, boolean useMock) {
        RestClient restClient = RestClient.create();
        List<String> prices = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        if (useMock) {
            uriBase = uriBaseMock;
        }

        String fullUri = uriBase + resourceName;
        System.out.println("Request URI: " + fullUri);

        try {
            // Recupera la risposta come stringa JSON
            String jsonResponse = restClient.get()
                    .uri(fullUri)
                    .retrieve()
                    .body(String.class);

            // Usa ObjectMapper per leggere il JSON
            JsonNode rootNode = objectMapper.readTree(jsonResponse);

            // Itera attraverso i valori del JSON
            Iterator<JsonNode> elements = rootNode.elements();
            while (elements.hasNext()) {
                JsonNode valueNode = elements.next();
                String value = valueNode.asText();
                prices.add(value);
            }

            System.out.println("Prices retrieved: " + prices);
        } catch (Exception e) {
            System.err.println("Error while processing JSON: " + e.getMessage());
            e.printStackTrace();
        }

        return prices;
    }
}
