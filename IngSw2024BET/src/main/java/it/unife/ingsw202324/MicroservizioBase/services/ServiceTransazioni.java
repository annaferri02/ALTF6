package it.unife.ingsw202324.MicroservizioBase.services;

import it.unife.ingsw202324.MicroservizioBase.models.Transazioni;
import it.unife.ingsw202324.MicroservizioBase.models.Utente;
import it.unife.ingsw202324.MicroservizioBase.repositories.TransazioniRepository;
import it.unife.ingsw202324.MicroservizioBase.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/* Service class per interrogare il db  */
@Service
public class ServiceTransazioni {
    @Autowired
    private TransazioniRepository transazioniRepository;
    private UtenteRepository utenteRepository;

    /* Metodo che effettua una select all sulla tabella Mysql */
    public List<Transazioni> getAll() {
        return transazioniRepository.findAll();
    }

    public List<Utente> getallutenti() {
        return utenteRepository.findAll();
    }

    /* Metodo che salva un record sulla tabella  */
    public void insert(Transazioni record){
        transazioniRepository.save(record);
    }

    /* Metodo che inserisce dati e li recupera da un db H2 (in assenza di mysql) */
    public String addElement(Transazioni record) {
        System.out.println(record.toString());

        if (record.getIdutente() == null) {
            return "ID utente non specificato";
        }

        boolean flag = false;
         for (Utente utente : getallutenti()) {
             if (utente.getID_utente().equals(record.getIdutente())) {
                 flag = true;
             }
         }

         if (!flag) {
             return "Utente non trovato";
         }

        this.insert(record);
        return record.toString();
    }


}
