package team5.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import team5.entities.Percorrenza;
import team5.exceptions.NotFoundException;

import java.util.List;
import java.util.OptionalDouble;
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

    public int findNumeroPercorrenze(UUID id_tratta, UUID id_mezzo) {
        Query query = em.createQuery(
                "SELECT COUNT(p) FROM Percorrenza p WHERE p.id_tratta = :id_tratta AND p.id_mezzo = :id_mezzo"
        );

        query.setParameter("id_tratta", id_tratta);
        query.setParameter("id_mezzo", id_mezzo);
        int rslt = (int) query.getSingleResult();
        return rslt;
    }

    public List<Integer> findTempoEffettivoPercorrenza(UUID id_tratta, UUID id_mezzo) {
        Query query = em.createQuery(
                "SELECT p.tempo_effettivo_minuti FROM Percorrenza p WHERE p.id_tratta = :id_tratta AND p.id_mezzo = :id_mezzo"
        );

        query.setParameter("id_tratta", id_tratta);
        query.setParameter("id_mezzo", id_mezzo);
        List<Integer> rslt =  query.getResultList();
        return rslt;
    }

    public float findTempoEffettivoPercorrenzaMedio(UUID id_tratta, UUID id_mezzo){
        Query query = em.createQuery(
                "SELECT p.tempo_effettivo_minuti FROM Percorrenza p WHERE p.id_tratta = :id_tratta AND p.id_mezzo = :id_mezzo"
        );
        query.setParameter("id_tratta", id_tratta);
        query.setParameter("id_mezzo", id_mezzo);
        List<Integer> rslt =  query.getResultList();
        OptionalDouble risultato = rslt.stream().mapToInt(i -> i.intValue()).average();
        return (float) risultato.getAsDouble();
    }
}
