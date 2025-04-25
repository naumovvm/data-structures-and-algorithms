package TehnikiAlgoritmi;

import java.util.Scanner;

public class zadaca8 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int br_zadaci = input.nextInt();
        int br_vraboteni = input.nextInt();
        int[] vremetraenje_na_zadaci = new int[br_zadaci];
        int suma=0;

        for (int i = 0; i < br_zadaci; i++) {
            vremetraenje_na_zadaci[i] = input.nextInt();
            suma+=vremetraenje_na_zadaci[i];
        }

        int dnevnoMaksRabotenje=br_vraboteni*8; // pr. 2ca vraboteni po 8 chasa = 16 chasa mozhe dnevno da se raboti
        // eden vraboten rabote maks 8 casa
        int potrebniDenovi=(suma+dnevnoMaksRabotenje-1)/dnevnoMaksRabotenje; // suma+dMR-1 pravam za da mi dade tocni vrednosti
        // primer (48+24-1)/24=71/24 =2.9 znaci 2 dena
        // a ako imame povekje od 48 primer 50h, (50+24-1)/24=73/24=3.02 i se zaokruzhuva na 3

        int slobodniChasovi=(potrebniDenovi*dnevnoMaksRabotenje)-suma; // mnozhime potrebnite denovi so dMR i gi odzimame sosumata
        // ova go pravime bidejki vaka se odzemaat vkupnite rabotni chasovi so potrebnite

        System.out.println(potrebniDenovi+" "+slobodniChasovi);

        input.close();
    }
}
