package FinkiAPSBookEPS;

import java.util.Scanner;
import java.util.Stack;

public class zadaca5 { // Infix to Postfix, strana 109
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String vlez = input.nextLine();

        infixToPostfix(vlez);

        input.close();
    }

    private static void infixToPostfix(String vlez) {
        Stack<Character> operatori = new Stack<>();
        String postfix = "";

        for (char ch : vlez.toCharArray()) {
            if (ch == ' ') {
                continue;
            }

            if (Character.isDigit(ch) || Character.isLetter(ch)) { // ako e broj ili bukva, ja dodava direkt na stringot
                postfix += ch;
            } else if (ch == '(') { // ako e zagrada se stava vo stack-ot
                operatori.push(ch);
            } else if (ch == ')') { // ako e zagrada, go izminuvame stackot i gi dodavame site operandi na stringot
                while (!operatori.isEmpty() && operatori.peek() != '(') { // se dodeka ne e prazen i ne e zatvorena zagrada
                    postfix += operatori.pop();
                }

                if (!operatori.isEmpty()) {
                    operatori.pop(); // ako posledniot operand ni e zagrada, odnosno ne e prazen stackot, pop
                }
            } else {
                // mora da proverime prioritet na znaci koga imame postfix notacija
                while (!operatori.isEmpty() && prioritet(ch) <= prioritet(operatori.peek())) { // dodeka ne e prazen i gledame prioritet
                    postfix += operatori.pop(); // se dodavaat na stringot
                }
                // otkako ke zavrshime so while loop-ot, go dodavame samiot ch koj go koristevme za sporedba vo stack-ot
                operatori.push(ch);
            }
        }

        while (!operatori.isEmpty()) { // ako ima ushte elementi vo stackot, se stavaat na kraj na stringot
            postfix += operatori.pop();
        }

        System.out.println(postfix); // pecati
    }

    private static int prioritet(char ch) {
        // se proveruva prioritet, */ se 2, +- se 1, ^ e 3
        if (ch == '*' || ch == '/') {
            return 2;
        } else if (ch == '+' || ch == '-') {
            return 1;
        } else if (ch == '^') {
            return 3;
        }

        return 0;
    }
}
// Vlez: a + b * (c^d - e)^(f + g * h) - i
// Izlez: 𝑎𝑏𝑐𝑑∧𝑒−𝑓𝑔ℎ*+∧*+𝑖−