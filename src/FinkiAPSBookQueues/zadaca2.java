package FinkiAPSBookQueues;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class zadaca2 { // Vozovi
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        LinkedList<Integer> lista = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            int val = input.nextInt();
            if(val!=0){
                lista.addLast(val);
            }
        }

        lista.sort(Comparator.naturalOrder());

        Queue<Integer> voz = new LinkedList<>();

        while(!lista.isEmpty()){
            voz.add(lista.poll());
        }

        System.out.println(voz);

        input.close();
    }
}
