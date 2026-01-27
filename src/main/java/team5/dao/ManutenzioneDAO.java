package team5.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import team5.entities.Manutenzione;
import team5.exceptions.NotFoundException;

import java.util.UUID;

public class ManutenzioneDAO {
    private final EntityManager em;
    public ManutenzioneDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Manutenzione nuovaManutenzione){
        // 1.nuova transazione
        EntityTransaction transaction = em.getTransaction();

        // 2. Inizializzare transazione
        transaction.begin();

        // 3. Aggiungiamo il nuovoEvento al PersistenceContext
        em.persist(nuovaManutenzione);

        // 4. Commit
        transaction.commit();

        // 5. SO
        System.out.println("La manutenzione " + nuovaManutenzione.toString() + " è stata salvata correttamente nel DB!");

    }

    public Manutenzione findbyID(String id_manutenzione){
        Manutenzione found = em.find(Manutenzione.class, UUID.fromString(id_manutenzione));
        if (found == null) throw new NotFoundException(id_manutenzione);
        return found;
    }

    public void findByIdAndDelete(String id_manutenzione) {
        // 1. Cerco lo studente
        Manutenzione found = this.findbyID(id_manutenzione);

        // 2. Creo una nuova transazione
        EntityTransaction transaction = em.getTransaction();

        // 3. Faccio partire la transazione
        transaction.begin();

        // 4. Rimuovo dal Persistence Context l'oggetto in questione
        em.remove(found);

        // 5. commit
        transaction.commit();

        // 6. Log di avvenuta cancellazione
        System.out.println("La manutenzione con id: " + id_manutenzione + " è stata eliminata correttamente!");
    }
}
