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
            isLeapYearFEBRUARYDay = 28,
            notLeapYearFEBRUARYDay = 29;


    //constructor to make the Date obj
    public Date(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
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
        if (year < minYear) {
            return false;
        }

        if (month < minMonth || month > maxMonth) {
            return false;
        }

        if (day < minDay || day > maxDay) {
            return false;
        }

        if (isLeapYear()) {
            if (day == notLeapYearFEBRUARYDay)
                return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return month + "/" + day + "/" + year;
    }

    @Override
    public int compareTo(Date o) {
        return 0;
    }
}