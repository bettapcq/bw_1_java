package team5.dao;

import jakarta.persistence.*;
import team5.entities.Biglietto;
import team5.entities.Utente;

import java.util.UUID;

public class UtenteDAO {
    private final EntityManager entityManager;

    public UtenteDAO(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    //SAVE UTENTE
    public void save(Utente newUtente){
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(newUtente);
        transaction.commit();
        System.out.println( "" +newUtente.getNome()+ " " +newUtente.getCognome()+ " stato salvato nel DB!!");
    }

    //FIND UTENTE ID
    public Utente findById(UUID id_utente) {
        try {
            TypedQuery<Utente> query =
                    entityManager.createQuery( "SELECT u FROM Utente u WHERE u.idUtente = :id", Utente.class);
            query.setParameter("id", id_utente);

            Utente utente = query.getSingleResult();
            System.out.println("Utente Trovato :" + utente.getIdUtente());
            return utente;
        } catch (NoResultException e) {
            System.out.println("Utente non Trovato per questo id:" + id_utente);
            throw new EntityNotFoundException();
        }
    }

    //DELETE UTENTE ID
    public void deleteById(UUID id_utente) {
        EntityTransaction tr = entityManager.getTransaction();
        tr.begin();
        Utente found = findById(id_utente);
        entityManager.remove(found);
        tr.commit();
        System.out.println("L'utente con Id:" + id_utente + "è stato eliminato!!");
    }

}
