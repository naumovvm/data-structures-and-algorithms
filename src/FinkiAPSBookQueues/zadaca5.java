package FinkiAPSBookQueues;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class zadaca5 { // Organizacija na ispit
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Queue<String> eTestIspit = new LinkedList<String>();
        Queue<String> zadaciIspit = new LinkedList<>();
        Queue<String> rezultat = new LinkedList<>();

        int eTestovi = input.nextInt();
        for (int i = 0; i < eTestovi; i++) {
            eTestIspit.add(input.next());
        }

        int zadaciBrojka = input.nextInt();
        for (int i = 0; i < zadaciBrojka; i++) {
            zadaciIspit.add(input.next());
        }

        int kompletBr = input.nextInt();
        for (int i = 0; i < kompletBr; i++) {
            String ime = input.next();
            eTestIspit.add(ime);
            zadaciIspit.add(ime);
        }

        int termin = 1;
        System.out.println("Polagaat e-test: ");
        while (!eTestIspit.isEmpty()) {
            System.out.println("termin " + termin);
            for (int i = 0; i < 20 && !eTestIspit.isEmpty(); i++) {
                System.out.println(eTestIspit.poll());
            }
            termin++;
        }

        System.out.println("Polagaat zadaci: ");
        termin = 1;
        while (!zadaciIspit.isEmpty()) {
            System.out.println("termin " + termin);
            for (int i = 0; i < 20 && !zadaciIspit.isEmpty(); i++) {
                System.out.println(zadaciIspit.poll());
            }
            termin++;
        }

        input.close();
    }
}
