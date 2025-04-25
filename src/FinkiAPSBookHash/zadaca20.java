package FinkiAPSBookHash;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

public class zadaca20 {
    public static class Ispit {
        String data;
        String saat;
        String prostorija;
        int saatINT;

        public Ispit(String data, String saat, String prostorija, int saatINT) {
            this.data = data;
            this.saat = saat;
            this.prostorija = prostorija;
            this.saatINT = saatINT;
        }

        @Override
        public String toString() {
            return this.saat + " " + this.prostorija;
        }
    }

    public static void main(String[] args) { // ispitna sesija, strana 251
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        input.nextLine();
        HashMap<String, ArrayList<Ispit>> mapa = new HashMap<>();

//        28/01/2016 09:00 315 Kalkulus 3
        for (int i = 0; i < n; i++) {
            String vlez = input.nextLine();
            String data = vlez.substring(0, 10);
            String saat = vlez.substring(11, 16);
            String prostorija = vlez.substring(17);
            int saatInt = Integer.parseInt(saat.replaceAll(":", ""));
            Ispit ispitche = new Ispit(data, saat, prostorija, saatInt);

            if (!mapa.containsKey(data)) {
                ArrayList<Ispit> ispiti = new ArrayList<>();
                ispiti.add(ispitche);
                mapa.put(data, ispiti);
            } else {
                ArrayList<Ispit> ispiti = mapa.get(data);
                ispiti.add(ispitche);
                mapa.put(data, ispiti);
            }
        }

        String data = input.nextLine();
        if(mapa.containsKey(data)) {
            ArrayList<Ispit> ispiti = mapa.get(data);
            ispiti.sort(Comparator.comparingInt(a -> a.saatINT));
            for (Ispit ispit : ispiti) {
                System.out.println(ispit);
            }
        }else{
            System.out.println("NEMA ISPITI NA TAA DATA");
        }

        input.close();
    }
}
