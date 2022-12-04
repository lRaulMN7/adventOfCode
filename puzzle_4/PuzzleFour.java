package puzzle_4;

import java.io.File;
import java.nio.file.Files;

public class PuzzleFour {

    public static void main(String[] args) {
        File file = new File("puzzle_4", "elf_schedule.txt");

        try (var stream = Files.lines(file.toPath())) {
            long overlapped = stream
                    .map(line -> line.split(","))
                    .filter(rangesStr -> {
                        String[] range1 = rangesStr[0].split("-");
                        String[] range2 = rangesStr[1].split("-");

                        int min1 = Integer.parseInt(range1[0]);
                        int max1 = Integer.parseInt(range1[1]);
                        int min2 = Integer.parseInt(range2[0]);
                        int max2 = Integer.parseInt(range2[1]);
                        return (min1 <= min2 && max1 >= max2) || (min2 <= min1 && max2 >= max1);
                    }).count();
            System.out.println("First solution: " + overlapped);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
