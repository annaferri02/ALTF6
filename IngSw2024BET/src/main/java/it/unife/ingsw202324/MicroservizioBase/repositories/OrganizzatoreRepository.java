package it.unife.ingsw202324.MicroservizioBase.repositories;

import it.unife.ingsw202324.MicroservizioBase.models.Organizzatore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/* Classe che definisce il repository (database)  */
public interface OrganizzatoreRepository extends JpaRepository<Organizzatore, Long> {

    // query per trovar tutti i nomi degli eventi di un organizzatore
    @Query("SELECT E.Nome FROM Evento E WHERE E.ID_Organizzatore = :ID_Organizzatore\n" +
            " and E.Data > CURRENT_DATE AND E.ID_Evento not in (Select ID_evento from Sponsorizzazione)")
    public List<String> findEventiOrganizzatore(@Param("ID_Organizzatore") String ID_Organizzatore);

}
