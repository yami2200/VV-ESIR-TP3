package fr.istic.vv;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    String[] valideStrings = {
        "",
        "{}",
        "[]",
        "()",
        "[()]",
        "{[]}",
        "({})",
        "([{}])",
        "[](){}",
        "[()]()",
        "[{()}]{()}"
    };

    @Test
    public void testIsBalanced1() {
        for (int i = 0; i < valideStrings.length; i++) {
            assertTrue(StringUtils.isBalanced(valideStrings[i]));

        }
    }

    String[] wrongStrings = {
            "}",
            "{",
            "[",
            "]",
            "(",
            ")",
            "[[",
            "())",
            "({}(",
            "([{}})",
            "[](){",
            ")[]()",
            "[{(}]{()}",
            "({})[((()))][][({)]"
    };

    @Test
    public void testIsBalanced2() {
        for (int i = 0; i < wrongStrings.length; i++) {
            assertFalse(StringUtils.isBalanced(wrongStrings[i]));

        }
    }


}