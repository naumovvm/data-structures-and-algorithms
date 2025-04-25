package TehnikiAlgoritmi;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

public class zadaca1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        LinkedList<Integer> list = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            int val = input.nextInt();
            list.addLast(val);
        }

        list.sort(Comparator.reverseOrder());

        for(int i : list){
            System.out.print(i);
        }
        input.close();
    }
}