package FinkiAPSBookEPS;

import java.util.Scanner;
import java.util.Stack;

public class zadaca1 { // od 86ta pocnuvaat ednodimenzionalni podatocni strukturi, prva zadaca e Zagradi strana 93
    public static boolean proverka(String input) {
        boolean isCorrect = true;
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < input.length(); i++) {
            char znak = input.charAt(i); // go zemame sekoj znak
            if (znak == '(' || znak == '[' || znak == '{') { // ako e otvorena zagrada, ja stavame vo stek
                stack.push(znak);
            } else if (znak == ')' || znak == ']' || znak == '}') { // ako e zatvorena pocnuva proverkata
                if (stack.isEmpty()) { // ako e prazen stekot, znaci nema soodvetna zagrada, ne e tocen izrazot
                    return false;
                } else {
                    char stackZnak = stack.pop(); // go zemame znakot od vrvot na stekot
                    if (!((stackZnak == '(' && znak == ')') || (stackZnak == '[' && znak == ']') || (stackZnak == '{' && znak == '}'))) {
                        return false; // ako ne se sovpagja zagradata so nitu edna od tie na vrvot na stekot, ili pak ne e zagrada, false
                    }
                }
            }
        }

        return isCorrect; // tocen e uslovot
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String vlez = input.nextLine();

        boolean check = proverka(vlez);
        if(check){
            System.out.println(vlez+" ima korektni zagradi.");
        }else{
            System.out.println(vlez+" nema korektni zagradi.");
        }

        input.close();
    }
}
// s x (s - a) x (s - b) x (s - c))