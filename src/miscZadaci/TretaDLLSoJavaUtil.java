package miscZadaci;

import java.util.*;

public class TretaDLLSoJavaUtil {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        LinkedList<Integer> lista1 = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            lista1.addLast(input.nextInt());
        }

        LinkedList<Integer> lista2 = new LinkedList<>();
        int m = input.nextInt();
        for (int i = 0; i < m; i++) {
            lista2.addLast(input.nextInt());
        }

        LinkedList<Integer> lista3= new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if(lista1.get(i)%2!=0){
                lista3.addLast(lista1.get(i));
            }
        }

        for (int i = 0; i < m; i++) {
            if(lista2.get(i)%2==0){
                lista3.addLast(lista2.get(i));
            }
        }

        Collections.sort(lista3);
        for (int i = 0; i < lista3.size(); i++) {
            if(i==lista3.size()-1){
                System.out.print(lista3.get(i));
                break;
            }
            System.out.print(lista3.get(i)+"<->");
        }
    }
}
