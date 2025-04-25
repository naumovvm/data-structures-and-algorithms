package TehnikiAlgoritmi;

import java.util.Scanner;
import static java.util.Arrays.binarySearch;

public class zadaca7 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int m = input.nextInt();
        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = input.nextInt();
        }

        int position=binarySearch(array, m);

        if (position>=0){
            System.out.println(position);
        }else{
            System.out.println("Ne postoi");
        }

        input.close();
    }
}
