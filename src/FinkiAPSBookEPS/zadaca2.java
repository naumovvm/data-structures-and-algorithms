package FinkiAPSBookEPS;

import java.util.Scanner;
import java.util.Stack;

public class zadaca2 { // Postfiks
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String vlez = input.nextLine();

        double rezultat = postfiks(vlez);
        System.out.println(rezultat);

        input.close();
    }

    private static double postfiks(String vlez) {
        double rezultat = 0;
        Stack<Double> stack = new Stack<>();
        double broj = 0;

        for (int i = 0; i < vlez.length(); i++) {
            char znak = vlez.charAt(i);
            if (znak == ' ') {
                continue;
            }
            if (Character.isDigit(znak)) {
                broj = Double.parseDouble(String.valueOf(znak)); // ako e broj go pretvaram u broj so via dve funk
                stack.push(broj);
                System.out.println("BROJ:"+broj);
            }

            if (stack.size() >= 2 && Character.isLetter(znak)) { // ako ima povekje od dva elementi u stackot, gi vadam poslednite dva
                Double posledenBroj = stack.pop();
                Double pretposledenBroj = stack.pop();
                // soglasno so znakot, gi vrsham potrebnite operacii
                if (znak == '+') {
                    stack.push(posledenBroj + pretposledenBroj);
                } else if (znak == '-') {
                    stack.push(posledenBroj - pretposledenBroj);
                } else if (znak == '*') {
                    stack.push(posledenBroj * pretposledenBroj);
                } else if (znak == '/') {
                    if(pretposledenBroj==0 || posledenBroj==0){
                        stack.push(0.0);
                    }else{
                        stack.push(pretposledenBroj / posledenBroj);
                    }
                }
            }else{
                System.out.println("Nedostasuva operand na pozicija " + i);
                return rezultat;
            }
        }

        if (stack.size() != 1) { // posle izminuvanje na inputot, ako stackot ne go sodrzhe samo rezultatot, togash imame greshka
            // odnosno imame vishok broevi
            System.out.println("Nevaliden operator");
//            while(!stack.isEmpty()){
//                System.out.println(stack.pop());
//            }
        } else {
            // vo sprotivno go zemame rezultatot i go stavame vo promenliva rezultat
            rezultat = stack.pop();
        }

        return rezultat;
    }
}
// Влез: 5 9 + 2 * 6 5 * +
// Излез: Rezultatot e 58.0