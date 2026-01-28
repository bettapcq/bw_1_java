package team5.dao;

import jakarta.persistence.*;
import team5.entities.Biglietto;
import team5.entities.Mezzo;
import team5.entities.Rivenditore;
import team5.exceptions.AlreadyEndorsedTicket;

import java.time.LocalDate;

public class BigliettoDAO {
    private final EntityManager entityManager;

    public BigliettoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //SAVE BIGLIETTO
    public void save(Biglietto newBiglietto) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(newBiglietto);
        transaction.commit();
        System.out.println("Il biglietto" + newBiglietto.getCodice_univoco() + " stato salvato nel DB!!");
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
    public int numeroBigliettiVidimatiPerMezzo(Mezzo mezzo) {
        TypedQuery<Integer> query = entityManager.createQuery("SELECT COUNT(b) FROM Biglietto b WHERE b.data_validazione IS NOT NULL AND b.mezzi = :mezzo", Integer.class);
        query.setParameter("mezzo", mezzo);
        System.out.println("Su questo mezzo sono stati vidimati " + query.getSingleResult() + " biglietti");
        return query.getSingleResult();

    }

    // numero biglietti vidimati in un periodo
    public int numeroBigliettiVidimatiDaData(LocalDate inizio) {
        TypedQuery<Integer> query = entityManager.createQuery(
                "SELECT COUNT(b) FROM Biglietto b WHERE b.data_validazione IS NOT NULL AND b.data_validazione >= :inizio", Integer.class);

        query.setParameter("inizio", inizio);

        System.out.println("Dal " + inizio + " sono stati vidimati " + query.getSingleResult() + " biglietti");
        return query.getSingleResult();
    }

    //EMISSIONE BIGLIETTI
    public void emissioneBiglietti(LocalDate data_emissione, Double costo, Rivenditore rivenditore) {
        EntityTransaction tr = entityManager.getTransaction();

        try {
            tr.begin();
            Biglietto biglietto = new Biglietto();
            biglietto.setData_emissione(data_emissione);
            biglietto.setCosto(costo);
            biglietto.setRivenditore(rivenditore);

            //GENERA UN CODICE UNIVOCO
            int count = (Integer) entityManager.createQuery("SELECT COUNT (b) FROM Biglietto b").getSingleResult();
            String codice = String.format("B-%04d", count + 1);
            biglietto.setCodice_univoco(codice);

            entityManager.persist(biglietto);
            tr.commit();
            System.out.println("Biglietto emesso " + biglietto);

        } catch (RuntimeException e) {
            if (tr.isActive()) tr.rollback();
            throw e;
        }

    }

    //NUMERO DI BIGLIETTI EMESSI DA UN PUNTO VENDITA E PER UN PERIODO DI TEMPO
    public int numeroBigliettiEmessiPerRivenditoriEPerPeriodo(Rivenditore rivenditore, LocalDate inizio, LocalDate fine) {
        TypedQuery<Integer> query = entityManager.createQuery("" +
                "SELECT COUNT(b) FROM Biglietto b " +
                "WHERE b.rivenditore = :rivenditore AND b.data_emissione BETWEEN :inizio AND :fine", Integer.class);
        query.setParameter("rivenditore", rivenditore);
        query.setParameter("inizio", inizio);
        query.setParameter("fine", fine);

        System.out.println("Nel punto vendita " + rivenditore + " dal " + inizio + " al " + fine + " sono stati emessi " + query.getSingleResult() + " biglietti");

        return query.getSingleResult();
    }
}

