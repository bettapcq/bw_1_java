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

    public PercorrenzeDAO(EntityManager em) {
        this.em = em;
    }

    public void savePerc(Percorrenza newPercorrenza) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(newPercorrenza);
        transaction.commit();
        System.out.println("la percorrenza con id: " + newPercorrenza.getId_percorrenza() + " è stata salvata correttamente");
    }

    public Percorrenza findByIdPercorrenza(UUID idpercorrenza) {
        Percorrenza found = em.find(Percorrenza.class, UUID.fromString(String.valueOf(idpercorrenza)));
        if (found == null) throw new NotFoundException(idpercorrenza);
        return found;
    }


    public void findPercorrenzaByIdAndDelete(UUID id_percorrenza) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Query query = em.createQuery("DELETE FROM Percorrenza a WHERE a.id_percorrenza = :id");
        query.setParameter("id", id_percorrenza);
        int numDeleted = query.executeUpdate();
        transaction.commit();
        System.out.println("Sono stati cancellati :" + numDeleted + " elementi");
    }

    public long findNumeroPercorrenze(String id_tratta, String id_mezzo) {
        Query query = em.createQuery(
                "SELECT COUNT(p) FROM Percorrenza p WHERE p.tratta.id_tratta = :id_tratta AND p.mezzo.id_mezzo = :id_mezzo"
        );
        query.setParameter("id_tratta", UUID.fromString(id_tratta));
        query.setParameter("id_mezzo", UUID.fromString(id_mezzo));
        long rslt = (long) query.getSingleResult();
        System.out.println("Sono effettuate : " + rslt + " percorrenze");
        return rslt;
    }

    public List<Integer> findTempoEffettivoPercorrenza(String id_tratta, String id_mezzo) {
        Query query = em.createQuery(
                "SELECT p.tempo_effettivo_minuti FROM Percorrenza p WHERE p.tratta.id_tratta = :id_tratta AND p.mezzo.id_mezzo = :id_mezzo"
        );

        query.setParameter("id_tratta", UUID.fromString(id_tratta));
        query.setParameter("id_mezzo", UUID.fromString(id_mezzo));
        List<Integer> rslt = query.getResultList();
        System.out.println("Lista percorrenze effettive: " + rslt);
        return rslt;
    }

    public double findTempoEffettivoPercorrenzaMedio(String id_tratta, String id_mezzo) {
        List<Integer> rslt = this.findTempoEffettivoPercorrenza(id_tratta, id_mezzo);
        OptionalDouble risultato = rslt.stream().mapToInt(i -> i.intValue()).average();
        System.out.println("La percorrenza media della tratta " + id_tratta + " e del mezzo " + id_mezzo + " è: " + risultato.getAsDouble());
        return risultato.getAsDouble();
    }
}
