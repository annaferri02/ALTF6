package it.unife.ingsw202324.MicroservizioBase.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/* Modello della taballa
* Le annotation indicano che questa classe è un entity bean,
* mappa una tabella che ha un nome fisico "my_table",
* che i costruttori sono generici e auto creati dal plugin lombok,
* e che lombok creerà anche tutti i getter e setter */
@Entity
@Table(name = "EVENTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Evento {
    @Id /* Annotation per definire la primary key della tabella */
    private String ID_Evento;
    private String Nome;
    private Long Data;
    private String Descrizione;
    private String ID_Organizzatore;
    private Boolean flag_ticket;
    private String IdLuogo;

    public String getID_Evento() {
        return ID_Evento;
    }

    public void setID_Evento(String ID_Evento) {
        this.ID_Evento = ID_Evento;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public Long getData() {
        return Data;
    }

    public void setData(Long data) {
        Data = data;
    }

    public String getDescrizione() {
        return Descrizione;
    }

    public void setDescrizione(String descrizione) {
        Descrizione = descrizione;
    }

    public String getID_Organizzatore() {
        return ID_Organizzatore;
    }

    public void setID_Organizzatore(String ID_Organizzatore) {
        this.ID_Organizzatore = ID_Organizzatore;
    }

    public Boolean getFlag_ticket() {
        return flag_ticket;
    }

    public void setFlag_ticket(Boolean flag_ticket) {
        this.flag_ticket = flag_ticket;
    }


    public String getIdLuogo() {
        return IdLuogo;
    }

    public void setIdLuogo(String idLuogo) {
        IdLuogo = idLuogo;
    }
}
