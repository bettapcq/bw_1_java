package team5;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import team5.dao.*;
import team5.entities.*;

import java.time.LocalDate;
import java.time.LocalTime;


public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("bw1javapu");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        BigliettoDAO b = new BigliettoDAO(em);
        RivenditoreDAO r = new RivenditoreDAO(em);
        UtenteDAO u = new UtenteDAO(em);
        TessereDAO t = new TessereDAO(em);
        AbbonamentiDAO a = new AbbonamentiDAO(em);
        ManutenzioneDAO md = new ManutenzioneDAO(em);
        MezzoDAO m = new MezzoDAO(em);
        PercorrenzeDAO p = new PercorrenzeDAO(em);
        TrattaDAO tr = new TrattaDAO(em);
//        Rivenditore rivenditore1 = new RivenditoreAutorizzato(394762938, "via da qua", LocalTime.now(), LocalTime.of(17, 0));
//        Rivenditore rivenditore2 = new RivenditoreAutorizzato(394754938, "via di li", LocalTime.now(), LocalTime.of(17, 0));
//        Rivenditore rivenditore3 = new DistributtoreAutomatico("Via di la", Stato.ATTIVO);
//        Rivenditore rivenditore4 = new DistributtoreAutomatico("Via per di qua", Stato.NON_ATTIVO);
//        Biglietto biglietto1 = new Biglietto(LocalDate.of(2026, 10, 10), 11.00, "b143", rivenditore1);
//        Biglietto biglietto2 = new Biglietto(LocalDate.now(), 10.00, "b643", rivenditore2);
//        Biglietto biglietto3 = new Biglietto(LocalDate.now(), 10.00, "b743", rivenditore3);
//        Biglietto biglietto4 = new Biglietto(LocalDate.now(), 10.00, "b843", rivenditore3);
//
//        Rivenditore rivenditore4DB = r.findById("67f3bdce-3e42-479e-a53c-ad0663f50830");
//
//        Biglietto biglietto5 = new Biglietto(LocalDate.of(2025, 3, 4), 10.00, "b943", rivenditore4DB);
//        Biglietto biglietto6 = new Biglietto(LocalDate.of(2025, 7, 11), 10.00, "b944", rivenditore4DB);
//
//        System.out.println("Hello World!1 ");

//        r.save(rivenditore1);
//        b.save(biglietto1);
//        r.save(rivenditore2);
//        b.save(biglietto2);
//        r.save(rivenditore3);
//        b.save(biglietto3);
//        b.save(biglietto4);
//        r.save(rivenditore4);
//        b.save(biglietto5);
//        b.save(biglietto6);

//        Utente utente1 = new Utente("Giacomo", "Poretti", LocalDate.of(1992, 06, 14));
//        Utente utente2 = new Utente("Aldo", "Baglio", LocalDate.of(1982, 07, 19));
//
//        Tessera tessera1 = new Tessera(LocalDate.now(), utente1);
//        Tessera tessera2 = new Tessera(LocalDate.of(2026, 01, 27), utente2);

//        u.save(utente1);
//        u.save(utente2);
//        t.save(tessera1);
//        t.save(tessera2);

//        Rivenditore rivenditore1DB = r.findById("696eb568-23da-46da-abf2-dd3cc6bc3ee8");
//        Rivenditore rivenditore3DB = r.findById("70d739d3-7bae-4f46-a56d-a68d813a2daf");
//        Tessera tessera1DB = t.getById("a3197b44-6b45-4149-a72c-cdf6f6335797");
//        Tessera tessera2DB = t.getById("2acf6b7c-4331-4d5b-b6fb-c9d744b44fe3");
//
//        Abbonamento abbonamento1 = new Abbonamento(LocalDate.now(), 50.00, "a234", Periodicita.SETTIMANALE, rivenditore1DB, tessera1DB);
//        Abbonamento abbonamento2 = new Abbonamento(LocalDate.of(2026, 2, 28), 120.00, "a237", Periodicita.MENSILE, rivenditore3DB, tessera2DB);
//
//        a.save(abbonamento1);
//        a.save(abbonamento2);

//        Mezzo mezzo1 = new Mezzo(40, LocalDate.of(2025, 01, 01), null, TipoMezzo.AUTOBUS);
//        Mezzo mezzo2 = new Mezzo(50, LocalDate.of(2024, 01, 01), null, TipoMezzo.TRAM);
//        Mezzo mezzo3 = new Mezzo(50, LocalDate.of(2022, 01, 01), LocalDate.of(2026, 01, 01), TipoMezzo.TRAM);
//
//        m.save(mezzo1);
//        m.save(mezzo2);
//        m.save(mezzo3);

//        Mezzo mezzo1DB = m.findbyID("2017f3a8-2109-49a1-a0a9-c8a176ae280d");
//        Mezzo mezzo2DB = m.findbyID("dbebd46a-003f-489c-b0c5-f5f7cd92d14c");
//        Mezzo mezzo3DB = m.findbyID("a0ecca98-9578-43a4-9c6c-6edbc82d3754");
//
//        Manutenzione manutenzione1 = new Manutenzione(LocalDate.of(2026, 1, 7), LocalDate.of(2026, 1, 20), mezzo1DB, Tipologia.CARROZZERIA);
//        Manutenzione manutenzione2 = new Manutenzione(LocalDate.of(2026, 1, 6), LocalDate.of(2026, 1, 17), mezzo2DB, Tipologia.MOTORE);
//        Manutenzione manutenzione3 = new Manutenzione(LocalDate.of(2026, 1, 27), LocalDate.of(2026, 1, 28), mezzo2DB, Tipologia.CARROZZERIA);
//        Manutenzione manutenzione4 = new Manutenzione(LocalDate.of(2023, 1, 27), LocalDate.of(2023, 1, 28), mezzo3DB, Tipologia.FRENI);
//        Manutenzione manutenzione5 = new Manutenzione(LocalDate.of(2026, 1, 27), LocalDate.now(), mezzo3DB, Tipologia.FRENI);

//        md.save(manutenzione1);
//        md.save(manutenzione2);
//        md.save(manutenzione3);
//        md.save(manutenzione4);

//        Tratta tratta1 = new Tratta(30, "via per di qua", "via qua", 60);
//        Tratta tratta2 = new Tratta(15, "via per di si", "via la", 30);
//
//        tr.save(tratta1);
//        tr.save(tratta2);

//        Tratta tratta1DB = tr.findbyID("bac492f1-12c6-405e-88cb-1ff90bb929bf");
//        Tratta tratta2DB = tr.findbyID("5c85d281-2a69-480e-8ff6-d45b73d03265");
//
//        Percorrenza percorrenza1 = new Percorrenza(mezzo1DB, tratta1DB, 40);
//        Percorrenza percorrenza2 = new Percorrenza(mezzo2DB, tratta2DB, 40);
//        Percorrenza percorrenza3 = new Percorrenza(mezzo1DB, tratta2DB, 40);
//
//        p.savePerc(percorrenza1);
//        p.savePerc(percorrenza2);
//        p.savePerc(percorrenza3);

        // TEST METODI:
        //1)vidimazione:
//        try {
//            b.vidimazioneBiglietto("B-0006", mezzo1DB);
//        } catch (AlreadyEndorsedTicket ex) {
//            System.out.println(ex);
//
//        //2)emissione biglietto:
//        b.emissioneBiglietti(LocalDate.now(), 9.20, rivenditore3DB);
//        b.emissioneBiglietti(LocalDate.of(2025, 11, 25), 5.70, rivenditore3DB);
//        b.emissioneBiglietti(LocalDate.of(2024, 3, 10), 9.40, mezzo2DB, rivenditore1DB);

        //3)emissione abbonamento:
//        a.emissioneAbbonamenti(LocalDate.of(2025, 11, 25), 120.00, Periodicita.MENSILE, rivenditore3DB, tessera1DB);

        //4)validita abbonamento da tessera
//        a.checkValidityByTessera("3281ff53-250c-4865-91c0-53bbe8fbc65d");

        //5)periodi manutenzione:
//        md.periodiManutenzione("dc872fb3-bdfd-4c0c-bc20-52c90d8d055e");

        //6)num biglietti vidimatisu mezzo o per periodo:
//        b.numeroBigliettiVidimatiPerMezzo(mezzo1DB);
//        b.numeroBigliettiVidimatiDaData(LocalDate.of(2020, 1, 1));

        //7)calcolo tempo effettivo tratta da mezzo:
//        p.findTempoEffettivoPercorrenza("33f91f88-ff55-4d9b-bdd4-2f4364e7be51", "22d92ce1-65d2-4468-a990-8aa723ad81d9");
        //p.findTempoEffettivoPercorrenzaMedio("33f91f88-ff55-4d9b-bdd4-2f4364e7be51", "22d92ce1-65d2-4468-a990-8aa723ad81d9");

        //8)numero percorrenze:
//        p.findNumeroPercorrenze("33f91f88-ff55-4d9b-bdd4-2f4364e7be51", "22d92ce1-65d2-4468-a990-8aa723ad81d9");

//        9)num biglietti e abb, emessi in un periodo, da un rivend:
//        b.numeroBigliettiEmessiPerRivenditoriEPerPeriodo(rivenditore1DB, LocalDate.of(2025, 1, 1), LocalDate.now());
//        b.numeroBigliettiEmessiPerRivenditoriEPerPeriodo(rivenditore3DB, LocalDate.of(2025, 1, 1), LocalDate.now());
//        a.numeroAbbonamentiEmessiPerRivenditoriEPerPeriodo(rivenditore1DB, LocalDate.of(2025, 1, 1), LocalDate.now());
//        a.numeroAbbonamentiEmessiPerRivenditoriEPerPeriodo(rivenditore3DB, LocalDate.of(2025, 1, 1), LocalDate.now());

        //10) EXTRA: PERCENTUALE MANUTENZIONE - SERVIZIO:
//        md.getPercentualeManutenzioneMezzo(mezzo1DB);

        em.close();
        emf.close();


    }
}
