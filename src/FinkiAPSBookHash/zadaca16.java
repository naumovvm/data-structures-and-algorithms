package FinkiAPSBookHash;

import java.util.HashMap;
import java.util.Scanner;

public class zadaca16 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int kandidati = input.nextInt();
        input.nextLine();
        HashMap<String, Double> kandidatiHash = new HashMap<>();
        HashMap<String, Double> eDnevnik = new HashMap<>();

        for (int i = 0; i < kandidati; i++) {
            String maticenBr = input.next();
            Double value = input.nextDouble();

            kandidatiHash.putIfAbsent(maticenBr, value);
        }

        int brPodatoci = input.nextInt();
        input.nextLine();

        for (int i = 0; i < brPodatoci; i++) {
            String maticenBr = input.next();
            Double value = input.nextDouble();

            eDnevnik.putIfAbsent(maticenBr, value);
        }

        input.nextLine();
        String maticenBr=input.next();

        if(kandidatiHash.containsKey(maticenBr)){
            Double prosekVnesen=kandidatiHash.get(maticenBr);
            if(eDnevnik.containsKey(maticenBr)){
                Double tocenProsek=eDnevnik.get(maticenBr);
                if(prosekVnesen.equals(tocenProsek)){
                    System.out.println("OK");
                }else{
                    System.out.println("NOT OK");
                }
            }else{
                System.out.println("EMPTY");
            }
        }else{
            System.out.println("EMPTY");
        }

        input.close();
    }
}
