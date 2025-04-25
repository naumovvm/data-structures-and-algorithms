package FinkiAPSBookEPS;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class zadaca7 { // Otrovni rastenija
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        LinkedList<Integer> lista = new LinkedList<>();

        if (n == 0) {
            System.out.println(0);
        } else {
            for (int i = 0; i < n; i++) {
                int val = input.nextInt();
                lista.addLast(val);
            }

            System.out.println(proveriRastenija(lista));
        }

        input.close();
    }

    private static int proveriRastenija(LinkedList<Integer> lista) {
        if (lista.isEmpty()) {
            System.out.println("Nema rastenija");
            return 0;
        }

        int denovi = 0;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < lista.size(); i++) {
            int broj = lista.get(i);
            if (stack.isEmpty()) {
                stack.push(broj);
            } else {
                int brojOdStack = stack.pop();
//                System.out.println("Stack: " + brojOdStack);
//                System.out.println("Broj: " + broj);
                if (brojOdStack >= broj) {
                    stack.push(brojOdStack);
                    stack.push(broj);
//                    denovi--;
                } else {
                    stack.push(brojOdStack);
                    denovi++;
                }
            }
        }

//        System.out.println(stack);

        return denovi;
    }
}
