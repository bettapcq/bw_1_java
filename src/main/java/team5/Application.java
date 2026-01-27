package team5;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import team5.entities.Biglietto;
import team5.entities.Mezzo;
import team5.entities.Rivenditore;
import team5.entities.RivenditoreAutorizzato;

import java.time.LocalDate;


public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("bw1javapu");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        Biglietto biglietto1 = new Biglietto (LocalDate.of(2023, 10, 10),11,"b142", null, Mezzo.Tipo.Autobus,);
        System.out.println("Hello World!1 ");


    }
}
