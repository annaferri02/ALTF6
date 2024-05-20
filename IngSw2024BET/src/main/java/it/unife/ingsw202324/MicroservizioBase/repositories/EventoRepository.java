package it.unife.ingsw202324.MicroservizioBase.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

/* Classe che definisce il repository (database)  */
public interface EventoRepository extends JpaRepository<it.unife.ingsw202324.MicroservizioBase.models.Evento, Long> {

}
