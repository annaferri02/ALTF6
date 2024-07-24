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
@Table(name = "BIGLIETTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Biglietto {
    @Id /* Annotation per definire la primary key della tabella */
    private String ID_Biglietto;
    private Long prezzo;
    private String ID_utente;
    private String ID_evento;
    private String PDF_biglietto;
    private String QRcode;
    private Integer Posto;


    public String getID_Biglietto() {
        return ID_Biglietto;
    }

    public void setID_Biglietto(String ID_Biglietto) {
        this.ID_Biglietto = ID_Biglietto;
    }

    public Long getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Long prezzo) {
        this.prezzo = prezzo;
    }

    public String getID_utente() {
        return ID_utente;
    }

    public void setID_utente(String ID_utente) {
        this.ID_utente = ID_utente;
    }

    public String getID_evento() {
        return ID_evento;
    }

    public void setID_evento(String ID_evento) {
        this.ID_evento = ID_evento;
    }

    public String getPDF_biglietto() {
        return PDF_biglietto;
    }

    public void setPDF_biglietto(String PDF_biglietto) {
        this.PDF_biglietto = PDF_biglietto;
    }

    public String getQRcode() {
        return QRcode;
    }

    public void setQRcode(String QRcode) {
        this.QRcode = QRcode;
    }

    public Integer getPosto() {
        return Posto;
    }

    public void setPosto(Integer Posto) {
        this.Posto = Posto;
    }
}
