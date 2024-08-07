package it.unife.ingsw202324.MicroservizioBase.repositories;

import it.unife.ingsw202324.MicroservizioBase.models.Biglietto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/* Classe che definisce il repository (database)  */
public interface BigliettoRepository extends JpaRepository<Biglietto, Long> {

    // Query per trovare i posti occupati di un evento
    @Query("SELECT b.Posto FROM Biglietto b WHERE b.ID_evento = :idEvento")
    List<String> findPostiByIdEvento(@Param("idEvento") String idEvento);

}
