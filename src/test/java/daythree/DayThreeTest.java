package daythree;

import common.FileReaderUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DayThreeTest {

  @Test
  public void testPartOneSingleLine() {
    var st1 = SchematicLine.parse(0, "...905&...74.............795...128...872....*....*.....521.........../......806&.........456.....93..869.........168..............*.........");
    var nd2 = SchematicLine.parse(1, "........../..=662.824.........+............863..47........-.700.....437..........316..........................96.*......348*632....138.$932.");
    var rd3 = SchematicLine.parse(2, "........-..........*.......................................................465....*.........243......&........*...94..+..........*..........");
    var result = DayThree.getNumbersAdjacentToSymbol(st1, nd2, rd3);
    var expected = new String[]{"905", "74", "662", "824", "128", "863", "47", "521", "437", "806", "316", "96", "168", "348", "632", "138", "932", "94"};
    assertEquals(expected.length, result.size());
  }

  @Test
  public void testParsing() {
    var s = SchematicLine.parse(0,".......*.825..+..........284-.....271................*........418....&.......*...............+.......781-..........92#...222............%452");
    assertEquals(8, s.numbers().size());
  }

  @Test
  public void testSomething() {
    var input = List.of(
        " 467..114..",
        "...*......",
        "..35..633.",
        "......#...",
        "617*......",
        ".....+.58.",
        "..592.....",
        "......755.",
        "...$.*....",
        ".664.598..");
    var result = DayThree.partOne(input);
    assertEquals(4361, result);
  }

  @Test
  public void testWithRealInput() throws IOException {
    var input = new FileReaderUtil().readFileToStringList("input3.txt");
    var schematics = DayThree.parseSchematic(input);
    assertEquals(140, schematics.size());
    var result = DayThree.partOne(input);
    System.out.println(result);
  }
}
