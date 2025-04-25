package FinkiAPSBookHash;

import java.util.HashMap;
import java.util.Scanner;

public class zadaca15 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        input.nextLine();
        HashMap<String, String> mapa = new HashMap<>(2 * n + 1);

        for (int i = 0; i < n; i++) {
            String zbor = input.next().toLowerCase();

            if (zbor.contains("[,.!?]")) {
                zbor.replaceAll("[,.!?]", "");
            }

            mapa.putIfAbsent(zbor, zbor);
        }

        input.nextLine();
        String[] recenica = input.nextLine().replaceAll("[,.!?]", "").split(" ");
        boolean isCorrect = true;

        for (int i = 0; i < recenica.length; i++) {
            if(Character.isUpperCase(recenica[i].charAt(0))){
                if (!mapa.containsKey(recenica[i].toLowerCase())) {
                    System.out.println(recenica[i]);
                    isCorrect = false;
                }
            }else{
                if (!mapa.containsKey(recenica[i])) {
                    System.out.println(recenica[i]);
                    isCorrect = false;
                }
            }
        }

        if(isCorrect){
            System.out.println("Bravo");
        }

        input.close();
    }
}
