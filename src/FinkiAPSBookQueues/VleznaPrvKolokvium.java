package FinkiAPSBookQueues;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class VleznaPrvKolokvium {
    public static class Chovek{
        String ime;
        int baranja;

        public Chovek(String ime, int baranja) {
            this.ime = ime;
            this.baranja = baranja;
        }
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        Queue<Chovek> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            String ime = input.next();
            int baranja = input.nextInt();
            queue.add(new Chovek(ime, baranja));
        }

        while(!queue.isEmpty()) {
            if(queue.peek().baranja == 0){
                System.out.println(queue.poll().ime);
            }else{
                queue.peek().baranja--;
                queue.add(queue.poll());
            }
        }
    }
}
