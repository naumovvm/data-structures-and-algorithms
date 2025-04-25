package TehnikiAlgoritmi;

import java.util.Arrays;
import java.util.Scanner;

public class zadaca5 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int m = input.nextInt();
        int n = input.nextInt();
        int[] possible_positions = new int[m];

        for (int i = 0; i < m; i++) {
            possible_positions[i] = input.nextInt();
        }

        System.out.println(minPositions(m, n, possible_positions));

        input.close();
    }

    private static int minPositions(int m, int n, int[] possiblePositions) {
        Arrays.sort(possiblePositions); // gi sortirame mozhnite pozicii
        int count = 0;
        int currentPos = 1;

        while (currentPos <= n) { // vrte dodeka ne se osvetle cela ulica
            int bestLight = -1; // ako e -1, znaci deka ne mozhe da se osvetli celata ulica
            int maxReach = -1; // inicijalno -1, potoa se menuva soodvetno pozicijata

            for (int pos : possiblePositions) {
                if (pos - 2 > currentPos) {
                    break;  // tuka pravime break, bidejki momentalnata pozicija e predaleku i ne e optimalno osvetluvanjeto
                }

                if (pos - 2 <= currentPos && pos + 2 >= currentPos) {
                    if (pos + 2 > maxReach) {  // ja zema pozicijata najdesno sho mozhe
                        maxReach = pos + 2; // setira maxReach
                        bestLight = pos;
                    }
                }
            }

            if (bestLight == -1) { // ako ne e osvetleno, znaci ne e optimalno osvetluvanjeto i kraj
                return -1;
            }

            count++; // zgolemuvame count
            currentPos = maxReach + 1; // otkako ke postavime svetilki, currentPos go mestime na prvata neosvetlena pozicija posle najdesnata svetilka
        }

        return count;
    }
}
