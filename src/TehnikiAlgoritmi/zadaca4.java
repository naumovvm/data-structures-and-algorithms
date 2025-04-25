package TehnikiAlgoritmi;

import java.util.Arrays;
import java.util.Scanner;

public class zadaca4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] startTime = new int[n];
        int[] endTime = new int[n];

        for (int i = 0; i < n; i++) {
            startTime[i] = input.nextInt();
            endTime[i] = input.nextInt();
        }

        Arrays.sort(startTime); // sortirame od najmal do najgolem
        Arrays.sort(endTime); // isto i tuka

        int maxRooms=0, currentRooms=0; // dva counters
        int i=0, j=0; // indeksi

        while(i<n && j<n){
            if(startTime[i]<=endTime[j]){ // ako vremeto na pocnuvanje e pomalo ili ednakvo na vremeto na zavrshuvanje
                // odnosno eden miting zavrshuva, pa odma posle nego drug pocnuva, togash znaci deka ne mozhe so edna soba da se pomine
                currentRooms++; // zgolemuvame counter, bidejki ke ni treba ushte edna soba za miting
                i++; // odime na sleden start time
            }else{
                currentRooms--; // ako mozhe vo edna soba, togash namaluvame counterot
                j++; // odime na sleden end time
            }
            maxRooms=Math.max(maxRooms,currentRooms); // naogjame max megju ovie dve
        }

        System.out.println(maxRooms);
        input.close();
    }
}
