package FinkiAPSBookHash;

import java.util.HashMap;
import java.util.Scanner;

public class zadaca18 { // Pronajdi go kriminalecot
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        input.nextLine();
        HashMap<String, String> databaza = new HashMap<>(2 * n + 1);

        for (int i = 0; i < n; i++) {
            String ime = input.next();
            String sekvenca1 = input.next();
            String sekvenca2 = input.next();
            String spoenaSekv = sekvenca1 + sekvenca2;

            databaza.putIfAbsent(spoenaSekv, ime);
        }

        input.nextLine();
        String sekvenca1=input.next();
        String sekvenca2=input.next();
        String spoenaSekv=sekvenca1+sekvenca2;

        if(databaza.containsKey(spoenaSekv)){
            System.out.println(databaza.get(spoenaSekv));
        }else{
            System.out.println("Nema kriminalec!");
        }

        input.close();
    }
}
