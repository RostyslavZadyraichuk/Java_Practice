package string_module;

import string_module.error.TextUtilsOperationException;

import java.util.*;

public class TextUtils {

    private TextUtils() {}

    private static void checkTextIsEmpty(String text)
            throws TextUtilsOperationException {
        if (text.trim().isEmpty()) {
            throw new TextUtilsOperationException("String parameter is empty.");
        }
    }

    public static Map<Character, Integer> getRepeatSymbolCounts(String text)
            throws TextUtilsOperationException {
        checkTextIsEmpty(text);
        Map<Character, Integer> symbolsRepeating = new HashMap<>();

        for (char symbol : text.toCharArray()) {
            if (symbol != ' ') {
//                java 8
//                if (!symbolsRepeating.containsKey(symbol)) {
//                    symbolsRepeating.put(symbol, 0);
//                }
//                int count = symbolsRepeating.get(symbol);

//              java 13
                int count = symbolsRepeating.computeIfAbsent(symbol, k -> 0);
                symbolsRepeating.put(symbol, count + 1);
            }
        }

        return symbolsRepeating;
    }

}
