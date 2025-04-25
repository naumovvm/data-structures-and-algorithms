package TehnikiAlgoritmi;

import java.util.Arrays;
import java.util.Scanner;

public class zadaca9 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int brCifri = input.nextInt();
        int broj = input.nextInt();

        if (brCifri <= 2 && broj > 20) {
            System.out.println("Ne postoi");
        } else {
            int[] finalenBroj = new int[brCifri];
            finalenBroj[0] = 1;
            broj--;

            for (int i = brCifri - 1; i > 1; i--) {
                if (broj > 9) {
                    finalenBroj[i] = 9;
                    broj -= 9;
                } else {
                    finalenBroj[i] = broj;
                    broj = 0;
                }
            }
//            System.out.println(broj);
            if (broj > 0) {
                for (int i = brCifri - 1; i > 0; i--) {
                    if (finalenBroj[i] == 0) {
                        finalenBroj[i] = broj;
                    }
                }
            }

            for (int cifra : finalenBroj) {
                System.out.print(cifra);
            }
        }

        input.close();
    }
}
