package puzzle_2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class PuzzleTwo {
    static Map<Character, Map<Character, Integer>> scoreOutcomeOne, scoreOutcomeTwo;

    public static void init() {
        scoreOutcomeOne = new HashMap<>();
        scoreOutcomeOne.put('X', Map.of('A', 4, 'B', 1, 'C', 7));
        scoreOutcomeOne.put('Y', Map.of('A', 8, 'B', 5, 'C', 2));
        scoreOutcomeOne.put('Z', Map.of('A', 3, 'B', 9, 'C', 6));

        scoreOutcomeTwo = new HashMap<>();
        scoreOutcomeTwo.put('X', Map.of('A', 3, 'B', 1, 'C', 2));
        scoreOutcomeTwo.put('Y', Map.of('A', 4, 'B', 5, 'C', 6));
        scoreOutcomeTwo.put('Z', Map.of('A', 8, 'B', 9, 'C', 7));
    }

    public static void main(String[] args) {
        File file = new File("puzzle_2", "elf_strategy.txt");
        init();

        try (var stream = Files.lines(file.toPath())) {
            int[] solution = stream
                    .map(line -> new int[]{
                            scoreOutcomeOne.get(line.charAt(2)).get(line.charAt(0)),
                            scoreOutcomeTwo.get(line.charAt(2)).get(line.charAt(0))
                    })
                    .reduce(new int[]{0, 0}, (a, b) -> new int[]{
                            a[0] + b[0],
                            a[1] + b[1]
                    });

            System.out.println("First part: " + solution[0]);
            System.out.println("Second part: " + solution[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
