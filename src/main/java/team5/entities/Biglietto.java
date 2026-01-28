package team5.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "biglietti")
public class Biglietto {

    @Column(name = "data_emissione")
    LocalDate data_emissione;
    @Column(name = "costo")
    Double costo;
    @Column(name = "codice_univco", unique = true)
    String codice_univoco;
    @Column(name = "data_validazione")

    LocalDate data_validazione;
    @Id
    @GeneratedValue
    @Column(name = "id_biglietto")
    private UUID idBiglietto;
    @ManyToOne
    @JoinColumn(name = "id_mezzo")
    private Mezzo mezzi;

    @ManyToOne
    @JoinColumn(name = "id_rivenditore")
    private Rivenditore rivenditore;

    public Biglietto() {
    }

    public Biglietto(LocalDate data_emissione, Double costo, String codice_univoco, Rivenditore rivenditore) {
        this.data_emissione = data_emissione;
        this.costo = costo;
        this.codice_univoco = codice_univoco;
        this.data_validazione = null;
        this.mezzi = null;
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

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public String getCodice_univoco() {
        return codice_univoco;
    }

    public void setCodice_univoco(String codice_univoco) {
        this.codice_univoco = codice_univoco;
    }

    public LocalDate getData_validazione() {
        return data_validazione;
    }

    public void setData_validazione(LocalDate data_validazione) {
        this.data_validazione = data_validazione;
    }

    public Mezzo getMezzi() {
        return mezzi;
    }

    public void setMezzi(Mezzo mezzi) {
        this.mezzi = mezzi;
    }

    public Rivenditore getRivenditore() {
        return rivenditore;
    }

    public void setRivenditore(Rivenditore rivenditore) {
        this.rivenditore = rivenditore;
    }

    @Override
    public String toString() {
        return "Biglietto{" +
                "idBiglietto=" + idBiglietto +
                ", data_emissione=" + data_emissione +
                ", costo=" + costo +
                ", codice_univoco='" + codice_univoco + '\'' +
                ", data_validazione=" + data_validazione +
                ", mezzi=" + mezzi +
                ", rivenditore=" + rivenditore +
                '}';
    }
}
