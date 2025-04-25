package FinkiAPSBookHash;

import java.util.Objects;
import java.util.Scanner;

public class zadaca6 { // Apteka
    public static class CBHT<K extends Comparable<K>, E> {

        // An object of class CBHT is a closed-bucket hash table, containing
        // entries of class MapEntry.
        private SLLNode<MapEntry<K, E>>[] buckets;

        @SuppressWarnings("unchecked")
        public CBHT(int m) {
            // Construct an empty CBHT with m buckets.
            buckets = (SLLNode<MapEntry<K, E>>[]) new SLLNode[m];
        }

        private int hash(K key) {
            // Translate key to an index of the array buckets.
            return Math.abs(key.hashCode()) % buckets.length;
        }

        public SLLNode<MapEntry<K, E>> search(K targetKey) {
            // Find which if any node of this CBHT contains an entry whose key is equal to targetKey.
            // Return a link to that node (or null if there is none).
            int b = hash(targetKey);
            SLLNode<MapEntry<K, E>> currNode = buckets[b];
            while (currNode != null) {
                MapEntry<K, E> currEntry = currNode.element;
                if (currEntry.key.equals(targetKey)) return currNode;
                else currNode = currNode.succ;
            }
            return null;
        }

        public void insert(K key, E val) {
            // Insert the entry <key, val> into this CBHT.
            // If entry with same key exists, overwrite it.
            MapEntry<K, E> newEntry = new MapEntry<>(key, val);
            int b = hash(key);
            SLLNode<MapEntry<K, E>> currNode = buckets[b];
            while (currNode != null) {
                MapEntry<K, E> currEntry = currNode.element;
                if (currEntry.key.equals(key)) {
                    // Make newEntry replace the existing entry ...
                    currNode.element = newEntry;
                    return;
                } else currNode = currNode.succ;
            }
            // Insert newEntry at the front of the SLL in bucket b ...
            buckets[b] = new SLLNode<>(newEntry, buckets[b]);
        }

        public void delete(K key) {
            // Delete the entry (if any) whose key is equal to key from this CBHT.
            int b = hash(key);
            SLLNode<MapEntry<K, E>> predNode = null, currNode = buckets[b];
            while (currNode != null) {
                MapEntry<K, E> currEntry = currNode.element;
                if (currEntry.key.equals(key)) {
                    if (predNode == null) buckets[b] = currNode.succ;
                    else predNode.succ = currNode.succ;
                    return;
                } else {
                    predNode = currNode;
                    currNode = currNode.succ;
                }
            }
        }

        public String toString() {
            String temp = "";
            for (int i = 0; i < buckets.length; i++) {
                temp += i + ":";
                SLLNode<MapEntry<K, E>> curr = buckets[i];
                while (curr != null) {
                    temp += curr.element.toString() + " ";
                    curr = curr.succ;
                }
                temp += "\n";
            }
            return temp;
        }
    }

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

    public static class MapEntry<K extends Comparable<K>, E> {
        // Each MapEntry object is a pair consisting of a key (a Comparable object)
        // and a value (an arbitrary object).
        K key;
        E value;

        public MapEntry(K key, E val) {
            this.key = key;
            this.value = val;
        }

        public String toString() {
            return "<" + key + "," + value + ">";
        }
    }

    public static class Lek {
        String ime;
        String posNeg;
        int cena;
        int brParcinja;

        public Lek(String ime, String posNeg, int cena, int brParcinja) {
            this.ime = ime;
            this.posNeg = posNeg;
            this.cena = cena;
            this.brParcinja = brParcinja;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Lek lek = (Lek) o;
            return posNeg == lek.posNeg && cena == lek.cena && brParcinja == lek.brParcinja && Objects.equals(ime, lek.ime);
        }

        @Override
        public int hashCode() {
            int hash = (100 * (100 * (100 * 0 + ime.charAt(2) + ime.charAt(1) + ime.charAt(0))) % 656565);
            return hash;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
//        ℎ(𝑤) = (100*(100*(100*0+ASCII(𝑐_3))+ASCII(𝑐_2))+ASCII(𝑐_1))%656565 <- hash funkc
        int n = input.nextInt();
        input.nextLine();
        CBHT<String, Lek> apteka = new CBHT<>(2 * n + 1);

        for (int i = 0; i < n; i++) {
            String[] vlez = input.nextLine().split(" ");
            String ime = vlez[0];
            int posNeg = Integer.parseInt(vlez[1]);
            int cena = Integer.parseInt(vlez[2]);
            int brParcinja = Integer.parseInt(vlez[3]);
            String condition = "";

            if (posNeg == 1) {
                condition = "POS";
            } else {
                condition = "NEG";
            }

            Lek lek = new Lek(ime, condition, cena, brParcinja);
            SLLNode<MapEntry<String, Lek>> node = apteka.search(ime);
            if (node == null) {
                apteka.insert(ime, lek);
            }
        }

        while (true) {
            String lek = input.next().toUpperCase();

            if (lek.equals("END")) {
                break;
            }

            int kolicina = input.nextInt();

            SLLNode<MapEntry<String, Lek>> node = apteka.search(lek);
            if (node == null) {
                System.out.println("Drug not found");
            } else {
                if (node.element.value.brParcinja > kolicina) {
                    node.element.value.brParcinja -= kolicina;
                    System.out.println(node.element.value.ime + " " + node.element.value.posNeg + " " + node.element.value.cena + " " + node.element.value.brParcinja);
                    System.out.println("Order made");
                }
            }
        }

        input.close();
    }
}
