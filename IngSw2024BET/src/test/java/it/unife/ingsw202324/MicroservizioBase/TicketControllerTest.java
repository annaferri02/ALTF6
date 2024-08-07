package it.unife.ingsw202324.MicroservizioBase;

import it.unife.ingsw202324.MicroservizioBase.api.TicketController;
import it.unife.ingsw202324.MicroservizioBase.models.*;
import it.unife.ingsw202324.MicroservizioBase.services.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TicketControllerTest {

    @Mock
    private ServiceTicket ticketService;  // Servizio per la gestione dei biglietti

    @Mock
    private EventoService eventoService;  // Servizio per la gestione degli eventi

    @Mock
    private LuogoService luogoService;    // Servizio per la gestione dei luoghi

    @Mock
    private ServiceTransazioni transazioniService;  // Servizio per la gestione delle transazioni

    @InjectMocks
    private TicketController ticketController;  // Controller da testare

    @BeforeEach
    void setUp() {
        // Inizializza i mock e le dipendenze iniettate
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetInfoBiglietti() {
        // Informazioni sul test
        System.out.println("TestGetInfoBiglietti - Start");
        System.out.println("Verifica del metodo getInfoBiglietti() per ottenere le informazioni sui posti occupati.");

        // Prepara i dati simulati
        TicketData mockTicketData = new TicketData();
        mockTicketData.setEvento(new Evento());
        mockTicketData.setLuogo(new Luogo());
        mockTicketData.setBiglietti(List.of());
        mockTicketData.setPrezzi(List.of());

        // Configura il comportamento simulato del servizio ticketService
        when(ticketService.getPostiByIdEvento(anyString())).thenReturn(List.of());

        // Chiama il metodo del controller
        TicketData result = ticketController.getInfoBiglietti();

        // Verifica il risultato
        System.out.println("TestGetInfoBiglietti - Risultato: " + result);
        assertNotNull(result);
        verify(ticketService).getPostiByIdEvento(anyString());  // Verifica che il metodo del servizio sia stato chiamato
    }

    @Test
    void testPay() {
        // Informazioni sul test
        System.out.println("TestPay - Start");
        System.out.println("Verifica del metodo pay() per effettuare il pagamento di biglietti e inserire la transazione.");

        // Dati simulati per Biglietto
        Biglietto mockBiglietto = new Biglietto();
        mockBiglietto.setPrezzo(90L);

        // Configura il comportamento simulato dei servizi
        when(ticketService.addElement(any(Biglietto.class))).thenReturn(mockBiglietto);
        doNothing().when(transazioniService).insert(any(Transazioni.class));

        // Crea una richiesta di esempio
        Map<String, Object> request = Map.of(
                "utente", "user1",
                "evento", "event1",
                "biglietti", List.of(Map.of(
                        "Tipo", "Tribuna Sinistra",
                        "Posto", 1
                ))
        );

        // Chiama il metodo del controller
        ResponseEntity<Map<String, Object>> response = ticketController.pay(request);

        // Verifica il risultato
        System.out.println("TestPay - Response: " + response);
        System.out.println("TestPay - Response Body: " + response.getBody());

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());  // Verifica che lo stato della risposta sia 200 OK
        verify(ticketService).addElement(any(Biglietto.class));  // Verifica che il metodo addElement sia stato chiamato
        verify(transazioniService).insert(any(Transazioni.class));  // Verifica che il metodo insert sia stato chiamato
    }

    @Test
    void testBookSeats() {
        // Informazioni sul test
        System.out.println("TestBookSeats - Start");
        System.out.println("Verifica del metodo bookSeats() per prenotare posti e restituire un messaggio di conferma.");

        // Crea una richiesta di esempio
        Map<String, Object> request = Map.of(
                "tribuna", "Tribuna Sinistra",
                "postiSelezionati", List.of(1, 2, 3),
                "count", 2
        );

        // Chiama il metodo del controller
        ResponseEntity<Map<String, Object>> response = ticketController.bookSeats(request);

        // Verifica il risultato
        System.out.println("TestBookSeats - Response: " + response);
        System.out.println("TestBookSeats - Response Body: " + response.getBody());

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());  // Verifica che lo stato della risposta sia 200 OK
    }
}
