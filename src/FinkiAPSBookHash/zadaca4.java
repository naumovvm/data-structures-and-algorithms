package FinkiAPSBookHash;

import java.util.HashMap;
import java.util.Scanner;

public class zadaca4 { // Epidemija
    public static class Gragjanin{
        String prezime;
        boolean sostojba;

        public Gragjanin(String prezime, boolean sostojba) {
            this.prezime = prezime;
            this.sostojba = sostojba;
        }
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        HashMap<String, Integer> pozitivni = new HashMap<>();
        HashMap<String, Integer> negativni = new HashMap<>();
        int n = input.nextInt();
        input.nextLine();

        for (int i = 0; i < n; i++) {
            String[] vlez = input.nextLine().split(" ");
            String naselba = vlez[0];

            if(vlez[2].equals("positive")){
                if(!pozitivni.containsKey(naselba)){
                    pozitivni.put(naselba, 1);
                }else{
                    pozitivni.put(naselba, pozitivni.get(naselba)+1);
                }
            }else{
                if(!negativni.containsKey(naselba)){
                    negativni.put(naselba, 1);
                }else{
                    negativni.put(naselba, negativni.get(naselba)+1);
                }
            }
        }

        String naselba = input.next();
        int brPozitivni = pozitivni.get(naselba);
        int brNegativni = negativni.get(naselba);

        float rizikFaktor = (float) brPozitivni /(brNegativni+brPozitivni);
        System.out.println(rizikFaktor);

        input.close();
    }
}
