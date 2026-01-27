package team5.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import team5.entities.Manutenzione;
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

    public void save(Manutenzione nuovaManutenzione){
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

    public Manutenzione findbyID(UUID id_manutenzione){
        Manutenzione found = em.find(Manutenzione.class,id_manutenzione);
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

    public double getPercentualeManutenzioneMezzo(UUID idMezzo, LocalDate dataInizioPeriodo, LocalDate dataFinePeriodo) {
        List<Manutenzione> lista = em.createQuery(
                        "SELECT m FROM Manutenzione m WHERE m.mezzo_in_manutenzione.id_mezzo = :idMezzo " +
                                "AND m.inizio_manutenzione >= :inizio AND (m.fine_manutenzione <= :fine OR m.fine_manutenzione IS NULL)",
                        Manutenzione.class)
                .setParameter("idMezzo", idMezzo)
                .setParameter("inizio", dataInizioPeriodo)
                .setParameter("fine", dataFinePeriodo)
                .getResultList();

        long giorniTotaliPeriodo = ChronoUnit.DAYS.between(dataInizioPeriodo, dataFinePeriodo);
        if (giorniTotaliPeriodo <= 0) return 0.0;

        long giorniInManutenzione = 0;

        for (Manutenzione m : lista) {
            LocalDate fine = (m.getFine_manutenzione() != null) ? m.getFine_manutenzione() : LocalDate.now();
            giorniInManutenzione += ChronoUnit.DAYS.between(m.getInizio_manutenzione(), fine);
        }

        // Calcolo percentuale
        double percentuale = ((double) giorniInManutenzione / giorniTotaliPeriodo) * 100;
        return Math.round(percentuale * 100.0) / 100.0;
    }
}
