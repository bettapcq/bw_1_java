package team5;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import team5.dao.*;
import team5.entities.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;


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
        Menu menu = new Menu(em);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Benvenuto nel gestionale trasporti del TEAM 5! ");
        System.out.println("1. Menu utente");
        System.out.println("2. Menu amministratore");
        System.out.println("0. Logout");
        int input = 0;
        try {
            input = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Dovevi inserire un numero. Inizializzare nuovamente il menu' ");
        }
        while (input != 0) {
            switch (input) {
                case 1: {
                    System.out.println("qui entra dentro il menu utente");
                    menu.menu_utente();
                    input = 0;
                    break;
                }
                case 2: {
                    menu.menu_amministratore();
                    input = 0;
                    break;
                }
                default: {
                    System.out.println("Per favore inserire uno dei numeri indicati nel menu'");
                    try {
                        input = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Dovevi inserire un numero ");
                    }
                    break;
                }

            }


        }
        System.out.println("Programma terminato");

//        Rivenditore rivenditore1 = new RivenditoreAutorizzato(394762938, "via da qua", LocalTime.now(), LocalTime.of(17, 0));
//        Rivenditore rivenditore2 = new RivenditoreAutorizzato(394754938, "via di li", LocalTime.now(), LocalTime.of(17, 0));
//        Rivenditore rivenditore3 = new DistributtoreAutomatico("Via di la", Stato.ATTIVO);
//        Rivenditore rivenditore4 = new DistributtoreAutomatico("Via per di qua", Stato.NON_ATTIVO);
//        Rivenditore rivenditore5 = new DistributtoreAutomatico("Via dalle scatole", Stato.ATTIVO);
//        Rivenditore rivenditore6 = new RivenditoreAutorizzato(392534987, "Via via", LocalTime.now(), LocalTime.of(19, 0));
//
//         r.save(rivenditore1);
//         r.save(rivenditore2);
//         r.save(rivenditore3);
//         r.save(rivenditore4);
//         r.save(rivenditore5);
//         r.save(rivenditore6);


//        Rivenditore rivenditore1DB = r.findById("147264b4-6bf2-4b38-b57e-265f68489909");
//        Rivenditore rivenditore2DB = r.findById("86af62ce-f8df-4fc5-a052-2558a2faabac");
//        Rivenditore rivenditore3DB = r.findById("d1d7650c-7815-4dd5-ae12-63c793e73322");
//        Rivenditore rivenditore6DB = r.findById("28ca18a0-6184-460c-a942-a82d22c7fbc2");
//        Rivenditore rivenditore5DB = r.findById("ebd4c818-49d1-47d6-8bfd-404c37d6e09c");
//
//
//        Biglietto biglietto1 = new Biglietto(LocalDate.of(2026, 10, 10), 11.00, "b143", rivenditore3DB);
//        Biglietto biglietto2 = new Biglietto(LocalDate.now(), 10.00, "b643", rivenditore5DB);
//        Biglietto biglietto3 = new Biglietto(LocalDate.now(), 10.00, "b743", rivenditore5DB);
//        Biglietto biglietto4 = new Biglietto(LocalDate.now(), 10.00, "b843", rivenditore1DB);
//        Biglietto biglietto5 = new Biglietto(LocalDate.of(2025, 3, 4), 10.00, "b943", rivenditore2DB);
//        Biglietto biglietto6 = new Biglietto(LocalDate.of(2025, 7, 11), 10.00, "b944", rivenditore6DB);
//
//
//
//        System.out.println("Hello World!1 ");
//
//        b.save(biglietto1);
//        b.save(biglietto2);
//        b.save(biglietto3);
//        b.save(biglietto4);
//        b.save(biglietto5);
//        b.save(biglietto6);

//        Utente utente1 = new Utente("Giacomo", "Poretti", LocalDate.of(1992, 06, 14));
//        Utente utente2 = new Utente("Aldo", "Baglio", LocalDate.of(1982, 07, 19));
//        Utente utente3 = new Utente("Giovanni", "Storti", LocalDate.of(1960, 03, 25));
//        Utente utente4 = new Utente("Giangiorgio", "Pupo", LocalDate.of(1900, 10, 03));
//
//        Tessera tessera1 = new Tessera(LocalDate.now(), utente1);
//        Tessera tessera2 = new Tessera(LocalDate.of(2026, 01, 27), utente2);
//        Tessera tessera3 = new Tessera(LocalDate.now(), utente3);
//        Tessera tessera4 = new Tessera(LocalDate.of(2026, 01, 10), utente4);
//
//        u.save(utente1);
//        u.save(utente2);
//        u.save(utente3);
//        u.save(utente4);
//
//        t.save(tessera1);
//        t.save(tessera2);
//        t.save(tessera3);
//        t.save(tessera4);
//
//        Rivenditore rivenditore1DB = r.findById("147264b4-6bf2-4b38-b57e-265f68489909");
//        Rivenditore rivenditore2DB = r.findById("86af62ce-f8df-4fc5-a052-2558a2faabac");
//        Rivenditore rivenditore3DB = r.findById("d1d7650c-7815-4dd5-ae12-63c793e73322");
//        Rivenditore rivenditore5DB = r.findById("ebd4c818-49d1-47d6-8bfd-404c37d6e09c");
//        Tessera tessera1DB = t.getById("11d4717a-af3b-4500-8c0f-29c56b01b753");
//        Tessera tessera2DB = t.getById("6969cce6-5ccf-4858-972d-6c2c5f57ec3c");
//        Tessera tessera3DB = t.getById("a6864656-d89c-4497-8d3a-96a4ff9c5f10");
//        Tessera tessera4DB = t.getById("ae22fe83-fa98-4418-b54d-0901af2d69bd");
//
//        Abbonamento abbonamento1 = new Abbonamento(LocalDate.now(), 50.00, "a234", Periodicita.SETTIMANALE, rivenditore1DB, tessera1DB);
//        Abbonamento abbonamento2 = new Abbonamento(LocalDate.of(2026, 2, 28), 120.00, "a237", Periodicita.MENSILE, rivenditore3DB, tessera2DB);
//        Abbonamento abbonamento3 = new Abbonamento(LocalDate.now(), 50.00, "a234", Periodicita.SETTIMANALE, rivenditore5DB, tessera3DB);
//       Abbonamento abbonamento4 = new Abbonamento(LocalDate.of(2026, 2, 28), 120.00, "a237", Periodicita.MENSILE, rivenditore2DB, tessera4DB);
//
//
//        a.save(abbonamento1);
//        a.save(abbonamento2);
//        a.save(abbonamento3);
//        a.save(abbonamento4);
//
//        Mezzo mezzo1 = new Mezzo(40, LocalDate.of(2025, 01, 01), null, TipoMezzo.AUTOBUS);
//        Mezzo mezzo2 = new Mezzo(50, LocalDate.of(2024, 01, 01), null, TipoMezzo.TRAM);
//        Mezzo mezzo3 = new Mezzo(50, LocalDate.of(2022, 01, 01), LocalDate.of(2026, 01, 01), TipoMezzo.TRAM);
//        Mezzo mezzo4 = new Mezzo(30, LocalDate.of(2026, 01, 17), null, TipoMezzo.AUTOBUS);
//        Mezzo mezzo5 = new Mezzo(60, LocalDate.of(2024, 03, 01), null, TipoMezzo.TRAM);
//        Mezzo mezzo6 = new Mezzo(55, LocalDate.of(2020, 01, 01), LocalDate.of(2025, 01, 01), TipoMezzo.AUTOBUS);
//
//        m.save(mezzo1);
//        m.save(mezzo2);
//        m.save(mezzo3);
//        m.save(mezzo4);
//        m.save(mezzo5);
//        m.save(mezzo6);
//
//        Mezzo mezzo1DB = m.findbyID("c8fb81d0-33b0-4880-a588-34d8d45fd6c4");
//        Mezzo mezzo3DB = m.findbyID("910333ce-7841-48bb-939a-761c99efc9c0");
//        Mezzo mezzo2DB = m.findbyID("a5e21c01-92ab-43d3-b411-985ee57d58a4");
//        Mezzo mezzo4DB = m.findbyID("0e96b674-18c8-484d-8311-285bcbeb0115");
//        Mezzo mezzo5DB = m.findbyID("9e9ca703-1bf3-4856-bcc1-8bdd1de4bfa8");
//        Mezzo mezzo6DB = m.findbyID("20bece98-f340-459d-8041-90a50dfd2ada");

//        Manutenzione manutenzione1 = new Manutenzione(LocalDate.of(2026, 1, 7), LocalDate.of(2026, 1, 20), mezzo1DB, Tipologia.CARROZZERIA);
//        Manutenzione manutenzione2 = new Manutenzione(LocalDate.of(2026, 1, 6), LocalDate.of(2026, 1, 17), mezzo4DB, Tipologia.MOTORE);
//        Manutenzione manutenzione3 = new Manutenzione(LocalDate.of(2026, 1, 27), LocalDate.of(2026, 1, 28), mezzo5DB, Tipologia.REVISIONE);
//        Manutenzione manutenzione4 = new Manutenzione(LocalDate.of(2023, 1, 04), LocalDate.of(2023, 1, 28), mezzo1DB, Tipologia.GOMME);
//        Manutenzione manutenzione5 = new Manutenzione(LocalDate.of(2026, 1, 11), LocalDate.now(), mezzo2DB, Tipologia.FRENI);
//        Manutenzione manutenzione6 = new Manutenzione(LocalDate.of(2026, 1, 15), LocalDate.now(), mezzo5DB, Tipologia.MOTORE);
//
//        md.save(manutenzione1);
//        md.save(manutenzione2);
//        md.save(manutenzione3);
//        md.save(manutenzione4);
//        md.save(manutenzione5);
//        md.save(manutenzione6);
//
//
//        Tratta tratta1 = new Tratta(30, "via per di qua", "via qua", 60);
//        Tratta tratta2 = new Tratta(15, "via per di si", "via la", 30);
//        Tratta tratta3 = new Tratta(45, "via Roma", "via vai", 60);
//        Tratta tratta4 = new Tratta(60, "via per di no", "Napoli", 70);
//        Tratta tratta5 = new Tratta(33, "via Milano", "via andiamo", 40);
//        Tratta tratta6 = new Tratta(10, "via di casa mia", "via di casa tua", 25);
//
//        tr.save(tratta1);
//        tr.save(tratta2);
//        tr.save(tratta3);
//        tr.save(tratta4);
//        tr.save(tratta5);
//        tr.save(tratta6);
//
//        Tratta tratta1DB = tr.findbyID("08ef22e6-ca03-4eb8-baa2-afed3f9298f1");
//        Tratta tratta2DB = tr.findbyID("ce6986f2-25e5-4b2e-9c0e-dc40c82f8839");
//        Tratta tratta3DB = tr.findbyID("89545d8d-c35f-43bb-b491-2c98092e031d");
//        Tratta tratta4DB = tr.findbyID("06aa9cf8-260f-415c-860f-68cfaeb93e7a");
//        Tratta tratta5DB = tr.findbyID("52203de1-0039-4e8c-8222-28339cf4276f");
//        Tratta tratta6DB = tr.findbyID("e017bf21-f0d5-403c-917a-972a87c5932d");
//        Percorrenza percorrenza1 = new Percorrenza(mezzo1DB, tratta1DB, 40);
//        Percorrenza percorrenza2 = new Percorrenza(mezzo2DB, tratta2DB, 40);
//        Percorrenza percorrenza3 = new Percorrenza(mezzo3DB, tratta3DB, 30);
//        Percorrenza percorrenza4 = new Percorrenza(mezzo4DB, tratta4DB, 20);
//        Percorrenza percorrenza5 = new Percorrenza(mezzo5DB, tratta5DB, 45);
//        Percorrenza percorrenza6 = new Percorrenza(mezzo6DB, tratta6DB, 60);
//        Percorrenza percorrenza7 = new Percorrenza(mezzo6DB, tratta6DB, 60);
//                Percorrenza percorrenza8 = new Percorrenza(mezzo1DB, tratta6DB, 60);
//                Percorrenza percorrenza9 = new Percorrenza(mezzo4DB, tratta6DB, 60);

//        p.savePerc(percorrenza1);
//        p.savePerc(percorrenza2);
//        p.savePerc(percorrenza3);
//        p.savePerc(percorrenza4);
//        p.savePerc(percorrenza5);
////        p.savePerc(percorrenza6);
//                p.savePerc(percorrenza7);
//        p.savePerc(percorrenza8);
//                p.savePerc(percorrenza9);

        scanner.close();
        em.close();
        emf.close();

    }


}