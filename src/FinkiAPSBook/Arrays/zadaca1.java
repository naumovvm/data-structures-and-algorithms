package FinkiAPSBook.Arrays;

import java.util.Scanner;

public class zadaca1 { // Prosek na niza
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] niza = new int[n];
        int prosek = 0;

        for (int i = 0; i < n; i++) {
            niza[i] = input.nextInt();
            prosek += niza[i];
        }

        prosek = prosek / n;
        int najblizokElement = niza[0];

        for (int broj : niza) {
            if (Math.abs(broj - prosek) < Math.abs(najblizokElement - prosek)) {
                najblizokElement = broj;
            }else if(Math.abs(broj - prosek) == Math.abs(najblizokElement - prosek) && broj<najblizokElement) {
                najblizokElement = broj;
            }
        }

        System.out.println(najblizokElement);
        input.close();
    }
}
