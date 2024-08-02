package it.unife.ingsw202324.MicroservizioBase.api;

import it.unife.ingsw202324.MicroservizioBase.models.Biglietto;
import it.unife.ingsw202324.MicroservizioBase.models.Evento;
import it.unife.ingsw202324.MicroservizioBase.models.Luogo;
import it.unife.ingsw202324.MicroservizioBase.services.ServiceTicket;
import it.unife.ingsw202324.MicroservizioBase.services.TemplateRestConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController /* Annotation per definire che la classe risponderà tramite rest  */
@RequestMapping("/ticket") /* Annotation per definire il path della classe  */
public class TicketController {
    @Autowired
    ServiceTicket ticketService;

    @RequestMapping("/costobiglietti") /* Annotation per definire il path del metodo (relativo alla classe)  */
    public List<Biglietto> testMysql() {
        return ticketService.getAll();
    }

    @RequestMapping("/testWithElements") /* Annotation per definire il path del metodo (relativo alla classe)  */
    public String addElement() {
        /* Chiamata a un servizio che ritorna inserisce dati e ritorna il db */
        return ticketService.addElement(this.callRest()).toString();
    }

    @CrossOrigin(origins = "http://localhost:5173") // Aggiungi questa linea
    @GetMapping("/getInfoBiglietti")
    public TicketData getInfoBiglietti() {

        TicketData ticketData = new TicketData();

        ticketData.setEvento(TemplateRestConsumer.getEventoMock("evento", null, true));
        ticketData.setLuogo(TemplateRestConsumer.getLuogoMock("luogo", null, true));
        ticketData.setBiglietti(ticketService.getPostiByIdEvento(ticketData.getEvento().getID_Evento()));

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

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Biglietti salvati con successo");
            response.put("biglietti", bigliettiSalvati);

            // Invio della risposta come JSON
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "Errore durante il salvataggio dei biglietti", "error", e.getMessage()));
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



class TicketData {
    private List<String> biglietti;
    private Evento evento;
    private Luogo luogo;

    public List<String> getBiglietti() {
        return biglietti;
    }

    public void setBiglietti(List<String > biglietti) {
        this.biglietti = biglietti;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Luogo getLuogo() {
        return luogo;
    }

    public void setLuogo(Luogo luogo) {
        this.luogo = luogo;
    }

}