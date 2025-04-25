package FinkiAPSBookEPS;

import java.util.Scanner;
import java.util.Stack;

public class zadaca10 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        // imame tri kutii, vlezna, pomoshna i rezultantna
        if (n != 0) {
            Stack<Character> vleznaKutja = new Stack<>();
            int counter = 0;

            for (int i = 0; i < n; i++) {
                char color = input.next().charAt(0);
                if(vleznaKutja.isEmpty()){
                    vleznaKutja.push(color);
                }else{
                    if(vleznaKutja.peek() == color){
                        if(counter<3){
                            vleznaKutja.push(color);
                            counter++;
                        }else{
                            while(vleznaKutja.peek()==color){
                                vleznaKutja.pop();
                            }

                            counter=0;
                        }
                    }else{
                        vleznaKutja.push(color);
                    }
                }
            }

            Stack<Character> rezultat = new Stack<>();
            while(!vleznaKutja.isEmpty() && vleznaKutja.peek()=='R'){
                rezultat.push(vleznaKutja.pop());
            }

            while(!vleznaKutja.isEmpty() && vleznaKutja.peek()=='G'){
                rezultat.push(vleznaKutja.pop());
            }

            while(!vleznaKutja.isEmpty() && vleznaKutja.peek()=='B'){
                rezultat.push(vleznaKutja.pop());
            }

            System.out.println(rezultat);
        }

        input.close();
    }
}
