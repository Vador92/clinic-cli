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

    //check if the date is a valid calendar date
    // cannot change the signature of this method
    // need to fix this
    // just need to check if this is a valid calendar day
    // need to fix this, see block comment below method
    public boolean isValid() {
        Calendar calendar = Calendar.getInstance();
        // checks if appt date or dob is set to today
        // this stuff is not needed
//        if (isToday(calendar)) {
//            return false;
//        }
//        if (isWeekend(calendar)) {
//            return false;
//        }
//        if (isBeyondSixMonths(calendar)) {
//            return false;
//        }
        // below stuff is fine
        // year validation
        if (year < minYear) {
            return false;
        }
        // moth validation
        if (month < minMonth || month > maxMonth){
            return false;
        }
        // day validation
        if (day < minDay || day > maxDay){
            return false;
        }
        // this is fine
        if(!checkDayRange()){
            return false;
        }
        return true;
    }

    /*
    For the months of January, March, May, July, August, October, and December, each has 31 days; April,
    June, September, and November each has 30 days; February has 28 days in a non-leap year, and 29
    days in a leap year. DO NOT use magic numbers for the months, days, and years. You can use the
    constants defined in the Calendar class or define your own constant names. Below are examples
    for defining the constant names.
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;
    o To determine whether a year is a leap year, follow these steps:
    Step 1. If the year is evenly divisible by 4, go to step 2. Otherwise, go to step 5.
    Step 2. If the year is evenly divisible by 100, go to step 3. Otherwise, go to step 4.
    Step 3. If the year is evenly divisible by 400, go to step 4. Otherwise, go to step 5.
    Step 4. The year is a leap year.
    Step 5. The year is not a leap year.
     */

    // other checks for appointment and dob
    // checks if the inputted date is today's date
    public boolean isToday(Calendar today) {
        int todayMonth = today.get(Calendar.MONTH) + zeroBaseShift;
        int todayDay = today.get(Calendar.DAY_OF_MONTH);
        int todayYear = today.get(Calendar.YEAR);

        return this.month == todayMonth
                && this.day == todayDay
                && this.year == todayYear;

    }

    // public boolean isBeforeToday (need to make this, check if appt is before today)

    public boolean isWeekend(Calendar date){
        date.set(year, month - zeroBaseShift, day);
        int dayOfWeek = date.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == Calendar.SATURDAY
                || dayOfWeek == Calendar.SUNDAY) {
            return true;
        }
        return false;
    }

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

    private boolean checkDayRange(){
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





}