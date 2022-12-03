package puzzle_2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class PuzzleTwo {
    static Map<Character, Map<Character, Integer>> scoreOutcome;
    static Map<Character, Integer> scoreShape;

    public static void init(){
        scoreOutcome = new HashMap<>();
        scoreOutcome.put('X', Map.of('A', 3,'B',0, 'C', 6));
        scoreOutcome.put('Y', Map.of('A', 6, 'B', 3, 'C', 0));
        scoreOutcome.put('Z', Map.of('A',0,'B', 6, 'C', 3));

        scoreShape = new HashMap<>();
        scoreShape.put('X', 1);
        scoreShape.put('Y', 2);
        scoreShape.put('Z', 3);
    }

    public static int computeScore(String elf, String human) {
        int score = scoreOutcome.get(human.charAt(0)).get(elf.charAt(0));
        int shape = scoreShape.get(human.charAt(0));
        return score + shape;
    }

    public static void main(String[] args) throws IOException {
        File file = new File("puzzle_2", "elf_strategy.txt"); //12855
        init();

        try (var stream = Files.lines(file.toPath())) {

            int solution = stream.map(line -> line.split(" "))
                    .mapToInt(line -> computeScore(line[0], line[1]))
                    .sum();

            System.out.println("Total points: " + solution);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
