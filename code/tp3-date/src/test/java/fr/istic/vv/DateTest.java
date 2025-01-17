package fr.istic.vv;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    /* #####################  CONSTRUCTOR TESTS  ##################### */

    @Test
    void testConstructor() {
        Date date = new Date(19, 3, 2014);
        assertEquals(19, date.getDay());
        assertEquals(3, date.getMonth());
        assertEquals(2014, date.getYear());
    }

    @Test
    void testConstructorInvalidDate() {
        assertThrows(IllegalArgumentException.class, () -> new Date(32, 1, 2001));
    }

    /* #####################  IS VALID DATE TESTS  ##################### */

    @Test
    public void isValidDateTest_InvalidDay_lower() {
        Date dateObject = new Date(1, 1, 2020);
        assertFalse(dateObject.isValidDate(0,1,2022));
    }
    @Test
    public void isValidDateTest_InvalidDay_upper() {
        Date dateObject = new Date(1, 1, 2020);
        assertFalse(dateObject.isValidDate(32,1,2022));
    }

    @Test
    public void isValidDateTest_InvalidMonth_lower() {
        Date dateObject = new Date(1, 1, 2020);
        assertFalse(dateObject.isValidDate(1,0,2022));
    }

    @Test
    public void isValidDateTest_ValidMonth_boundary() {
        Date dateObject = new Date(1, 1, 2020);
        assertTrue(dateObject.isValidDate(1,1,2022));
    }

    @Test
    public void isValidDateTest_ValidMonth_boundary2() {
        Date dateObject = new Date(1, 1, 2020);
        assertTrue(dateObject.isValidDate(1,12,2022));
    }

    @Test
    public void isValidDateTest_InvalidMonth_upper() {
        Date dateObject = new Date(1, 1, 2020);
        assertFalse(dateObject.isValidDate(1,13,2022));
    }

    @Test
    public void isValidDateTest_InvalidDay30DaysMonthJune() {
        Date dateObject = new Date(1, 1, 2020);
        assertFalse(dateObject.isValidDate(31,6,2022));
    }

    @Test
    public void isValidDateTest_InvalidDay30DaysMonthApril() {
        Date dateObject = new Date(1, 1, 2020);
        assertFalse(dateObject.isValidDate(31,4,2022));
    }

    @Test
    public void isValidDateTest_InvalidDay30DaysMonthSept() {
        Date dateObject = new Date(1, 1, 2020);
        assertFalse(dateObject.isValidDate(31,9,2022));
    }

    @Test
    public void isValidDateTest_InvalidDay30DaysMonthNov() {
        Date dateObject = new Date(1, 1, 2020);
        assertFalse(dateObject.isValidDate(31,11,2022));
    }

    @Test
    public void isValidDateTest_InvalidDayFebruary() {
        Date dateObject = new Date(1, 1, 2020);
        assertFalse(dateObject.isValidDate(30,2,2022));
    }

    @Test
    public void isValidDateTest_InvalidDay28DaysMonth() {
        Date dateObject = new Date(1, 1, 2020);
        assertFalse(dateObject.isValidDate(29,2,2022));
    }

    @Test
    public void isValidDateTest_InvalidYear() {
        Date dateObject = new Date(1, 1, 2020);
        assertFalse(dateObject.isValidDate(1,1,-1));
    }

    @Test
    public void isValidDateTest_ValidYearBoundary() {
        Date dateObject = new Date(1, 1, 2020);
        assertTrue(dateObject.isValidDate(1,1,0));
    }

    @Test
    public void isValidDateTest_InvalidDayMonth() {
        Date dateObject = new Date(1, 1, 2020);
        assertFalse(dateObject.isValidDate(-5,22,2022));
    }

    @Test
    public void isValidDateTest_InvalidDayYear() {
        Date dateObject = new Date(1, 1, 2020);
        assertFalse(dateObject.isValidDate(-5,5,-47));
    }

    @Test
    public void isValidDateTest_InvalidMonthYear() {
        Date dateObject = new Date(1, 1, 2020);
        assertFalse(dateObject.isValidDate(5,55,-47));
    }

    @Test
    public void isValidDateTest_InvalidDayMonthYear() {
        Date dateObject = new Date(1, 1, 2020);
        assertFalse(dateObject.isValidDate(887,-57,-427));
    }

    @Test
    public void isValidDateTest_LastDay30DaysMonth() {
        Date dateObject = new Date(1, 1, 2020);
        assertTrue(dateObject.isValidDate(30,9,2022));
    }

    @Test
    public void isValidDateTest_LastDay31DaysMonth() {
        Date dateObject = new Date(1, 1, 2020);
        assertTrue(dateObject.isValidDate(31,1,2022));
    }

    @Test
    public void isValidDateTest_LastDay28DaysMonth() {
        Date dateObject = new Date(1, 1, 2020);
        assertTrue(dateObject.isValidDate(28,2,2022));
    }

    @Test
    public void isValidDateTest_LastDay29DaysMonth() {
        Date dateObject = new Date(1, 1, 2020);
        assertTrue(dateObject.isValidDate(29,2,2020));
    }

    @Test
    public void isValidDateTest_ValidDate() {
        Date dateObject = new Date(1, 1, 2020);
        assertTrue(dateObject.isValidDate(19,3,2014));
    }

    /* #####################  IS LEAP YEAR TESTS  ##################### */

    @Test
    public void isLeapYearTest_LeapYear() {
        Date dateObject = new Date(1, 1, 2020);
        assertTrue(dateObject.isLeapYear(2020));
    }

    @Test
    public void isLeapYearTest_LeapYearMod400() {
        Date dateObject = new Date(1, 1, 2020);
        assertTrue(dateObject.isLeapYear(2000));
    }

    @Test
    public void isLeapYearTest_NotLeapYear() {
        Date dateObject = new Date(1, 1, 2020);
        assertFalse(dateObject.isLeapYear(2021));
    }

    @Test
    public void isLeapYearTest_NotLeapYearMod100() {
        Date dateObject = new Date(1, 1, 2020);
        assertFalse(dateObject.isLeapYear(1800));
    }

    @Test
    public void isLeapYearTest_InvalidYear() {
        Date dateObject = new Date(1, 1, 2020);
        assertFalse(dateObject.isLeapYear(-1));
    }

    /* ##################### NEXT DATE TESTS  ##################### */

    @Test
    public void nextDateTest() {
        Date date = new Date(19, 3, 2014);
        assertEquals(date.nextDate(), new Date(20, 3, 2014));
    }

    @Test
    public void nextDateTest_LastDay30DaysMonth() {
        for(int month : new int[]{4, 6, 9, 11}) {
            Date date = new Date(30, month, 2014);
            assertEquals(date.nextDate(), new Date(1, month + 1, 2014));
        }
    }

    @Test
    public void nextDateTest_LastDay31DaysMonths() {
        for(int month : new int[]{1, 3, 5, 7, 8, 10}) {
            Date date = new Date(31, month, 2001);
            assertEquals(date.nextDate(), new Date(1, month + 1, 2001));
        }
    }

    @Test
    public void nextDateTest_LastDay28DaysMonth() {
        Date date = new Date(28, 2, 2001);
        assertEquals(date.nextDate(), new Date(1, 3, 2001));
    }

    @Test
    public void nextDateTest_LastDay29DaysMonth() {
        Date date = new Date(29, 2, 2000);
        assertEquals(date.nextDate(), new Date(1, 3, 2000));
    }

    @Test
    public void nextDateTest_LastDay31DaysMonth_YearEnd() {
        Date date = new Date(31, 12, 2001);
        assertEquals(date.nextDate(), new Date(1, 1, 2002));
    }

    /* #####################  PREVIOUS DAY TESTS  ##################### */

    @Test
    public void previousDateTest() {
        Date date = new Date(19, 3, 2014);
        assertEquals(date.previousDate(), new Date(18, 3, 2014));
    }

    @Test
    public void previousDateTest_FirstDay30DaysMonth() {
        Date date = new Date(1, 6, 2001);
        assertEquals(date.previousDate(), new Date(31, 5, 2001));
    }

    @Test
    public void previousDateTest_FirstDay31DaysMonth() {
        Date date = new Date(1, 5, 2001);
        assertEquals(date.previousDate(), new Date(30, 4, 2001));
    }

    @Test
    public void previousDateTest_FirstDayYear() {
        Date date = new Date(1, 1, 2001);
        assertEquals(date.previousDate(), new Date(31, 12, 2000));
    }

    @Test
    public void previousDateTest_FirstDayMarchLeapYear() {
        Date date = new Date(1, 3, 2020);
        assertEquals(date.previousDate(), new Date(29, 2, 2020));
    }

    @Test
    public void previousDateTest_FirstDayMarchNotLeapYear() {
        Date date = new Date(1, 3, 2021);
        assertEquals(date.previousDate(), new Date(28, 2, 2021));
    }

    @Test
    void previousDateTest_firstValidDate() {
        assertThrows(IllegalArgumentException.class, () -> new Date(1, 1, 0).previousDate());
    }

    /* #####################  COMPARE TO TESTS  ##################### */

    @Test
    public void compareToTest_EqualsDate() {
        Date date = new Date(19, 3, 2014);
        assertEquals(date.compareTo(new Date(19, 3, 2014)), 0);
    }

    @Test
    public void compareToTest_YearBefore() {
        Date date = new Date(19, 3, 2013);
        assertEquals(date.compareTo(new Date(19, 3, 2014)), -1);
    }

    @Test
    public void compareToTest_YearAfter() {
        Date date = new Date(19, 3, 2015);
        assertEquals(date.compareTo(new Date(19, 3, 2014)), 1);
    }

    @Test
    public void compareToTest_MonthBefore() {
        Date date = new Date(19, 2, 2014);
        assertEquals(date.compareTo(new Date(19, 3, 2014)), -1);
    }

    @Test
    public void compareToTest_MonthAfter() {
        Date date = new Date(19, 4, 2014);
        assertEquals(date.compareTo(new Date(19, 3, 2014)), 1);
    }

    @Test
    public void compareToTest_DayBefore() {
        Date date = new Date(18, 3, 2014);
        assertEquals(date.compareTo(new Date(19, 3, 2014)), -1);
    }

    @Test
    public void compareToTest_DayAfter() {
        Date date = new Date(20, 3, 2014);
        assertEquals(date.compareTo(new Date(19, 3, 2014)), 1);
    }

    @Test
    public void compareToTest_NullDate() {
        Date date = new Date(20, 3, 2014);
        assertThrows(NullPointerException.class, () -> date.compareTo(null));
    }

    /* #####################  EQUALS TESTS  ##################### */

    @Test
    public void equalsTest_EqualDate() {
        Date date = new Date(19, 3, 2014);
        assertTrue(date.equals(new Date(19, 3, 2014)));
    }

    @Test
    public void equalsTest_SameDate() {
        Date date = new Date(19, 3, 2014);
        assertTrue(date.equals(date));
    }

    @Test
    public void equalsTest_DifferentObjectClass() {
        Date date = new Date(19, 3, 2014);
        assertFalse(date.equals(new LinkedList<Integer>()));
    }

    @Test
    public void equalsTest_NullObject() {
        Date date = new Date(19, 3, 2014);
        assertFalse(date.equals(null));
    }

    @Test
    public void equalsTest_DifferentDate() {
        Date date = new Date(19, 3, 2014);
        assertFalse(date.equals(new Date(20, 3, 2014)));
    }

}