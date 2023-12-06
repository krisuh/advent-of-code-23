package common;

import daythree.StringMatchWithRow;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringMatchTest {

    @Test
    public void testIsAdjacentTo() {
        StringMatchWithRow m = new StringMatchWithRow(1,"123", 0, 2);
        StringMatchWithRow m2 = new StringMatchWithRow(2, "*", 3, 3);
        Assertions.assertTrue(m.isAdjacentTo(m2));
        Assertions.assertTrue(m2.isAdjacentTo(m));
    }
}
