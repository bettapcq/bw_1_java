package team5.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import team5.entities.Rivenditore;

import java.util.List;
import java.util.UUID;

public class RivenditoreDAO {

    private final EntityManager em;

    public RivenditoreDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Rivenditore rivenditore) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(rivenditore);
        tx.commit();
    }

    public Rivenditore findById(UUID id) {
        return em.find(Rivenditore.class, id);
    }

    public List<Rivenditore> findAll() {
        return em.createQuery(
                "SELECT r FROM Rivenditore r",
                Rivenditore.class
        ).getResultList();
    }

    public void delete(UUID id) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Rivenditore r = em.find(Rivenditore.class, id);
        if (r != null) {
            em.remove(r);
        }
        tx.commit();
    }
}
