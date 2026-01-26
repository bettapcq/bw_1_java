package team5.entities;

import jakarta.persistence.*;

import java.util.UUID;
@Entity
@Table(name = "rivenditori")
@Inheritance (strategy = InheritanceType.JOINED)
public abstract class  Rivenditore {
    @Id
    @GeneratedValue
   private UUID id_rivenditore;
   private String indirizzo;

    public Rivenditore() {
    }

    public Rivenditore(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public UUID getId_rivenditore() {
        return id_rivenditore;
    }


    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }


    @Override
    public String toString() {
        return "Rivenditore{" +
                "id_rivenditori=" + id_rivenditore +
                ", indirizzo='" + indirizzo + '\'' +
                '}';
    }
}
