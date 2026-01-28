package team5;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Persistence;
import team5.dao.*;
import team5.entities.*;
import team5.exceptions.AlreadyEndorsedTicket;

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
        ManutenzioneDAO md = new ManutenzioneDAO (em);
        MezzoDAO m = new MezzoDAO(em);
        PercorrenzeDAO p = new PercorrenzeDAO(em);
        TrattaDAO tr = new TrattaDAO(em);
        Rivenditore rivenditore1 = new RivenditoreAutorizzato(394762938,"via da qua", LocalTime.now(), LocalTime.of(17,0)) ;
        Rivenditore rivenditore2 = new RivenditoreAutorizzato(394754938,"via di li", LocalTime.now(), LocalTime.of(17,0)) ;
        Rivenditore rivenditore3 = new DistributtoreAutomatico("Via di la",Stato.ATTIVO);
        Rivenditore rivenditore4 = new DistributtoreAutomatico("Via per di qua", Stato.NON_ATTIVO);
        Biglietto biglietto1 = new Biglietto (LocalDate.of(2026, 10, 10),11,"b143", rivenditore1 );
        Biglietto biglietto2 = new Biglietto (LocalDate.now(),10,"b643", rivenditore2 );
        Biglietto biglietto3 = new Biglietto (LocalDate.now(),10,"b743", rivenditore3 );
        Biglietto biglietto4 = new Biglietto (LocalDate.now(),10,"b843", rivenditore3 );
        System.out.println("Hello World!1 ");

 /*       r.save(rivenditore1);
        b.save(biglietto1);
        r.save(rivenditore2);
        b.save(biglietto2);
        r.save(rivenditore3);
        b.save(biglietto3);
        b.save(biglietto4);
        r.save(rivenditore4);*/


        Utente utente1 = new Utente("Giacomo", "Poretti", LocalDate.of(1992,06,14));
        Utente utente2 = new Utente("Aldo", "Baglio", LocalDate.of(1982,07,19));

        Tessera tessera1 = new Tessera(LocalDate.now(),utente1);
        Tessera tessera2 = new Tessera(LocalDate.of(2026,01,27),utente2);

     /*   u.save(utente1);
        u.save(utente2);
        t.save(tessera1);
        t.save(tessera2);*/

  /*      Rivenditore rivenditore1DB = r.findById("952f0680-f909-43ad-bb47-8e84e8b68b3a");
        Rivenditore rivenditore3DB = r.findById("e808f1cc-12ea-4621-8b8b-11dcca053869");
        Tessera tessera1DB = t.getById("1dc801b7-42e9-4846-afd0-8616d86dcaf1");
        Tessera tessera2DB = t.getById("48011847-54df-4595-9182-10f625246fd9");

        Abbonamento abbonamento1 = new Abbonamento(LocalDate.now(), 50.00, "a234",Periodicita.SETTIMANALE, rivenditore1DB,tessera1DB);
        Abbonamento abbonamento2 = new Abbonamento(LocalDate.of(2026,2,28), 120.00, "a237",Periodicita.MENSILE, rivenditore3DB,tessera2DB);

        a.save(abbonamento1);
        a.save(abbonamento2);*/

        Mezzo mezzo1 = new Mezzo(40,LocalDate.of(2025,01,01),null, TipoMezzo.AUTOBUS);
        Mezzo mezzo2 = new Mezzo(50,LocalDate.of(2024,01,01),null,TipoMezzo.TRAM);
        Mezzo mezzo3 = new Mezzo(50,LocalDate.of(2022,01,01),LocalDate.of(2026,01,01),TipoMezzo.TRAM);

        Mezzo mezzo1DB = m.findbyID("0a2a00b7-634f-47a4-ba83-6e289ed253a0");
        Mezzo mezzo2DB = m.findbyID("c302b38b-e171-4bfb-8b5c-28ce60b854f7");
        Mezzo mezzo3DB = m.findbyID("faed769f-5f79-4f53-9e44-08971bbb19c7");
        Manutenzione manutenzione1 = new Manutenzione(LocalDate.of(2026,1,7),LocalDate.of(2026,1,20),mezzo1DB,Tipologia.CARROZZERIA);
        Manutenzione manutenzione2 = new Manutenzione(LocalDate.of(2026,1,6),LocalDate.of(2026,1,17),mezzo2DB,Tipologia.MOTORE);


/*        m.save(mezzo1);
        m.save(mezzo2);
        m.save(mezzo3);*/

/*        md.save(manutenzione1);
        md.save(manutenzione2);
        Tratta tratta1 = new Tratta(30, "via per di qua", "via qua", 60);
        Tratta tratta2 = new Tratta(15, "via per di si", "via la", 30);
*/
/*        tr.save(tratta1);
        tr.save(tratta2);*/

        Tratta tratta1DB = tr.findbyID("28c9bc10-7b26-4baf-be31-a54cc9f8cb98");
        Percorrenza percorrenza1 = new Percorrenza(mezzo1DB,tratta1DB,40);
        p.savePerc(percorrenza1);


        //    b.vidimazioneBiglietto("b143", null);
/*        try {
            b.vidimazioneBiglietto("b143", null);
        }catch(AlreadyEndorsedTicket ex){
            System.out.println( ex);

        }*/

        em.close();
        emf.close();


    }
}
