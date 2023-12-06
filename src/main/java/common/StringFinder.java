package common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StringFinder {

    public List<StringMatch> getMatchedStrings(List<String> stringsToSearch, String stringToSearchFrom) {
        List<Integer> charIndexesToSearch = new ArrayList<>(Collections.nCopies(stringsToSearch.size(), 0));
        List<StringMatch> stringMatches = new ArrayList<>();
        for (int i = 0; i < stringToSearchFrom.length(); i++) {
            char c = stringToSearchFrom.charAt(i);
            for (int j = 0; j < stringsToSearch.size(); j++) {
                int indexToLook = charIndexesToSearch.get(j);
                String stringToSearch = stringsToSearch.get(j);
                if (c != stringToSearch.charAt(indexToLook) && indexToLook > 0) {
                    charIndexesToSearch.set(j, 0);
                    indexToLook = charIndexesToSearch.get(j);
                }
                if (c == stringToSearch.charAt(indexToLook)) {
                    indexToLook += 1;
                    if (indexToLook >= stringToSearch.length()) {
                        StringMatch match = new StringMatch(stringToSearch, i - (stringToSearch.length() - 1), i);
                        stringMatches.add(match);
                        charIndexesToSearch.set(j, 0);
                    } else {
                        charIndexesToSearch.set(j, indexToLook);
                    }
                }
            }
        }
        return stringMatches;
    }
}
