package team5.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name="manutenzioni")
public class Manutenzione {

    public enum Tipologia{
        Ruote,
        Motore,
        Carrozzeria,
        Elettronica,
        Meccanica_altro
    }

    @Id
    @GeneratedValue
    private UUID id_manutenzione;

    @Column
    private LocalDate inizio_manutenzione;
    private LocalDate fine_manutenzione;
    private Tipologia tipologia;

    @ManyToOne
    @JoinColumn(name="id_mezzo")
    private Mezzo mezzo_in_manutenzione;

    public Manutenzione() {
    }

    public Manutenzione(LocalDate fine_manutenzione, LocalDate inizio_manutenzione, Mezzo mezzo_in_manutenzione, Tipologia tipologia) {
        this.fine_manutenzione = fine_manutenzione;
        this.inizio_manutenzione = inizio_manutenzione;
        this.mezzo_in_manutenzione = mezzo_in_manutenzione;
        this.tipologia = tipologia;
    }

    public LocalDate getFine_manutenzione() {
        return fine_manutenzione;
    }

    public void setFine_manutenzione(LocalDate fine_manutenzione) {
        this.fine_manutenzione = fine_manutenzione;
    }

    public UUID getId_manutenzione() {
        return id_manutenzione;
    }

    public void setId_manutenzione(UUID id_manutenzione) {
        this.id_manutenzione = id_manutenzione;
    }

    public LocalDate getInizio_manutenzione() {
        return inizio_manutenzione;
    }

    public void setInizio_manutenzione(LocalDate inizio_manutenzione) {
        this.inizio_manutenzione = inizio_manutenzione;
    }

    public Mezzo getMezzo_in_manutenzione() {
        return mezzo_in_manutenzione;
    }

    public void setMezzo_in_manutenzione(Mezzo mezzo_in_manutenzione) {
        this.mezzo_in_manutenzione = mezzo_in_manutenzione;
    }

    public Tipologia getTipologia() {
        return tipologia;
    }

    public void setTipologia(Tipologia tipologia) {
        this.tipologia = tipologia;
    }

    @Override
    public String toString() {
        return "Manutenzione{" +
                "fine_manutenzione=" + fine_manutenzione +
                ", id_manutenzione=" + id_manutenzione +
                ", inizio_manutenzione=" + inizio_manutenzione +
                ", tipologia=" + tipologia +
                ", mezzo_in_manutenzione=" + mezzo_in_manutenzione +
                '}';
    }
}
