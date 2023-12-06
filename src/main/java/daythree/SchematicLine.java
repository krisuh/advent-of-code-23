package daythree;

import common.StringMatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public record SchematicLine(int rowNum, List<StringMatchWithRow> numbers, List<StringMatchWithRow> symbols) {

    public static SchematicLine parse(int rowNum, String input) {
        List<StringMatchWithRow> matches = new ArrayList<>();
        StringBuilder caughtNumber = new StringBuilder();
        StringBuilder caughtSymbol = new StringBuilder();
        for (int i = 0; i < input.toCharArray().length; i++) {
            char c = input.charAt(i);
            if (!Character.isDigit(c) && caughtNumber.length() > 0) {
                var sm = new StringMatchWithRow(rowNum, caughtNumber.toString(), i - caughtNumber.length(), i - 1);
                matches.add(sm);
                caughtNumber = new StringBuilder();
            }
            if (Character.isDigit(c)) {
                caughtNumber.append(c);
            } else if (c != '.') {
                caughtSymbol.append(c);
                var sm = new StringMatchWithRow(rowNum, caughtSymbol.toString(), i, i);
                matches.add(sm);
                caughtSymbol = new StringBuilder();
            }
        }
        if (caughtNumber.length() > 0) {
            var sm = new StringMatchWithRow(rowNum, caughtNumber.toString(), input.length() - 1 - caughtNumber.length(), input.length() - 1);
            matches.add(sm);
        }
        var symbols = matches.stream().filter(stringMatch -> !SchematicLine.isInteger(stringMatch.stringMatched())).toList();
        var numbers = matches.stream().filter(stringMatch -> SchematicLine.isInteger(stringMatch.stringMatched())).toList();
        return new SchematicLine(rowNum, numbers, symbols);
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static SchematicLine empty() {
        return new SchematicLine(-1, Collections.emptyList(), Collections.emptyList());
    }
}
