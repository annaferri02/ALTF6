package it.unife.ingsw202324.MicroservizioBase.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@Entity
@Table(name = "EVENTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Evento {

    @Id
    @JsonProperty("ID_Evento")
    private String ID_Evento;

    @JsonProperty("Nome")
    private String Nome;

    @JsonProperty("Data")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date Data;

    @JsonProperty("Descrizione")
    private String Descrizione;

    @JsonProperty("ID_Organizzatore")
    private String ID_Organizzatore;

    @JsonProperty("flag_ticket")
    private Boolean flag_ticket;

    @JsonProperty("IdLuogo")
    private String IdLuogo;
}
