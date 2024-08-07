package it.unife.ingsw202324.MicroservizioBase;

import it.unife.ingsw202324.MicroservizioBase.api.MainController;
import it.unife.ingsw202324.MicroservizioBase.models.Sponsorizzazione;
import it.unife.ingsw202324.MicroservizioBase.services.EventoService;
import it.unife.ingsw202324.MicroservizioBase.services.OrganizzatoreService;
import it.unife.ingsw202324.MicroservizioBase.services.SponsorizzazioneService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MainControllerTest {

    @Mock
    private OrganizzatoreService myService;

    @Mock
    private EventoService eventoService;

    @Mock
    private SponsorizzazioneService sponsorizzazioneService;

    @InjectMocks
    private MainController mainController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Testa il metodo getEventiOrganizzatore del MainController.
     * Questo metodo dovrebbe restituire un elenco di eventi per un organizzatore specifico.
     */
    @Test
    void testGetEventiOrganizzatore() {
        // Mock del comportamento di myService per restituire un elenco di eventi
        when(myService.getEventiOrganizzatore(anyString())).thenReturn(List.of("Evento1", "Evento2"));

        // Chiamata al metodo del controller
        List<String> eventi = mainController.getEventiOrganizzatore();

        // Stampa i dettagli della risposta per il debug
        System.out.println("TestGetEventiOrganizzatore - Eventi restituiti: " + eventi);

        // Verifica che la risposta non sia nulla e contenga gli eventi attesi
        assertNotNull(eventi);
        assertEquals(2, eventi.size());
        assertTrue(eventi.contains("Evento1"));
        assertTrue(eventi.contains("Evento2"));
    }

    /**
     * Testa il metodo infoPromo del MainController.
     * Questo metodo dovrebbe calcolare e restituire informazioni sulla promozione
     * basate sul nome dell'evento e sul tipo di promozione forniti nella richiesta.
     */
    @Test
    void testInfoPromo() {
        System.out.println("TestInfoPromo - Start");
        System.out.println("Test di verifica del metodo infoPromo() del controller MainController" +
                " che permette di calcolare e restituire informazioni sulla promozione basate sul nome dell'evento e sul tipo di promozione forniti nella richiesta.");
        // Mock del comportamento di eventoService per restituire una data di evento
        try {
            Date dataEvento = new SimpleDateFormat("yyyy-MM-dd").parse("2024-12-25");
            when(eventoService.getDataEvento(anyString())).thenReturn(dataEvento);
        } catch (Exception e) {
            fail("Errore nel parsing della data: " + e.getMessage());
        }

        // Prepara la richiesta con i dettagli dell'evento e della promozione
        Map<String, Object> request = new HashMap<>();
        request.put("nomeEvento", "Evento1");
        request.put("nomePromo", "1");

        // Chiamata al metodo del controller
        ResponseEntity<Map<String, Object>> response = mainController.infoPromo(request);

        // Stampa i dettagli della risposta per il debug
        System.out.println("TestInfoPromo - Response: " + response);

        // Verifica che la risposta non sia nulla e che abbia lo stato 200 OK
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());

        // Estrai e verifica il corpo della risposta
        Map<String, Object> responseBody = response.getBody();
        assertNotNull(responseBody);
        System.out.println("TestInfoPromo - Dettagli risposta: " + responseBody);
        assertEquals("Evento1", responseBody.get("Nome"));
        assertEquals("2024-12-25", responseBody.get("Data"));
        assertTrue(responseBody.containsKey("Giorni"));
        assertTrue(responseBody.containsKey("Prezzo"));
    }

    /**
     * Testa il metodo infoPromo del MainController con una richiesta non valida.
     * Questo metodo dovrebbe restituire un errore se i parametri richiesti non sono forniti.
     */
    @Test
    void testInfoPromoInvalidRequest() {
        System.out.println("TestInfoPromoInvalidRequest - Start");
        System.out.println("Test di verifica del metodo infoPromo() del controller MainController" +
                " che permette di calcolare e restituire informazioni sulla promozione basate sul nome dell'evento e sul tipo di promozione forniti nella richiesta.");
        // Prepara una richiesta con parametri mancanti
        Map<String, Object> request = new HashMap<>();
        // Missing required fields

        // Chiamata al metodo del controller
        ResponseEntity<Map<String, Object>> response = mainController.infoPromo(request);

        // Stampa i dettagli della risposta per il debug
        System.out.println("TestInfoPromoInvalidRequest - Response: " + response);

        // Verifica che la risposta abbia lo stato 400 Bad Request
        assertNotNull(response);
        assertEquals(400, response.getStatusCodeValue());
        System.out.println("TestInfoPromoInvalidRequest - Risposta per richiesta non valida: " + response.getBody());
    }

    /**
     * Testa il metodo processPromoOrder del MainController.
     * Questo metodo dovrebbe salvare una sponsorizzazione nel sistema
     * basata sui dettagli forniti nella richiesta.
     */
    @Test
    void testProcessPromoOrder() {
        System.out.println("TestProcessPromoOrder - Start");
        System.out.println("Test di verifica del metodo processPromoOrder() del controller MainController" +
                " che permette di inviare le promozioni selezionate in modo chiaro per poterle visualizzare correttamente nella pagina" +
                " di frontend. Calcolandone il costo e la durata della promozione.");
        // Mock del comportamento di eventoService per restituire un ID evento
        when(eventoService.getIDEvento(anyString())).thenReturn("123");

        // Mock del comportamento di sponsorizzazioneService per inserire una sponsorizzazione
        doNothing().when(sponsorizzazioneService).insert(any(Sponsorizzazione.class));

        // Prepara la richiesta con i dettagli della sponsorizzazione
        Map<String, Object> request = new HashMap<>();
        request.put("NomeEvento", "Evento1");
        request.put("Prezzo", "100");
        request.put("TipologiaPromozione", "Promo1");

        // Chiamata al metodo del controller
        ResponseEntity<Map<String, Object>> response = mainController.processPromoOrder(request);

        // Stampa i dettagli della risposta per il debug
        System.out.println("TestProcessPromoOrder - Response: " + response);

        // Verifica che la risposta non sia nulla e che abbia lo stato 200 OK
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());

        // Estrai e verifica il corpo della risposta
        Map<String, Object> responseBody = response.getBody();
        assertNotNull(responseBody);
        System.out.println("TestProcessPromoOrder - Dettagli risposta: " + responseBody);
        assertEquals("Sponsorizzazione salvata con successo", responseBody.get("message"));

        // Verifica che il metodo insert di sponsorizzazioneService sia stato chiamato
        verify(sponsorizzazioneService).insert(any(Sponsorizzazione.class));
    }

    /**
     * Testa il metodo processPromoOrder del MainController con una richiesta non valida.
     * Questo metodo dovrebbe restituire un errore se i parametri richiesti non sono forniti.
     */
    @Test
    void testProcessPromoOrderInvalidRequest() {
        System.out.println("TestProcessPromoOrderInvalidRequest - Start");
        System.out.println("Test di verifica del metodo processPromoOrder() del controller MainController" +
                " che permette di salvare una sponsorizzazione nel sistema basata sui dettagli forniti nella richiesta." +
                " Inoltre, il metodo restituisce un messaggio di conferma della sponsorizzazione che sara utilizzato anche dal gruppo che gestisce le notifiche.");

        // Prepara una richiesta con parametri mancanti
        Map<String, Object> request = new HashMap<>();
        // Missing required fields

        // Chiamata al metodo del controller
        ResponseEntity<Map<String, Object>> response = mainController.processPromoOrder(request);

        // Stampa i dettagli della risposta per il debug
        System.out.println("TestProcessPromoOrderInvalidRequest - Response: " + response);

        // Verifica che la risposta abbia lo stato 400 Bad Request
        assertNotNull(response);
        assertEquals(400, response.getStatusCodeValue());
        System.out.println("TestProcessPromoOrderInvalidRequest - Risposta per richiesta non valida: " + response.getBody());
    }
}
