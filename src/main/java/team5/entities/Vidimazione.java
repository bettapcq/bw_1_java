package team5.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "vidimazioni")
public class Vidimazione {

    @Id
    @GeneratedValue
    @Column(name = "id_vidimazione")
    private UUID idVidimazione;

    @ManyToOne
    @JoinColumn(name = "id_mezzo")
    private Mezzo mezzo;

    @OneToOne
    @JoinColumn(name = "id_biglietto")
    private Biglietto biglietto;

    @Column(name = "data_vidimazione")
    private LocalDate dataVidimazione;

    public Vidimazione(){
        //costruttore vuoto
    }

    public Vidimazione(Mezzo mezzo, Biglietto biglietto, LocalDate dataVidimazione){
        this.mezzo = mezzo;
        this.biglietto = biglietto;
        this.dataVidimazione = dataVidimazione;
    }


    public UUID getIdVidimazione() {
        return idVidimazione;
    }



    public Mezzo getMezzo() {
        return mezzo;
    }

    public void setMezzo(Mezzo mezzo) {
        this.mezzo = mezzo;
    }

    public Biglietto getBiglietto() {
        return biglietto;
    }

    public void setBiglietto(Biglietto biglietto) {
        this.biglietto = biglietto;
    }

    public LocalDate getDataVidimazione() {
        return dataVidimazione;
    }

    public void setDataVidimazione(LocalDate dataVidimazione) {
        this.dataVidimazione = dataVidimazione;
    }


    @Override
    public String toString() {
        return "Vidimazione{" +
                "idVidimazione=" + idVidimazione +
                ", mezzo=" + mezzo +
                ", biglietto=" + biglietto +
                ", dataVidimazione=" + dataVidimazione +
                '}';
    }
}
