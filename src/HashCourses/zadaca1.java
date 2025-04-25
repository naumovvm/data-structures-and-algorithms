package HashCourses;

import java.util.Objects;
import java.util.Scanner;

public class zadaca1 {
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

    public static class Vraboteni implements Comparable<Vraboteni> {
        String ime;
        int vozrast;

        public Vraboteni(String ime, int vozrast) {
            this.ime = ime;
            this.vozrast = vozrast;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vraboteni vraboteni = (Vraboteni) o;
            return vozrast == vraboteni.vozrast && Objects.equals(ime, vraboteni.ime);
        }

        @Override
        public int hashCode() {
            int hash = Integer.hashCode(ime.charAt(0)) * vozrast;
            return hash;
        }

        @Override
        public int compareTo(Vraboteni o) {
            int ime_compare = ime.compareTo(o.ime);
            if (ime_compare != 0) {
                return ime_compare;
            }

            return Integer.compare(vozrast, o.vozrast);
        }

        @Override
        public String toString() {
            return "<" + ime + "," + vozrast + ">";
        }
    }

    public static class Proekti {
        int rabotno_vreme;
        int plata_po_cas;

        public Proekti(int rabotno_vreme, int plata_po_cas) {
            this.rabotno_vreme = rabotno_vreme;
            this.plata_po_cas = plata_po_cas;
        }

        public int vkupnaPlata() {
            return this.rabotno_vreme * this.plata_po_cas;
        }

        @Override
        public String toString() {
            return "<" + rabotno_vreme + "," + plata_po_cas + ">";
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        CBHT<Vraboteni, Proekti> hash = new CBHT<Vraboteni, Proekti>(10);

        for (int i = 0; i < n; i++) {
            String ime = input.next();
            int vozrast = input.nextInt();
            int rabotno_vreme = input.nextInt();
            int plata_po_cas = input.nextInt();

            Vraboteni vraboten = new Vraboteni(ime, vozrast);
            Proekti proekt = new Proekti(rabotno_vreme, plata_po_cas);

            SLLNode<MapEntry<Vraboteni, Proekti>> node = hash.search(vraboten);

            if (node == null) {
                hash.insert(vraboten, proekt);
            } else {
                if (proekt.vkupnaPlata() > node.element.value.vkupnaPlata()) {
                    hash.insert(vraboten, proekt);
                }
            }
        }

        System.out.println(hash);
        input.close();
    }
}
