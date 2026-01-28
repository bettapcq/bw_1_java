package team5.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import team5.entities.Tessera;
import team5.exceptions.NotFoundException;

import java.util.UUID;

public class TessereDAO {


    private EntityManager em;

    public TessereDAO(EntityManager em) {
        this.em = em;
    }

    //SAVE:
    public void save(Tessera nuovaTessera) {
        EntityTransaction t = em.getTransaction();

        t.begin();

        em.persist(nuovaTessera);

        t.commit();

        System.out.println("La tessera con id " + nuovaTessera.getIdTessera() + " è stata aggiunta al DB");

    }

    //SEARCH:
    public Tessera getById(String idTessera) {
        TypedQuery<Tessera> query = em.createQuery(
                "SELECT t FROM Tessera t WHERE t.idTessera = :idTessera",
                Tessera.class
        );
        query.setParameter("idTessera", UUID.fromString(idTessera));

        return query.getSingleResult();
    }

    //DELETE:
    public void deleteById(String idTessera) {
        EntityTransaction t = em.getTransaction();
        t.begin();

        Query query = em.createQuery("DELETE FROM Tessera t WHERE t.idTessera = :idTessera");
        query.setParameter("idTessera", idTessera);

        int numDeleted = query.executeUpdate();

        t.commit();
        System.out.println(numDeleted + " elementi cancellati");
        System.out.println("La tessera con id " + idTessera + " è stata rimossa dal DB");
    }


    //RINNOVO TESSERA
    public void renewCard(UUID idTessera) {
        Tessera found = em.find(Tessera.class, idTessera);
        if (found != null) {
            if (found.isAttiva()) {
                System.out.println("La tessera è ancora attiva, scadrà il giorno: " + found.getData_scadenza());
            } else {
                found.setData_scadenza(found.getData_scadenza().plusYears(1));
                found.setAttiva(true);
                System.out.println("La tessera è stata rinnovata, la nuova data di scadenza è: " + found.getData_scadenza());
            }
        } else throw new NotFoundException(idTessera);
    }
}
