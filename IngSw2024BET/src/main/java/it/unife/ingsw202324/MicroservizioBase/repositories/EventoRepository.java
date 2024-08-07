package it.unife.ingsw202324.MicroservizioBase.repositories;

import it.unife.ingsw202324.MicroservizioBase.models.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/* Classe che definisce il repository (database)  */
public interface EventoRepository extends JpaRepository<it.unife.ingsw202324.MicroservizioBase.models.Evento, Long> {

    // Query per trovare la data di inizio di un evento
    @Query("SELECT E.Data FROM Evento E WHERE E.Nome = :Nome")
    public Date getDataEvento(@Param("Nome") String Nome);

    //Query per trovare l'ID di un evento
    @Query("SELECT E.ID_Evento FROM Evento E WHERE E.Nome = :Nome")
    public String getIDEvento(@Param("Nome") String Nome);

    //Query per trovare  gli eventi da un id
    @Query("SELECT Evento FROM Evento E WHERE E.ID_Evento = :ID_Evento")
    public Evento getEventoByID(@Param("ID_Evento") String ID_Evento);

}
