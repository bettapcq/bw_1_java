package team5.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import team5.entities.Abbonamento;

public class AbbonamentiDAO {

    private EntityManager em;

    public AbbonamentiDAO(EntityManager em) {
        this.em = em;
    }

    //SAVE:
    public void save(Abbonamento nuovoAbbonamento) {
        EntityTransaction t = em.getTransaction();

        t.begin();

        em.persist(nuovoAbbonamento);

        t.commit();

        System.out.println("L'abbonamento n° " + nuovoAbbonamento.getCodice_univoco() + " è stato aggiunto al DB");

    }

    //SEARCH:
    public Abbonamento getByUniqueNumber(String codiceUnivoco) {
        TypedQuery<Abbonamento> query = em.createQuery(
                "SELECT a FROM Abbonamento a WHERE a.codice_univoco = :codiceUnivoco",
                Abbonamento.class
        );
        query.setParameter("codiceUnivoco", codiceUnivoco);

        return query.getSingleResult();
    }

    //DELETE:
    public void deleteByUniqueNumber(String codiceUnivoco) {
        EntityTransaction t = em.getTransaction();
        t.begin();

        Query query = em.createQuery("DELETE FROM Abbonamento a WHERE a.codice_univoco = :codiceUnivoco");
        query.setParameter("codiceUnivoco", codiceUnivoco);

        int numDeleted = query.executeUpdate();

        t.commit();
        System.out.println(numDeleted + " elementi cancellati");
        System.out.println("L'abbonamento num° " + codiceUnivoco + " è stato rimosso dal DB");
    }

}
