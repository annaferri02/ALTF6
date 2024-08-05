package it.unife.ingsw202324.MicroservizioBase.api;

import it.unife.ingsw202324.MicroservizioBase.models.Biglietto;
import it.unife.ingsw202324.MicroservizioBase.models.Transazioni;
import it.unife.ingsw202324.MicroservizioBase.services.ServiceTicket;
import it.unife.ingsw202324.MicroservizioBase.services.ServiceTransazioni;
import it.unife.ingsw202324.MicroservizioBase.services.TemplateRestConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController /* Annotation per definire che la classe risponderà tramite rest  */
@RequestMapping("/transaction") /* Annotation per definire il path della classe  */
public class TransactionController {
    @Autowired
    ServiceTransazioni transazioniService;
    @RequestMapping("/transazioni") /* Annotation per definire il path del metodo (relativo alla classe)  */
    public List<Transazioni> testMysql() {
        return transazioniService.getAll();
    }

    @RequestMapping("/addtransaction") /* Annotation per definire il path del metodo (relativo alla classe)  */
    public String addElement() {
        /* Chiamata a un servizio che ritorna inserisce dati e ritorna il db */
        return transazioniService.addElement(this.callRest());
    }

    @RequestMapping("/callREST") /* Annotation per definire il path del metodo (relativo alla classe)  */
    public Transazioni callRest() {
        return TemplateRestConsumer.callREST3("new", null, true);
    }




}