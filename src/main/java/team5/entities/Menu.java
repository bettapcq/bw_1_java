package team5.entities;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import team5.exceptions.NotFoundException;

import javax.management.Query;
import java.util.Scanner;
import java.util.UUID;


public class Menu {

    private static EntityManager em;
    public Menu(EntityManager em) {
        this.em = em;
    }
    static Scanner scanner = new Scanner(System.in);
    public static void menu_utente() {
        System.out.println("Menu Utente:  ");
        System.out.println("1. Tempo stimato per una tratta ");
        System.out.println("2. Verifica validità abbonamento ");
        System.out.println("3. Verifica validita tessera ");
        System.out.println("4. Rinnovo tessera ");
        System.out.println("5. Quali mezzi passano per una tratta ");

        int input = Integer.parseInt(scanner.nextLine());

        switch (input) {
            case 1: {
                System.out.println("Inserici la tratta: ");
                String tratta = scanner.nextLine();
                Tratta found = em.find(Tratta.class, UUID.fromString(tratta));
                if (found != null) {
                    System.out.println("La tratta ha un tempo previsto di percorrenza di : " + found.getTempo_previsto_minuti());
                } else throw new NotFoundException(found.getId_tratta());
                break;
            }
            default:{
                System.out.println("Inserisci un numero valido");
                scanner.nextLine();
            }
        }
    }

    public static void menu_amministratore(){

    }


}
