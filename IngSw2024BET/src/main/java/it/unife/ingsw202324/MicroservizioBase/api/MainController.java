package it.unife.ingsw202324.MicroservizioBase.api;

import it.unife.ingsw202324.MicroservizioBase.models.MyValues;
import it.unife.ingsw202324.MicroservizioBase.models.Sponsorizzazione;
import it.unife.ingsw202324.MicroservizioBase.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static org.bouncycastle.asn1.x500.style.BCStyle.L;

@RestController /* Annotation per definire che la classe risponderà tramite rest  */
@RequestMapping("/api") /* Annotation per definire il path della classe  */
public class MainController {

    @Autowired
    OrganizzatoreService myService;
    @Autowired
    EventoService eventoService;
    @Autowired
    SponsorizzazioneService sponsorizzazioneService;

    @CrossOrigin(origins = "http://localhost:5174") // Aggiungi questa linea
    @GetMapping("/getEventiOrganizzatore")
    public List<String> getEventiOrganizzatore() {
        String ID_Organizzatore = TemplateRestConsumer.getIdOrg("organizzatore", null, true);
        return myService.getEventiOrganizzatore(ID_Organizzatore);
    }

    @CrossOrigin(origins = "http://localhost:5174") // Aggiungi questa linea
    @PostMapping("/getInfoPromo")
    public ResponseEntity<Map<String, Object>> infoPromo(@RequestBody Map<String, Object> request) {
        // Estrai i dati dal map
        String nomeEvento = (String) request.get("nomeEvento");
        String nomePromo = (String) request.get("nomePromo");

        System.out.println("Request data: " + request);

        // Aggiungi log per il debug
        if (nomeEvento == null || nomeEvento.isEmpty()) {
            System.err.println("nomeEvento è null o vuoto");
            return ResponseEntity.badRequest().body(null);
        }
        if (nomePromo == null || nomePromo.isEmpty()) {
            System.err.println("nomePromo è null o vuoto");
            return ResponseEntity.badRequest().body(null);
        }


        // Esegui le operazioni necessarie con i dati ricevuti
        try {
            Date dataEvento = eventoService.getDataEvento(nomeEvento);
            System.out.println("Data evento: " + dataEvento);
            LocalDate dateFromString = dataEvento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate currentDate = LocalDate.now();
            Long giorni = ChronoUnit.DAYS.between(currentDate, dateFromString);// Corretto il calcolo della differenza

            Long Prezzo;

            switch (nomePromo) {
                case "1":
                    Prezzo = (long) (giorni * 0.8);
                    break;
                case "2":
                    Prezzo = (long) (giorni * 0.5);
                    break;
                case "3":
                    Prezzo = (long) (giorni * 0.65);
                    break;
                case "4":
                    Prezzo = (long) (giorni * 1.2);
                    break;
                default:
                    Prezzo = (long) (giorni * 1);
                    break;
            }


            // Estrai solo la parte della data (senza l'ora)
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dataEventoSoloData = dateFormat.format(dataEvento);

            Map<String, Object> response = new HashMap<>();
            response.put("Nome", nomeEvento);
            response.put("Data", dataEventoSoloData);
            response.put("Giorni", giorni);
            response.put("Prezzo", Prezzo);

            // Invio della risposta come JSON
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Log dell'eccezione
            System.err.println("Errore durante l'elaborazione della richiesta: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    @CrossOrigin(origins = "http://localhost:5174")
    @PostMapping("/processPromoOrder")
    public ResponseEntity<Map<String, Object>> processPromoOrder(@RequestBody Map<String, Object> request) {

        // Ottieni i valori dalla mappa con il tipo corretto
        String NomeEvento = request.get("NomeEvento") != null ? request.get("NomeEvento").toString() : null;
        String Prezzo = request.get("Prezzo") != null ? request.get("Prezzo").toString() : null;
        String Tipologia = request.get("TipologiaPromozione") != null ? request.get("TipologiaPromozione").toString() : null;

        if (NomeEvento == null || NomeEvento.isEmpty()) {
            System.err.println("NomeEvento è null o vuoto");
            return ResponseEntity.badRequest().body(createErrorResponse("NomeEvento è null o vuoto"));
        }
        if (Prezzo == null || Prezzo.isEmpty()) {
            System.err.println("Prezzo è null o vuoto");
            return ResponseEntity.badRequest().body(createErrorResponse("Prezzo è null o vuoto"));
        }
        if (Tipologia == null || Tipologia.isEmpty()) {
            System.err.println("Tipologia è null o vuoto");
            return ResponseEntity.badRequest().body(createErrorResponse("Tipologia è null o vuoto"));
        }

        String IdEvento = eventoService.getIDEvento(NomeEvento);
        if (IdEvento == null) {
            System.err.println("ID Evento non trovato per il NomeEvento fornito");
            return ResponseEntity.badRequest().body(createErrorResponse("ID Evento non trovato"));
        }

        Sponsorizzazione sponsorizzazione = new Sponsorizzazione();
        sponsorizzazione.setID_evento(IdEvento);
        sponsorizzazione.setCosto(Long.parseLong(Prezzo));
        sponsorizzazione.setTipologia(Tipologia);
        sponsorizzazione.setTimestamp(LocalDateTime.now());

        try {
            sponsorizzazioneService.insert(sponsorizzazione);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Sponsorizzazione salvata con successo");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.err.println("Errore durante l'elaborazione della richiesta: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).body(createErrorResponse("Errore interno del server"));
        }
    }

    private Map<String, Object> createErrorResponse(String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", message);
        return response;
    }

}