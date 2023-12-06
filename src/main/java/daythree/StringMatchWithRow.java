package daythree;

import common.StringMatch;

import java.util.ArrayList;
import java.util.List;

public record StringMatchWithRow(int rowNum, String stringMatched, Integer startingIndex, Integer endingIndex) {
  public boolean isAdjacentTo(StringMatchWithRow other) {
    int left = startingIndex - 1;
    int right = endingIndex + 1;
    if (rowNum != other.rowNum && (rowNum - 1) != other.rowNum && (rowNum + 1) != other.rowNum) {
      return false;
    }
    List<Integer> range = new ArrayList<>();
    for (int i = left; i <= right; i++) {
      range.add(i);
    }
    return range.contains(other.startingIndex) || range.contains(other.endingIndex);
  }

  public List<StringMatchWithRow> getAllAdjacents(List<StringMatchWithRow> others) {
    return others.stream().filter(this::isAdjacentTo).toList();
  }

  public int toInteger() {
    return Integer.parseInt(stringMatched);
  }

}
