package EdnoDimenzionalniPodatocniStrukturi;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class zadaca2 {
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

    public static void main(String[] args) { // sekvencijalno se izminuvat tuka
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

            if (science == 1) {
                scienceQ.add(student);
            }else if (scifi == 1) {
                scifiQ.add(student);
            }else if (history == 1) {
                historyQ.add(student);
            }
        }

        while(!scienceQ.isEmpty()) {
            Student student = scienceQ.poll();
            if(student.scifi==1){
                scifiQ.add(student);
            }else if(student.history==1){
                historyQ.add(student);
            }else{
                System.out.println(student.name);
            }
        }

        while(!scifiQ.isEmpty()) {
            Student student = scifiQ.poll();
            if(student.history==1){
                historyQ.add(student);
            }else{
                System.out.println(student.name);
            }
        }

        while(!historyQ.isEmpty()) {
            Student student = historyQ.poll();
            System.out.println(student.name);
        }

        input.close();
    }
}
