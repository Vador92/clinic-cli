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

    private static final int START_OF_DAY_HOUR = 0;
    private static final int START_OF_DAY_MINUTE = 0;
    private static final int START_OF_DAY_SECOND = 0;
    private static final int START_OF_DAY_MILLISECOND = 0;

    public static final int
            isLeapYearFEBRUARYDay = 29,
            notLeapYearFEBRUARYDay = 28;

    //constructor to make the Date obj
    public Date(String date) {
        String[] parts = date.split("/");
        this.month = Integer.parseInt(parts[0]);
        ;
        this.day = Integer.parseInt(parts[1]);
        this.year = Integer.parseInt(parts[2]);
    }

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

    public boolean isValid() {

        // maximum day values validation based on month
        if (!checkMonthDays()) {
            return false;
        }

        // year validation
        if (year < minYear) {
            return false;
        }

        // month validation
        if (month < minMonth || month > maxMonth) {
            return false;
        }

        // day validation
        if (day < minDay || day > maxDay) {
            return false;
        }

        return true;
    }

    // checks if the inputted date is today's date
    public boolean isToday(Calendar today) {
        Calendar todayClone = (Calendar) today.clone();
        int todayMonth = todayClone.get(Calendar.MONTH) + zeroBaseShift;
        int todayDay = todayClone.get(Calendar.DAY_OF_MONTH);
        int todayYear = todayClone.get(Calendar.YEAR);

        return this.month == todayMonth
                && this.day == todayDay
                && this.year == todayYear;

    }

    // checks if an appointment is schedules to a date before today
    public boolean isBeforeToday(Calendar today) {
        Calendar todayClone = (Calendar) today.clone();
        Calendar todayDate = Calendar.getInstance();
        todayDate.set(this.year,
                this.month - zeroBaseShift,
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

    // checks if the day is on a weekend
    public boolean isWeekend(Calendar date) {
        Calendar dateClone = (Calendar) date.clone();
        dateClone.set(year, month - zeroBaseShift, day);
        int dayOfWeek = dateClone.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == Calendar.SATURDAY
                || dayOfWeek == Calendar.SUNDAY) {
            return true;
        }
        return false;
    }

    // checks if the date is in the future
    public boolean isInFuture(Calendar date) {
        Calendar dateClone = (Calendar) date.clone();
        Calendar today = Calendar.getInstance();
        dateClone.set(year, month - zeroBaseShift, day);
        return dateClone.after(today);
    }

    public boolean isBeyondSixMonths(Calendar today) {

        Calendar sixMonthsLater = (Calendar) today.clone();
        // Add six months to the copied date
        sixMonthsLater.add(sixMonthsLater.MONTH, 6);  // Adding exactly 6 months

        Calendar thisDate = Calendar.getInstance();
        thisDate.set(this.year, this.month - zeroBaseShift, this.day);  // months are zero-based, so subtract 1 from this.month

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
                return year % QUATERCENTENNIAL == 0;
            } else {
                return true;
            }
        }
        return false;
    }

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

    public static void main(String[] args) {
        // Test case 1: Invalid date - Year is below 1900 (years before 1900 are not valid years)
        Date date1 = new Date("02/30/1800");
        System.out.println("Test Case 1 - Expected: false, Actual: " + date1.isValid());

        // Test case 2: Invalid date - April 31 (April only has 30 days)
        Date date2 = new Date("04/31/2023");
        System.out.println("Test Case 2 - Expected: false, Actual: " + date2.isValid());

        // Test case 3: Invalid date - Month is 13 (no month beyond 12)
        Date date3 = new Date("13/15/2023");
        System.out.println("Test Case 3 - Expected: false, Actual: " + date3.isValid());

        // Test case 4: Invalid date - Day is 0 (no month has a day 0)
        Date date4 = new Date("12/00/2023");
        System.out.println("Test Case 4 - Expected: false, Actual: " + date4.isValid());

        // Test case 5: Invalid date - February 29 on a non-leap year
        Date date8 = new Date("02/29/2023"); // 2023 is not a leap year
        System.out.println("Test Case 5 - Expected: false, Actual: " + date8.isValid());

        // Test case 6: Valid date - February 29 on a leap year
        Date date5 = new Date("02/29/2024"); // Leap year
        System.out.println("Test Case 6 - Expected: true, Actual: " + date5.isValid());

        // Test case 7: Valid date - December 31 (last day of the year)
        Date date6 = new Date("12/31/2023");
        System.out.println("Test Case 7 - Expected: true, Actual: " + date6.isValid());
    }
}