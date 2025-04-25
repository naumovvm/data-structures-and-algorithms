package FinkiAPSBookHash;

import java.util.*;

public class zadaca21 {
    public static class Menadzer {
        String menadzer;
        int brojac;

        public Menadzer(String menadzer, int brojac) {
            this.menadzer = menadzer;
            this.brojac = brojac;
        }

        @Override
        public String toString() {
            return this.menadzer + ": " + this.brojac;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        input.nextLine();
        HashMap<String, Menadzer> mapa = new HashMap<>(2 * n + 1);

        for (int i = 0; i < n; i++) {
            String[] vlez = input.nextLine().split(",");
            String vraboten = vlez[0];
            String menadzer = vlez[1];

            if (!vraboten.equals(menadzer)) {
                if (!mapa.containsKey(menadzer)) {
                    Menadzer m = new Menadzer(menadzer, 1);
                    mapa.put(menadzer, m);
                } else {
                    Menadzer m = mapa.get(menadzer);
                    m.brojac += 1;
                    mapa.put(menadzer, m);
                }
            }
        }

        ArrayList<Menadzer> mdz = new ArrayList<>();

        if (mapa.isEmpty()) {
            System.out.println("Empty");
        } else {
            mdz.addAll(mapa.values());
        }

        mdz.sort((a,b) -> a.brojac-b.brojac);

        for(Menadzer m : mdz) {
            System.out.println(m);
        }

        input.close();
    }
}
//7
//Aleksandra,Marko
//Beti,Marko
//Marko,Filip
//Darko,Elena
//Elena,Filip
//Filip,Filip
//Trajko,Filip