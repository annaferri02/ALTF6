package it.unife.ingsw202324.MicroservizioBase.repositories;

import it.unife.ingsw202324.MicroservizioBase.models.Biglietto;
import it.unife.ingsw202324.MicroservizioBase.models.Luogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/* Classe che definisce il repository (database)  */
public interface LuogoRepository extends JpaRepository<Biglietto, Long> {

    @Query("SELECT Luogo FROM Luogo l WHERE l.idLuogo = :idLuogo")
    Luogo getLuogoById(@Param("idLuogo") String idLuogo);

}
