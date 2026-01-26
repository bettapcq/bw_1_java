package team5.entities;

import jakarta.persistence.*;

import java.util.UUID;

public class Tessera {

    @Id
    @GeneratedValue
    @Column(name = "id_tessera")
    private UUID idTessera;

    @Column(name = "attiva")
    private boolean attiva;

    @OneToOne
    @JoinColumn(name = "id_utente")
    private Utente utente;

    public Tessera(){
        //costruttore vuoto
    }

//    public Tessera(UUID idTessera, boolean attiva, Utente utente){
//        this.idTessera = idTessera;
//        this.attiva = attiva;
//        this.utente = utente.getIdUtente();
//
//    }

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

//    public void setUtente(Utente utente) {
//        this.utente = utente;
//    }

    @Override
    public String toString() {
        return "Tessera{" +
                "idTessera=" + idTessera +
                ", attiva=" + attiva +
                ", utente=" + utente +
                '}';
    }
}
