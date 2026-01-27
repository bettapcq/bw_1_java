package team5;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import team5.entities.Mezzo;
import team5.entities.Percorrenza;
import team5.entities.Tratta;

import java.time.LocalTime;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("bw1javapu");
    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();

       // Percorrenza percorrenza1 = new Percorrenza(LocalTime.of(2,30),);
        System.out.println("Hello World!1 ");

        em.close();
        emf.close();

    }
}
