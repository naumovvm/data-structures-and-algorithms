package FinkiAPSBookQueues;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class zadaca1 { // Round Robin, strana 125
    public static class Process {
        String name;
        int arrivalTime;
        int executionTime;

        public Process(String name, int arrivalTime, int executionTime) {
            this.name = name;
            this.arrivalTime = arrivalTime;
            this.executionTime = executionTime;
        }
    }

    public static void main(String[] args) {
        // Sekoj proces ima ime, vreme na pristignuvanje i vreme na izvrshuvanje
        // Logikata na izvrshuvanje e prv dojden, prv usluzhen
        // Treba se sortirat spored vreme na pristignuvanje, ako se so isto vreme togash toa so pogolemo vreme na izvrshuvanje
        //
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        Queue<Process> queue = new LinkedList<>();
        LinkedList<Process> processes = new LinkedList<>();
        LinkedList<String> result = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            String name = input.next();
            int arrivalTime = input.nextInt();
            int executionTime = input.nextInt();
            Process pr = new Process(name, arrivalTime, executionTime);
            processes.addLast(pr);
        }

        int quantum = input.nextInt();

        processes.sort((a, b) -> {
            if (a.arrivalTime != b.arrivalTime) {
                return Integer.compare(a.arrivalTime, b.arrivalTime);
            }

            return Integer.compare(a.executionTime, b.executionTime);
        });

        int currentTime = 0;
        int index = 0;

        while (!queue.isEmpty() || index < processes.size()) {
            while (index < processes.size() && processes.get(index).arrivalTime <= currentTime) {
                queue.add(processes.get(index));
                index++;
            }

            if (queue.isEmpty()) {
                currentTime++;
            }

            Process currProcess = queue.poll();

            if (currProcess != null) {
                result.add(currProcess.name);

                int executionTime = Math.min(quantum, currProcess.executionTime);
                currProcess.arrivalTime -= executionTime;
                currentTime += executionTime;
            }
            if (currProcess != null && currProcess.arrivalTime > 0) {
                while (index < processes.size() && processes.get(index).arrivalTime <= currentTime) {
                    queue.add(processes.get(index));
                    index++;
                }
                queue.add(currProcess);
            }
        }

        System.out.println(result);
        input.close();
    }
}
