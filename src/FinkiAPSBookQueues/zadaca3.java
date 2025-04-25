package FinkiAPSBookQueues;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class zadaca3 { // Napredni problemi: Kolokvium str 141
    public static void main(String[] args) {
        // Prv red: n - kapacitet na studenti vo eden termin
        // Vtor red: brojot na studenti
        // Tret red: studentite imeprezime zaedno
        // Output: studentite koi navistina polagaat matematika
        Scanner input = new Scanner(System.in);
        Queue<String> matematikaFake = new LinkedList<>();
        Queue<String> ostanati = new LinkedList<>();
        Queue<String> realMatematicari = new LinkedList<>();

        int studentiTermini = input.nextInt();

        int fakeStudenti = input.nextInt();
        for (int i = 0; i < fakeStudenti; i++) {
            matematikaFake.add(input.next());
        }

        int ostanatiStudenti = input.nextInt();
        for (int i = 0; i < ostanatiStudenti; i++) {
            ostanati.add(input.next());
        }

        int realMat = input.nextInt();
        for (int i = 0; i < realMat; i++) {
            realMatematicari.add(input.next());
        }

        LinkedList<String> lista = new LinkedList<>();

        while (!matematikaFake.isEmpty()) { // tuka vrshime proverka dali tie sho se u fake redicata, se stvarno matematicarite
            String student = matematikaFake.poll();
            if (realMatematicari.contains(student)) {
                lista.addLast(student); // ako se, togash gi stavame u lista za pecatenje na kraj
                realMatematicari.remove(student); // gi vadime od redicata
            } else {
                ostanati.add(student); // ako se fejk, gi stavame vo posebna redica, odnosno vo bilo koj termin
            }
        }

        lista.addAll(ostanati); // gi dodavame site na krajot od listata

        int brojac = 1; // pocnuvajki od prv termin, gi pecatime studentite
        while (!lista.isEmpty()) {
            System.out.println(brojac);
            for (int i = 0; i < studentiTermini && !lista.isEmpty(); i++) { // pecatime po broj na lugje vo termin
                System.out.println(lista.poll());
            }
            brojac++;
        }

        input.close();
    }
}
