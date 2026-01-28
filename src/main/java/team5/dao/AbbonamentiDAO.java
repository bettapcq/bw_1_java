package team5.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import team5.entities.Abbonamento;
import team5.entities.Periodicita;
import team5.entities.Rivenditore;
import team5.entities.Tessera;
import team5.exceptions.NotFoundException;

import java.time.LocalDate;
import java.util.UUID;

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

    //VERIFICA VALITIDA' ABBONAMENTO BY NUM TESSERA:
    public void checkValidityByTessera(String idTessera) {
        Abbonamento found = em.createQuery(
                        "SELECT a FROM Abbonamento a WHERE a.tessera.idTessera = :idTessera",
                        Abbonamento.class)
                .setParameter("idTessera", UUID.fromString(idTessera))
                .getSingleResult();
        if (found != null) {
            if (found.getData_scadenza().isAfter(LocalDate.now())) {
                System.out.println("L'abbonamento n° " + found.getCodice_univoco() + " è scaduto!");
            } else {
                System.out.println("L'abbonamento n° " + found.getCodice_univoco() + " è attivo e scadrà il giorno: " + found.getData_scadenza());
            }
        } else throw new NotFoundException(found.getIdAbbonamento());
    }

    //EMISSIONE Abbonamenti
    public void emissioneAbbonamenti(LocalDate data_emissione, double costo, Periodicita periodicita, Rivenditore rivenditore, Tessera tessera) {
        EntityTransaction tr = em.getTransaction();

        try {
            tr.begin();
            Abbonamento abbonamento = new Abbonamento();
            abbonamento.setCosto(costo);
            abbonamento.setTessera(tessera);
            abbonamento.setPeriodicita(periodicita);
            abbonamento.setRivenditore(rivenditore);
            abbonamento.setDataEmissione(data_emissione);

            //GENERA UN CODICE UNIVOCO
            Long count = (Long) em.createQuery("SELECT COUNT (a) FROM Abbonamento a").getSingleResult();
            String codice = String.format("A-%04d", count + 1);
            abbonamento.setCodice_univoco(codice);

            em.persist(abbonamento);
            tr.commit();
            System.out.println("Abbonamento emesso " + abbonamento);

        } catch (RuntimeException e) {
            if (tr.isActive()) tr.rollback();
            throw e;
        }

    }

    //NUMERO DI ABBONAMENTI EMESSI DA UN PUNTO VENDITA E PER UN PERIODO DI TEMPO
    public Long numeroAbbonamentiEmessiPerRivenditoriEPerPeriodo(Rivenditore rivenditore, LocalDate inizio, LocalDate fine) {
        TypedQuery<Long> query = em.createQuery("" +
                "SELECT COUNT(a) FROM Abbonamento a " +
                "WHERE a.rivenditore = :rivenditore AND a.dataEmissione BETWEEN :inizio AND :fine", Long.class);
        query.setParameter("rivenditore", rivenditore);
        query.setParameter("inizio", inizio);
        query.setParameter("fine", fine);

        System.out.println("Nel punto vendita " + rivenditore + " dal " + inizio + " al " + fine + " sono stati emessi " + query.getSingleResult() + " abbonamenti");

        return query.getSingleResult();
    }
}
