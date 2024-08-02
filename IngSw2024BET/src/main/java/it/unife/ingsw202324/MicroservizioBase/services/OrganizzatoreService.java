package it.unife.ingsw202324.MicroservizioBase.services;

import it.unife.ingsw202324.MicroservizioBase.models.MyValues;
import it.unife.ingsw202324.MicroservizioBase.models.Organizzatore;
import it.unife.ingsw202324.MicroservizioBase.repositories.MyRepository;
import it.unife.ingsw202324.MicroservizioBase.repositories.OrganizzatoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/* Service class per interrogare il db  */
@Service
public class OrganizzatoreService {
    @Autowired
    private OrganizzatoreRepository myRepository;

    /* Metodo che effettua una select all sulla tabella Mysql */
    public List<Organizzatore> getAll() {
        return myRepository.findAll();
    }


    /* Metodo che salva un record sulla tabella  */
    public void insert(Organizzatore record){
        myRepository.save(record);
    }

    public List<String> getEventiOrganizzatore(String ID_Organizzatore) {
        return myRepository.findEventiOrganizzatore(ID_Organizzatore);
    }


}
