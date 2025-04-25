package FinkiAPSBookEPS;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class zadaca3 { // Napredni problemi: Ponishtuvanje topcinja
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String[] vlez = input.nextLine().split(" ");
        LinkedList<String> topcinja = new LinkedList<>();

        for (int i = 0; i < vlez.length; i++) {
            topcinja.addLast(vlez[i]);
        }

        ponishtiTopcinja(topcinja);

        input.close();
    }

    private static void ponishtiTopcinja(LinkedList<String> topcinja) {
//      Поништување на топчиња може да настане само доколку тие се од иста боjа и со спротивен знак.
        Stack<String> crveni = new Stack<>();
        Stack<String> zeleni = new Stack<>();
        Stack<String> plavi = new Stack<>();
        String ponishteni="";
        String topce="";

        for (int i = 0; i < topcinja.size(); i++) {
            topce = topcinja.get(i);
            if(topce.charAt(0)=='R'){
                if(!crveni.isEmpty()){
                    if(crveni.peek().equals(topce)){
                        crveni.push(topce);
                    }else{ // Ako se so razlicen znak
                        crveni.pop();
                    }
                }else{ // ako e prazen stekot
                    crveni.push(topce);
                }
            }else if(topce.charAt(0)=='G'){
                if(!zeleni.isEmpty()){
                    if(zeleni.peek().equals(topce)){
                        zeleni.push(topce);
                    }else{
                        zeleni.pop();
                    }
                }else{
                    zeleni.push(topce);
                }
            }else if(topce.charAt(0)=='B'){
                if(!plavi.isEmpty()){
                    if(plavi.peek().equals(topce)){
                        plavi.push(topce);
                    }else{
                        plavi.pop();
                    }
                }else{
                    plavi.push(topce);
                }
            }
        }

        int count = 0;

        while(!crveni.isEmpty()){
            if(crveni.peek().charAt(1)=='+'){
                ponishteni+=crveni.pop().replace('+', '-')+" ";
            }else{
                ponishteni+=crveni.pop()+" ";
            }
            count++;
        }

        while(!zeleni.isEmpty()){
            if(zeleni.peek().charAt(1)=='+'){
                ponishteni+=zeleni.pop().replace('+', '-')+" ";
            }else{
                ponishteni+=zeleni.pop()+" ";
            }
            count++;
        }

        while(!plavi.isEmpty()){
            if(plavi.peek().charAt(1)=='+'){
                ponishteni+=plavi.pop().replace('+', '-')+" ";
            }else{
                ponishteni+=plavi.pop()+" ";
            }
            count++;
        }

        System.out.println(count);
        if(count!=0){
            System.out.println(ponishteni.trim());
        }
    }
}
