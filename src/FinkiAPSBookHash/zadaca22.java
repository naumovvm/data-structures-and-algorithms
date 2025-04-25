package FinkiAPSBookHash;

import java.util.HashMap;
import java.util.Scanner;

public class zadaca22 {
    public static void main(String[] args) {
//        Ivana Vodnjanska 4
//        Ime na dete, ime na ulica, br na ulica
        Scanner input = new Scanner(System.in);
        int brDeca = input.nextInt();
        input.nextLine();

        HashMap<String, String> deca = new HashMap<>();
        HashMap<String, String> noviIminja = new HashMap<>();

        for (int i = 0; i < brDeca; i++) {
            String ime = input.next();
            String ulica = input.nextLine();

            deca.putIfAbsent(ime, ulica);
        }

        int brUlici = input.nextInt();
        input.nextLine();

        for (int i = 0; i < brUlici; i++) {
            String staroIme = input.next();
            String novoIme = input.next();

            noviIminja.putIfAbsent(staroIme, novoIme);
        }

        input.nextLine();
        String dete = input.next();
        String ulica="";

        if(deca.containsKey(dete)){
            ulica=deca.get(dete);
            String copyUlica = ulica.replaceAll("[0123456789]", "");
            String brojka = ulica.replaceAll("[^0123456789]", "");
            if(!noviIminja.containsKey(copyUlica)){
                System.out.println(noviIminja.get(copyUlica.trim())+" "+brojka);
            }else{
                System.out.println("Greshka");
            }
        }else{
            System.out.println("Nema dete");
        }



        input.close();
    }
}
