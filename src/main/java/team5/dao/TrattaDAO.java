package team5.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import team5.entities.Manutenzione;
import team5.entities.Tratta;
import team5.exceptions.NotFoundException;

import java.util.UUID;

public class TrattaDAO {
    private final EntityManager em;
    public TrattaDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Tratta nuovaTratta){
        // 1.nuova transazione
        EntityTransaction transaction = em.getTransaction();

        // 2. Inizializzare transazione
        transaction.begin();

        // 3. Aggiungiamo il nuovoEvento al PersistenceContext
        em.persist(nuovaTratta);

        // 4. Commit
        transaction.commit();

        // 5. SO
        System.out.println("La tratta " + nuovaTratta.toString() + " è stata salvata correttamente nel DB!");

    }

    public Tratta findbyID(String id_tratta){
        Tratta found = em.find(Tratta.class, UUID.fromString(id_tratta));
        if (found == null) throw new NotFoundException(UUID.fromString(id_tratta));
        return found;
    }

    public void findByIdAndDelete(String id_tratta) {
        // 1. Cerco lo studente
        Tratta found = this.findbyID(id_tratta);

        // 2. Creo una nuova transazione
        EntityTransaction transaction = em.getTransaction();

        // 3. Faccio partire la transazione
        transaction.begin();

        // 4. Rimuovo dal Persistence Context l'oggetto in questione
        em.remove(found);

        // 5. commit
        transaction.commit();

        // 6. Log di avvenuta cancellazione
        System.out.println("La tratta con id: " + id_tratta + " è stata eliminata correttamente!");
    }
}
