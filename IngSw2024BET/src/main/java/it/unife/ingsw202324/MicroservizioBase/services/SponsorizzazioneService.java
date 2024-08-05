package it.unife.ingsw202324.MicroservizioBase.services;

import it.unife.ingsw202324.MicroservizioBase.models.MyValues;
import it.unife.ingsw202324.MicroservizioBase.models.Sponsorizzazione;
import it.unife.ingsw202324.MicroservizioBase.repositories.EventoRepository;
import it.unife.ingsw202324.MicroservizioBase.repositories.SponsorizzazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/* Service class per interrogare il db  */
@Service
public class SponsorizzazioneService {
    @Autowired
    private SponsorizzazioneRepository myRepository;

    public void insert(Sponsorizzazione record){
        myRepository.save(record);
    }


}
