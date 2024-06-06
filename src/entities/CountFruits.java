package entities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CountFruits {
    public static int Count() {
        String fileName = "src/Map/Map.txt";
        int fruitsCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                for (char c : line.toCharArray()) {
                    if (c == '*') {
                        fruitsCount++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Total number of fruits: " + fruitsCount);
        return fruitsCount;
    }


}