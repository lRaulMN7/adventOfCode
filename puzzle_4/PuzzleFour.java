package puzzle_4;

import java.io.File;
import java.nio.file.Files;
import java.util.concurrent.atomic.AtomicLong;

public class PuzzleFour {

    public static void main(String[] args) {
        File file = new File("puzzle_4", "elf_schedule.txt");

        try (var stream = Files.lines(file.toPath())) {
            AtomicLong fullyOverlap = new AtomicLong(0L);
            AtomicLong partiallyOverlap = new AtomicLong(0L);

            stream
                    .map(line -> line.split(","))
                    .forEach(rangesStr -> {
                        String[] range1 = rangesStr[0].split("-");
                        String[] range2 = rangesStr[1].split("-");

                        int min1 = Integer.parseInt(range1[0]);
                        int max1 = Integer.parseInt(range1[1]);
                        int min2 = Integer.parseInt(range2[0]);
                        int max2 = Integer.parseInt(range2[1]);

                        if ((min1 <= min2 && min2 <= max1) || (min2 <= min1 && min1 <= max2)) {
                            partiallyOverlap.getAndIncrement();
                            if( (min1 <= min2 && max1 >= max2) || (min2 <= min1 && max2 >= max1)){
                                fullyOverlap.getAndIncrement();
                            }
                        }
                    });
            System.out.println("First solution: " + fullyOverlap.get());
            System.out.println("Second solution: " + partiallyOverlap.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
