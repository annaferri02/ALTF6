package it.unife.ingsw202324.MicroservizioBase.models;


import java.util.List;

public class TicketData {
    private List<String> biglietti;
    private Evento evento;
    private Luogo luogo;

    public List<String> getBiglietti() {
        return biglietti;
    }

    public void setBiglietti(List<String > biglietti) {
        this.biglietti = biglietti;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Luogo getLuogo() {
        return luogo;
    }

    public void setLuogo(Luogo luogo) {
        this.luogo = luogo;
    }

}