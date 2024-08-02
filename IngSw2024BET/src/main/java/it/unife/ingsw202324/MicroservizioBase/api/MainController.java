package it.unife.ingsw202324.MicroservizioBase.api;

import it.unife.ingsw202324.MicroservizioBase.models.MyValues;
import it.unife.ingsw202324.MicroservizioBase.services.EventoService;
import it.unife.ingsw202324.MicroservizioBase.services.MyService;
import it.unife.ingsw202324.MicroservizioBase.services.OrganizzatoreService;
import it.unife.ingsw202324.MicroservizioBase.services.TemplateRestConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @CrossOrigin(origins = "http://localhost:5173") // Aggiungi questa linea
    @GetMapping("/getEventiOrganizzatore")
    public List<String> getEventiOrganizzatore() {
        String ID_Organizzatore = TemplateRestConsumer.getIdOrg("organizzatore", null, true);
        return myService.getEventiOrganizzatore(ID_Organizzatore);
    }

    @CrossOrigin(origins = "http://localhost:5173") // Aggiungi questa linea
    @PostMapping("/getInfoPromo")
    public ResponseEntity<Map<String, Object>> infoPromo(@RequestBody Map<String, Object> request) {
        // Estrai i dati dal map
        String nomeEvento = (String) request.get("nomeEvento");
        String nomePromo = (String) request.get("nomePromo");

        System.out.println("Request data: " + request);



        // Esegui le operazioni necessarie con i dati ricevuti
        Date dataEvento = eventoService.getDataEvento(nomeEvento);
        LocalDate dateFromString = dataEvento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate currentDate = LocalDate.now();
        Long giorni = ChronoUnit.DAYS.between(dateFromString, currentDate);
        Long Prezzo = (long) 0;

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


        Map<String, Object> response = new HashMap<>();
        response.put("Nome", nomeEvento);
        response.put("Data", dataEvento);
        response.put("Giorni", giorni);
        response.put("Prezzo", Prezzo);


        // Invio della risposta come JSON
        return ResponseEntity.ok(response);
    }



}