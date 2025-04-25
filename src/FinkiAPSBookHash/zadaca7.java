package FinkiAPSBookHash;

import java.util.HashMap;
import java.util.Scanner;

public class zadaca7 { // Telefonski imenik
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        input.nextLine();
        HashMap<String, String> mapa = new HashMap();
        String copyBr = "";

        for (int i = 0; i < n; i++) {
            String[] vlez = input.nextLine().split(" ");
            String broj = vlez[0];
            String ime = vlez[1];

            if (broj.charAt(0) == '0') {
                copyBr = "+389" + broj.substring(1);
                mapa.put(broj, ime);
                mapa.put(copyBr, ime);
            }
        }

        String broj = input.next();

        if(mapa.containsKey(broj)){
            System.out.println(mapa.get(broj));
        }else{
            System.out.println("Nepostoecki");
        }

        input.close();
    }
}
