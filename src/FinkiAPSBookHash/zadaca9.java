package FinkiAPSBookHash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class zadaca9 { // Vremenska prognoza
    public static class Grad {
        String pocetok;
        String kraj;
        Double stepeni;

        public Grad(String pocetok, String kraj, Double stepeni) {
            this.pocetok = pocetok;
            this.kraj = kraj;
            this.stepeni = stepeni;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Grad grad = (Grad) o;

            return Objects.equals(pocetok, grad.pocetok) && Objects.equals(kraj, grad.kraj);
        }

        @Override
        public String toString() {
            return this.pocetok + " - " + this.kraj + " " + this.stepeni;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        input.nextLine();
        HashMap<String, ArrayList<Grad>> mapa = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String[] vlez = input.nextLine().split(" ");
            String grad = vlez[0];
            String pocetok = vlez[1];
            String kraj = vlez[2];
            Double stepeni = Double.parseDouble(vlez[3]);

            Grad g = new Grad(pocetok, kraj, stepeni);

            if (!mapa.containsKey(grad)) {
                ArrayList<Grad> gradovi = new ArrayList<>();
                gradovi.addLast(g);
                mapa.put(grad, gradovi);
            } else {
                ArrayList<Grad> gradovi = mapa.get(grad);
                gradovi.addLast(g);
                mapa.put(grad, gradovi);
            }
        }

        String grad = input.next();

        if(!mapa.containsKey(grad)){
            System.out.println(grad+": does not exist");
        }else{
            System.out.println(grad+":");
            for(Grad g : mapa.get(grad)){
                System.out.println(g);
            }
        }

        input.nextLine();
    }
}
