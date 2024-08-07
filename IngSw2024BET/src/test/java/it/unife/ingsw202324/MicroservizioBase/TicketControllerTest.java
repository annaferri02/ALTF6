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
    private ServiceTicket ticketService;

    @Mock
    private EventoService eventoService;

    @Mock
    private LuogoService luogoService;

    @Mock
    private ServiceTransazioni transazioniService;

    @InjectMocks
    private TicketController ticketController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetInfoBiglietti() {
        System.out.println("TestGetInfoBiglietti - Start");
        System.out.println("Test di verifica del metodo getInfoBiglietti() del controller TicketController" +
                " che restituisce le informazioni sui posti gia occupati per un evento" +
                " in modo tale da poter visualizzare nella pagina di acquisto i posti disponibili e quelli gia occupati.");

        TicketData mockTicketData = new TicketData();
        mockTicketData.setEvento(new Evento());
        mockTicketData.setLuogo(new Luogo());
        mockTicketData.setBiglietti(List.of());
        mockTicketData.setPrezzi(List.of());

        when(ticketService.getPostiByIdEvento(anyString())).thenReturn(List.of());

        TicketData result = ticketController.getInfoBiglietti();

        System.out.println("TestGetInfoBiglietti - Risultato: " + result);
        assertNotNull(result);
        verify(ticketService).getPostiByIdEvento(anyString());
    }

    @Test
    void testPay() {
        System.out.println("TestPay - Start");
        System.out.println("Test di verifica del metodo pay() del controller TicketController" +
                " che permette di effettuare il pagamento di uno o piu biglietti per un evento"+
                " , di inserire la transazione nel database e i biglietti acquistati con tutte le specifiche."
                + " Inoltre, il metodo restituisce un messaggio di conferma del pagamento che sara utilizzato anche dal gruppo che gestisce le notifiche.");


        // Mock the behavior of ticketService.addElement
        Biglietto mockBiglietto = new Biglietto();
        mockBiglietto.setPrezzo(90L);
        when(ticketService.addElement(any(Biglietto.class))).thenReturn(mockBiglietto);

        // Mock the behavior of transazioniService.insert
        doNothing().when(transazioniService).insert(any(Transazioni.class));

        // Create a sample request
        Map<String, Object> request = Map.of(
                "utente", "user1",
                "evento", "event1",
                "biglietti", List.of(Map.of(
                        "Tipo", "Tribuna Sinistra",
                        "Posto", 1
                ))
        );

        // Call the controller method
        ResponseEntity<Map<String, Object>> response = ticketController.pay(request);

        System.out.println("TestPay - Response: " + response);
        System.out.println("TestPay - Response Body: " + response.getBody());

        // Verify that the response status is 200 OK
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());

        // Verify that the ticketService.addElement was called with the expected Biglietto
        verify(ticketService).addElement(any(Biglietto.class));

        // Verify that the transazioniService.insert was called
        verify(transazioniService).insert(any(Transazioni.class));
    }

    @Test
    void testBookSeats() {
        System.out.println("TestBookSeats - Start");
        System.out.println("Test di verifica del metodo bookSeats() del controller TicketController" +
                " che permette di prenotare uno o piu posti per un evento, di inserire la prenotazione nel database e i posti prenotati con tutte le specifiche e mandare la rielaborazione dei biglietti al frontend per la visualizzazione in modo coerente."
                + " Inoltre, il metodo restituisce un messaggio di conferma della prenotazione che sara utilizzato anche dal gruppo che gestisce le notifiche.");

        Map<String, Object> request = Map.of(
                "tribuna", "Tribuna Sinistra",
                "postiSelezionati", List.of(1, 2, 3),
                "count", 2
        );

        ResponseEntity<Map<String, Object>> response = ticketController.bookSeats(request);

        System.out.println("TestBookSeats - Response: " + response);
        System.out.println("TestBookSeats - Response Body: " + response.getBody());

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
    }
}
