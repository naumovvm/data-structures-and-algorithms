package TehnikiAlgoritmi;

import java.util.Arrays;
import java.util.Scanner;

public class tehniki2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] pristignuvanje = new int[n];
        int[] poagjanje = new int[n];

        for (int i = 0; i < n; i++) {
            pristignuvanje[i] = input.nextInt();
            poagjanje[i] = input.nextInt();
        }

        Arrays.sort(pristignuvanje);
        Arrays.sort(poagjanje);

        int max_platformi=0, momentalni_platformi=0;
        int i=0, j=0;

        while(i<n && j<n){
            if(pristignuvanje[i]<=poagjanje[j]){
                momentalni_platformi++;
                i++;
                max_platformi=Math.max(max_platformi, momentalni_platformi);
            }else{
                momentalni_platformi--;
                j++;
            }
        }

        System.out.println(max_platformi);
        input.close();
    }
}