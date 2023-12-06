package dayfour;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class DayFour {

  public static int partOne(List<String> input) {
    var cards = input.stream().map(Card::parse).toList();
    var sum = 0;
    for (var game : cards) {
      sum += game.getPoints();
    }
    return sum;
  }

  public static int partTwo(List<String> input) {
    var cards = input.stream().map(Card::parse).toList();
    return countCopies(cards);
  }

  public static int countCopies(List<Card> cards) {
    var map = new HashMap<Integer, List<Card>>();
    for (Card card : cards) {
      map.compute(card.id(), (key, val) -> {
        if (val == null) {
          var cardList = new ArrayList<Card>();
          cardList.add(card);
          return cardList;
        }
        val.add(card);
        return val;
      });
    }
    for (var entry : map.entrySet()) {
      for (Card card : entry.getValue()) {
        var countOfCopies = card.getCount();
        var copies = cards.stream().skip(card.id()).limit(countOfCopies).toList();
        for (Card copy : copies) {
          map.get(copy.id()).add(copy);
        }
      }
    }
    return map.entrySet().stream().map(entry -> entry.getValue().size()).reduce(0, Integer::sum);
  }
}
