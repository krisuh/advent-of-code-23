package dayfour;

import java.util.List;
import java.util.stream.Stream;

public record Card(int id, List<Integer> winningNumbers, List<Integer> numbersHad) {
  public static Card parse(String input) {
    var idAndNumbers = input.split(":");
    var id = idAndNumbers[0].replaceAll("\\s+", " ").split(" ")[1].trim();
    var numbers = idAndNumbers[1].trim().split("\\|");
    var winningNumbersArray = numbers[0].trim().split(" ");
    var numbersHadArray = numbers[1].trim().split(" ");
    return new Card(Integer.parseInt(id),
        Stream.of(winningNumbersArray).map(s -> s.replaceAll("\\s+", "")).filter(s -> !s.isBlank()).map(Integer::parseInt).toList(),
        Stream.of(numbersHadArray).map(s -> s.replaceAll("\\s+", "")).filter(s -> !s.isBlank()).map(Integer::parseInt).toList()
    );
  }

  public int getPoints() {
    var numbersGuessed = getCount();
    if (numbersGuessed == 1) {
      return 1;
    }
    return (int) Math.pow(2, numbersGuessed - 1);
  }

  public int getCount() {
    return (int) winningNumbers.stream().filter(numbersHad::contains).count();
  }
}
