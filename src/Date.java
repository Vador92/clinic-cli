import java.util.Calendar;

/**
 * This is the Date class which manages all the generations of dates
 * This checks for all the edge cases provided by the project description:
 * 1. February day range difference during a leap year
 * 2. Each month's maximum day value (different for different months)
 * 3. Checks if a date is in the past, present, or future
 * 4. Checks if a date is more than six months in the future
 * 5. Checks if a date has a valid month, day, and year
 * @author Yuet Yue, Varun Doreswamy
 */
public class Date implements Comparable<Date> {

    // constants to represent the start of each time metric
    private static final int START_OF_DAY_HOUR = 0;
    private static final int START_OF_DAY_MINUTE = 0;
    private static final int START_OF_DAY_SECOND = 0;
    private static final int START_OF_DAY_MILLISECOND = 0;

    // constants for leap year calculations
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;

    // constants for month, day, and year edge case checking
    public static final int MINYEAR = 1900;
    public static final int MINMONTH = 1;
    public static final int MAXMONTH = 12;
    public static final int MINDAY = 1;
    public static final int MAXDAY = 31;

    // month constants for representing months in ints
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

    // constants for month operations and eliminates zero base month values
    public static final int ZEROBASESHIFT = 1;
    public static final int ADDSIXMONTHS = 6;

    // day constants to represent the max day in February (leap and non-leap)
    public static final int
            isLeapYearFEBRUARYDay = 29,
            notLeapYearFEBRUARYDay = 28;

    // instance variables
    private int year;
    private int month;
    private int day;


    /**
     * This is the constructor method for creating dates
     * The date object contains month, day, and year as separate ints
     * @param date gets read and split into separate ints for manipulation
     */
    public Date(String date) {
        String[] parts = date.split("/");
        this.month = Integer.parseInt(parts[0]);
        this.day = Integer.parseInt(parts[1]);
        this.year = Integer.parseInt(parts[2]);
    }

    /**
     * This method checks if a year is a leap year
     * @return TRUE if it is a leap year, FALSE if it is a non-leap year
     */
    private boolean isLeapYear() {
        if (year % QUADRENNIAL == 0) {
            if (year % CENTENNIAL == 0) {
                return year % QUATERCENTENNIAL == 0;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * This method validates the maximum day value for a specified month
     * @return TRUE if the max day is valid to a proper month, FALSE if not
     */
    private boolean checkMonthDays() {
        //month and day range validation
        switch (month) {
            case JANUARY:
            case MARCH:
            case MAY:
            case JULY:
            case AUGUST:
            case OCTOBER:
            case DECEMBER:
                return day <= 31;
            case APRIL:
            case JUNE:
            case SEPTEMBER:
            case NOVEMBER:
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

    /**
     * This method acts as a getter method for the date
     * @param date gets read and added into a new object
     * @return the new date object in mm/dd/yyyy format
     */
    public static Date getDate(String date) {
        try {
            Date newDate = new Date(date);
            if (newDate.isValid()) {
                return newDate;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * This method validates the inputted dates based on specified edge cases
     * This validates month, day, and year values
     * This also validates the day range for specific months
     * @return TRUE if the date is valid, FALSE if the date is invalid
     */
    public boolean isValid() {
        if (!checkMonthDays()) {
            return false;
        }
        if (year < MINYEAR) {
            return false;
        }
        if (month < MINMONTH || month > MAXMONTH) {
            return false;
        }
        if (day < MINDAY || day > MAXDAY) {
            return false;
        }
        return true;
    }

    /**
     * This method checks if the selected calendar date is today
     * @param today used as a point of comparison to today's calendar date
     * @return TRUE if the inputted date is today, FALSE if it's not today
     */
    public boolean isToday(Calendar today) {
        Calendar todayClone = (Calendar) today.clone();
        int todayMonth = todayClone.get(Calendar.MONTH) + ZEROBASESHIFT;
        int todayDay = todayClone.get(Calendar.DAY_OF_MONTH);
        int todayYear = todayClone.get(Calendar.YEAR);

        return this.month == todayMonth
                && this.day == todayDay
                && this.year == todayYear;

    }

    /**
     * This method checks if the selected calendar date is in the past
     * @param today used as a point of comparison to a date before today
     * @return True if the date is in the past, FALSE if it's not in the past
     */
    public boolean isBeforeToday(Calendar today) {
        Calendar todayClone = (Calendar) today.clone();
        Calendar todayDate = Calendar.getInstance();
        todayDate.set(this.year,
                this.month - ZEROBASESHIFT,
                this.day,
                START_OF_DAY_HOUR,
                START_OF_DAY_MINUTE,
                START_OF_DAY_SECOND);

        todayClone.set(Calendar.HOUR_OF_DAY, START_OF_DAY_HOUR);
        todayClone.set(Calendar.MINUTE, START_OF_DAY_MINUTE);
        todayClone.set(Calendar.SECOND, START_OF_DAY_SECOND);
        todayClone.set(Calendar.MILLISECOND, START_OF_DAY_MILLISECOND);

        return todayDate.before(todayClone);
    }

    /**
     * This method checks if the selected calendar date is on a weekend
     * @param date used to check if the selected date is on Sat or Sun
     * @return TRUE if date is on the weekend, FALSE if it's not
     */
    public boolean isWeekend(Calendar date) {
        Calendar dateClone = (Calendar) date.clone();
        dateClone.set(year, month - ZEROBASESHIFT, day);
        int dayOfWeek = dateClone.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == Calendar.SATURDAY
                || dayOfWeek == Calendar.SUNDAY) {
            return true;
        }
        return false;
    }

    /**
     * This method checks if the selected calendar date is in the future
     * @param date used to check if the selected date is after today's date
     * @return TRUE if the date is in the future, FALSE if it's not
     */
    public boolean isInFuture(Calendar date) {
        Calendar dateClone = (Calendar) date.clone();
        Calendar today = Calendar.getInstance();
        dateClone.set(year, month - ZEROBASESHIFT, day);
        return dateClone.after(today);
    }

    /**
     * This method checks if the date is beyond six months into the future
     * @param today used to check if the date is beyond six months
     * @return TRUE if beyond six months into the future, FALSE if it's not
     */
    public boolean isBeyondSixMonths(Calendar today) {

        Calendar sixMonthsLater = (Calendar) today.clone();
        sixMonthsLater.add(sixMonthsLater.MONTH, ADDSIXMONTHS);

        Calendar thisDate = Calendar.getInstance();
        thisDate.set(this.year, this.month - ZEROBASESHIFT, this.day);

        return thisDate.after(sixMonthsLater);
    }

    /**
     * This method acts as a getter method for the month of the date
     * @return the month of the date object
     */
    public int getMonth() {
        return month;
    }

    /**
     * This method acts as a getter method for the day of the date
     * @return the day of the date object
     */
    public int getDay() {
        return day;
    }

    /**
     * This method acts as a getter method for the year of the date
     * @return the year of the date object
     */
    public int getYear() {
        return year;
    }

    /**
     * This method returns the date in the proper String format
     * @return String of the month, day, and year of the date object
     */
    @Override
    public String toString() {
        return String.format("%d/%d/%d", month, day, year);
    }

    /**
     * This method compares the values within date for sorting purposes
     * @param other the object to be compared with the month, day, and year
     * @return -1 if date is earlier, 1 if date is later, 0 if date is equal
     */
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

    /**
     * This method checks for duplicate date objects
     * @param object to compare another date object for duplicate checking
     * @return TRUE if the date is a duplicate, FALSE if it is different
     */
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


    /**
     * This main method acts as the testbed main() for this class
     * Test Edge Cases:
     * 1. Year value below 1900
     * 2. Max day value that does not exist to a corresponding month
     * 3. Invalid month value
     * 4. Invalid day value
     * 5. February leap-year constrictions
     * @param args as the input of dates used for testing edge cases
     */
    public static void main(String[] args) {
        // Test case 1: Invalid date - Year is below 1900
        // (years before 1900 are not valid years)
        Date date1 = new Date("02/30/1800");
        System.out.println("Test Case 1 - Expected: false, Actual: "
                + date1.isValid());

        // Test case 2: Invalid date - April 31 (April only has 30 days)
        Date date2 = new Date("04/31/2023");
        System.out.println("Test Case 2 - Expected: false, Actual: "
                + date2.isValid());

        // Test case 3: Invalid date - Month is 13 (no month beyond 12)
        Date date3 = new Date("13/15/2023");
        System.out.println("Test Case 3 - Expected: false, Actual: "
                + date3.isValid());

        // Test case 4: Invalid date - Day is 0 (no month has a day 0)
        Date date4 = new Date("12/00/2023");
        System.out.println("Test Case 4 - Expected: false, Actual: "
                + date4.isValid());

        // Test case 5: Invalid date - February 29 on a non-leap year
        Date date8 = new Date("02/29/2023"); // 2023 is not a leap year
        System.out.println("Test Case 5 - Expected: false, Actual: "
                + date8.isValid());

        // Test case 6: Valid date - February 29 on a leap year
        Date date5 = new Date("02/29/2024"); // Leap year
        System.out.println("Test Case 6 - Expected: true, Actual: "
                + date5.isValid());

        // Test case 7: Valid date - December 31 (last day of the year)
        Date date6 = new Date("12/31/2023");
        System.out.println("Test Case 7 - Expected: true, Actual: "
                + date6.isValid());
    }
}