package team5;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Application {
    private static final EntityManagerFactory ef = Persistence.createEntityManagerFactory("bw1java");
    public static void main(String[] args) {
        EntityManager em;
        em = ef.createEntityManager();
        System.out.println("Hello World!1 ");
        ef.close();
        em.close();
    }
}
