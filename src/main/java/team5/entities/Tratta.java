package team5.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="tratte")
public class Tratta {
    @Id
    @GeneratedValue
    private UUID id_tratta;

    @Column
    private String partenza;
    private String capolinea;
    private int lunghezza_km;
    private int tempo_previsto_minuti;

    public Tratta() {
    }

    public Tratta( int lunghezza_km, String partenza, String capolinea, int tempo_previsto_minuti) {
        this.capolinea = capolinea;
        this.lunghezza_km = lunghezza_km;
        this.partenza = partenza;
        this.tempo_previsto_minuti = tempo_previsto_minuti;
    }

    public String getCapolinea() {
        return capolinea;
    }

    public void setCapolinea(String capolinea) {
        this.capolinea = capolinea;
    }

    public UUID getId_tratta() {
        return id_tratta;
    }

    public void setId_tratta(UUID id_tratta) {
        this.id_tratta = id_tratta;
    }

    public int getLunghezza_km() {
        return lunghezza_km;
    }

    public void setLunghezza_km(int lunghezza_km) {
        this.lunghezza_km = lunghezza_km;
    }

    public String getPartenza() {
        return partenza;
    }

    public void setPartenza(String partenza) {
        this.partenza = partenza;
    }


    public int getTempo_previsto_minuti() {
        return tempo_previsto_minuti;
    }

    public void setTempo_previsto_minuti(int tempo_previsto_minuti) {
        this.tempo_previsto_minuti = tempo_previsto_minuti;
    }

    @Override
    public String toString() {
        return "Tratta{" +
                "capolinea='" + capolinea + '\'' +
                ", id_tratta=" + id_tratta +
                ", partenza='" + partenza + '\'' +
                ", lunghezza_km=" + lunghezza_km +
                ", tempo_previsto_minuti=" + tempo_previsto_minuti +
                '}';
    }
}
