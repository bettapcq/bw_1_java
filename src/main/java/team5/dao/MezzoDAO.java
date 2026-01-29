package team5.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import team5.entities.Mezzo;
import team5.exceptions.NotFoundException;

import java.util.UUID;

public class MezzoDAO {
    private final EntityManager em;

    public MezzoDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Mezzo nuovoMezzo) {
        // 1.nuova transazione
        EntityTransaction transaction = em.getTransaction();

        // 2. Inizializzare transazione
        transaction.begin();

        // 3. Aggiungiamo il nuovoEvento al PersistenceContext
        em.persist(nuovoMezzo);

        // 4. Commit
        transaction.commit();

        // 5. SO
        System.out.println("Il mezzo " + nuovoMezzo.toString() + " è stato salvato correttamente nel DB!");

    }

    public Mezzo findbyID(String id_mezzo) {
        Mezzo found = em.find(Mezzo.class, UUID.fromString(id_mezzo));
        if (found == null) throw new NotFoundException(UUID.fromString(id_mezzo));
        return found;
    }


    public void findByIdAndDelete(String id_mezzo) {
        // 1. Cerco lo studente
        Mezzo found = this.findbyID(id_mezzo);

        // 2. Creo una nuova transazione
        EntityTransaction transaction = em.getTransaction();

        // 3. Faccio partire la transazione
        transaction.begin();

        // 4. Rimuovo dal Persistence Context l'oggetto in questione
        em.remove(found);

        // 5. commit
        transaction.commit();

        // 6. Log di avvenuta cancellazione
        System.out.println("Il mezzo con id: " + id_mezzo + " è stato eliminato correttamente!");
    }
}
