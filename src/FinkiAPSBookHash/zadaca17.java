package FinkiAPSBookHash;

import java.util.HashMap;
import java.util.Scanner;

public class zadaca17 { // Povikuvacki br na zemja, strana 248
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        input.nextLine();
        HashMap<String, String> drzhavi = new HashMap<>(2 * n + 1);

        for (int i = 0; i < n; i++) {
            String[] vlez = input.nextLine().split(" ");
            String pocetniCifri = vlez[0];
            String drzhava = vlez[1];

            drzhavi.putIfAbsent(pocetniCifri, drzhava);
        }

        String broj = input.next().replace("+", "");
        String broj2Cifri = broj.substring(0, 2);
        broj = broj.substring(0, 3);

        if(drzhavi.containsKey(broj)){
            System.out.println(drzhavi.get(broj));
        } else if (drzhavi.containsKey(broj2Cifri)) {
            System.out.println(drzhavi.get(broj2Cifri));
        }else{
            System.out.println("Ne postoe");
        }

        input.close();
    }
}
