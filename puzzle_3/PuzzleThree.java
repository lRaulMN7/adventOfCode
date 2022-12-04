package puzzle_3;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.IntStream;

public class PuzzleThree {

    static int findRepeating(String leftCompartment, String rightCompartment) {
          for (int i = 0; i < leftCompartment.length(); i++) {
              char leftChar = leftCompartment.charAt(i);
              if (rightCompartment.indexOf(leftChar) >= 0) {
                  return leftChar;
              }
          }
        return 0;
    }

    static int getPriorityOf(int item){
        return Character.isUpperCase(item) ? item - 'A' + 27 : item - 'a' + 1;
    }

    static int findBadgeElement(List<String[]> rucksacks){
        List<String> rucksack = new ArrayList<>();
        rucksacks.stream().map(compartments -> compartments[0].concat(compartments[1])).forEach(rucksack::add); // I think this should not be needed, but feels natural to join them

        Optional<Character> commonChar = rucksack.get(0).chars().mapToObj(c -> (char) c)
                .filter(c -> rucksack.get(1).contains(String.valueOf(c)) && rucksack.get(2).contains(String.valueOf(c)))
                .findFirst();

        return commonChar.orElse('a');
    }


    public static void main(String[] args) {
        File file = new File("puzzle_3", "elf_rucksacks.txt");

        try (var stream = Files.lines(file.toPath())) {

            List<String[]> collect = stream.map(line -> new String[]{line.substring(0, line.length() / 2), line.substring(line.length() / 2)})
                    .toList(); // flat stream to list of racks

            //this is a pagination in order to group the racks in groups of 3
            List<List<String[]>> badgeGroups = IntStream.range(0, (collect.size() + 3 - 1) / 3)
                    .mapToObj(i -> collect.subList(i * 3, i * 3 + 3))
                    .toList();

            int[] solution = badgeGroups.stream()
                    .map(rucksacksOfGroup -> {
                        int sumPriorities = rucksacksOfGroup.stream()
                                .mapToInt(rucksack -> findRepeating(rucksack[0], rucksack[1]))
                                .map(PuzzleThree::getPriorityOf).sum();
                        int sumBadges = getPriorityOf(findBadgeElement(rucksacksOfGroup));
                        return new int[]{sumPriorities, sumBadges};
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
