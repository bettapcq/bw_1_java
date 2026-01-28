package team5.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import team5.entities.Manutenzione;
import team5.entities.Mezzo;
import team5.exceptions.NotFoundException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

public class ManutenzioneDAO {
    private final EntityManager em;

    public ManutenzioneDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Manutenzione nuovaManutenzione) {
        // 1.nuova transazione
        EntityTransaction transaction = em.getTransaction();

        // 2. Inizializzare transazione
        transaction.begin();

        // 3. Aggiungiamo il nuovoEvento al PersistenceContext
        em.persist(nuovaManutenzione);

        // 4. Commit
        transaction.commit();

        // 5. SO
        System.out.println("La manutenzione " + nuovaManutenzione.toString() + " è stata salvata correttamente nel DB!");

    }

    public Manutenzione findbyID(UUID id_manutenzione) {
        Manutenzione found = em.find(Manutenzione.class, id_manutenzione);
        if (found == null) throw new NotFoundException(id_manutenzione);
        return found;
    }

    public void findByIdAndDelete(UUID id_manutenzione) {
        // 1. Cerco lo studente
        Manutenzione found = this.findbyID(id_manutenzione);

        // 2. Creo una nuova transazione
        EntityTransaction transaction = em.getTransaction();

        // 3. Faccio partire la transazione
        transaction.begin();

        // 4. Rimuovo dal Persistence Context l'oggetto in questione
        em.remove(found);

        // 5. commit
        transaction.commit();

        // 6. Log di avvenuta cancellazione
        System.out.println("La manutenzione con id: " + id_manutenzione + " è stata eliminata correttamente!");
    }
    //percentuale manutenzione-servizio

    public void getPercentualeManutenzioneMezzo(Mezzo mezzo) {
        Query query = em.createQuery(
                "SELECT m FROM Manutenzione m WHERE m.mezzo_in_manutenzione.id_mezzo = :idMezzo");
        query.setParameter("idMezzo", mezzo.getId_mezzo());

        LocalDate dataInizioPeriodo = mezzo.getInizio_attivita();
        LocalDate dataFinePeriodo = mezzo.getFine_attivita();
        List<Manutenzione> listaM = query.getResultList();

        LocalDate dataOggi = LocalDate.now();

        if (dataFinePeriodo == null) {
            dataFinePeriodo = dataOggi;
        }

//        LocalDate dataInizioMan = manutenzione.getInizio_manutenzione();
//                LocalDate dataFineMan = manutenzione.getFine_manutenzione();
//
//                if(dataFineMan == null){
//                    dataFineMan = dataOggi;
//                }

        List<Integer> totGgPerManutenzione = listaM.stream()
                .map(manutenzione ->

                        Math.toIntExact(ChronoUnit.DAYS.between(manutenzione.getInizio_manutenzione(), manutenzione.getFine_manutenzione() == null ? LocalDate.now() : manutenzione.getFine_manutenzione())))
                .toList();

        int totGgInManutenzione = totGgPerManutenzione.stream().reduce(0, (acc, gg) -> acc + gg);

        int giorniTotaliAttività = Math.toIntExact(ChronoUnit.DAYS.between(dataInizioPeriodo, dataFinePeriodo));

        int totGgInServizio = giorniTotaliAttività - totGgInManutenzione;

        // Calcolo percentuale
        double percentuale = ((double) totGgInManutenzione / giorniTotaliAttività) * 100;

        System.out.println("Giorni totali in servizio: " + totGgInServizio);
        System.out.println("Giorni totali in manutenzione: " + totGgInManutenzione);
        System.out.println("Giorni totali di attività: " + giorniTotaliAttività);
        System.out.println("Percentuale manutenzione-servizio: " + Math.round(percentuale * 100.0) / 100.0 + "%");

// 1) per ogni manutenzione - il tot giorni trascorsi per quella manutenzione
// 2) prendo tutti i totali delle manutenzioni (gg) - reduce
//tot complessivo di tutte le manutenzioni della lista
// tot gg attività di questo mezzo, e sottraggo il tot gg complessivi manutenzioni.
//        int giorniTotaliPeriodo = Math.toIntExact(ChronoUnit.DAYS.between(dataInizioPeriodo, dataFinePeriodo));
//        if (giorniTotaliPeriodo <= 0) return 0.0;
//
//        int giorniInManutenzione = 0;
//
//        for (Manutenzione m : lista) {
//            LocalDate fine = (m.getFine_manutenzione() != null) ? m.getFine_manutenzione() : LocalDate.now();
//            giorniInManutenzione += ChronoUnit.DAYS.between(m.getInizio_manutenzione(), fine);
//        }
//
//
    }

    public void periodiManutenzione(String id_mezzo) {
        Query query1 = em.createQuery(
                "SELECT m.inizio_manutenzione FROM Manutenzione m WHERE m.mezzo_in_manutenzione.id_mezzo = :id_mezzo"
        );
        query1.setParameter("id_mezzo", UUID.fromString(id_mezzo));
        List<LocalDate> rslt1 = query1.getResultList();

        Query query2 = em.createQuery(
                "SELECT m.fine_manutenzione FROM Manutenzione m WHERE m.mezzo_in_manutenzione.id_mezzo = :id_mezzo"
        );
        query2.setParameter("id_mezzo", UUID.fromString(id_mezzo));
        List<LocalDate> rslt2 = query2.getResultList();

        for (int i = 0; i < rslt1.size(); i++) {
            LocalDate l1 = null;
            LocalDate l2 = null;
            try {
                l1 = rslt1.get(i);
            } catch (Exception e) {
            }

            try {
                l2 = rslt2.get(i);
            } catch (Exception e) {
            }
            if (l2 == null) {
                System.out.println(
                        "Manutenzione dal " + l1 +
                                " ad oggi"
                );
            } else {
                System.out.println(
                        "Manutenzione dal " + l1 +
                                " al " + l2
                );
            }

        }
    }
}
