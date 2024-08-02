package it.unife.ingsw202324.MicroservizioBase.repositories;

import it.unife.ingsw202324.MicroservizioBase.models.Organizzatore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/* Classe che definisce il repository (database)  */
public interface OrganizzatoreRepository extends JpaRepository<Organizzatore, Long> {
    @Query("SELECT E.Nome FROM Evento E WHERE E.ID_Organizzatore = :ID_Organizzatore")
    public List<String> findEventiOrganizzatore(@Param("ID_Organizzatore") String ID_Organizzatore);

}
