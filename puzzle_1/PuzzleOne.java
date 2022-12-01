package puzzle_1;

import java.io.*;
import java.nio.file.Files;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class PuzzleOne {
    public static void main(String[] args) throws IOException {


        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> Integer.compare(b, a));

        File file = new File("puzzle_1", "elf_calories.txt");

        AtomicInteger currentCalories = new AtomicInteger(0);

        Files.lines(file.toPath()).forEach(line -> {
            if (line.isEmpty() ) {
                queue.add(currentCalories.get());
                currentCalories.set(0);
            } else {
                currentCalories.addAndGet(Integer.parseInt(line));
            }
        });

        int maxCalories = queue.poll();
        int secondCalories = queue.poll();
        int thirdCalories = queue.poll();

        System.out.println("Part1:" + maxCalories);
        System.out.println("Part2:" + (maxCalories + secondCalories + thirdCalories));



    }
}
