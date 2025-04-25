package EdnoDimenzionalniPodatocniStrukturi;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class zadaca5 {
    public static class Student {
        String name;
        int docs;
        int index;
        int middleSchool;

        public Student(String name, int docs, int index, int middleSchool) {
            this.name = name;
            this.docs = docs;
            this.index = index;
            this.middleSchool = middleSchool;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        input.nextLine();
        Queue<Student> docsQ = new LinkedList<>();
        Queue<Student> indexQ = new LinkedList<>();
        Queue<Student> middleSchoolQ = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            String name = input.nextLine();
            int docs = input.nextInt();
            int index = input.nextInt();
            int middleSchool = input.nextInt();
            input.nextLine();
            Student student = new Student(name, docs, index, middleSchool);

            if (docs == 1) {
                docsQ.add(student);
            } else if (index == 1) {
                indexQ.add(student);
            } else if (middleSchool == 1) {
                middleSchoolQ.add(student);
            }
        }

        // 2-3-1, paralelno
        Queue<String> result = new LinkedList<>();
        while (!docsQ.isEmpty() || !indexQ.isEmpty() || !middleSchoolQ.isEmpty()) {
            for (int i = 0; i < 2 && !docsQ.isEmpty(); i++) {
                Student student = docsQ.poll();
                if (!result.contains(student.name)) {
                    if (student.index == 1) {
                        indexQ.add(student);
                    } else if (student.middleSchool == 1) {
                        middleSchoolQ.add(student);
                    } else {
                        result.add(student.name);
                    }
                }
            }

            for (int i = 0; i < 3 && !indexQ.isEmpty(); i++) {
                Student student = indexQ.poll();
                if (!result.contains(student.name)) {
                    if (student.middleSchool == 1) {
                        middleSchoolQ.add(student);
                    } else {
                        result.add(student.name);
                    }
                }
            }

            if (!middleSchoolQ.isEmpty()) {
                Student student = middleSchoolQ.poll();
                if (!result.contains(student.name)) {
                    result.add(student.name);
                }
            }
        }

        for (String name : result) {
            System.out.println(name);
        }

        input.close();
    }
}
