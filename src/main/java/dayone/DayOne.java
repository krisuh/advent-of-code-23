package dayone;

import common.StringFinder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DayOne {

    public static Map<String, String> numberNamesToDigit = Map.of(
            "one", "1",
            "two", "2",
            "three", "3",
            "four", "4",
            "five", "5",
            "six", "6",
            "seven", "7",
            "eight", "8",
            "nine", "9",
            "1", "1");
    public static List<String> numberNames = List.of(
            "one", "1",
            "two", "2",
            "three", "3",
            "four", "4",
            "five", "5",
            "six", "6",
            "seven", "7",
            "eight", "8",
            "nine", "9"
    );

    public static int partOne(List<String> input) {
        return input.stream().map(DayOne::firstAndLastAsNumber).reduce(0, Integer::sum);
    }

    public static int partTwo(List<String> input) {
        var numbers = input.stream().map(DayOne::firstAndLastAsNumberPartTwo).toList();
        return numbers.stream().reduce(0, Integer::sum);
    }

    public static int firstAndLastAsNumber(String input) {
        List<Character> digits = new ArrayList<>();
        for (char c : input.toCharArray()) {
            if (Character.isDigit(c)) {
                digits.add(c);
            }
        }
        if (digits.isEmpty()) {
            return 0;
        }
        String firstAndLast = "" + digits.get(0) + digits.get(digits.size() - 1);
        return Integer.parseInt(firstAndLast);
    }

    public static int firstAndLastAsNumberPartTwo(String input) {
        StringFinder stringFinder = new StringFinder();
        var matches = stringFinder.getMatchedStrings(numberNames, input);
        var digits = matches.stream().map(
                match -> numberNamesToDigit.getOrDefault(match.stringMatched(), match.stringMatched())).toList();
        if (digits.isEmpty()) {
            return 0;
        }
        var s = digits.get(0) + digits.get(digits.size() - 1);
        return Integer.parseInt(s);
    }
}
