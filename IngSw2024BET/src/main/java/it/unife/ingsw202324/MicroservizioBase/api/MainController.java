package it.unife.ingsw202324.MicroservizioBase.api;

import it.unife.ingsw202324.MicroservizioBase.models.MyValues;
import it.unife.ingsw202324.MicroservizioBase.services.MyService;
import it.unife.ingsw202324.MicroservizioBase.services.TemplateRestConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController /* Annotation per definire che la classe risponderà tramite rest  */
@RequestMapping("/api") /* Annotation per definire il path della classe  */
public class MainController {
    @Autowired
    MyService myService;
    @RequestMapping("/costobiglietti") /* Annotation per definire il path del metodo (relativo alla classe)  */
    public List<MyValues> testMysql() {
        return myService.getAll();
    }

    @RequestMapping("/testWithElements") /* Annotation per definire il path del metodo (relativo alla classe)  */
    public List<MyValues> addElements() {

        /* Chiamata a un servizio che ritorna inserisce 3 dati e ritorna il db */
        return myService.addElements();
    }

    @RequestMapping("/callREST") /* Annotation per definire il path del metodo (relativo alla classe)  */
    public String callRest() {
        return TemplateRestConsumer.callREST("biglietto", null, true);
    }

    @RequestMapping("/callREST2") /* Annotation per definire il path del metodo (relativo alla classe)  */
    public String callRest2() {
        return TemplateRestConsumer.callREST("bello", null, true);
    }


}