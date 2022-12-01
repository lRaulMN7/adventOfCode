package puzzle_1;

import java.io.*;
import java.nio.file.Files;
import java.util.concurrent.atomic.AtomicInteger;

public class PuzzleOne {
    public static void main(String[] args) throws IOException {

        File file = new File("puzzle_1", "elf_calories.txt");

        AtomicInteger currentCalories = new AtomicInteger(0);
        AtomicInteger maxCalories = new AtomicInteger(0);

        Files.lines(file.toPath()).forEach(line -> {
            if (line.isEmpty() ) {
                if(currentCalories.get() > maxCalories.get()) {
                    maxCalories.set(currentCalories.get());
                }
                currentCalories.set(0);
            } else {
                currentCalories.addAndGet(Integer.parseInt(line));
            }
        });

        System.out.println(maxCalories.get());



    }
}
