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

    @Column(name = "codice_univco")
    String codice_univoco;

    @Column(name = "data_validazione")
    LocalDate data_validazione;

    @ManyToOne
    @JoinColumn(name = "id_mezzo")
    private Mezzo mezzi;

    @ManyToOne
    @JoinColumn(name = "id_rivenditore")
    private Rivenditore rivenditore;

    public Biglietto(){
    }

    public Biglietto(LocalDate data_emissione, int costo, String codice_univoco, LocalDate data_validazione, Mezzo mezzi, Rivenditore rivenditore) {
        this.data_emissione = data_emissione;
        this.costo = costo;
        this.codice_univoco = codice_univoco;
        this.data_validazione = data_validazione;
        this.mezzi = mezzi;
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
