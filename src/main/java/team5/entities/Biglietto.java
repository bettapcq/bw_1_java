package team5.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "biglietti")
public class Biglietto {

    @Id
    @GeneratedValue
    @Column(name = "id_biglietto")
    private UUID idBiglietto;

    @Column(name = "data_emissione")
    LocalDate data_emissione;

    @Column(name = "costo")
    int costo;

    @Column(name = "utilizzato")
    boolean utilizzato;

    @ManyToOne
    @JoinColumn(name = "id_utente")
    private Utente utente;

    @ManyToOne
    @JoinColumn(name = "id_rivenditore")
    private Rivenditore rivenditore;

    public Biglietto(){
    }

    public Biglietto(LocalDate data_emissione, int costo, boolean utilizzato, Utente utente, Rivenditore rivenditore) {
        this.data_emissione = data_emissione;
        this.costo = costo;
        this.utilizzato = utilizzato;
        this.utente = utente;
        this.rivenditore = rivenditore;
    }

    public UUID getIdBiglietto() {
        return idBiglietto;
    }

    public LocalDate getData_emissione() {
        return data_emissione;
    }

    public void setData_emissione(LocalDate data_emissione) {
        this.data_emissione = data_emissione;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public boolean isUtilizzato() {
        return utilizzato;
    }

    public void setUtilizzato(boolean utilizzato) {
        this.utilizzato = utilizzato;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    @Override
    public String toString() {
        return "Biglietto{" +
                "idBiglietto=" + idBiglietto +
                ", data_emissione=" + data_emissione +
                ", costo=" + costo +
                ", utilizzato=" + utilizzato +
                ", utente=" + utente +
                '}';
    }
}
