package it.unife.ingsw202324.MicroservizioBase;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.unife.ingsw202324.MicroservizioBase.models.Biglietto;
import it.unife.ingsw202324.MicroservizioBase.models.Evento;
import it.unife.ingsw202324.MicroservizioBase.models.Luogo;
import it.unife.ingsw202324.MicroservizioBase.models.Transazioni;
import it.unife.ingsw202324.MicroservizioBase.services.TemplateRestConsumer;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TemplateRestConsumerTest {

    private final RestTemplate restTemplate = mock(RestTemplate.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    // Metodo di supporto per creare risposte JSON simulate
    private String mockJsonResponse(String key, String value) {
        return String.format("{ \"%s\": \"%s\" }", key, value);
    }

    @Test
    void testGetIdOrg() {
        // Informazioni sul test
        System.out.println("TestGetIdOrg - Start");
        System.out.println("Verifica del metodo getIdOrg() per ottenere l'ID dell'organizzatore.");

        // Test del metodo getIdOrg senza chiamate reali a REST
        String resourceName = "organizzatore";
        String uriBase = "http://localhost:3000/api/";
        String id = TemplateRestConsumer.getIdOrg(resourceName, uriBase, true);

        // Verifica del risultato
        assertEquals("O0001", id);
    }

    @Test
    void testGetEventoMock() {
        // Informazioni sul test
        System.out.println("TestGetEventoMock - Start");
        System.out.println("Verifica del metodo getEventoMock() per ottenere un evento specifico.");

        // Prepara la risposta simulata per l'evento
        String jsonResponse = mockJsonResponse("evento", "{\"id\": \"123\", \"nome\": \"Evento Test\"}");
        String resourceName = "evento";
        String uriBase = "http://localhost:3000/api/";
        when(restTemplate.getForObject(uriBase + resourceName, String.class)).thenReturn(jsonResponse);

        // Chiama il metodo e verifica il risultato
        Evento evento = TemplateRestConsumer.getEventoMock(resourceName, uriBase, true);
        assertNotNull(evento);
        assertEquals("E0001", evento.getID_Evento());
        assertEquals("Concerto Live", evento.getNome());
    }

    @Test
    void testGetLuogoMock() {
        // Informazioni sul test
        System.out.println("TestGetLuogoMock - Start");
        System.out.println("Verifica del metodo getLuogoMock() per ottenere un luogo specifico.");

        // Prepara la risposta simulata per il luogo
        String jsonResponse = mockJsonResponse("luogo", "{\"id\": \"456\", \"nome\": \"Luogo Test\"}");
        String resourceName = "luogo";
        String uriBase = "http://localhost:3000/api/";
        when(restTemplate.getForObject(uriBase + resourceName, String.class)).thenReturn(jsonResponse);

        // Chiama il metodo e verifica il risultato
        Luogo luogo = TemplateRestConsumer.getLuogoMock(resourceName, uriBase, true);
        assertNotNull(luogo);
        assertEquals("L00001", luogo.getIdLuogo());
        assertEquals("Teatro Nuovo", luogo.getNome());
    }

    @Test
    void testGetPricesMock() {
        // Informazioni sul test
        System.out.println("TestGetPricesMock - Start");
        System.out.println("Verifica del metodo getPricesMock() per ottenere una lista di prezzi.");

        // Prepara la risposta simulata per i prezzi
        String jsonResponse = "[\"40\", \"90\", \"60\", \"80\", \"80\"]";
        String resourceName = "prices";
        String uriBase = "http://localhost:3000/api/";
        when(restTemplate.getForObject(uriBase + resourceName, String.class)).thenReturn(jsonResponse);

        // Chiama il metodo e verifica il risultato
        List<String> prices = TemplateRestConsumer.getPricesMock(resourceName, uriBase, true);
        assertNotNull(prices);
        assertEquals(5, prices.size());
        assertTrue(prices.contains("40"));
        assertTrue(prices.contains("90"));
        assertTrue(prices.contains("60"));
        assertTrue(prices.contains("80"));
        assertTrue(prices.contains("80"));
    }
}
