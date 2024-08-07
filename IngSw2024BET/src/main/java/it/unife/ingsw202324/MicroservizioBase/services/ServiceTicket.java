package it.unife.ingsw202324.MicroservizioBase.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.unife.ingsw202324.MicroservizioBase.models.Biglietto;
import it.unife.ingsw202324.MicroservizioBase.repositories.BigliettoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/* Service class per interrogare il db  */
@Service
public class ServiceTicket {
    @Autowired
    private BigliettoRepository myRepository;

    /* Metodo che effettua una select all sulla tabella Mysql */
    public List<Biglietto> getAll() {
        return myRepository.findAll();
    }

    /* Metodo che salva un record sulla tabella  */
    public void insert(Biglietto record){
            myRepository.save(record);
            System.out.println("Record inserito");
    }

    /* Metodo che inserisce dati e li recupera da un db H2 (in assenza di mysql) */
    public Biglietto addElement(Biglietto record) {
        System.out.println(record.toString());
        this.insert(record);
        return record;
    }

    /* Metodo che recupera i dati da un db H2 (in assenza di mysql) */
    public String callRest() {
        return TemplateRestConsumer.callREST("new", null, true);
    }

    // Metodo che recupera i posti occupati per un evento
    public List<String> getPostiByIdEvento(String idEvento) {
        return myRepository.findPostiByIdEvento(idEvento);
    }
}
