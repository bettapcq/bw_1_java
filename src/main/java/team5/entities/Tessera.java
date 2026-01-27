package team5.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tessere")
public class Tessera {

    @Id
    @GeneratedValue
    @Column(name = "id_tessera")
    private UUID idTessera;

    @Column(name = "attiva")
    private boolean attiva;

    @Column(name = "data_emissione")
    private LocalDate data_emissione;

    @Column(name = "data_scadenza")
    private LocalDate data_scadenza;

    @OneToOne
    @JoinColumn(name = "id_utente")
    private Utente utente;

    public Tessera(){
        //costruttore vuoto
    }

    public Tessera(boolean attiva, LocalDate data_emissione, LocalDate data_scadenza, Utente utente) {
        this.attiva = attiva;
        this.data_emissione = data_emissione;
        this.data_scadenza = data_scadenza;
        this.utente = utente;
    }

    public UUID getIdTessera() {
        return idTessera;
    }

    public boolean isAttiva() {
        return attiva;
    }

    public void setAttiva(boolean attiva) {
        this.attiva = attiva;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
      this.utente = utente;
    }

    public LocalDate getData_emissione() {
        return data_emissione;
    }

    public void setData_emissione(LocalDate data_emissione) {
        this.data_emissione = data_emissione;
    }

    public LocalDate getData_scadenza() {
        return data_scadenza;
    }

    public void setData_scadenza(LocalDate data_scadenza) {
        this.data_scadenza = data_scadenza;
    }

    @Override
    public String toString() {
        return "Tessera{" +
                "idTessera=" + idTessera +
                ", attiva=" + attiva +
                ", utente=" + utente +
                '}';
    }
}
