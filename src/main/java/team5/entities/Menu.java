package team5.entities;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import team5.dao.*;
import team5.exceptions.AlreadyEndorsedTicket;
import team5.exceptions.NotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;


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
                    System.out.println("Inserici la data di fine manutenzione: ");
                    LocalDate data_fine7 = LocalDate.parse(scanner.nextLine());
                    try {
                        EntityTransaction t = em.getTransaction();
                        t.begin();
                        Query query = em.createQuery("UPDATE Manutenzione m SET m.fine_manutenzione=:data_fine7 WHERE m.id_manutenzione=:manutenzione7_id");
                        query.setParameter("manutenzione7_id", UUID.fromString(manutenzione7_id));
                        query.setParameter("data_fine7",data_fine7);
                        query.executeUpdate();
                        t.commit();
                        System.out.println("La data di fine manutenzione e' stata aggiornata a " + data_fine7);
                    } catch (Exception e) {
                        System.out.println("Dovevi inserire l'ID valido ed una data di fine manutenzione valida ");
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
                    System.out.println("Inserici l'ID di un mezzo: ");
                    String mezzo9_id = scanner.nextLine();
                    System.out.println("Inserici la data di fine attività: ");
                    LocalDate data_fine8 = LocalDate.parse(scanner.nextLine());
                    try {
                        EntityTransaction t = em.getTransaction();
                        t.begin();
                        Query query = em.createQuery("UPDATE Mezzo me SET me.fine_attivita = :data_fine8 WHERE me.id_mezzo=:mezzo9_id");
                        query.setParameter("mezzo9_id", UUID.fromString(mezzo9_id));
                        query.setParameter("data_fine8",data_fine8);
                        query.executeUpdate();
                        t.commit();
                        System.out.println("La data di fine attività e' stata aggiunta a " + data_fine8);
                    } catch (Exception e) {
                        System.out.println("Dovevi inserire l'ID valido ed una data di fine manutenzione valida ");
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