import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import string_module.error.TextUtilsOperationException;
import string_module.TextUtils;

import java.util.*;

class TextUtilsTest {

    private static final String EMPTY = "";
    private static final String ONLY_SPACES = "       ";
    private static final String TEXT_WITH_SPACES = " te st  ";
    private static final String SINGLE_SYMBOL = "t";
    private static final String DOUBLED_SYMBOL = "tt";
    private static final String SYMBOLS_COUPLE = "ts";
    private static final String ONE_CASE_TEXT = "frequency test";
    private static final String TWO_CASES_TEXT = "FrEqUeNcY TeSt";

    //1.1.
    @ParameterizedTest
    @ValueSource(strings = {EMPTY, ONLY_SPACES})
    void testRepeatSymbolsWithoutSymbols(String text) {
        assertThrows(TextUtilsOperationException.class,
                () -> TextUtils.getRepeatSymbolCounts(text));
    }

    @Test
    void testRepeatSymbolsWithSpacesDoesNotThrowException() {
        assertDoesNotThrow(() -> TextUtils.getRepeatSymbolCounts(TEXT_WITH_SPACES));
    }

    @Test
    void testRepeatSymbolsWithOneSymbolDoesNotThrowException() {
        assertDoesNotThrow(() -> TextUtils.getRepeatSymbolCounts(SINGLE_SYMBOL));
    }

    @Test
    void testRepeatSymbolsReturnCorrectFrequency() throws Exception {
        int expectedCountOfLowerCaseE = 3;
        int expectedCountOfLowerCaseT = 2;
        int expectedCountOfLowerCaseC = 1;
        Map<Character, Integer> symbolRepeating =
                TextUtils.getRepeatSymbolCounts(ONE_CASE_TEXT);

        int actualCountOfLowerCaseE = symbolRepeating.get('e');
        int actualCountOfLowerCaseT = symbolRepeating.get('t');
        int actualCountOfLowerCaseC = symbolRepeating.get('c');

        assertEquals(expectedCountOfLowerCaseE, actualCountOfLowerCaseE);
        assertEquals(expectedCountOfLowerCaseC, actualCountOfLowerCaseC);
        assertEquals(expectedCountOfLowerCaseT, actualCountOfLowerCaseT);
    }

    @Test
    void testRepeatSymbolsDistinguishCases() throws Exception {
        int expectedCountOfLowerCaseE = 2;
        int expectedCountOfLowerCaseT = 1;
        int expectedCountOfUpperCaseE = 1;
        int expectedCountOfUpperCaseT = 1;
        Map<Character, Integer> symbolRepeating =
                TextUtils.getRepeatSymbolCounts(TWO_CASES_TEXT);

        int actualCountOfLowerCaseE = symbolRepeating.get('e');
        int actualCountOfLowerCaseT = symbolRepeating.get('t');
        int actualCountOfUpperCaseE = symbolRepeating.get('E');
        int actualCountOfUpperCaseT = symbolRepeating.get('T');

        assertEquals(expectedCountOfLowerCaseE, actualCountOfLowerCaseE);
        assertEquals(expectedCountOfLowerCaseT, actualCountOfLowerCaseT);
        assertEquals(expectedCountOfUpperCaseE, actualCountOfUpperCaseE);
        assertEquals(expectedCountOfUpperCaseT, actualCountOfUpperCaseT);
    }

    //1.2.
    @ParameterizedTest
    @ValueSource(strings = {EMPTY, ONLY_SPACES})
    void testGetFirstNonRepeatedWithoutSymbols(String text) {
        assertThrows(TextUtilsOperationException.class,
                () -> TextUtils.getFirstNonRepeatSymbol(text));
    }

    @Test
    void testGetFirstNonRepeatedWithSpacesDoesNotThrowException() {
        assertDoesNotThrow(() -> TextUtils.getFirstNonRepeatSymbol(TEXT_WITH_SPACES));
    }

    @ParameterizedTest
    @ValueSource(strings = {SINGLE_SYMBOL, SYMBOLS_COUPLE, ONE_CASE_TEXT, TWO_CASES_TEXT})
    void testGetFirstNonRepeatedReturnsSymbol(String text) throws Exception {
        assertFalse(TextUtils.getFirstNonRepeatSymbol(text).isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {DOUBLED_SYMBOL, "abccba", "abcabc"})
    void testGetFirstNonRepeatedIsEmpty(String text) throws Exception {
        assertTrue(TextUtils.getFirstNonRepeatSymbol(text).isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {SINGLE_SYMBOL, SYMBOLS_COUPLE})
    void testGetFirstNonRepeatedIsCorrect(String text) throws Exception {
        char expected = 't';
        char notExpected = 's';
        Optional<Character> actualOpt = TextUtils.getFirstNonRepeatSymbol(text);
        char actual = actualOpt.orElseThrow();
        assertEquals(expected, actual);
        assertNotEquals(notExpected, actual);
    }

}
