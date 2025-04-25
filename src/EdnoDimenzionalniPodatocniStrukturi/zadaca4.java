package EdnoDimenzionalniPodatocniStrukturi;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class zadaca4 {
    public static class Student {
        String name;
        int docs;
        int index;
        int middleSchoolDocs;

        public Student(String name, int docs, int index, int middleSchoolDocs) {
            this.name = name;
            this.docs = docs;
            this.index = index;
            this.middleSchoolDocs = middleSchoolDocs;
        }

        public String getName() {
            return name;
        }

        public int getDocs() {
            return docs;
        }

        public int getIndex() {
            return index;
        }

        public int getMiddleSchoolDocs() {
            return middleSchoolDocs;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        input.nextLine();
        Queue<Student> docsQ = new LinkedList<>();
        Queue<Student> indexQ = new LinkedList<>();
        Queue<Student> middleSchoolDocsQ = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            String name = input.nextLine();
            int doc = input.nextInt();
            int index = input.nextInt();
            int middleSchoolDoc = input.nextInt();
            input.nextLine();
            Student student = new Student(name, doc, index, middleSchoolDoc);

            if (doc == 1) {
                docsQ.add(student);
            } else if (index == 1) {
                indexQ.add(student);
            } else if (middleSchoolDoc == 1) {
                middleSchoolDocsQ.add(student);
            }
        }

        // Paralelno odat site queues, studentot ode prvo kaj docsQ, pa kaj indexQ, pa na kraj kaj middleSchoolDocsQ
        // Sekoj queue mozhe samo po eden student da opsluzhi
        Queue<String> result = new LinkedList<>();
        while (!docsQ.isEmpty() || !indexQ.isEmpty() || !middleSchoolDocsQ.isEmpty()) {
            for (int i = 0; i < 1 && !docsQ.isEmpty(); i++) {
                Student student = docsQ.poll();
                if (!result.contains(student.name)) {
                    if (student.index == 1) {
                        indexQ.add(student);
                    } else if (student.middleSchoolDocs == 1) {
                        middleSchoolDocsQ.add(student);
                    } else {
                        result.add(student.name);
                    }
                }
            }

            for (int i = 0; i < 1 && !indexQ.isEmpty(); i++) {
                Student student = indexQ.poll();
                if (!result.contains(student.name)) {
                    if (student.middleSchoolDocs == 1) {
                        middleSchoolDocsQ.add(student);
                    } else {
                        result.add(student.name);
                    }
                }
            }

            for (int i = 0; i < 1 && !middleSchoolDocsQ.isEmpty(); i++) {
                Student student = middleSchoolDocsQ.poll();
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
