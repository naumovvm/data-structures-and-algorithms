package Sortiranje;

import java.util.LinkedList;
import java.util.Scanner;

public class zadaca3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        LinkedList<Integer> list = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            int val = input.nextInt();
            list.addLast(val);
        }

        for (int i = 0; i < n-1; i++) {
            for (int j = i+1; j < n; j++) {
                if(list.get(i)>list.get(j)){
                    int temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.print(list.get(i)+" ");
        }
    }
}
