package team5;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Persistence;
import team5.dao.BigliettoDAO;
import team5.dao.RivenditoreDAO;
import team5.entities.*;
import team5.exceptions.AlreadyEndorsedTicket;

import java.time.LocalDate;


public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("bw1javapu");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        BigliettoDAO b = new BigliettoDAO(em);
        RivenditoreDAO r = new RivenditoreDAO(em);
        Rivenditore rivenditore1 = new RivenditoreAutorizzato(394762938,"via da qua", null, null) ;
        Biglietto biglietto1 = new Biglietto (LocalDate.of(2026, 10, 10),11,"b143", rivenditore1 );
        System.out.println("Hello World!1 ");

        //r.save(rivenditore1);
        //b.save(biglietto1);
    //    b.vidimazioneBiglietto("b143", null);
try {
    b.vidimazioneBiglietto("b143", null);
}catch(AlreadyEndorsedTicket ex){
    System.out.println( ex);

}


    }
}
