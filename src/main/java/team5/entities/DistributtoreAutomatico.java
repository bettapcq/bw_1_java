package team5.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "distributori automatici ")
public class DistributtoreAutomatico extends Rivenditore{
    @Id
    @GeneratedValue
    private UUID id_distributoreautomatico;
    @Enumerated(EnumType.STRING)
    @Column(name="Stato")
    private Stato stato;

    public DistributtoreAutomatico(String indirizzo, Stato stato) {
        super(indirizzo);
        this.stato = stato;
    }

    public DistributtoreAutomatico() {

    }

    public UUID getId_distributoreautomatico() {
        return id_distributoreautomatico;
    }


    public Stato getStato() {
        return stato;
    }

    public void setStato(Stato stato) {
        this.stato = stato;
    }
}
