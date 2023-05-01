import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import string_module.error.TextUtilsOperationException;
import string_module.TextUtils;

import java.util.*;

class TextUtilsTest {

    @ParameterizedTest
    @ValueSource(strings = {"", "      "})
    void testRepeatSymbolsWithoutSymbols(String text) {
        assertThrows(TextUtilsOperationException.class,
                () -> TextUtils.getRepeatSymbolCounts(text));
    }

    @Test
    void testRepeatSymbolsWithSpacesDoesNotThrowException() {
        String testedText = " te st  ";
        assertDoesNotThrow(() -> TextUtils.getRepeatSymbolCounts(testedText));
    }

    @Test
    void testRepeatSymbolsWithOneSymbolDoesNotThrowException() {
        String testedText = "t";
        assertDoesNotThrow(() -> TextUtils.getRepeatSymbolCounts(testedText));
    }

    @Test
    void testRepeatSymbolsReturnCorrectFrequency() throws Exception {
        String testedText = "frequency test";
        int expectedCountOfLowerCaseE = 3;
        int expectedCountOfLowerCaseT = 2;
        int expectedCountOfLowerCaseC = 1;
        Map<Character, Integer> symbolRepeating =
                TextUtils.getRepeatSymbolCounts(testedText);

        int actualCountOfLowerCaseE = symbolRepeating.get('e');
        int actualCountOfLowerCaseT = symbolRepeating.get('t');
        int actualCountOfLowerCaseC = symbolRepeating.get('c');

        assertEquals(expectedCountOfLowerCaseE, actualCountOfLowerCaseE);
        assertEquals(expectedCountOfLowerCaseC, actualCountOfLowerCaseC);
        assertEquals(expectedCountOfLowerCaseT, actualCountOfLowerCaseT);
    }

    @Test
    void testRepeatSymbolsDistinguishCases() throws Exception {
        String testedText = "FrEqUeNcY TeSt";
        int expectedCountOfLowerCaseE = 2;
        int expectedCountOfLowerCaseT = 1;
        int expectedCountOfUpperCaseE = 1;
        int expectedCountOfUpperCaseT = 1;
        Map<Character, Integer> symbolRepeating =
                TextUtils.getRepeatSymbolCounts(testedText);

        int actualCountOfLowerCaseE = symbolRepeating.get('e');
        int actualCountOfLowerCaseT = symbolRepeating.get('t');
        int actualCountOfUpperCaseE = symbolRepeating.get('E');
        int actualCountOfUpperCaseT = symbolRepeating.get('T');

        assertEquals(expectedCountOfLowerCaseE, actualCountOfLowerCaseE);
        assertEquals(expectedCountOfLowerCaseT, actualCountOfLowerCaseT);
        assertEquals(expectedCountOfUpperCaseE, actualCountOfUpperCaseE);
        assertEquals(expectedCountOfUpperCaseT, actualCountOfUpperCaseT);
    }
}
