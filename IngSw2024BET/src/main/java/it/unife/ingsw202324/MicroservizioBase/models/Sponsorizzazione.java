package it.unife.ingsw202324.MicroservizioBase.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "SPONSORIZZAZIONE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sponsorizzazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdSponsorizzazione")
    private Integer IdSponsorizzazione;

    @Column(name = "ID_evento")
    private String ID_evento;

    @Column(name = "Tipologia")
    private String Tipologia;

    @Column(name = "Costo")
    private Long Costo;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;
}
