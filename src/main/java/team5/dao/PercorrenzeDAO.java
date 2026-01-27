package team5.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import team5.entities.Percorrenza;
import team5.exceptions.NotFoundException;

import java.util.UUID;

public class PercorrenzeDAO {
    private EntityManager em;
    public PercorrenzeDAO(EntityManager em) {this.em = em;}

    public void savePerc(Percorrenza newPercorrenza) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(newPercorrenza);
        transaction.commit();
        System.out.println("la percorrenza con id: " + newPercorrenza.getId_percorrenza()+ " è stata salvata correttamente");



    }

    public Percorrenza findByIdPercorrenza (UUID idpercorrenza) {
        Percorrenza found = em.find(Percorrenza.class, UUID.fromString(String.valueOf(idpercorrenza)));
        if (found == null ) throw new NotFoundException(idpercorrenza);
        return found;
    }



    public void findPercorrenzaByIdAndDelete(UUID id_percorrenza) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Query query = em.createQuery("DELETE FROM Percorrenza a WHERE a.id_percorrenza = :id");
        query.setParameter("id", id_percorrenza);
        int numDeleted = query.executeUpdate();
        transaction.commit();
        System.out.println("sono stati cancellati :" + numDeleted + " elementi");
    }



}
