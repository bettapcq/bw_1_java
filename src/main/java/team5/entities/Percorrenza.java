package team5.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name="percorrenze")
public class Percorrenza {
    @Id
    @GeneratedValue
    private UUID id_percorrenza;

    @ManyToOne
    @JoinColumn(name="id_mezzo")
    private Mezzo id_mezzo;

    @ManyToOne
    @JoinColumn(name="id_tratta")
    private Tratta id_tratta;

    @Column
    private int tempo_effettivo_minuti;

    public Percorrenza() {
    }


}
