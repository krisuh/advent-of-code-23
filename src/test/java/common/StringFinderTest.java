package common;

import common.StringFinder;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringFinderTest {

    @Test
    public void testMatchingStrings() {
        List<String> stringsToSearch = List.of("eight", "three");
        String s = "eightthree";
        var m = new StringFinder();
        var result = m.getMatchedStrings(stringsToSearch, s);
        assertEquals(2, result.size());
    }
}
