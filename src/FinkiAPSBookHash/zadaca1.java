package FinkiAPSBookHash;

import java.util.HashMap;
import java.util.Scanner;

public class zadaca1 { // rodendeni, strana 181
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        input.nextLine();
        HashMap<Integer, Integer> mapa = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String[] data = input.nextLine().split("\\.");
            int mesec = Integer.parseInt(data[1]);

            if(!mapa.containsKey(mesec)){
                mapa.put(mesec, 1);
            }else{
                mapa.put(mesec, mapa.get(mesec) + 1);
            }
        }

        int mesec = input.nextInt();
        if(mapa.containsKey(mesec)){
            System.out.println(mapa.get(mesec));
        }else{
            System.out.println("Nema rodendeni vo toj mesec!");
        }

        input.close();
    }
}
