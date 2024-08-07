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

    // Utilizza un metodo di supporto per simulare una risposta JSON generica
    private String mockJsonResponse(String key, String value) {
        return String.format("{ \"%s\": \"%s\" }", key, value);
    }

    @Test
    void testGetIdOrg() {
        System.out.println("TestGetIdOrg - Start");
        System.out.println("Test di verifica del metodo getIdOrg() del servizio TemplateRestConsumer" +
                " che restituisce l'ID di un organizzatore.");
        // Testa la logica del metodo getIdOrg senza chiamare REST
        String resourceName = "organizzatore";
        String uriBase = "http://localhost:3000/api/";
        String id = TemplateRestConsumer.getIdOrg(resourceName, uriBase, true);
        assertEquals("O0001", id);
    }

    @Test
    void testGetEventoMock() {
        System.out.println("TestGetEventoMock - Start");
        System.out.println("Test di verifica del metodo getEventoMock() del servizio TemplateRestConsumer" +
                " che restituisce un evento specifico.");

        // Prepara la risposta simulata
        String jsonResponse = mockJsonResponse("evento", "{\"id\": \"123\", \"nome\": \"Evento Test\"}");
        String resourceName = "evento";
        String uriBase = "http://localhost:3000/api/";
        when(restTemplate.getForObject(uriBase + resourceName, String.class)).thenReturn(jsonResponse);

        // Chiama il metodo
        Evento evento = TemplateRestConsumer.getEventoMock(resourceName, uriBase, true);

        // Verifica il risultato
        assertNotNull(evento);
        assertEquals("E0001", evento.getID_Evento());
        assertEquals("Concerto Live", evento.getNome());

    }

    @Test
    void testGetLuogoMock() {
        System.out.println("TestGetLuogoMock - Start");
        System.out.println("Test di verifica del metodo getLuogoMock() del servizio TemplateRestConsumer" +
                " che restituisce un luogo specifico per un evento.");

        // Prepara la risposta simulata
        String jsonResponse = mockJsonResponse("luogo", "{\"id\": \"456\", \"nome\": \"Luogo Test\"}");
        String resourceName = "luogo";
        String uriBase = "http://localhost:3000/api/";
        when(restTemplate.getForObject(uriBase + resourceName, String.class)).thenReturn(jsonResponse);

        // Chiama il metodo
        Luogo luogo = TemplateRestConsumer.getLuogoMock(resourceName, uriBase, true);

        // Verifica il risultato
        assertNotNull(luogo);
        assertEquals("L00001", luogo.getIdLuogo());
        assertEquals("Teatro Nuovo", luogo.getNome());
    }

    @Test
    void testGetPricesMock() {
        System.out.println("TestGetPricesMock - Start");
        System.out.println("Test di verifica del metodo getPricesMock() del servizio TemplateRestConsumer" +
                " che restituisce una lista di prezzi per un evento specifico.");


        // Prepara la risposta simulata
        String jsonResponse = "[\"40\", \"90\", \"60\", \"80\", \"80\"]";
        String resourceName = "prices";
        String uriBase = "http://localhost:3000/api/";
        when(restTemplate.getForObject(uriBase + resourceName, String.class)).thenReturn(jsonResponse);

        // Chiama il metodo
        List<String> prices = TemplateRestConsumer.getPricesMock(resourceName, uriBase, true);

        // Verifica il risultato
        assertNotNull(prices);
        assertEquals(5, prices.size());
        assertTrue(prices.contains("40"));
        assertTrue(prices.contains("90"));
        assertTrue(prices.contains("60"));
        assertTrue(prices.contains("80"));
        assertTrue(prices.contains("80"));
    }
}
