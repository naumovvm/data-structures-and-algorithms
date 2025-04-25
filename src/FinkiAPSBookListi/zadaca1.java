package FinkiAPSBookListi;

import java.util.LinkedList;
import java.util.Scanner;

public class zadaca1 { // izbrisi posledno pojavuvanje na broj
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        LinkedList<Integer> lista = new LinkedList<>();


        for (int i = 0; i < n; i++) {
            lista.addLast(input.nextInt());
        }

        int broj = input.nextInt();
        int index_posledno_pojavuvanje = 0;

        for (int i = 0; i < n; i++) {
            if (lista.get(i) == broj) {
                index_posledno_pojavuvanje = i;
            }
        }
        System.out.println(lista);
        lista.remove(index_posledno_pojavuvanje);
        System.out.println(lista);
        input.close();
    }
}
