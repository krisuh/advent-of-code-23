package daytwo;

import java.util.List;
import java.util.stream.Stream;

public record Game(int id, List<GameSubset> subsets) {

    public GameSubset getMinSubset() {
        int minRed = subsets.stream().map(GameSubset::red).max(Integer::compareTo).orElse(0);
        int minBlue = subsets.stream().map(GameSubset::blue).max(Integer::compareTo).orElse(0);
        int minGreen = subsets.stream().map(GameSubset::green).max(Integer::compareTo).orElse(0);
        return new GameSubset(minRed, minGreen, minBlue);
    }

    public static class Parser {
        public static Game parse(String input) {
            var mainParts = input.split(": ");
            var id = mainParts[0].split(" ")[1];
            var subsets = Stream.of(mainParts[1].split("; ")).map(GameSubset.Parser::parse).toList();
            return new Game(Integer.parseInt(id), subsets);
        }
    }

}
