package it.unife.ingsw202324.MicroservizioBase.services;

import it.unife.ingsw202324.MicroservizioBase.models.Evento;
import it.unife.ingsw202324.MicroservizioBase.models.Luogo;
import it.unife.ingsw202324.MicroservizioBase.repositories.EventoRepository;
import it.unife.ingsw202324.MicroservizioBase.repositories.LuogoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/* Service class per interrogare il db  */
@Service
public class LuogoService {
    @Autowired
    private LuogoRepository myRepository;


    public Luogo getLuogoById(String idLuogo) {
        return myRepository.getLuogoById(idLuogo);
    }


}
