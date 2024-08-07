package it.unife.ingsw202324.MicroservizioBase.services;

import it.unife.ingsw202324.MicroservizioBase.models.Evento;
import it.unife.ingsw202324.MicroservizioBase.models.Organizzatore;
import it.unife.ingsw202324.MicroservizioBase.repositories.EventoRepository;
import it.unife.ingsw202324.MicroservizioBase.repositories.OrganizzatoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/* Service class per interrogare il db  */
@Service
public class EventoService {
    @Autowired
    private EventoRepository myRepository;

    // metodo per ottenere la data di un evento
    public Date getDataEvento(String Nome) {
        return myRepository.getDataEvento(Nome);
    }

    // metodo per ottenere l'id di un evento
    public String getIDEvento(String Nome) {
        return myRepository.getIDEvento(Nome);
    }

    // metodo per ottenere l'evento dall'id
    public Evento getEventoByID(String ID_Evento) {
        return myRepository.getEventoByID(ID_Evento);
    }


}
