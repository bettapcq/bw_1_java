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

//
//    @ManyToOne
//    @JoinColumn(name = "id_reseller", nullable = false)
//    private Rivenditore rivenditore;

    @ManyToOne
    @JoinColumn(name = "id_tessera", nullable = false)
    private Tessera tessera;

    public UUID getIdAbbonamento() {
        return idAbbonamento;
    }


    public LocalDate getDataEmissione() {
        return dataEmissione;
    }

    public void setDataEmissione(LocalDate dataEmissione) {
        this.dataEmissione = dataEmissione;
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
