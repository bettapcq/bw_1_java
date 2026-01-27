package team5.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import team5.entities.Tessera;

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
        query.setParameter("idTessera", idTessera);

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

}
