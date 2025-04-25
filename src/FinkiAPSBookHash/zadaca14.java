package FinkiAPSBookHash;

import java.util.ArrayList;
import java.util.Scanner;

public class zadaca14 { // Rodendeni
    public static class OBHT<K extends Comparable<K>, E> {

        // An object of class OBHT is an open-bucket hash table, containing entries
        // of class MapEntry.
        private MapEntry<K, E>[] buckets;

        // buckets[b] is null if bucket b has never been occupied.
        // buckets[b] is former if bucket b is formerly-occupied
        // by an entry that has since been deleted (and not yet replaced).

        static final int NONE = -1; // ... distinct from any bucket index.

        @SuppressWarnings({"rawtypes", "unchecked"})
        private static final MapEntry former = new MapEntry(null, null);
        // This guarantees that, for any genuine entry e,
        // e.key.equals(former.key) returns false.

        private int occupancy = 0;
        // ... number of occupied or formerly-occupied buckets in this OBHT.

        @SuppressWarnings("unchecked")
        public OBHT(int m) {
            // Construct an empty OBHT with m buckets.
            buckets = (MapEntry<K, E>[]) new MapEntry[m];
        }


        private int hash(K key) {
            // Translate key to an index of the array buckets.
            return Math.abs(key.hashCode()) % buckets.length;
        }


        public int search(K targetKey) {
            // Find which if any bucket of this OBHT is occupied by an entry whose key
            // is equal to targetKey. Return the index of that bucket.
            int b = hash(targetKey);

            for (int n_search = 0; n_search < buckets.length; n_search++) {
                MapEntry<K, E> currEntry = buckets[b];
                if (currEntry == null) return NONE;
                else if (currEntry.key.equals(targetKey)) return b;
                else b = (b + 1) % buckets.length;
            }
            return NONE;
        }

        public MapEntry<K, E> getBucket(int bucket) {
            return buckets[bucket];
        }


        public void insert(K key, E val) {
            // Insert the entry <key, val> into this OBHT.
            MapEntry<K, E> newEntry = new MapEntry<K, E>(key, val);
            int b = hash(key);
            int n_search = 0;
            Integer free_position = null;
            Integer existing_position = null;
            while (n_search < buckets.length) {
                MapEntry<K, E> currEntry = buckets[b];
                if (currEntry == null) {
                    if (free_position == null) free_position = b;
                    break;
                } else if (key.equals(currEntry.key)) {
                    existing_position = b;
                    break;
                } else {
                    if (currEntry == former && free_position == null) {
                        free_position = b;
                    }
                    b = (b + 1) % buckets.length;
                    n_search++;
                }
            }
            if (existing_position != null)
                buckets[existing_position] = newEntry;
            else if (free_position != null) {
                buckets[free_position] = newEntry;
                if (++occupancy == buckets.length) {
                    System.out.println("Hash tabelata e polna!!!");
                }
            } else {
                // failed to insert, table was already full
            }
        }


        @SuppressWarnings("unchecked")
        public void delete(K key) {
            // Delete the entry (if any) whose key is equal to key from this OBHT.
            int b = hash(key);
            int n_search = 0;
            while (n_search < buckets.length) {
                MapEntry<K, E> currEntry = buckets[b];
                if (currEntry == null) return;
                else if (currEntry.key.equals(key)) {
                    buckets[b] = former;
                    return;
                } else {
                    b = (b + 1) % buckets.length;
                    n_search++;
                }
            }
        }


        public String toString() {
            String temp = "";
            for (int i = 0; i < buckets.length; i++) {
                temp += i + ":";
                if (buckets[i] == null)
                    temp += "\n";
                else if (buckets[i] == former)
                    temp += "former\n";
                else
                    temp += buckets[i] + "\n";
            }
            return temp;
        }


        public OBHT<K, E> clone() {
            OBHT<K, E> copy = new OBHT<K, E>(buckets.length);
            for (int i = 0; i < buckets.length; i++) {
                MapEntry<K, E> e = buckets[i];
                if (e != null && e != former)
                    copy.buckets[i] = new MapEntry<K, E>(e.key, e.value);
                else
                    copy.buckets[i] = e;
            }
            return copy;
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

    public static class Licnost {
        String ime;
        int godini;

        public Licnost(String ime, int godini) {
            this.ime = ime;
            this.godini = godini;
        }

        @Override
        public String toString() {
            return ime + " " + godini;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        input.nextLine();
        OBHT<String, ArrayList<Licnost>> tabela = new OBHT<>(2 * n + 1);

        for (int i = 0; i < n; i++) {
            String[] vlez = input.nextLine().split(" ");
            String ime = vlez[0];
            String prezime = vlez[1];
            String data = vlez[2];
            // 15/05/2024
            int godini = 2023 - Integer.parseInt(data.substring(6));
            String celoImePrezime = ime + " " + prezime;
            Licnost l = new Licnost(celoImePrezime, godini);
            data = data.substring(0, 5);

            int search = tabela.search(data);
            if (search == -1) {
                ArrayList<Licnost> licnosti = new ArrayList<>();
                licnosti.add(l);
                tabela.insert(data, licnosti);
            }else{
                ArrayList<Licnost> licnosti = tabela.buckets[search].value;
                licnosti.add(l);
                tabela.insert(data, licnosti);
            }
        }

        String data = input.next();
        String rodenden = data.substring(0, 5);
        int search = tabela.search(rodenden);
        if (search == -1) {
            System.out.println("Nema rodendeni na taa data");
        } else {
            for(Licnost l : tabela.buckets[search].value) {
                System.out.println(l);
            }
        }


        input.close();
    }
}
