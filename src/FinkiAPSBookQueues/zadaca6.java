package FinkiAPSBookQueues;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class zadaca6 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int brAsistenti = input.nextInt();
        Queue<String> asistenti = new LinkedList<String>();

        for (int i = 0; i < brAsistenti; i++) {
            asistenti.add(input.next());
        }

        int brPredmeti = input.nextInt();
        LinkedList<String> aps = new LinkedList<>();
        LinkedList<String> mis = new LinkedList<>();
        LinkedList<String> oos = new LinkedList<>();
        LinkedList<String> predmeti = new LinkedList<>();
        input.nextLine();
        for (int i = 0; i < brPredmeti; i++) {
            String vlez = input.nextLine();
            predmeti.add(vlez);
        }

        int otsutniAsistenti = input.nextInt();
        for (int i = 0; i < otsutniAsistenti; i++) {
            String asistent = input.next();
            if (asistenti.contains(asistent)) {
                asistenti.remove(asistent);
            }
        }

        input.nextLine();

        for (int i = 0; i < brPredmeti; i++) {
            String[] vlez = predmeti.get(i).split(" ");
            String predmet = vlez[0];
            int potrebniAsistenti = Integer.parseInt(vlez[1]);

            if (predmet.equals("APS")) {
                for (int j = 0; j < potrebniAsistenti; j++) {
                    String asistent = asistenti.poll();
                    if (aps.isEmpty() && !asistenti.isEmpty()) {
                        aps.add(asistent);
                        asistenti.add(asistent);
                    } else {
                        aps.add(asistent);
                        asistenti.add(asistent);
                    }
                }
            } else if (predmet.equals("MIS")) {
                for (int j = 0; j < potrebniAsistenti; j++) {
                    String asistent = asistenti.poll();
                    if (mis.isEmpty() && !asistenti.isEmpty()) {
                        mis.add(asistent);
                        asistenti.add(asistent);
                    } else {
                        mis.add(asistent);
                        asistenti.add(asistent);
                    }
                }
            } else if (predmet.equals("OOS")) {
                for (int j = 0; j < potrebniAsistenti; j++) {
                    String asistent = asistenti.poll();
                    if (oos.isEmpty() && !asistenti.isEmpty()) {
                        oos.add(asistent);
                        asistenti.add(asistent);
                    } else {
                        oos.add(asistent);
                        asistenti.add(asistent);
                    }
                }
            }
        }

        System.out.println("APS");
        System.out.println(aps.size());
        for (String ass : aps) {
            System.out.println(ass);
        }

        System.out.println();

        System.out.println("MIS");
        System.out.println(mis.size());
        for (String ass : mis) {
            System.out.println(ass);
        }

        System.out.println();

        System.out.println("OOS");
        System.out.println(oos.size());
        for (String ass : oos) {
            System.out.println(ass);
        }

        input.close();
    }
}
