package EdnoDimenzionalniPodatocniStrukturi;

import java.util.Scanner;
import java.util.Stack;

//Даден е код на модифициран програмски јазик каде функциите се претставени со отворен и затворен таг ("imeFunkcija" и "endimeFunkcija").
// Ваша задача е да дефинирате дали даден програмски код е валиден.
public class zadaca1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Stack<String> stack = new Stack<>();
        boolean isValid = true;

        while (true) {
            String value = input.nextLine();
            if (value.equals("x")) { // ako e x odma pravi break
                break;
            }

            if (!value.startsWith("end")) { // ako stringot ne pocnuva so end, go dodavame na stekot
                stack.push(value);
            } else { // ako pocnuva so end, togash ne e validen formatot i pravime break
                if (stack.isEmpty()) {
                    isValid = false;
                    break;
                }
                String lastFunc = stack.pop(); // ja zemame poslednata funkcijata
                if (!value.equals("end" + lastFunc)) { // proveruvame dali si isti funkciite u smisol primer func.equals(endfunc)
                    isValid = false; // ako se, togash isValid=false i pravime break odma
                    break;
                }
            }
        }

        if (!stack.isEmpty()) {
            isValid = false; // ako ne e prazen stackot znaci ne e tocen formatot i se pravi break
        }

        if (isValid) {
            System.out.println("Valid");
        } else {
            System.out.println("Invalid");
        }
    }
}
