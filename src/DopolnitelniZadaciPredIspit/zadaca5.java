package DopolnitelniZadaciPredIspit;

import java.util.ArrayList;
import java.util.Scanner;

public class zadaca5 {
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

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        CBHT<String, String> tabela = new CBHT<>(2 * n + 1);

        for (int i = 0; i < n; i++) {
            String word = input.next();

            if (!Character.isLetter(word.charAt(word.length() - 1))) {
                word = word.substring(0, word.length() - 1);
            }

            word = word.toLowerCase();
            SLLNode<MapEntry<String, String>> node = tabela.search(word);

            if (node == null) {
                tabela.insert(word, word);
            }
        }

        input.nextLine();
        String sentence = input.nextLine();

        if (!sentence.equals(".")) {
            boolean isCorrect = true;
            ArrayList<String> incorrectWords = new ArrayList<>();
            String[] splitSentence = sentence.split(" ");
            for (String word : splitSentence) {
                if (!Character.isLetter(word.charAt(word.length() - 1))) {
                    word = word.substring(0, word.length() - 1);
                }

                word = word.toLowerCase();
                SLLNode<MapEntry<String, String>> node = tabela.search(word);

                if (node == null) {
                    isCorrect = false;
                    incorrectWords.add(word);
                }
            }

            if (isCorrect) {
                System.out.println("Bravo");
            } else {
                for (String word : incorrectWords) {
                    System.out.println(word);
                }
            }
        } else {
            System.out.println("Bravo");
        }
        input.close();
    }
}
