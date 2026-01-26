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
    private int tempo_effettivo_minuti;

    @ManyToMany
    @JoinTable(
            name = "tratte_mezzi",
            joinColumns = @JoinColumn(name = "id_tratta"),
            inverseJoinColumns = @JoinColumn(name = "id_mezzo")
    )
    private List<Mezzo> mezzi = new ArrayList<>();

    public Tratta() {
    }

    public Tratta(String capolinea, int lunghezza_km, List<Mezzo> mezzi, String partenza, int tempo_effettivo_minuti, int tempo_previsto_minuti) {
        this.capolinea = capolinea;
        this.lunghezza_km = lunghezza_km;
        this.mezzi = mezzi;
        this.partenza = partenza;
        this.tempo_effettivo_minuti = tempo_effettivo_minuti;
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

    public List<Mezzo> getMezzi() {
        return mezzi;
    }

    public void setMezzi(List<Mezzo> mezzi) {
        this.mezzi = mezzi;
    }

    public String getPartenza() {
        return partenza;
    }

    public void setPartenza(String partenza) {
        this.partenza = partenza;
    }

    public int getTempo_effettivo_minuti() {
        return tempo_effettivo_minuti;
    }

    public void setTempo_effettivo_minuti(int tempo_effettivo_minuti) {
        this.tempo_effettivo_minuti = tempo_effettivo_minuti;
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
                ", tempo_effettivo_minuti=" + tempo_effettivo_minuti +
                ", mezzi=" + mezzi +
                '}';
    }
}
