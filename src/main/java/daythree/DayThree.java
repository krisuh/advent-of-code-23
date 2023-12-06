package daythree;

import common.StringMatch;

import java.util.*;
import java.util.stream.Collectors;

public class DayThree {
  public static int partOne(List<String> input) {
    var schematics = DayThree.parseSchematic(input);
    var numbers = getNumbersAdjacentToSymbol(schematics);
    return numbers.stream().map(StringMatchWithRow::stringMatched).map(Integer::parseInt).reduce(0, Integer::sum);
  }

  public static int partTwo(List<String> input) {
    var schematics = DayThree.parseSchematic(input);
    var gears = findAllGears(schematics);
    var sum = 0;
    for (var gear : gears) {
      var rowNum = gear.rowNum();
      if (rowNum == 0) {
        var self = schematics.get(rowNum);
        var down = schematics.get(rowNum + 1);
        sum += findGearRatios(gear, self.numbers(), down.numbers());
      } else if (rowNum == schematics.size() - 1) {
        var self = schematics.get(rowNum);
        var up = schematics.get(rowNum - 1);
        sum += findGearRatios(gear, up.numbers(), self.numbers());
      } else {
        var self = schematics.get(rowNum);
        var up = schematics.get(rowNum - 1);
        var down = schematics.get(rowNum + 1);
        sum += findGearRatios(gear, up.numbers(), self.numbers(), down.numbers());
      }
    }
    return sum;
  }

  public static List<SchematicLine> parseSchematic(List<String> input) {
    var result = new ArrayList<SchematicLine>();
    for (int i = 0; i < input.size(); i++) {
      result.add(SchematicLine.parse(i, input.get(i)));
    }
    return result;
  }

  public static Set<StringMatchWithRow> getNumbersAdjacentToSymbol(List<SchematicLine> schematics) {
    var result = new HashSet<StringMatchWithRow>();
    var sortedSchematics = schematics.stream().sorted(Comparator.comparing(SchematicLine::rowNum)).toList();
    for (int i = 1; i < sortedSchematics.size() - 1; i += 1) {
      var up = schematics.get(i - 1);
      var middle = schematics.get(i);
      var down = schematics.get(i + 1);
      result.addAll(getNumbersAdjacentToSymbol(up, middle, down));
    }
    return result;
  }

  public static Set<StringMatchWithRow> getNumbersAdjacentToSymbol(SchematicLine up, SchematicLine middle, SchematicLine down) {
    var middleNumbersAdjacentToSelf = middle.numbers().stream().filter(n -> middle.symbols().stream().anyMatch(n::isAdjacentTo)).toList();
    var middleNumbersAdjacentToUp = middle.numbers().stream().filter(n -> up.symbols().stream().anyMatch(n::isAdjacentTo)).toList();
    var middleNumbersAdjacentToDown = middle.numbers().stream().filter(n -> down.symbols().stream().anyMatch(n::isAdjacentTo)).toList();

    var downNumbersAdjacentToSelf = down.numbers().stream().filter(n -> down.symbols().stream().anyMatch(n::isAdjacentTo)).toList();
    var downNumbersAdjacentToMiddle = down.numbers().stream().filter(n -> middle.symbols().stream().anyMatch(n::isAdjacentTo)).toList();

    var upNumbersAdjacentToSelf = up.numbers().stream().filter(n -> up.symbols().stream().anyMatch(n::isAdjacentTo)).toList();
    var upNumbersAdjacentToMiddle = up.numbers().stream().filter(n -> middle.symbols().stream().anyMatch(n::isAdjacentTo)).toList();
    var allCombined = new ArrayList<StringMatchWithRow>();
    allCombined.addAll(middleNumbersAdjacentToSelf);
    allCombined.addAll(middleNumbersAdjacentToUp);
    allCombined.addAll(middleNumbersAdjacentToDown);
    allCombined.addAll(downNumbersAdjacentToSelf);
    allCombined.addAll(downNumbersAdjacentToMiddle);
    allCombined.addAll(upNumbersAdjacentToSelf);
    allCombined.addAll(upNumbersAdjacentToMiddle);
    return new HashSet<>(allCombined);
  }

  public static List<StringMatchWithRow> findAllGears(List<SchematicLine> schematics) {
    return schematics.stream().flatMap((s) -> s.symbols().stream()).filter(s -> s.stringMatched().equals("*")).toList();
  }

  public static int findGearRatios(StringMatchWithRow gear, List<StringMatchWithRow> up, List<StringMatchWithRow> down) {
    var upAdjacents = gear.getAllAdjacents(up);
    var downAdjacents = gear.getAllAdjacents(down);
    var adjacents = new ArrayList<StringMatchWithRow>();
    adjacents.addAll(upAdjacents);
    adjacents.addAll(downAdjacents);
    if (adjacents.size() != 2) {
      return 0;
    }
    var first = adjacents.get(0).toInteger();
    var second = adjacents.get(1).toInteger();
    return first * second;
  }

  public static int findGearRatios(StringMatchWithRow gear, List<StringMatchWithRow> up, List<StringMatchWithRow> middle, List<StringMatchWithRow> down) {
    var upAdjacents = gear.getAllAdjacents(up);
    var downAdjacents = gear.getAllAdjacents(down);
    var middleAdjacents = gear.getAllAdjacents(middle);
    var adjacents = new ArrayList<StringMatchWithRow>();
    adjacents.addAll(upAdjacents);
    adjacents.addAll(middleAdjacents);
    adjacents.addAll(downAdjacents);
    if (adjacents.size() != 2) {
      return 0;
    }
    var first = adjacents.get(0).toInteger();
    var second = adjacents.get(1).toInteger();
    return first * second;
  }
}
