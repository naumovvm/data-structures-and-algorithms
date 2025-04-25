package Sortiranje;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class zadaca1 { // OddEvenSort
    static void oddEvenSort(int a[], int n) {
        // Vasiot kod tuka
//        Given a sequence of N natural numbers.
//        It is necessary to sort the sequence so that in the first part of the sequence the odd numbers from it will be sorted in ascending order,
//        and in the second part the even numbers will be sorted in descending order.
        int neparni = 0, parni = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] % 2 == 0) {
                parni++;
            } else {
                neparni++;
            }
        }

        Integer[] parniArr = new Integer[parni];
        Integer[] neparniArr = new Integer[neparni];
        parni = 0;
        neparni = 0;

        for (int i = 0; i < n; i++) {
            if(a[i] % 2 == 0) {
                parniArr[parni++]=a[i];
            }
        }

        for (int i = 0; i < n; i++) {
            if(a[i] % 2 != 0) {
                neparniArr[neparni++]=a[i];
            }
        }

        Arrays.sort(neparniArr);
        Arrays.sort(parniArr, Collections.reverseOrder());

        int j=0;
        for (int i = 0; i < n; i++) {
            if (i < neparni) {
                a[i]=neparniArr[i];
            }else{
                a[i]=parniArr[j++];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        int i;
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String s = stdin.readLine();
        int n = Integer.parseInt(s);

        s = stdin.readLine();
        String[] pom = s.split(" ");
        int[] a = new int[n];
        for (i = 0; i < n; i++)
            a[i] = Integer.parseInt(pom[i]);
        oddEvenSort(a, n);
        for (i = 0; i < n - 1; i++)
            System.out.print(a[i] + " ");
        System.out.print(a[i]);
    }
}

