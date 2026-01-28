package team5.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "abbonamenti")
public class Abbonamento {
    @Column(name = "codice_univco")
    String codice_univoco;
    @Id
    @GeneratedValue
    @Column(name = "id_abbonamento")
    private UUID idAbbonamento;
    @Column(name = "data_emissione")
    private LocalDate dataEmissione;
    @Column(name = "costo")
    private Double costo;
    @Enumerated(EnumType.STRING)
    @Column(name = "Periodicità", nullable = false)
    private Periodicita periodicita;

    @Column(name = "data_scadenza")
    private LocalDate data_scadenza;


    @ManyToOne
    @JoinColumn(name = "id_rivenditore", nullable = false)
    private Rivenditore rivenditore;

    @ManyToOne
    @JoinColumn(name = "id_tessera", nullable = false)
    private Tessera tessera;

    public Abbonamento() {
        //costr vuoto
    }

    public Abbonamento(LocalDate dataEmissione, Double costo, String codice_univoco, Periodicita periodicita, Rivenditore rivenditore, Tessera tessera) {
        this.dataEmissione = dataEmissione;
        this.costo = costo;
        this.codice_univoco = codice_univoco;
        this.periodicita = periodicita;
        this.rivenditore = rivenditore;
        this.tessera = tessera;
        switch (periodicita) {
            case SETTIMANALE:
                this.data_scadenza = dataEmissione.plusWeeks(1);
                break;
            case MENSILE:
                this.data_scadenza = dataEmissione.plusMonths(1);
                break;
            case ANNUALE:
                this.data_scadenza = dataEmissione.plusYears(1);
                break;
            default:
                this.data_scadenza = dataEmissione;
                break;
        }
    }

    public UUID getIdAbbonamento() {
        return idAbbonamento;
    }


    public LocalDate getDataEmissione() {
        return dataEmissione;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Tessera getTessera() {
        return tessera;
    }

    public void setTessera(Tessera tessera) {
        this.tessera = tessera;
    }

    public String getCodice_univoco() {
        return codice_univoco;
    }

    public Periodicita getPeriodicita() {
        return periodicita;
    }

    public Rivenditore getRivenditore() {
        return rivenditore;
    }

    public LocalDate getData_scadenza() {
        return data_scadenza;
    }

    public void setData_scadenza(LocalDate data_scadenza) {
        this.data_scadenza = data_scadenza;
    }

    public void setCodice_univoco(String codice) {
    }

    public void setPeriodicita(Periodicita periodicita) {
    }

    public void setRivenditore(Rivenditore rivenditore) {
    }

    public void setDataEmissione(LocalDate dataEmissione) {
    }

    @Override
    public String toString() {
        return "Abbonamento{" +
                "idAbbonamento=" + idAbbonamento +
                ", dataEmissione=" + dataEmissione +
                ", costo=" + costo +
                ", tessera=" + tessera +
                '}';
    }
}
