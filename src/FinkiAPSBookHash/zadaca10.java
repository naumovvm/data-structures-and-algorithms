package FinkiAPSBookHash;

import java.util.HashMap;
import java.util.Scanner;

public class zadaca10 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // Prvite n reda se licata so izdadena patna dozvola
        // Potoa se m reda so lica koi go promenile svoeto ime ili prezime
        // Posledniot red e daden br na pasosot koj treba se proveri
        HashMap<String, String> patnaDozvola = new HashMap<>();
        HashMap<String, String> promena = new HashMap<>();
        int n = input.nextInt();
        input.nextLine();

        for (int i = 0; i < n; i++) {
            String[] vlez = input.nextLine().split(" ");
            String kodche = vlez[0];
            String ime = vlez[1];

            patnaDozvola.putIfAbsent(kodche, ime);
        }

        int m = input.nextInt();
        input.nextLine();
        for (int i = 0; i < m; i++) {
            String[] vlez = input.nextLine().split(" ");
            String staroIme = vlez[0];
            String novoIme = vlez[1];

            promena.putIfAbsent(staroIme, novoIme);
        }

        String kodche = input.next();

        if(patnaDozvola.containsKey(kodche)){
            String ime = patnaDozvola.get(kodche);
            if(!promena.containsKey(ime)){
                System.out.println("Allowed");
            }else{
                System.out.println("Kaput, go back");
            }
        }

        input.close();
    }
}
