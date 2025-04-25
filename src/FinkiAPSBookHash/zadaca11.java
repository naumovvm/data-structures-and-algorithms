package FinkiAPSBookHash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class zadaca11 { // Datoteki
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        input.nextLine();
        HashMap<String, ArrayList<String>> direktorium = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String[] vlez = input.nextLine().split(" ");
            String path = vlez[0];
            String fileName = vlez[1];
            String fileContent = vlez[2].replaceAll("[()]", "");
            String file = path + " " + fileName;

            if (direktorium.containsKey(fileContent)) {
                ArrayList<String> files = direktorium.get(fileContent);
                files.add(file);
                direktorium.put(fileContent, files);
            } else {
                ArrayList<String> files = new ArrayList<>();
                files.add(file);
                direktorium.put(fileContent, files);
            }
        }

        int m = input.nextInt();
        input.nextLine();

        for (int i = 0; i < m; i++) {
            String[] vlez = input.nextLine().split(" ");
            String komanda = vlez[0];
            String path = vlez[1];
            String fileName = vlez[2];
            String fileContent = vlez[3].replaceAll("[()]", "");
            String file = path + " " + fileName;
            if (komanda.equals("add")) {
                if (direktorium.containsKey(fileContent)) {
                    ArrayList<String> files = direktorium.get(fileContent);
                    files.add(file);
                    direktorium.put(fileContent, files);
                } else {
                    ArrayList<String> files = new ArrayList<>();
                    files.add(file);
                    direktorium.put(fileContent, files);
                }
            } else if (komanda.equals("delete")) {
                if (direktorium.containsKey(fileContent)) {
                    direktorium.remove(fileContent);
                }
            } else if (komanda.equals("find")) {
                if (direktorium.containsKey(fileContent)) {
                    System.out.println("true");
                } else {
                    System.out.println("false");
                }
            }
        }

        String fileContent = input.next();
        if (direktorium.containsKey(fileContent)) {
            for (String file : direktorium.get(fileContent)) {
                System.out.print(file + " ");
            }
        } else {
            System.out.println("No such file located.");
        }

        input.close();
    }
}
