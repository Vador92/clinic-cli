/**
 * @author Yuet
 */

import java.util.Calendar;

public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;

    public static final int minYear = 1900;
    public static final int minMonth = 1;
    public static final int maxMonth = 12;
    public static final int minDay = 1;
    public static final int maxDay = 31;

    public static final int
            JANUARY = 1,
            FEBRUARY = 2,
            MARCH = 3,
            APRIL = 4,
            MAY = 5,
            JUNE = 6,
            JULY = 7,
            AUGUST = 8,
            SEPTEMBER = 9,
            OCTOBER = 10,
            NOVEMBER = 11,
            DECEMBER = 12;

    public static final int zeroBaseShift = 1;
    public static final int addSixMonths = 6;

    public static final int
            isLeapYearFEBRUARYDay = 29,
            notLeapYearFEBRUARYDay = 28;

    //constructor to make the Date obj
    public Date(String date) {
        String[] parts = date.split("/");
        this.month = Integer.parseInt(parts[0]);;
        this.day = Integer.parseInt(parts[1]);
        this.year = Integer.parseInt(parts[2]);
    }

    public static Date getDate(String date){
        try{
            Date newDate = new Date(date);
            if (newDate.isValid()){
                return newDate;
            }
            return null;
        }
        catch(Exception e){
            return null;
        }
    }

    public boolean isValid() {

        // year validation
        if (year < minYear) {
            return false;
        }

        // month validation
        if (month < minMonth || month > maxMonth){
            return false;
        }

        // day validation
        if (day < minDay || day > maxDay){
            return false;
        }

        // maximum day values validation based on month
        if (!checkMonthDays()) {
            return false;
        }

        return true;
    }

    // checks if the inputted date is today's date
    public boolean isToday(Calendar today) {
        int todayMonth = today.get(Calendar.MONTH) + zeroBaseShift;
        int todayDay = today.get(Calendar.DAY_OF_MONTH);
        int todayYear = today.get(Calendar.YEAR);

        return this.month == todayMonth
                && this.day == todayDay
                && this.year == todayYear;

    }

    // checks if an appointment is schedules to a date before today
    public boolean isBeforeToday(Calendar today) {

        Calendar todayDate = Calendar.getInstance();
        todayDate.set(this.year, this.month - zeroBaseShift, this.day, 0, 0, 0);

        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);

        return todayDate.before(today);
    }

    // checks if the day is on a weekend
    public boolean isWeekend(Calendar date){
        date.set(year, month - zeroBaseShift, day);
        int dayOfWeek = date.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == Calendar.SATURDAY
                || dayOfWeek == Calendar.SUNDAY) {
            return true;
        }
        return false;
    }

    // checks if the date is in the future
    public boolean isInFuture(Calendar date){
        Calendar today = Calendar.getInstance();
        date.set(year, month - zeroBaseShift, day);
        return date.after(today);
    }

    // need to fix this
    public boolean isBeyondSixMonths(Calendar today) {

        // Create a copy of the 'today' Calendar object to avoid modifying the original
        Calendar sixMonthsLater = (Calendar) today.clone();

        // Add six months to the copied date
        sixMonthsLater.add(Calendar.MONTH, addSixMonths);

        // Create a Calendar instance for the current Date object
        Calendar thisDate = Calendar.getInstance();
        thisDate.set(this.year, this.month - zeroBaseShift, this.day);

        // Check if thisDate is after sixMonthsLater (i.e., more than six months in the future)
        return thisDate.after(sixMonthsLater);
    }


    // getter method for month
    public int getMonth() {
        return month;
    }

    // getter method for day
    public int getDay() {
        return day;
    }

    // getter method for year
    public int getYear() {
        return year;
    }

    // converts the full date to a string with 0s in front of the string literals if necessary
    @Override
    public String toString() {
        return String.format("%d/%d/%d", month, day, year);
    }

    // compare the month, day, and year of two Date Objects in sequences
    @Override
    public int compareTo(Date other) {
        // Compare years
        if (this.year != other.year) {
            return Integer.compare(this.year, other.year);
        }

        // Compare months
        if (this.month != other.month) {
            return Integer.compare(this.month, other.month);
        }

        // Compare days
        return Integer.compare(this.day, other.day);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        // Cast the object to a Date instance
        Date other = (Date) object;

        // Compare year, month, and day
        return this.year == other.year
                && this.month == other.month
                && this.day == other.day;
    }

    //check if year is leap year
    private boolean isLeapYear() {
        if (year % QUADRENNIAL == 0) {
            if (year % CENTENNIAL == 0) {
                if (year % QUATERCENTENNIAL == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkMonthDays(){
        //month and day range validation
        switch (month) {
            case JANUARY: case MARCH: case MAY: case JULY:
            case AUGUST: case OCTOBER: case DECEMBER:
                return day <= 31;
            case APRIL: case JUNE: case SEPTEMBER: case NOVEMBER:
                return day <= 30;
            case FEBRUARY:
                if (isLeapYear()) {
                    return day <= isLeapYearFEBRUARYDay;
                } else {
                    return day <= notLeapYearFEBRUARYDay;
                }
            default:
                return false;

        }
    }

        public static void main(String[] args) {
            testCreationObjectDateA();
            testOutputObjectDateA();
            testCreationObjectDateB();
            testOutputObjectDateB();
            testValidDates();
            testIsToday();
            testInvalidDates();
            testIsBeyondSixMonths();
            testDuplicateAppointmentDates();
        }

        static void testCreationObjectDateA() {
            Date date = new Date("10/21/2003");
            if (date.getMonth() == 10) {
                System.out.println("Passed month test for Date A.");
            } else {
                System.out.println("Failed month test for Date A.");
            }

            if (date.getDay() == 21) {
                System.out.println("Passed day test for Date A.");
            } else {
                System.out.println("Failed day test for Date A.");
            }

            if (date.getYear() == 2003) {
                System.out.println("Passed year test for Date A.");
            } else {
                System.out.println("Failed year test for Date A.");
            }
        }

        static void testOutputObjectDateA() {
            Date date = new Date("10/21/2003");
            if (date.toString().equals("10/21/2003")) {
                System.out.println("Passed final output test for Date A.");
            } else {
                System.out.println("Failed final output test for Date A.");
            }
        }

        static void testCreationObjectDateB() {
            Date date = new Date("02/02/2003");
            if (date.getMonth() == 2) {
                System.out.println("Passed month test for Date B.");
            } else {
                System.out.println("Failed month test for Date B.");
            }

            if (date.getDay() == 2) {
                System.out.println("Passed day test for Date B.");
            } else {
                System.out.println("Failed day test for Date B.");
            }

            if (date.getYear() == 2003) {
                System.out.println("Passed year test for Date B.");
            } else {
                System.out.println("Failed year test for Date B.");
            }
        }

        static void testOutputObjectDateB() {
            Date date = new Date("02/02/2003");
            if (date.toString().equals("2/2/2003")) {
                System.out.println("Passed final output test for Date B.");
            } else {
                System.out.println("Failed final output test for Date B.");
            }
        }

        static void testValidDates() {
            Date validDate = new Date("05/21/2003");
            if (validDate.isValid()) {
                System.out.println("Valid date test passed: 05/21/2003.");
            } else {
                System.out.println("Valid date test failed: 05/21/2003.");
            }

            Date leapYearDate = new Date("02/29/2000");
            if (leapYearDate.isValid()) {
                System.out.println("Leap year date test passed: 02/29/2000.");
            } else {
                System.out.println("Leap year date test failed: 02/29/2000.");
            }

            Date nonLeapYearDate = new Date("02/28/2001");
            if (nonLeapYearDate.isValid()) {
                System.out.println("Non-leap year date test passed: 02/28/2001.");
            } else {
                System.out.println("Non-leap year date test failed: 02/28/2001.");
            }
        }

        static void testIsToday() {
            Calendar calendar = Calendar.getInstance();
            Date todayDate = new Date(String.format("%d/%d/%d",
                    calendar.get(Calendar.MONTH) + 1,
                    calendar.get(Calendar.DAY_OF_MONTH),
                    calendar.get(Calendar.YEAR)));

            if (todayDate.isToday(calendar)) {
                System.out.println("Today's date test passed.");
            } else {
                System.out.println("Today's date test failed.");
            }

            Date pastDate = new Date("01/01/2020");
            if (!pastDate.isToday(calendar)) {
                System.out.println("Past date test passed.");
            } else {
                System.out.println("Past date test failed.");
            }

            Date futureDate = new Date("12/31/2099");
            if (!futureDate.isToday(calendar)) {
                System.out.println("Future date test passed.");
            } else {
                System.out.println("Future date test failed.");
            }
        }

        static void testInvalidDates() {
            Calendar calendar = Calendar.getInstance();
            Date invalidTodayDate = new Date(String.format("%d/%d/%d",
                    calendar.get(Calendar.MONTH) + 1,
                    calendar.get(Calendar.DAY_OF_MONTH),
                    calendar.get(Calendar.YEAR)));
            if (!invalidTodayDate.isValid()) {
                System.out.println("Invalid today's date test passed.");
            } else {
                System.out.println("Invalid today's date test failed.");
            }

            Date invalidFebDate = new Date("02/30/2003");
            if (!invalidFebDate.isValid()) {
                System.out.println("Invalid February date test passed.");
            } else {
                System.out.println("Invalid February date test failed.");
            }

            Date invalidLeapDate = new Date("02/29/2001");
            if (!invalidLeapDate.isValid()) {
                System.out.println("Invalid leap year date test passed.");
            } else {
                System.out.println("Invalid leap year date test failed.");
            }

            Date invalidMonthDate = new Date("13/15/2000");
            if (!invalidMonthDate.isValid()) {
                System.out.println("Invalid month test passed.");
            } else {
                System.out.println("Invalid month test failed.");
            }

            Date invalidDayDate = new Date("04/31/2000");
            if (!invalidDayDate.isValid()) {
                System.out.println("Invalid day test passed.");
            } else {
                System.out.println("Invalid day test failed.");
            }

            Date invalidYearDate = new Date("01/01/1899");
            if (!invalidYearDate.isValid()) {
                System.out.println("Invalid year test passed.");
            } else {
                System.out.println("Invalid year test failed.");
            }
        }

        static void testIsBeyondSixMonths() {
            Calendar today = Calendar.getInstance();

            Calendar sixMonthsLater = (Calendar) today.clone();
            sixMonthsLater.add(Calendar.MONTH, 6);
            Date exactlySixMonthsLater = new Date(
                    String.format("%d/%d/%d",
                            sixMonthsLater.get(Calendar.MONTH) + 1,
                            sixMonthsLater.get(Calendar.DAY_OF_MONTH),
                            sixMonthsLater.get(Calendar.YEAR))
            );
            if (!exactlySixMonthsLater.isBeyondSixMonths(today)) {
                System.out.println("Date exactly six months later test passed.");
            } else {
                System.out.println("Date exactly six months later test failed.");
            }

            Calendar moreThanSixMonthsLater = (Calendar) today.clone();
            moreThanSixMonthsLater.add(Calendar.MONTH, 7);
            Date beyondSixMonths = new Date(
                    String.format("%d/%d/%d",
                            moreThanSixMonthsLater.get(Calendar.MONTH) + 1,
                            moreThanSixMonthsLater.get(Calendar.DAY_OF_MONTH),
                            moreThanSixMonthsLater.get(Calendar.YEAR))
            );
            if (beyondSixMonths.isBeyondSixMonths(today)) {
                System.out.println("Date more than six months later test passed.");
            } else {
                System.out.println("Date more than six months later test failed.");
            }

            Calendar lessThanSixMonthsLater = (Calendar) today.clone();
            lessThanSixMonthsLater.add(Calendar.MONTH, 5);
            Date withinSixMonths = new Date(
                    String.format("%d/%d/%d",
                            lessThanSixMonthsLater.get(Calendar.MONTH) + 1,
                            lessThanSixMonthsLater.get(Calendar.DAY_OF_MONTH),
                            lessThanSixMonthsLater.get(Calendar.YEAR))
            );
            if (!withinSixMonths.isBeyondSixMonths(today)) {
                System.out.println("Date within six months test passed.");
            } else {
                System.out.println("Date within six months test failed.");
            }
        }

        static void testDuplicateAppointmentDates() {
            Date appointmentDate1 = new Date("09/25/2024");
            Date appointmentDate2 = new Date("09/25/2024");
            if (appointmentDate1.equals(appointmentDate2)) {
                System.out.println("Duplicate appointment date test passed.");
            } else {
                System.out.println("Duplicate appointment date test failed.");
            }

            Date appointmentDate3 = new Date("09/25/2024");
            Date appointmentDate4 = new Date("10/01/2024");
            if (!appointmentDate3.equals(appointmentDate4)) {
                System.out.println("Different appointment date test passed.");
            } else {
                System.out.println("Different appointment date test failed.");
            }

            if (!appointmentDate1.equals(null)) {
                System.out.println("Null comparison test passed.");
            } else {
                System.out.println("Null comparison test failed.");
            }

            if (!appointmentDate1.equals("someString")) {
                System.out.println("Comparison with another class test passed.");
            } else {
                System.out.println("Comparison with another class test failed.");
            }
        }
}