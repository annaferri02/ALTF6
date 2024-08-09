package it.unife.ingsw202324.MicroservizioBase.api;

import it.unife.ingsw202324.MicroservizioBase.models.*;
import it.unife.ingsw202324.MicroservizioBase.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController /* Annotation per definire che la classe risponderà tramite rest  */
@RequestMapping("/ticket") /* Annotation per definire il path della classe  */
public class TicketController {
    @Autowired
    ServiceTicket ticketService;
    @Autowired
    EventoService eventoService;
    @Autowired
    LuogoService luogoService;
    @Autowired
    ServiceTransazioni transazioniService;


  @CrossOrigin(origins = "http://localhost:5173") // Aggiungi questa linea
  @GetMapping("/getInfoBiglietti")
  public TicketData getInfoBiglietti() {
      TicketData ticketData = new TicketData();

      ticketData.setEvento(TemplateRestConsumer.getEventoMock("evento", null, true));

      ticketData.setLuogo(TemplateRestConsumer.getLuogoMock("luogo", null, true));

      ticketData.setBiglietti(ticketService.getPostiByIdEvento(ticketData.getEvento().getID_Evento()));

      ticketData.setPrezzi(TemplateRestConsumer.getPricesMock("prices", null, true));

      return ticketData;
  }



    @CrossOrigin(origins = "http://localhost:5173") // Aggiungi questa linea
    @PostMapping("/Pagamento")
    public ResponseEntity<Map<String, Object>> pay(@RequestBody Map<String, Object> request) {
        try {
            String utente = (String) request.get("utente");
            String evento = (String) request.get("evento");
            List<Map<String, Object>> biglietti = (List<Map<String, Object>>) request.get("biglietti");

            if (utente == null || evento == null || biglietti == null) {
                return ResponseEntity.badRequest().body(Map.of("message", "Dati mancanti nella richiesta"));
            }

            // Esegui le operazioni necessarie con i dati ricevuti
            List<Biglietto> bigliettiSalvati = new ArrayList<>();

            for (Map<String, Object> posto : biglietti) {
                String tipo = (String) posto.get("Tipo");
                Integer postoNumero = (Integer) posto.get("Posto");

                Biglietto biglietto = new Biglietto();
                biglietto.setID_utente(utente);
                biglietto.setID_evento(evento);
                biglietto.setTipologia(tipo);
                biglietto.setPosto(postoNumero);
                biglietto.setID_Biglietto(RandomString(10));

                switch (biglietto.getTipologia()){
                    case "Tribuna Sinistra":
                        biglietto.setPrezzo(90L);
                        break;
                    case "Tribuna Destra":
                        biglietto.setPrezzo(90L);
                        break;
                    case "Tribuna Centrale":
                        biglietto.setPrezzo(70L);
                        break;
                    case "Parterre":
                        biglietto.setPrezzo(50L);
                        break;
                    case "PIT":
                        biglietto.setPrezzo(50L);
                        break;
                    default:
                        biglietto.setPrezzo(100L);
                        break;
                }

                Biglietto bigliettoInserito = ticketService.addElement(biglietto);
                bigliettiSalvati.add(bigliettoInserito);
            }

            Transazioni transazione = new Transazioni();

            transazione.setIdutente(utente);
            Long costoTotale = 0L;
            for (Biglietto biglietto : bigliettiSalvati) {
                costoTotale += biglietto.getPrezzo();
            }

            transazione.setCosto(costoTotale);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedTimestamp = LocalDateTime.now().format(formatter);
            transazione.setTimestamp(formattedTimestamp);
            transazioniService.insert(transazione);


            Map<String, Object> response = new HashMap<>();
            response.put("message", "Biglietti salvati con successo");
            response.put("esitoPAGAMENTO", "OK");
            response.put("biglietti", bigliettiSalvati);

            // Invio della risposta come JSON
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("", "Errore durante il salvataggio dei biglietti", "error", e.getMessage(), "esitoPAGAMENTO", "KO"));
        }
    }


    @CrossOrigin(origins = "http://localhost:5173") // Aggiungi questa linea
    @PostMapping("/gotoPagamento")
    public ResponseEntity<Map<String, Object>> bookSeats(@RequestBody Map<String, Object> request) {
        // Estrai i dati dal map
        String tribuna = (String) request.get("tribuna");
        List<Integer> postiSelezionati = (List<Integer>) request.get("postiSelezionati");
        int count = (int) request.get("count");

        // Esegui le operazioni necessarie con i dati ricevuti

        List<Map<String, Object>> postiDettagliati = new ArrayList<>();

        for (Integer posto : postiSelezionati) {
            Map<String, Object> postoInfo = new HashMap<>();
            postoInfo.put("Tipo", determinareTipo(posto));
            postoInfo.put("Posto", posto);
            postiDettagliati.add(postoInfo);
        }

        // Aggiunta di posti con tipo "tribuna" per 'count' volte
        for (int i = 0; i < count; i++) {
            Map<String, Object> postoInfo = new HashMap<>();
            postoInfo.put("Tipo", tribuna);
            postoInfo.put("Posto", 0);
            postiDettagliati.add(postoInfo);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("posti", postiDettagliati);

        // Invio della risposta come JSON
        return ResponseEntity.ok(response);
    }


    @RequestMapping("/callREST") /* Annotation per definire il path del metodo (relativo alla classe)  */
    public Biglietto callRest() {
        return TemplateRestConsumer.callREST2("new", null, true);
    }


    private String determinareTipo(int posto) {
        if (posto < 101){
            return "Tribuna Sinistra";
        } else if (posto >= 101 && posto < 201){
            return "Tribuna Destra";
        } else {
            return "Tribuna Centrale";
        }
    }

    private static String RandomString(int n){
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            int index = (int)(AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }

        return sb.toString();
    }



}


