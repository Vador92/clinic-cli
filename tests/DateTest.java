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
    void testIsBeyondSixMonths() {
        Calendar today = Calendar.getInstance();

        // Test Case 1: Date exactly 6 months from today (should return false)
        Calendar sixMonthsLater = (Calendar) today.clone();
        sixMonthsLater.add(Calendar.MONTH, 6);
        Date exactlySixMonthsLater = new Date(
                String.format("%d/%d/%d",
                        sixMonthsLater.get(Calendar.MONTH),
                        sixMonthsLater.get(Calendar.DAY_OF_MONTH),
                        sixMonthsLater.get(Calendar.YEAR))
        );
        assertFalse(exactlySixMonthsLater.isBeyondSixMonths(today), "Date exactly six months later should not be beyond six months");

        // Test Case 2: Date more than 6 months from today (should return true)
        Calendar moreThanSixMonthsLater = (Calendar) today.clone();
        moreThanSixMonthsLater.add(Calendar.MONTH, 7);  // Add 7 months to ensure it is beyond six months
        Date beyondSixMonths = new Date(
                String.format("%d/%d/%d",
                        moreThanSixMonthsLater.get(Calendar.MONTH) + 1,
                        moreThanSixMonthsLater.get(Calendar.DAY_OF_MONTH),
                        moreThanSixMonthsLater.get(Calendar.YEAR))
        );
        assertTrue(beyondSixMonths.isBeyondSixMonths(today), "Date more than six months later should be beyond six months");

        // Test Case 3: Date less than 6 months from today (should return false)
        Calendar lessThanSixMonthsLater = (Calendar) today.clone();
        lessThanSixMonthsLater.add(Calendar.MONTH, 5);  // Add 5 months to ensure it's less than six months
        Date withinSixMonths = new Date(
                String.format("%d/%d/%d",
                        lessThanSixMonthsLater.get(Calendar.MONTH) + 1,
                        lessThanSixMonthsLater.get(Calendar.DAY_OF_MONTH),
                        lessThanSixMonthsLater.get(Calendar.YEAR))
        );
        assertFalse(withinSixMonths.isBeyondSixMonths(today), "Date within six months should not be beyond six months");
    }

    @Test
    public void testDuplicateAppointmentDates() {
        // Create two Date objects with the same date
        Date appointmentDate1 = new Date("09/25/2024");
        Date appointmentDate2 = new Date("09/25/2024");

        // They should be equal, meaning they represent the same appointment date
        assertTrue(appointmentDate1.equals(appointmentDate2));

        // Create two Date objects with different dates
        Date appointmentDate3 = new Date("09/25/2024");
        Date appointmentDate4 = new Date("10/01/2024");

        // These should not be equal, meaning they represent different appointment dates
        assertFalse(appointmentDate3.equals(appointmentDate4));

        // Check null comparison
        assertFalse(appointmentDate1.equals(null));

        // Check comparison with an object of another class
        assertFalse(appointmentDate1.equals("someString"));
    }

}





