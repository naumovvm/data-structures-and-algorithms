package TehnikiAlgoritmi;

import java.util.Arrays;
import java.util.Scanner;

public class zadaca3 {
    public static class Task {
        int time_needed;
        int money;

        public Task(int time_needed, int money) {
            this.time_needed = time_needed;
            this.money = money;
        }

        public double hourlyEarning() {
            return (double) this.money / this.time_needed;
        }
        // 10 60 => hourly e 6
        // 20 100 => hourly e 5
        // 30 120 => hourly e 4
    }

    // knapsack problem, znaci treba da gi zememe rabotite koi najmnogu vredat
    // So ovoj test case, prvo zemame zadaca so vreme 10, znaci 40-10=30, vrednost=60
    // Potoa ja zemame zadacata so vrednost 20, 30-20=10, vrednost=60+100=160
    // Na kraj ostanuva zadacata so vrednost 30, od koja ke zememe samo 1/3, a toa e 120/3=40, vrednost=160+40=200
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        Task[] tasks = new Task[n];

        for (int i = 0; i < n; i++) {
            int time_needed = input.nextInt();
            int money = input.nextInt();
            Task task = new Task(time_needed, money);
            tasks[i]=task;
        }

        int maxHours=40, maxProfit=0;
        Arrays.sort(tasks, (task1, task2) -> Double.compare(task2.hourlyEarning(), task1.hourlyEarning()));
        // gi sortiram spored nivnite hourly vrednosti

        for (int i = 0; i < n; i++) { //
            if(maxHours>=tasks[i].time_needed) { // tuka gledame dali mozhe cel task se zeme
                maxHours-=tasks[i].time_needed;
                maxProfit+=tasks[i].money;
            }else{
                maxProfit+=((double)maxHours/tasks[i].time_needed)*tasks[i].money; // ako ne mozhe cel task togash se zema samo del
                // vremeto sho e preostanato go delime so vremeto na zadacata, pa toa go mnozhime so vrednosta
                break; // break obavezno bidejki znaci tolku nema mesto vekje
            }
        }

        System.out.println(maxProfit);
        input.close();
    }
}
