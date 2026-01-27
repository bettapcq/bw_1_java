package team5;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import team5.dao.AbbonamentiDAO;
import team5.dao.TessereDAO;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("bw1javapu");
    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        System.out.println("Hello World!1 ");

        AbbonamentiDAO ad = new AbbonamentiDAO(em);
        TessereDAO td = new TessereDAO(em);

        em.close();
        emf.close();

    }
}
