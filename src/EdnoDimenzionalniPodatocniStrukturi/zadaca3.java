package EdnoDimenzionalniPodatocniStrukturi;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class zadaca3 {
    public static class Student {
        String name;
        int science;
        int scifi;
        int history;

        public Student(String name, int science, int scifi, int history) {
            this.name = name;
            this.science = science;
            this.scifi = scifi;
            this.history = history;
        }
    }

    // Tuka imame 2,1,2 problem
    // Treba paralelno tri shalteri (queues) da rabotat
    // 2,1,2 ja oznacuva brzinata, science mozhe 2 studenti, scifi 1, history isto taka 2
    // Studentite mozhe da pobaraat razlicen tip na knigi, ne mora da bide vo redosledot science-scifi-history
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        input.nextLine();
        Queue<Student> scienceQ = new LinkedList<>();
        Queue<Student> scifiQ = new LinkedList<>();
        Queue<Student> historyQ = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            String name = input.nextLine();
            int science = input.nextInt();
            int scifi = input.nextInt();
            int history = input.nextInt();
            input.nextLine();
            Student student = new Student(name, science, scifi, history);

            // tuka so if-ovite se dodavat site studenti vo potrebnite queues, ako e 1 se dodavaat, ako e 0 togash tie ne zemaat kniga od toj shalter
            if (science == 1) {
                scienceQ.add(student);
            } else if (scifi == 1) {
                scifiQ.add(student);
            } else if (history == 1) {
                historyQ.add(student);
            }
        }

        Queue<String> result = new LinkedList<>(); //  nov queue vo koj se cuvaat samo iminjata redosledno
        // while loop-ot vrti dodeka ne se ispraznat site queues
        while (!scienceQ.isEmpty() || !scifiQ.isEmpty() || !historyQ.isEmpty()) {
            // prviot for e za science queue-ot, toj vrti dodeka ne gi opsluzi maks 2 studenti ili dodeka ne e prazen
            for (int i = 0; i < 2 && !scienceQ.isEmpty(); i++) {
                // sekogash pred da vadime element od queue, proveruvame dali e prazen
                Student student = scienceQ.poll(); // zemame student od science queue
                if (!result.contains(student.name)) { // proveruvame dali vekje go ima vo rezultatniot queue
                    // Vo if i elif uslovot, gi dodavame u scifiQ i historyQ bidejki primer:
                    // Ivan mozhe ke pobara od trite shalteri po edna kniga, zatoa
                    // prven se procesira vo scienceQ, pa se dodava na scifiQ, pa tamu se procesira, pa se dodava vo historyQ i tamu se procesira
                    if (student.scifi == 1) { // ako sodrzhi scifi vrednost, se dodava na tia scifiQ
                        scifiQ.add(student);
                    } else if (student.history == 1) { // ako sodrzhi history vrednost, se dodava na historyQ za da se procesira
                        historyQ.add(student);
                    } else {
                        result.add(student.name); // ako uopshte nema knigi za tia Queues, direkt se dodava vo rezultatot
                    }
                }
            }

            if (!scifiQ.isEmpty()) { // tuka e obicen if bidejki samo 1 student mozhe da se opsluzi vo daden moment
                Student student = scifiQ.poll(); // go zemame studentot
                if (!result.contains(student.name)) { // proveruvame dali go ima vekje vo rezultantniot queue
                    if (student.history == 1) { // ako ima kniga za vo historyQ, go dodavame i tamu
                        historyQ.add(student);
                    } else {
                        result.add(student.name); // ako nema togash dirket
                    }
                }
            }

            for (int i = 0; i < 2 && !historyQ.isEmpty(); i++) { // tuka pak opsluzuvame dvajca studenti
                Student student = historyQ.poll(); // go zemame studentot od history queue
                if (!result.contains(student.name)) { // ako ne go sodrzhi imeto vo rezultantniot queue, go dodavame tamu
                    result.add(student.name);
                }
            }
            // Vrti vo ciklus dodeka ne se ispraznat site queues
        }

        for (String name : result) {
            System.out.println(name);
        }
        //        System.out.println(result);

        input.close();
    }
}
