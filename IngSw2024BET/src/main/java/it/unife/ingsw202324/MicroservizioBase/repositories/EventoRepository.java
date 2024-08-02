package it.unife.ingsw202324.MicroservizioBase.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/* Classe che definisce il repository (database)  */
public interface EventoRepository extends JpaRepository<it.unife.ingsw202324.MicroservizioBase.models.Evento, Long> {

    @Query("SELECT E.Data FROM Evento E WHERE E.Nome = :Nome")
    public Date getDataEvento(@Param("Nome") String Nome);

}
