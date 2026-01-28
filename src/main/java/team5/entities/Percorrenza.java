package team5.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "percorrenze")
public class Percorrenza {
    @Id
    @GeneratedValue
    private UUID id_percorrenza;

    @ManyToOne
    @JoinColumn(name = "id_mezzo")
    private Mezzo mezzo;

    @ManyToOne
    @JoinColumn(name = "id_tratta")
    private Tratta tratta;

    @Column
    private int tempo_effettivo_minuti;

    public Percorrenza() {
    }

    public Percorrenza(Mezzo mezzo, Tratta tratta, int tempo_effettivo_minuti) {
        this.mezzo = mezzo;
        this.tratta = tratta;
        this.tempo_effettivo_minuti = tempo_effettivo_minuti;
    }

    public Mezzo getId_mezzo() {
        return mezzo;
    }

    public void setId_mezzo(Mezzo id_mezzo) {
        this.mezzo = id_mezzo;
    }

    public UUID getId_percorrenza() {
        return id_percorrenza;
    }

    public void setId_percorrenza(UUID id_percorrenza) {
        this.id_percorrenza = id_percorrenza;
    }

    public Tratta getId_tratta() {
        return tratta;
    }

    public void setId_tratta(Tratta id_tratta) {
        this.tratta = id_tratta;
    }

    public int getTempo_effettivo_minuti() {
        return tempo_effettivo_minuti;
    }

    public void setTempo_effettivo_minuti(int tempo_effettivo_minuti) {
        this.tempo_effettivo_minuti = tempo_effettivo_minuti;
    }

    @Override
    public String toString() {
        return "Percorrenza{" +
                "id_mezzo=" + mezzo +
                ", id_percorrenza=" + id_percorrenza +
                ", id_tratta=" + tratta +
                ", tempo_effettivo_minuti=" + tempo_effettivo_minuti +
                '}';
    }
}
