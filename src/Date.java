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

    //check if year is leap year
    private boolean isLeapYear() {
        if (year % QUADRENNIAL == 0) {
            if (year % CENTENNIAL == 0) {
                if (year % QUATERCENTENNIAL == 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    //check if the date is a valid calendar date
    public boolean isValid() {

        // year validation
        if (year < minYear) return false;

        // moth validation
        if (month < minMonth || month > maxMonth) return false;

        // day validation
        if (day < minDay || day > maxDay) return false;

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

    // compare the month, day, and year of two Date Objects in sequence
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


}