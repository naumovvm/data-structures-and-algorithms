package FinkiAPSBookHash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class zadaca8 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        input.nextLine();
        HashMap<Integer, ArrayList<String>> mapa = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String[] vlez = input.nextLine().split(" ");
            String ime = vlez[0];
            String[] data = vlez[1].split("\\.");
            int mesec = Integer.parseInt(data[1]);

            if(!mapa.containsKey(mesec)){
                ArrayList<String> iminja = new ArrayList<>();
                iminja.add(ime);
                mapa.put(mesec, iminja);
            }else{
                ArrayList<String> iminja = mapa.get(mesec);
                iminja.add(ime);
                mapa.put(mesec, iminja);
            }
        }

        int mesec = input.nextInt();

        if(mapa.containsKey(mesec)){
            for(String ime : mapa.get(mesec)){
                System.out.println(ime);
            }
        }else{
            System.out.println("Nema rodendeni so toj mesec!");
        }

        input.close();
    }
}
