package it.unife.ingsw202324.MicroservizioBase.api;

import it.unife.ingsw202324.MicroservizioBase.models.Biglietto;
import it.unife.ingsw202324.MicroservizioBase.models.Evento;
import it.unife.ingsw202324.MicroservizioBase.models.Luogo;
import it.unife.ingsw202324.MicroservizioBase.services.ServiceTicket;
import it.unife.ingsw202324.MicroservizioBase.services.TemplateRestConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @RequestMapping("/callREST") /* Annotation per definire il path del metodo (relativo alla classe)  */
    public Biglietto callRest() {
        return TemplateRestConsumer.callREST2("new", null, true);
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