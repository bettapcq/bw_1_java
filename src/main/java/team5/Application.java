package team5;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import team5.dao.*;
import team5.entities.Menu;

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
        System.out.println("Se sei un utente premi 1, se sei un amministratore premi 2. Per uscire premi 0: ");
        int input = 0;
        try {
            input = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Dovevi inserire un numero. Iniziallizzare nuovamente il menu' ");
        }
        while (input != 0) {
            switch (input) {
                case 1: {
                    menu.menu_utente();
                    break;
                }
                case 2: {
                    menu.menu_amministratore();
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


        scanner.close();
        em.close();
        emf.close();

    }


}