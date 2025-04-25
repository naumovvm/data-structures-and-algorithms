package ZadaciPrvKolokvium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.*;
import java.util.NoSuchElementException;

public class zadaca5 { // card_trick
    public static class SLLNode<E> {
        protected E element;
        protected SLLNode<E> succ;

        public SLLNode(E elem, SLLNode<E> succ) {
            this.element = elem;
            this.succ = succ;
        }

        @Override
        public String toString() {
            return element.toString();
        }
    }

    public static class LinkedStack<E> extends Stack<E> {
        // top e link do prviot jazol ednostrano-povrzanata lista koja sodrzi gi elementite na stekot .
        private SLLNode<E> top;
        int size;

        public LinkedStack() {
            // Konstrukcija na nov, prazen stek.
            top = null;
            size = 0;
        }

        public String toString() {
            SLLNode<E> current = top;
            StringBuilder s = new StringBuilder();
            while (current != null) {
                s.append(current.element);
                s.append(" ");
                current = current.succ;
            }
            return s.toString();
        }

        public boolean isEmpty() {
            // Vrakja true ako i samo ako stekot e prazen.
            return (top == null);
        }

        public void clear() {
            // Go prazni stekot.
            top = null;
            size = 0;
        }

        public E peek() {
            // Go vrakja elementot na vrvot od stekot.
            if (top == null)
                throw new NoSuchElementException();
            return top.element;
        }

        public E push(E x) {
            // Go dodava x na vrvot na stekot.
            top = new SLLNode<E>(x, top);
            size++;
            return x;
        }

        public int size() {
            // Ja vrakja dolzinata na stekot.
            return size;
        }

        public E pop() {
            // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
            if (top == null)
                throw new NoSuchElementException();
            E topElem = top.element;
            size--;
            top = top.succ;
            return topElem;
        }

    }

//  Metodi kaj queue:
//  add() - dodava element, frla exception ako queue e full
//  offer() - ista funkcija kako add() samo ne frla exception tuku true/false (false ako e full)
//  remove() i poll() isto rabotat, samo remove frla exception, a poll vrakja null ako queue e prazen
//  element() i peek() isto rabotat, dzirnuvat na head (glavata) sho ima, element frla exception ako e prazen, peek samo dava null ako e prazen
//  size(), isEmpty(), clear() se jasni
    public static int countShuffles(int baranata_karta) {
        int shuffle_counter = 0;
        Queue<Integer> karti = new LinkedList<>();
        for (int i = 1; i <= 51; i++) {
            karti.add(i); // tuka se kreira shpilot so karti od 1 do 51
        }

        while (baranata_karta != karti.peek()) { // dodeka baranata karta ne dojde na vrv na shpilot
            LinkedList<Integer> prevrteni_karti = new LinkedList<>(); // so linked list rabotam bidejki najlesno i najbrzo se prevrtuvat kartite
            for (int i = 0; i < 7; i++) { // gi zemam prvite 7 karti
                prevrteni_karti.add(karti.remove());
            }
            Collections.reverse(prevrteni_karti); // gi prevrtuvam

            for(int karta : prevrteni_karti) { // potoa sekoa karta od prevrteni ja stavam posledna, zaedno so karta od vrvot na shpilot
                karti.add(karta); // prvo edna od prevrtenite
                karti.add(karti.remove()); // pa od vrvot na shpilot
            }

            shuffle_counter++; // ako seto ova se sluce i ne se najde baranata karta, znaci edno meshanje deka se izvrshilo
        }

        return shuffle_counter;
    }

    // stari reshenija podole, gornoto e pooptimalno

//    public static int count(int N) {
//        int shuffle_counter = 0;
//        Queue<Integer> cards = new LinkedList<>();
//        for (int i = 1; i <= 51; i++) {
//            cards.add(i);  // popolni go shpilot od 1 do 51
//        }

// reshenie so niza i queue
//        while (cards.peek() != N) { // vrti dodeka baranata karta ne dojde prva vo Queue-ot, sekoja iteracija = 1 shuffle,
//            int[] reversedCards = new int[7];
//            int reverse_counter = 0;
//
//            for (int i = 0; i < 7; i++) {
//                reversedCards[reverse_counter++] = cards.poll();
//                // gi brishe prvite 7 karti od queue-ot i gi stava vo niza
//                // so cel da mu go prevrte redosledot
//            }
//
//            for (int i = reverse_counter - 1; i >= 0; i--) {
//                cards.add(reversedCards[i]); // vrati ja prevrtenata karta vo shpilot
//                if (!cards.isEmpty()) {
//                    cards.add(cards.poll()); // premesti edna karta od prva pozicija vo posledna pozicija
//                }
//            }
//            shuffle_counter++;
//        }

//        while (cards.peek() != N) {
//            LinkedStack<Integer> reversedCards = new LinkedStack<>();
//            int reverse_counter = 0;
//
//            for (int i = 0; i < 7; i++) {
//                reversedCards.push(cards.poll());
//                reverse_counter++;
//            }
//
//            for (int i = reverse_counter; i > 0; i--) {
//                cards.add(reversedCards.pop());
//                if (!cards.isEmpty()) {
//                    cards.add(cards.poll());
//                }
//            }
//            shuffle_counter++;
//        }
//
//        return shuffle_counter;
//    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(countShuffles(Integer.parseInt(br.readLine())));
    }
}

