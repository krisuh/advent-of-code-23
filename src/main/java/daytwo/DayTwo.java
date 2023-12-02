package daytwo;

import java.util.List;

public class DayTwo {

    public static int partOne(List<String> input) {
        List<Game> games = input.stream().map(Game.Parser::parse).toList();
        var possibleGames = games.stream().filter(DayTwo::isPossibleGame).toList();
        return possibleGames.stream().map(Game::id).reduce(0, Integer::sum);
    }

    public static int partTwo(List<String> input) {
        List<Game> games = input.stream().map(Game.Parser::parse).toList();
        return games.stream().map(Game::getMinSubset).map(GameSubset::getPower).reduce(0, Integer::sum);
    }

    private static boolean isPossibleGame(Game game) {
        var bluePossible = game.subsets().stream().allMatch(subset -> subset.blue() <= 14);
        var redPossible = game.subsets().stream().allMatch(subset -> subset.red() <= 12);
        var greenPossible = game.subsets().stream().allMatch(subset -> subset.green() <= 13);
        if (!bluePossible || !redPossible || !greenPossible) {
            return false;
        }
        return true;
    }
}
