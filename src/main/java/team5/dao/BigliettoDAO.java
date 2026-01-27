package team5.dao;

import jakarta.persistence.*;
import team5.entities.Biglietto;

import java.util.UUID;

public class BigliettoDAO {
    private final EntityManager entityManager;

    public BigliettoDAO(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    //SAVE BIGLIETTO
    public void save(Biglietto newBiglietto){
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(newBiglietto);
        transaction.commit();
        System.out.println( "Il biglietto" +newBiglietto.getCodice_univoco()+ " stato salvato nel DB!!");
    }

    //FIND BIGLIETTO CODICE_UNIVOCO
    public Biglietto findByCodiceUnivoco(String codice_univoco) {
        try {
            TypedQuery<Biglietto> query =
                    entityManager.createQuery(
                    "SELECT b FROM Biglietto b WHERE b.codice_univoco = :codice", Biglietto.class);
            query.setParameter("codice", codice_univoco);

            Biglietto biglietto = query.getSingleResult();
            System.out.println("Biglietto Trovato :" + biglietto);
            return biglietto;
        } catch (NoResultException b) {
            System.out.println("Biglietto non trovato per questo codice:" + codice_univoco);
            throw new EntityNotFoundException();
        }
    }

    //DELETE BIGLIETTO CODICE_UNIVOCO
    public void deleteByCodiceUNivoco(String codice_univoco) {
        EntityTransaction tr = entityManager.getTransaction();
        tr.begin();
        Biglietto found = findByCodiceUnivoco(codice_univoco);
        entityManager.remove(found);
        tr.commit();
        System.out.println("Il biglietto con codice:" + codice_univoco + "è stato eliminato!!");
    }

}
