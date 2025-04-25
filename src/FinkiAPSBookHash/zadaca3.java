package FinkiAPSBookHash;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class zadaca3 { // Anagrami
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        input.nextLine();
        HashMap<String, Integer> mapa = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String anagram = input.next();
            char[] gramche = anagram.toCharArray();
            Arrays.sort(gramche);
            anagram = new String(gramche);

            if (!mapa.containsKey(anagram)) {
                mapa.put(anagram, 1);
            } else {
                mapa.put(anagram, mapa.get(anagram) + 1);
            }
        }

        String anagram = input.next();
        char[] gramche = anagram.toCharArray();
        Arrays.sort(gramche);
        anagram = new String(gramche);
        if (!mapa.containsKey(anagram)) {
            System.out.println("Nema");
        } else {
            System.out.println(mapa.get(anagram));
        }

        input.close();
    }
}
