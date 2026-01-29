package team5.entities;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import team5.dao.*;
import team5.exceptions.AlreadyEndorsedTicket;
import team5.exceptions.NotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;


public class Menu {


    static Scanner scanner = new Scanner(System.in);
    public EntityManager em;
    TrattaDAO trD;
    AbbonamentiDAO ab;
    TessereDAO te;
    PercorrenzeDAO pe;
    RivenditoreDAO rv;
    BigliettoDAO bt;
    MezzoDAO me;
    UtenteDAO ut;
    ManutenzioneDAO ma;

    public Menu(EntityManager em) {
        this.em = em;
        this.trD = new TrattaDAO(em);
        this.ab = new AbbonamentiDAO(em);
        this.te = new TessereDAO(em);
        this.pe = new PercorrenzeDAO(em);
        this.rv = new RivenditoreDAO(em);
        this.bt = new BigliettoDAO(em);
        this.me = new MezzoDAO(em);
        this.ut = new UtenteDAO(em);
        this.ma = new ManutenzioneDAO(em);
    }


    public void menu_amministratore() {

        System.out.println("Menu Amministratore:  ");
        System.out.println("0. Torna indietro");
        System.out.println("1. Emissione biglietti ");
        System.out.println("2. Emissione abbonamento ");
        System.out.println("3. Numero biglietti vidimati su un mezzo ");
        System.out.println("4. Biglietti emessi da un rivenditore");
        System.out.println("5. Abbonamenti emessi da un rivenditore");
        System.out.println("6. Numero di percorrenze e tempo medio");
        System.out.println("7. Chiudere manutenzione di un mezzo");
        System.out.println("8. Periodi manutenzione mezzo e calcolo della percentuale");
        System.out.println("9. Modificare vita di un mezzo");
        System.out.println("10.Numero biglietti vidimati da data");
        int input = 0;
        try {
            input = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Dovevi inserire un numero. Inizializzare nuovamente il menu' ");
        }

        while (input != 0) {
            switch (input) {
                case 1: {
                    System.out.println("Inserici il costo del biglietto: ");
                    double costo = Integer.parseInt(scanner.nextLine());
                    System.out.println("Inserisci l'id del rivenditore");
                    String id = scanner.nextLine();
                    try {
                        Rivenditore riv = rv.findById(id);
                        bt.emissioneBiglietti(LocalDate.now(), costo, riv);
                    } catch (Exception e) {
                        System.out.println("Dovevi inserire un ID valido ");
                    }
                    break;
                }
                case 2: {
                    System.out.println("Inserici il costo dell'abbonamento: ");
                    double costoAbb = Integer.parseInt(scanner.nextLine());
                    System.out.println("Inserisci la periodicità del tuo abbonamento: ");
                    String period = scanner.nextLine();
                    System.out.println("Inserisci l'id della tessera: ");
                    String numTessera = scanner.nextLine();
                    System.out.println("Inserisci l'id del rivenditore");
                    String id = scanner.nextLine();
                    try {
                        Rivenditore riv = rv.findById(id);
                        Tessera tes = te.getById(numTessera);
                        LocalDate dataOggi = LocalDate.now();
                        ab.emissioneAbbonamenti(dataOggi, costoAbb, Periodicita.valueOf(period.toUpperCase()), riv, tes);
                    } catch (Exception e) {
                        System.out.println("Dovevi inserire un ID valido ");
                    }
                    break;
                }
                case 3: {
                    System.out.println("Inserici l'ID del mezzo: ");
                    String mezzo = scanner.nextLine();
                    try {
                        Mezzo found = me.findbyID(mezzo);
                        Long numBt = bt.numeroBigliettiVidimatiPerMezzo(found);
                        System.out.println("Il num di biglietti vidimati per il mezzo: ");
                        System.out.println(mezzo);
                        System.out.println("è: " + numBt);
                    } catch (Exception e) {
                        System.out.println("Dovevi inserire un ID valido ");
                    }
                    break;

                }

                case 4: {
                    System.out.println("Inserici l'ID del rivenditore: ");
                    String rivenditore_id = scanner.nextLine();
                    System.out.println("Inserici la data di inizio (aaaa,mm,gg): ");
                    LocalDate data_inizio = LocalDate.parse(scanner.nextLine());
                    System.out.println("Inserici la data di fine (aaaa,mm,gg): ");
                    LocalDate data_fine = LocalDate.parse(scanner.nextLine());
                    try {
                        Rivenditore rivenditore = rv.findById(rivenditore_id);
                        bt.numeroBigliettiEmessiPerRivenditoriEPerPeriodo (rivenditore,data_inizio,data_fine);
                    } catch (Exception e) {
                        System.out.println("Dovevi inserire un ID valido ");
                    } finally {
                        break;
                    }
                }

                case 5: {
                    System.out.println("Inserici l'ID del rivenditore: ");
                    String rivenditore_iD = scanner.nextLine();
                    System.out.println("Inserici la data di inizio (aaaa,mm,gg): ");
                    LocalDate data_inizio_ = LocalDate.parse(scanner.nextLine());
                    System.out.println("Inserici la data di fine (aaaa,mm,gg): ");
                    LocalDate data_fine_ = LocalDate.parse(scanner.nextLine());
                    try {
                        Rivenditore rivenditore_ = rv.findById(rivenditore_iD);
                        ab.numeroAbbonamentiEmessiPerRivenditoriEPerPeriodo(rivenditore_,data_inizio_,data_fine_);
                    } catch (Exception e) {
                        System.out.println("Dovevi inserire un ID valido ");
                    } finally {
                        break;
                    }
                }

                case 6: {
                    System.out.println("Inserici l'ID della tratta: ");
                    String tratta6 = scanner.nextLine();
                    System.out.println("Inserici l'ID del mezzo: ");
                    String mezzo6 = scanner.nextLine();
                    try {
                        pe.findNumeroPercorrenze(tratta6,mezzo6);
                        pe.findTempoEffettivoPercorrenzaMedio(tratta6,mezzo6);
                    } catch (Exception e) {
                        System.out.println("Dovevi inserire degli ID validi ");
                    } finally {
                        break;
                    }
                }
                case 7: {
                    System.out.println("Inserici l'ID della manutenzione: ");
                    String manutenzione7_id = scanner.nextLine();
                    try {

                        // 1. Cerco lo studente
                        Manutenzione manutenzione7 = ma.findbyID(manutenzione7_id);
                        manutenzione7.setFine_manutenzione(LocalDate.now());
                        // 2. Creo una nuova transazione
                        EntityTransaction transaction = em.getTransaction();
                        // 3. Faccio partire la transazione
                        transaction.begin();
                        // 4. Rimuovo dal Persistence Context l'oggetto in questione
                        em.remove(manutenzione7);

                        // 5. commit
                        transaction.commit();

                    } catch (Exception e) {
                        System.out.println("Dovevi inserire l'ID valido ");
                    } finally {
                        break;
                    }
                }
                case 8: {
                    System.out.println("Inserici l'ID della mezzo: ");
                    String mezzo8_id = scanner.nextLine();
                    try {
                        Mezzo mezzo8 = me.findbyID(mezzo8_id);
                        ma.periodiManutenzione(mezzo8_id);
                        ma.getPercentualeManutenzioneMezzo(mezzo8);
                    } catch (Exception e) {
                        System.out.println("Dovevi inserire l'ID valido ");
                    } finally {
                        break;
                    }
                }
                case 9: {
                    System.out.println("Inserici l'ID della mezzo: ");
                    String mezzo9_id = scanner.nextLine();
                    try {
                        Mezzo mezzo9 = me.findbyID(mezzo9_id);

                    } catch (Exception e) {
                        System.out.println("Dovevi inserire l'ID valido ");
                    } finally {
                        break;
                    }
                }
                case 10: {
                    System.out.println("Inserici la data di inizio (aaaa,mm,gg): ");
                    LocalDate data_inizio_ = LocalDate.parse(scanner.nextLine());
                    try {
                        bt.numeroBigliettiVidimatiDaData(data_inizio_);
                    } catch (Exception e) {
                        System.out.println("Dovevi inserire l'ID valido ");
                    } finally {
                        break;
                    }
                }
                default: {
                    System.out.println("Inserisci un numero valido");
                    scanner.nextLine();
                    break;
                }
            }
            System.out.println("Menu Amministratore:  ");
            System.out.println("0. Torna indietro");
            System.out.println("1. Emissione biglietti ");
            System.out.println("2. Emissione abbonamento ");
            System.out.println("3. Numero biglietti vidimati su un mezzo ");
            System.out.println("4. Biglietti emessi da un rivenditore");
            System.out.println("5. Abbonamenti emessi da un rivenditore");
            System.out.println("6. Numero di percorrenze e tempo medio");
            System.out.println("7. Chiudere manutenzione di un mezzo");
            System.out.println("8. Periodi manutenzione mezzo e calcolo della percentuale");
            System.out.println("9. Modificare vita di un mezzo");
            System.out.println("10.Numero biglietti vidimati da data");
            input = 0;
            try {
                input = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Dovevi inserire un numero. Inizializzare nuovamente il menu' ");
            }
        }
        System.out.println("menu chiuso!");
    }


    public void menu_utente() {


        System.out.println("Menu Utente:  ");
        System.out.println("0. Torna indietro");
        System.out.println("1. Tempo stimato per una tratta ");
        System.out.println("2. Verifica validità abbonamento ");
        System.out.println("3. Rinnova la tessera ");
        System.out.println("4. Quali tratte percorre un mezzo");
        System.out.println("5. Quali mezzi passano per una tratta");
        System.out.println("6. Vidima biglietto");

        int input = 0;
        try {
            input = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Dovevi inserire un numero. Inizializzare nuovamente il menu' ");
        }

        while (input != 0) {
            switch (input) {
                case 1: {
                    System.out.println("Inserici la tratta: ");
                    String tratta = scanner.nextLine();
                    try {
                        Tratta found = trD.findbyID(tratta);
                        if (found != null) {
                            System.out.println("La tratta ha un tempo previsto di percorrenza di : " + found.getTempo_previsto_minuti());
                        } else throw new NotFoundException(found.getId_tratta());
                    } catch (Exception e) {
                        System.out.println("Dovevi inserire un ID valido ");
                    }
                    break;
                }
                case 2: {
                    System.out.println("Inserici l'ID tessera: ");
                    String tessera = scanner.nextLine();
                    try {
                        ab.checkValidityByTessera(tessera);
                    } catch (Exception e) {
                        System.out.println("Dovevi inserire un ID valido ");
                    }
                    break;
                }
                case 3: {
                    System.out.println("Inserici l'ID della tessera che vuoi rinnovare: ");
                    String tessera = scanner.nextLine();
                    try {

                        te.renewCard(tessera);
                    } catch (Exception e) {
                        System.out.println("Dovevi inserire un ID valido ");
                    }
                    break;

                }

                case 4: {
                    System.out.println("Inserici l'ID del mezzo: ");
                    String mezzo = scanner.nextLine();
                    try {

                        List<Percorrenza> lista = pe.tutteLetratteDiUnMezzo(mezzo);
                        lista.forEach(percorrenza -> System.out.println(percorrenza));
                    } catch (Exception e) {
                        System.out.println("Dovevi inserire un ID valido ");
                    }
                    break;
                }

                case 5: {
                    System.out.println("Inserici l'ID della tratta: ");
                    String tratta = scanner.nextLine();
                    try {
                        List<Percorrenza> lista = pe.tuttiMezziPerUnaTratta(tratta);
                        lista.forEach(percorrenza -> System.out.println(percorrenza));

                    } catch (Exception e) {
                        System.out.println("Dovevi inserire un ID valido ");
                    }
                    break;
                }

                case 6: {
                    System.out.println("Inserici il codice univoco del biglietto: ");
                    String biglietto = scanner.nextLine();
                    System.out.println("Inserici l'ID del mezzo: ");
                    String id_mezzo = scanner.nextLine();
                    Mezzo mezzo = me.findbyID(id_mezzo);
                    try {
                        bt.vidimazioneBiglietto(biglietto, mezzo);
                    }
                    catch (AlreadyEndorsedTicket e){
                        System.out.println("questo biglietto è già stato vidimato, non fare il furbo bastardo");
                    }
                    break;
                }
                default: {
                    System.out.println("Inserisci un numero valido");
                    scanner.nextLine();
                    break;
                }
            }

            System.out.println("Menu Utente:  ");
            System.out.println("0. Torna indietro");
            System.out.println("1. Tempo stimato per una tratta ");
            System.out.println("2. Verifica validità abbonamento ");
            System.out.println("3. Rinnova la tessera ");
            System.out.println("4. Quali tratte percorre un mezzo");
            System.out.println("5. Quali mezzi passano per una tratta");
            System.out.println("6. Vidima biglietto");

            input = 0;
            try {
                input = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Dovevi inserire un numero. Inizializzare nuovamente il menu' ");
            }

        }

        System.out.println("menu chiuso!");

    }
}


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
/// /
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