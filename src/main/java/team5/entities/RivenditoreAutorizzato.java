package team5.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table (name = "rivenditori autorizzati")
public class RivenditoreAutorizzato extends Rivenditore{

   private LocalTime orarioApertura;
   private LocalTime orearioChiusura;
   private int numeroTelefono;



public RivenditoreAutorizzato(){}

    public RivenditoreAutorizzato(int numeroTelefono, String indirizzo, LocalTime orarioApertura, LocalTime orearioChiusura) {
        super(indirizzo);
        this.orarioApertura = orarioApertura;
        this.orearioChiusura = orearioChiusura;
        this.numeroTelefono = numeroTelefono;
    }

    public int getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(int numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }




    public LocalTime getOrearioChiusura() {
        return orearioChiusura;
    }

    public void setOrearioChiusura(LocalTime orearioChiusura) {
        this.orearioChiusura = orearioChiusura;
    }

    public LocalTime getOrarioApertura() {
        return orarioApertura;
    }

    public void setOrarioApertura(LocalTime orarioApertura) {
        this.orarioApertura = orarioApertura;
    }


    @Override
    public String toString() {
        return "RivenditoreAutorizzato{" +
                ", orarioApertura=" + orarioApertura +
                ", orearioChiusura=" + orearioChiusura +
                '}';
    }
}
