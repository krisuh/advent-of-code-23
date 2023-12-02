package dayone;

import dayone.DayOne;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class DayOneTest {

    public Map<String, Integer> inputExpectedMap = Map.of(
            "two1nine", 29,
            "eightwothree", 83,
            "abcone2threexyz", 13,
            "xtwone3four", 24,
            "4nineeightseven2", 42,
            "zoneight234", 14,
            "7pqrstsixteen", 76,
            "ccpeightbcvknglvcv81gcjnlnfnine9", 89,
            "739", 79,
            "2xmdmtgcjhd8eighttwo", 22
    );

    @Test
    public void testFirstAndLastAsNumberPartTwo() {
        for (var entry : inputExpectedMap.entrySet()) {
            var result = DayOne.firstAndLastAsNumberPartTwo(entry.getKey());
            Assertions.assertEquals(entry.getValue(), result);
        }
    }
}
