package FinkiAPSBookEPS;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class zadaca9 { // Tancovi dvojki
    public static void main(String[] args) {
        // Imame M i Z tancovi (mashki i zhenski)
        // Ima tri tipa tancovi: O, S i L
        Scanner input = new Scanner(System.in);
        LinkedList<String> vlez = new LinkedList<>();
        String[] vlez2 = input.nextLine().split(" ");

        for (int i = 0; i < vlez2.length; i++) {
            vlez.addLast(vlez2[i]);
        }

        izbrojDvojki(vlez);

        input.close();
    }

    private static void izbrojDvojki(LinkedList<String> vlez) {
        int count = 0;
        Stack<String> osnovniTanc = new Stack<>();
        Stack<String> standardniTanc = new Stack<>();
        Stack<String> latinoTanc = new Stack<>();
        LinkedList<String> rezultat = new LinkedList<>();

        for (int i = 0; i < vlez.size(); i++) {
            String izbor = vlez.get(i);

            if (izbor.charAt(0) == 'O') {
                if (osnovniTanc.isEmpty()) {
                    osnovniTanc.push(izbor);
                } else {
                    if (osnovniTanc.peek().charAt(1) == 'M' && izbor.charAt(1) == 'Z'
                            || osnovniTanc.peek().charAt(1) == 'Z' && izbor.charAt(1) == 'M') {
                        osnovniTanc.pop();
                    } else {
                        osnovniTanc.push(izbor);
                    }
                }
            } else if (izbor.charAt(0) == 'S') {
                if (standardniTanc.isEmpty()) {
                    standardniTanc.push(izbor);
                } else {
                    if (standardniTanc.peek().charAt(1) == 'M' && izbor.charAt(1) == 'Z'
                            || standardniTanc.peek().charAt(1) == 'Z' && izbor.charAt(1) == 'M') {
                        standardniTanc.pop();
                    } else {
                        standardniTanc.push(izbor);
                    }
                }
            } else if (izbor.charAt(0) == 'L') {
                if (latinoTanc.isEmpty()) {
                    latinoTanc.push(izbor);
                } else {
                    if (latinoTanc.peek().charAt(1) == 'M' && izbor.charAt(1) == 'Z'
                            || latinoTanc.peek().charAt(1) == 'Z' && izbor.charAt(1) == 'M') {
                        latinoTanc.pop();
                    } else {
                        latinoTanc.push(izbor);
                    }
                }
            }
        }

        while (!osnovniTanc.isEmpty()) {
            if (osnovniTanc.peek().charAt(1) == 'M') {
                String tanc = osnovniTanc.pop();
                tanc = tanc.charAt(0) + "Z";
                rezultat.addLast(tanc);
            } else {
                String tanc = osnovniTanc.pop();
                tanc = tanc.charAt(0) + "M";
                rezultat.addLast(tanc);
            }
            count++;
        }

        while (!standardniTanc.isEmpty()) {
            if (standardniTanc.peek().charAt(1) == 'M') {
                String tanc = standardniTanc.pop();
                tanc = tanc.charAt(0) + "Z";
                rezultat.addLast(tanc);
            } else {
                String tanc = standardniTanc.pop();
                tanc = tanc.charAt(0) + "M";
                rezultat.addLast(tanc);
            }
            count++;
        }

        while (!latinoTanc.isEmpty()) {
            if (latinoTanc.peek().charAt(1) == 'M') {
                String tanc = latinoTanc.pop();
                tanc = tanc.charAt(0) + "Z";
                rezultat.addLast(tanc);
            } else {
                String tanc = latinoTanc.pop();
                tanc = tanc.charAt(0) + "M";
                rezultat.addLast(tanc);
            }
            count++;
        }

        System.out.println(count);
        if(count==0){
            System.out.println("Nema vishok lugje.");
        }else{
            for(String r : rezultat){
                System.out.print(r+" ");
            }
        }
    }
}
