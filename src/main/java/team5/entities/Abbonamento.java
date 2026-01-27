package team5.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "abbonamenti")
public class Abbonamento {
    @Id
    @GeneratedValue
    @Column(name = "id_abbonamento")
    private UUID idAbbonamento;

    @Column(name = "data_emissione")
    private LocalDate dataEmissione;

    @Column(name = "costo")
    private Double costo;

    @Column(name = "codice_univco")
    String codice_univoco;

    @Enumerated(EnumType.STRING)
    @Column(name="Periodicit")
    private Periodicita periodicita;



    @ManyToOne
    @JoinColumn(name = "id_rivenditore", nullable = false)
    private Rivenditore rivenditore;

    @ManyToOne
    @JoinColumn(name = "id_tessera", nullable = false)
    private Tessera tessera;

    public Abbonamento(){
    //costr vuoto
        }

    public Abbonamento(LocalDate dataEmissione, Double costo, String codice_univoco, Periodicita periodicita, Rivenditore rivenditore, Tessera tessera) {
        this.dataEmissione = dataEmissione;
        this.costo = costo;
        this.codice_univoco = codice_univoco;
        this.periodicita = periodicita;
        this.rivenditore = rivenditore;
        this.tessera = tessera;
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

    public String getCodice_univoco() {
        return codice_univoco;
    }

    public Periodicita getPeriodicita() {
        return periodicita;
    }

    public Rivenditore getRivenditore() {
        return rivenditore;
    }

    public void setTessera(Tessera tessera) {
        this.tessera = tessera;
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
