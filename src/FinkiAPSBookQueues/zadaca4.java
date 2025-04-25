package FinkiAPSBookQueues;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class zadaca4 { // Konstultacii
    public static class Student {
        String ime;
        String prashanje;

        public Student(String ime, String prashanje) {
            this.ime = ime;
            this.prashanje = prashanje;
        }
    }

    public static void main(String[] args) {
// Prvo ke se usluzhat studentite po APS, potoa po MMS
// Sekoj student ima po edno prashanje od tipot: A, B, C i D
// Ako posledovatelno dva studenti postavat isti prashanja, vtoriot studenti se premestuva na kraj od redicata i se zema student od MMS
// So dr zborovi, ako poslednoto prashanje e X i pak bide postaveno prashanje X, studentot odi na kraj i se zema student od MMS
// Se zema student od MMS samo ako ne e prazna taa redica
// Na kraj treba da se ispecati konecniot rezultat na vleguvnaje
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        Queue<Student> apsRedica = new LinkedList<>();
        Queue<String> mmsRedica = new LinkedList<>();
        Queue<String> finalenRaspored = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            String ime = input.next();
            String prashanje = input.next();
            Student s = new Student(ime, prashanje);
            apsRedica.add(s);
        }

        input.nextLine();
        int m = input.nextInt();
        for (int i = 0; i < m; i++) {
            String ime = input.next();
            mmsRedica.add(ime);
        }

        String poslednoPrashanje = "";
        while (!apsRedica.isEmpty()) {
            Student student = apsRedica.poll();
            if (finalenRaspored.isEmpty()) {
                finalenRaspored.add(student.ime);
                poslednoPrashanje = student.prashanje;
            } else {
                if (poslednoPrashanje.equals(student.prashanje)) {
                    apsRedica.add(student);
                    if (!mmsRedica.isEmpty()) {
                        finalenRaspored.add(mmsRedica.poll());
                    }
                } else {
                    finalenRaspored.add(student.ime);
                    poslednoPrashanje = student.prashanje;
                }
            }
        }

        if(!mmsRedica.isEmpty()) {
            finalenRaspored.addAll(mmsRedica);
        }

        for(String ime : finalenRaspored) {
            System.out.println(ime);
        }

        input.close();
    }
}
