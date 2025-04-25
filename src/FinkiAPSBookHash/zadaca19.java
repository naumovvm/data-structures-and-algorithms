package FinkiAPSBookHash;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class zadaca19 { // Permutacii
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        input.nextLine();
        HashMap<String, Integer> mapa = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String zbor = input.nextLine();
            char[] charche = zbor.toCharArray();
            Arrays.sort(charche);
            zbor=new String(charche);

            if(!mapa.containsKey(zbor)){
                mapa.put(zbor, 1);
            }else{
                mapa.put(zbor, mapa.get(zbor)+1);
            }
        }

        String zbor = input.nextLine();
        char[] charche = zbor.toCharArray();
        Arrays.sort(charche);
        zbor=new String(charche);
        if(mapa.containsKey(zbor)){
            System.out.println(mapa.get(zbor));
        }else{
            System.out.println(0);
        }

        input.close();
    }
}
