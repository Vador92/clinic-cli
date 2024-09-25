import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Calendar;

class DateTest {

    @Test
    void testCreationObjectDateA() {
        Date date = new Date("10/21/2003");

        assertEquals(10, date.getMonth(), "passed month tests");
        assertEquals(21, date.getDay(), "passed day tests");
        assertEquals(2003, date.getYear(), "passed year tests");

    }

    @Test
    void testOutputObjectDateA() {

        Date date = new Date("10/21/2003");

        assertEquals("10/21/2003", date.toString(), "passed final output");
    }

    @Test
    void testCreationObjectDateB() {
        Date date = new Date("02/02/2003");

        assertEquals(2, date.getMonth(), "passed month tests");
        assertEquals(2, date.getDay(), "passed day tests");
        assertEquals(2003, date.getYear(), "passed year tests");

    }

    @Test
    void testOutputObjectDateB() {

        Date date = new Date("02/02/2003");

        assertEquals("2/2/2003", date.toString(), "passed final output");
    }

    @Test
    void testValidDates() {

        Date validDate = new Date("05/21/2003");
        assertTrue(validDate.isValid(), "Date should be valid: 05/21/2003");

        Date leapYearDate = new Date("02/29/2000");
        assertTrue(leapYearDate.isValid(), "Date should be valid: 02/29/2000 (leap year)");

        Date nonLeapYearDate = new Date("02/28/2001");
        assertTrue(nonLeapYearDate.isValid(), "Date should be valid: 02/28/2001 (non-leap year)");
    }

    @Test
    void testIsToday() {
        // Testing today
        Calendar calendar = Calendar.getInstance();
        Date todayDate = new Date(
                String.format("%d/%d/%d",
                        calendar.get(Calendar.MONTH) + 1,
                        calendar.get(Calendar.DAY_OF_MONTH),
                        calendar.get(Calendar.YEAR)));
        assertTrue(todayDate.isToday(calendar), "Date should match today's date");

        // Testing a past date
        Date pastDate = new Date("01/01/2020");
        assertFalse(pastDate.isToday(calendar), "Date should not match today's date");

        // Testing a future date
        Date futureDate = new Date("12/31/2099");
        assertFalse(futureDate.isToday(calendar), "Date should not match today's date");
    }

    @Test
    void testInvalidDates() {

        // Testing invalid date (the appt date or dob is set to today's date)
        Calendar calendar = Calendar.getInstance();
        Date invalidTodayDate = new Date(
                String.format("%d/%d/%d",
                        calendar.get(Calendar.MONTH) + 1,
                        calendar.get(Calendar.DAY_OF_MONTH),
                        calendar.get(Calendar.YEAR)));
        assertFalse(invalidTodayDate.isValid(), "Date should be invalid as the date is set to today's date");

        // Testing invalid date (there is no 30th day in feb)
        Date invalidFebDate = new Date("02/30/2003");
        assertFalse(invalidFebDate.isValid(), "Date should be invalid: 02/30/2003");

        // Testing leap year with invalid date (there is no 29th day in a non-leap year in feb)
        Date invalidLeapDate = new Date("02/29/2001");
        assertFalse(invalidLeapDate.isValid(), "Date should be invalid: 02/29/2001 (not a leap year)");

        // Testing invalid month (there is no 13th month)
        Date invalidMonthDate = new Date("13/15/2000");
        assertFalse(invalidMonthDate.isValid(), "Date should be invalid: 13/15/2000");

        // Testing invalid day (there is no 31st day in april)
        Date invalidDayDate = new Date("04/31/2000");
        assertFalse(invalidDayDate.isValid(), "Date should be invalid: 04/31/2000");

        // Testing year before 1900 (self-explanatory)
        Date invalidYearDate = new Date("01/01/1899");
        assertFalse(invalidYearDate.isValid(), "Date should be invalid: 01/01/1899 (year < 1900)");
    }

    @Test
    void testIsInSixMonths() {
        Calendar calendar = Calendar.getInstance();

        // Testing date exactly six months from today
        calendar.add(Calendar.MONTH, 6);
        Date sixMonthsDate = new Date(
                String.format("%d/%d/%d",
                        calendar.get(Calendar.MONTH) + 1,
                        calendar.get(Calendar.DAY_OF_MONTH),
                        calendar.get(Calendar.YEAR)));
        calendar.add(Calendar.MONTH, -6); // Reset calendar
        assertTrue(sixMonthsDate.isBeyondSixMonths(calendar), "Date should be exactly six months from today");

        // Testing a date not exactly six months from today
        Date notSixMonthsDate = new Date("01/01/2020");
        assertFalse(notSixMonthsDate.isBeyondSixMonths(calendar), "Date should not be six months from today");
    }
}





