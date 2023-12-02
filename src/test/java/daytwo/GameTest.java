package daytwo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {
    @Test
    public void testParse() {
        var result = Game.Parser.parse("Game 1: 1 green, 6 red, 4 blue; 2 blue, 6 green, 7 red; 3 red, 4 blue, 6 green; 3 green; 3 blue, 2 green, 1 red");
        assertEquals(1, result.id());
        assertEquals(5, result.subsets().size());
    }
}
