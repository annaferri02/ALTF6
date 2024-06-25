package it.unife.ingsw202324.MicroservizioBase.repositories;

import it.unife.ingsw202324.MicroservizioBase.models.Transazioni;
import org.springframework.data.jpa.repository.JpaRepository;

/* Classe che definisce il repository (database)  */
public interface TransazioniRepository extends JpaRepository<Transazioni, Long> {

}
