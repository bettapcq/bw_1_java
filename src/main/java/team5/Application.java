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

        Scanner scanner = new Scanner(System.in);
        System.out.println("Se sei un utente premi 1, se sei un amministratore premi 2. Per uscire premi 0.");
        int input =0;
        try{
            input= Integer.parseInt(scanner.nextLine());
        }
        catch (NumberFormatException e){
            System.out.println("Dovevi inserire un numero. Iniziallizzare nuovamente il menu' ");
        }
        while (input != 0){
            switch (input){
                case 1:{
                    Menu.menu_utente();
                    break;
                }
                case 2:{
                    Menu.menu_amministratore();
                    break;
                }
                default:{
                    System.out.println("Per favore inserire uno dei numeri indicati nel menu'");
                    try{
                        input= Integer.parseInt(scanner.nextLine());
                    }
                    catch (NumberFormatException e){
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
//        Biglietto biglietto1 = new Biglietto(LocalDate.of(2026, 10, 10), 11.00, "b143", rivenditore1);
//        Biglietto biglietto2 = new Biglietto(LocalDate.now(), 10.00, "b643", rivenditore2);
//        Biglietto biglietto3 = new Biglietto(LocalDate.now(), 10.00, "b743", rivenditore3);
//        Biglietto biglietto4 = new Biglietto(LocalDate.now(), 10.00, "b843", rivenditore3);
//
//        Rivenditore rivenditore4DB = r.findById("495a8da2-c7e8-409c-97b7-141e8e87ff77");

//       Biglietto biglietto5 = new Biglietto(LocalDate.of(2025, 3, 4), 10.00, "b943", rivenditore4DB);
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
//
//        Utente utente1 = new Utente("Giacomo", "Poretti", LocalDate.of(1992, 06, 14));
//        Utente utente2 = new Utente("Aldo", "Baglio", LocalDate.of(1982, 07, 19));
//
//        Tessera tessera1 = new Tessera(LocalDate.now(), utente1);
//        Tessera tessera2 = new Tessera(LocalDate.of(2026, 01, 27), utente2);
//
//        u.save(utente1);
//        u.save(utente2);
//        t.save(tessera1);
//        t.save(tessera2);

//        Rivenditore rivenditore1DB = r.findById("191ca50f-b8ca-4a16-aedd-5111657664b0");
//        Rivenditore rivenditore3DB = r.findById("8d397b00-4433-443b-9a3e-27f0040633ac");
//        Tessera tessera1DB = t.getById("6eb6ab54-119b-4434-b789-32d31ae7ef82");
//        Tessera tessera2DB = t.getById("b08a2cde-44b6-4dd7-99c2-ece889be8b5a");
//
//        Abbonamento abbonamento1 = new Abbonamento(LocalDate.now(), 50.00, "a234", Periodicita.SETTIMANALE, rivenditore1DB, tessera1DB);
//        Abbonamento abbonamento2 = new Abbonamento(LocalDate.of(2026, 2, 28), 120.00, "a237", Periodicita.MENSILE, rivenditore3DB, tessera2DB);
//
//        a.save(abbonamento1);
//        a.save(abbonamento2);
//
//        Mezzo mezzo1 = new Mezzo(40, LocalDate.of(2025, 01, 01), null, TipoMezzo.AUTOBUS);
//        Mezzo mezzo2 = new Mezzo(50, LocalDate.of(2024, 01, 01), null, TipoMezzo.TRAM);
//        Mezzo mezzo3 = new Mezzo(50, LocalDate.of(2022, 01, 01), LocalDate.of(2026, 01, 01), TipoMezzo.TRAM);
//
//        m.save(mezzo1);
//        m.save(mezzo2);
//        m.save(mezzo3);

//        Mezzo mezzo1DB = m.findbyID("b0f92df1-f381-4082-87c5-e1e3bcfc8b07");
//        Mezzo mezzo3DB = m.findbyID("47c59e83-cc96-4a4e-bede-e0a9b9daecc5");
//        Mezzo mezzo2DB = m.findbyID("88e93e09-ef12-45e8-a644-98640b28426d");
////
//        Manutenzione manutenzione1 = new Manutenzione(LocalDate.of(2026, 1, 7), LocalDate.of(2026, 1, 20), mezzo1DB, Tipologia.CARROZZERIA);
//        Manutenzione manutenzione2 = new Manutenzione(LocalDate.of(2026, 1, 6), LocalDate.of(2026, 1, 17), mezzo3DB, Tipologia.MOTORE);
//        Manutenzione manutenzione3 = new Manutenzione(LocalDate.of(2026, 1, 27), LocalDate.of(2026, 1, 28), mezzo3DB, Tipologia.CARROZZERIA);
//        Manutenzione manutenzione4 = new Manutenzione(LocalDate.of(2023, 1, 27), LocalDate.of(2023, 1, 28), mezzo2DB, Tipologia.FRENI);
//        Manutenzione manutenzione5 = new Manutenzione(LocalDate.of(2026, 1, 27), LocalDate.now(), mezzo3DB, Tipologia.FRENI);
//
//        md.save(manutenzione1);
//        md.save(manutenzione2);
//        md.save(manutenzione3);
//        md.save(manutenzione4);
//
//        Tratta tratta1 = new Tratta(30, "via per di qua", "via qua", 60);
//        Tratta tratta2 = new Tratta(15, "via per di si", "via la", 30);
//
//        tr.save(tratta1);
//        tr.save(tratta2);

//        Tratta tratta1DB = tr.findbyID("40d5bfdc-c252-4e97-9532-4a67fb334bac");
//        Tratta tratta2DB = tr.findbyID("4d6ed740-bc2e-4a0c-8bb7-736b551bafdd");
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

        scanner.close();
        em.close();
        emf.close();

    }
}