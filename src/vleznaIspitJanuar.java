import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

// Otvaraci i zatvaracki tagovi, primer: #Heading1 e otvaracki, a Heading1# e zatvaracki.
// Mora sekoj otvaracki tag da ima soodveten zatvaracki. Ne mozhe da ima Heading tagovi vgnezdeni vo Body tagovi.
// Najdole ima test cases

public class vleznaIspitJanuar {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        input.nextLine();
        LinkedList<String> lista = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            String vlez = input.nextLine();
            lista.addLast(vlez);
        }

        if (checkStructure(lista)) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }

        input.close();
    }

    private static boolean checkStructure(LinkedList<String> lista) {
        Stack<String> stack = new Stack<>();
        boolean isCorrect = true;

        for (String word : lista) {
            word = word.trim();
            if (word.startsWith("#")) {
                if (stack.isEmpty()) { // ako e prazen push
                    stack.push(word);
                } else {
                    if (stack.size() == 1 && stack.peek().contains("Body")) {
                        if (word.contains("Heading")) {
                            isCorrect = false;
                            break;
                        }
                    } else {
                        if (word.contains("Heading")) {
                            if (stack.peek().charAt(8) < word.charAt(8)) {
                                stack.push(word);
                            } else {
                                isCorrect = false;
                                break;
                            }
                        } else if (word.contains("Body")) {
                            stack.push(word);
                        }
                    }
                }
            } else if (word.endsWith("#")) {
                if (stack.isEmpty()) {
                    isCorrect = false;
                    break;
                } else if (word.contains("Body")) {
                    if (stack.peek().contains("Body")) {
                        stack.pop();
                    } else {
                        isCorrect = false;
                        break;
                    }
                } else if (word.contains("Heading")) {
                    if (stack.peek().contains("Body")) {
                        isCorrect = false;
                        break;
                    } else if (stack.peek().charAt(8) == word.charAt(7)) {
                        stack.pop();
                    } else {
                        isCorrect = false;
                        break;
                    }
                }
            }
        }

        if (!stack.isEmpty()) {
            isCorrect = false;
        }

        return isCorrect;
    }
}

//5
//#Heading3
//#Heading1
//text
//Heading1#
//Heading3#
//Output: false

//6
//#Heading2
//#Heading1
//Heading1#
//#Body
//Body#
//Heading2#
//Output: false

//5
//#Heading3
//#Heading2
//#Heading3
//Body#
//Heading2#
//Output: false

//9
//#Heading1
//#Heading2
//#Heading3
//Heading3#
//Heading2#
//#Body
//Some text
//Body#
//Heading1#
//Output: true

//6
//#Heading3
//#Heading2
//#Heading1
//Heading1#
//Heading2#
//Heading3#
//Output: false

//5
//#Heading1
//#Body
//Some text inside the body.
//Body#
//Heading1#
//Output: true

//6
//#Heading1
//#Heading2
//Heading1#
//Heading2#
//#Body
//Body#
//Output: false

//10
//#Heading1
//#Heading2
//#Body
//Some text inside the body.
//Body#
//Heading2#
//#Body
//More text inside another body.
//Body#
//Heading1#
//Output: true

//5
//#Heading1
//#Body
//Some text inside the body.
//Heading2#
//Heading1#
//Output: false

//4
//#Heading1
//Some text inside the body.
//Body#
//Heading1#
//Output: false

//2
//#Body
//Body#
//Output: true

//8
//#Heading1
//#Body
//Text in first body.
//Body#
//#Body
//Text in second body.
//Body#
//Heading1#
//Output: true

