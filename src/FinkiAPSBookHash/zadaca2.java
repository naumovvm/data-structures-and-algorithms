package FinkiAPSBookHash;

import java.util.HashMap;
import java.util.Scanner;

public class zadaca2 { // Najdobra ponuda
    public static class Ponuda {
        String vreme;
        String grad;
        int pari;

        public Ponuda(String vreme, String grad, int pari) {
            this.vreme = vreme;
            this.grad = grad;
            this.pari = pari;
        }

        @Override
        public String toString() {
            return this.vreme + " " + this.grad + " " + this.pari;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        input.nextLine();
        HashMap<String, Ponuda> mapa = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String[] vlez = input.nextLine().split(" ");
            String data = vlez[0];
            String vreme = vlez[1];
            String grad = vlez[2];
            int pari = Integer.parseInt(vlez[3]);

            Ponuda p = new Ponuda(vreme, grad, pari);

            if (!mapa.containsKey(data)) {
                mapa.put(data, p);
            } else {
                int pariMapa = mapa.get(data).pari;
                if (pari > pariMapa) {
                    mapa.replace(data, p);
                }
            }
        }

        String data = input.nextLine();
        if (mapa.containsKey(data)) {
            System.out.println(mapa.get(data));
        } else {
            System.out.println("No offers");
        }

        input.close();
    }
}
