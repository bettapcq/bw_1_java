package team5.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="mezzi")
public class Mezzo {


    @Id
    @GeneratedValue
    private UUID id_mezzo;

    @Column
    private int capienza;
    private LocalDate inizio_attivita;
    private LocalDate fine_attivita;
    @Enumerated(EnumType.STRING)
    @Column(name="tipoMezzo")
    private TipoMezzo tipoMezzo;
    @OneToMany(mappedBy = "mezzo_in_manutenzione")
    protected List<Manutenzione> manutenzioni = new ArrayList<>();
    public Mezzo() {
    }

    public Mezzo( LocalDate inizio_attivita, LocalDate fine_attivita, TipoMezzo tipoMezzo) {

        this.fine_attivita = fine_attivita;
        this.inizio_attivita = inizio_attivita;
        this.tipoMezzo = tipoMezzo;
        this.capienza = (tipoMezzo ==  TipoMezzo.AUTOBUS)? 60 : 45;
    }

    public int getCapienza() {
        return capienza;
    }

    public void setCapienza(int capienza) {
        this.capienza = capienza;
    }

    public LocalDate getFine_attivita() {
        return fine_attivita;
    }

    public void setFine_attivita(LocalDate fine_attivita) {
        this.fine_attivita = fine_attivita;
    }

    public UUID getId_mezzo() {
        return id_mezzo;
    }

    public LocalDate getInizio_attivita() {
        return inizio_attivita;
    }

    public void setInizio_attivita(LocalDate inizio_attivita) {
        this.inizio_attivita = inizio_attivita;
    }

    public TipoMezzo getTipoMezzo() {
        return tipoMezzo;
    }

    public void setTipoMezzo(TipoMezzo tipoMezzo) {
        this.tipoMezzo = tipoMezzo;
    }

    @Override
    public String toString() {
        return "Mezzo{" +
                "id_mezzo=" + id_mezzo +
                ", capienza=" + capienza +
                ", inizio_attivita=" + inizio_attivita +
                ", fine_attivita=" + fine_attivita +
                ", tipoMezzo=" + tipoMezzo +
                '}';
    }
}
