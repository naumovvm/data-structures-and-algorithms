package ZadaciPrvKolokvium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.*;

public class zadaca4 {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(evaluateExpression(input.readLine()));
    }

    private static int evaluateExpression(String input) {
        int result = 0, num = 0;
        Stack<Integer> stack = new Stack<>();
        char ch;
        char operator = '+';

        for (int i = 0; i < input.length(); i++) {
            ch = input.charAt(i);
            if (Character.isDigit(ch)) {
                num = num * 10 + Character.getNumericValue(ch);
                System.out.println("Num: " + num);
            }

            if (!Character.isDigit(ch) && ch != ' ' || i == input.length() - 1) {
                if (operator == '+') {
                    stack.push(num);
                } else if (operator == '*') {
                    stack.push(stack.pop() * num);
                } else if (operator == '/') {
                    stack.push(stack.pop() / num);
                } else if (operator == '-') {
                    stack.push(-num);
                }
                operator = ch;
                num = 0;
            }
        }

        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }
}
