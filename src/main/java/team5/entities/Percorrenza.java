package team5.entities;

import jakarta.persistence.*;

import java.sql.Time;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "percorrenze")
public class Percorrenza {

    @Id
    @GeneratedValue
    private UUID id_percorrenza;

    @Column(name = "tempo_effettivo")
    private LocalTime tempo_effettivo;

    @ManyToOne
    @JoinColumn(name = "id_tratta")
    private Tratta tratte;

    @ManyToOne
    @JoinColumn(name = "id_mezzo")
    private Mezzo mezzi;

    public Percorrenza() {
    }

    public Percorrenza(LocalTime tempo_effettivo, Tratta tratte, Mezzo mezzi) {
        this.tempo_effettivo = tempo_effettivo;
        this.tratte = tratte;
        this.mezzi = mezzi;
    }

    public UUID getId_percorrenza() {
        return id_percorrenza;
    }

    public LocalTime getTempo_effettivo() {
        return tempo_effettivo;
    }

    public void setTempo_effettivo(LocalTime tempo_effettivo) {
        this.tempo_effettivo = tempo_effettivo;
    }

    public Tratta getTratte() {
        return tratte;
    }

    public void setTratte(Tratta tratte) {
        this.tratte = tratte;
    }

    public Mezzo getMezzi() {
        return mezzi;
    }

    public void setMezzi(Mezzo mezzi) {
        this.mezzi = mezzi;
    }

    @Override
    public String toString() {
        return "Percorrenza{" +
                "id_percorrenza=" + id_percorrenza +
                ", tempo_effettivo=" + tempo_effettivo +
                ", tratte=" + tratte +
                ", mezzi=" + mezzi +
                '}';
    }
}
