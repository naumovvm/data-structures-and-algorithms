package FinkiAPSBookEPS;

import java.util.Scanner;
import java.util.Stack;

public class zadaca4 { // Molekula na voda
    public static void main(String[] args) {
//       H H O H H O H H O H H H H H O H O H O O H O O H H H
        // Primer vlez, vodata e sostavena od dva vodorodi i eden kislorod
        Scanner input = new Scanner(System.in);
        String sekvenca = input.nextLine();

        izbrojAtomi(sekvenca);

        input.close();
    }

    private static void izbrojAtomi(String sekvenca) {
        int count = 0;
        Stack<Character> vodorodi = new Stack<>();
        Stack<Character> kislorod = new Stack<>();
        char atom = ' ';
        String preostanatiAtomi = "";
        for (int i = 0; i < sekvenca.length(); i++) {
            atom = sekvenca.charAt(i);

            if (atom == ' ') {
                continue;
            }

            if (atom == 'H') {
                if (vodorodi.isEmpty()) {
                    vodorodi.push(atom);
                } else {
                    vodorodi.push(atom);
                    if (!kislorod.isEmpty()) {
                        kislorod.pop();
                        vodorodi.pop();
                        vodorodi.pop();
                        count++;
                    }
                }
            } else if (atom == 'O') {
                if (kislorod.isEmpty()) {
                    kislorod.push(atom);
                } else {
                    kislorod.push(atom);
                    if (vodorodi.size() >= 2) {
                        vodorodi.pop();
                        vodorodi.pop();
                        kislorod.pop();
                        count++;
                    }
                }
            }
        }

        while (!vodorodi.isEmpty()) {
            preostanatiAtomi += vodorodi.pop() + " ";
        }

        while (!kislorod.isEmpty()) {
            preostanatiAtomi += kislorod.pop() + " ";
        }

        System.out.println(count);
        System.out.println(preostanatiAtomi);
    }
}
//H H O H H O H H O H H O H H O H H O H H O H H O H O H
//count=9 molekuli;
