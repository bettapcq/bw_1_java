package team5.dao;

import jakarta.persistence.*;
import team5.entities.Biglietto;
import team5.entities.Mezzo;
import team5.exceptions.AlreadyEndorsedTicket;
import team5.exceptions.NotFoundException;

import java.time.LocalDate;
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

    public void vidimazioneBiglietto(String codiceunivoco, Mezzo mezzo) {
        EntityTransaction et = entityManager.getTransaction();
        et.begin();
        Biglietto biglietto = findByCodiceUnivoco(codiceunivoco);
        if (biglietto.getData_validazione() != null) {
            throw new AlreadyEndorsedTicket(" questo biglietto è già stato vidimato, non fare il furbo bastardo");
        } else {
            biglietto.setData_validazione(LocalDate.now());
            // entityManager.merge(biglietto);
            biglietto.setMezzi(mezzo);
            entityManager.merge(biglietto);
            et.commit();
            System.out.println("il biglietto   " + codiceunivoco + " " + mezzo + " è stato vidimato ");
        }
    }
    //  numero biglietti vidimati su un mezzo
    public long numeroBigliettiVidimatiPerMezzo(Mezzo mezzo) {
        TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(b) FROM Biglietto b WHERE b.data_validazione IS NOT NULL AND b.mezzi = :mezzo", Long.class);
        query.setParameter("mezzo", mezzo);
        return query.getSingleResult();
    }

    // numero biglietti vidimati in un periodo
    public long numeroBigliettiVidimatiDaData(LocalDate inizio) {
            TypedQuery<Long> query = entityManager.createQuery(
                    "SELECT COUNT(b) FROM Biglietto b WHERE b.data_validazione IS NOT NULL AND b.data_validazione >= :inizio", Long.class);

            query.setParameter("inizio", inizio);
            return query.getSingleResult();
        }
    }

