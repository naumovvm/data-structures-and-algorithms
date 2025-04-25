package Sortiranje;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class zadaca2 {
    public static void main(String[] args) throws IOException {
        int i;
        BufferedReader stdin = new BufferedReader( new InputStreamReader(System.in));
        String s = stdin.readLine();
        int n = Integer.parseInt(s);

        s = stdin.readLine();
        String [] pom = s.split(" ");
        int [] a = new int[n];
        for(i=0;i<n;i++)
            a[i]=Integer.parseInt(pom[i]);
        shakerSort(a,n);
    }

    private static void shakerSort(int[] a, int n) {

    }
}
