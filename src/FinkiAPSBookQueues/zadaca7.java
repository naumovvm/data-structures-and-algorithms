package FinkiAPSBookQueues;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class zadaca7 { // Konsultacii v2
    // Ima tri tipa na studenti: kratki prashanja, objasnuvanje zadaci i komplet
    // Prvi se tie so kratki prashanja,
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Queue<String> kratkiPrashanja = new LinkedList<>();
        Queue<String> objasnuvanja = new LinkedList<>();
        Queue<String> komplet = new LinkedList<>();
        LinkedList<String> redosled = new LinkedList<>();

        int kratkiPrashanjaBr = input.nextInt();
        for (int i = 0; i < kratkiPrashanjaBr; i++) {
            String student = input.next();
            kratkiPrashanja.add(student);
        }

        int objasnuvanjaBr=input.nextInt();
        for (int i = 0; i < objasnuvanjaBr; i++) {
            String student = input.next();
            objasnuvanja.add(student);
        }

        int kompletBr = input.nextInt();
        for (int i = 0; i < kompletBr; i++) {
            String student = input.next();
            komplet.add(student);
        }
        String k = "";
        while(true){
            if(!kratkiPrashanja.isEmpty()){
                redosled.add(kratkiPrashanja.poll());
                if(!objasnuvanja.isEmpty()){
                    redosled.add(objasnuvanja.poll());
                }else{
                    if(!komplet.isEmpty()){
                        k = komplet.poll();
                        redosled.add(k);
                        komplet.add(k);
                    }
                }
            }else{
                if(!komplet.isEmpty()){
                    k = komplet.poll();
                    redosled.add(k);
                    komplet.add(k);
                    if(!objasnuvanja.isEmpty()){
                        redosled.add(objasnuvanja.poll());
                    }
                }
            }

            if(kratkiPrashanja.isEmpty() && objasnuvanja.isEmpty()){
                break;
            }
        }

        for(String ime : redosled){
            System.out.println(ime);
        }

        input.close();
    }
}
//4
//IlinkaIvanoska
//IgorKulev
//MagdalenaKostoska
//HristinaMihajloska
//2
//AnastasMishev
//VladimirTrajkovik
//1
//SlobodanKalajdziski