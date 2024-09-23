import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    @Test
    void testCreationObjectDateA() {
        Date date = new Date(10,21,2003);

        assertEquals(10, date.getMonth(), "passed month tests");
        assertEquals(21, date.getDay(), "passed day tests");
        assertEquals(2003, date.getYear(), "passed year tests");

    }

    @Test
    void testOutputObjectDateA() {

        Date date = new Date(10, 21, 2003);

        assertEquals("10/21/2003", date.toString(), "passed final output");
    }

    @Test
    void testCreationObjectDateB() {
        Date date = new Date(2,2,2003);

        assertEquals(2, date.getMonth(), "passed month tests");
        assertEquals(2, date.getDay(), "passed day tests");
        assertEquals(2003, date.getYear(), "passed year tests");

    }

    @Test
    void testOutputObjectDateB() {

        Date date = new Date(2, 2, 2003);

        assertEquals("02/02/2003", date.toString(), "passed final output");
    }

    @Test
    void testValidDates() {

        Date validDate = new Date(5, 21, 2003);
        assertTrue(validDate.isValid(), "Date should be valid: 05/21/2003");

        Date leapYearDate = new Date(2, 29, 2000);
        assertTrue(leapYearDate.isValid(), "Date should be valid: 02/29/2000 (leap year)");

        Date nonLeapYearDate = new Date(2, 28, 2001);
        assertTrue(nonLeapYearDate.isValid(), "Date should be valid: 02/28/2001 (non-leap year)");
    }

    @Test
    void testInvalidDates() {

        // Testing invalid date (there is no 30th day in feb)
        Date invalidFebDate = new Date(2, 30, 2003);
        assertFalse(invalidFebDate.isValid(), "Date should be invalid: 02/30/2003");

        // Testing leap year with invalid date (there is no 29th day in a non-leap year in feb)
        Date invalidLeapDate = new Date(2, 29, 2001);
        assertFalse(invalidLeapDate.isValid(), "Date should be invalid: 02/29/2001 (not a leap year)");

        // Testing invalid month (there is no 13th month)
        Date invalidMonthDate = new Date(13, 15, 2000);
        assertFalse(invalidMonthDate.isValid(), "Date should be invalid: 13/15/2000");

        // Testing invalid day (there is no 31st day in april)
        Date invalidDayDate = new Date(4, 31, 2000);
        assertFalse(invalidDayDate.isValid(), "Date should be invalid: 04/31/2000");

        // Testing year before 1900 (self-explanatory)
        Date invalidYearDate = new Date(1, 1, 1899);
        assertFalse(invalidYearDate.isValid(), "Date should be invalid: 01/01/1899 (year < 1900)");
    }
}



