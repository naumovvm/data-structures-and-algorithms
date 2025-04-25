package ZadaciPrvKolokvium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class zadaca3 {
    public static void main(String[] args) throws IOException {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        br.close();

        // Vasiot kod tuka
        // n=adults, m=children
        // ticket price = 100den
        // prv sout e min den, vtor sout e maks den
        if (M == 0) { // ako nema deca
            System.out.println(N*100);
            System.out.println(N*100);
        }else if(N>M){ // ako se adults od deca
            System.out.println(N*100);
            System.out.println((N*100)+(M-1)*100);
        }else{
            System.out.println(M*100);
            System.out.println((N*100)+(M-1)*100);
        }
    }
}
